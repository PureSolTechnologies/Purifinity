package com.puresoltechnologies.purifinity.server.passwordstore.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordEncrypter;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.db.PasswordStoreKeyspace;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordEncryptionException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordStrengthCalculator;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;
import com.puresoltechnologies.purifinity.server.wildfly.utils.EmailAddressValidator;

/**
 * This is the central implementation of the {@link PasswordStore}.
 * 
 * @author "Rick-Rainer Ludwig"
 * 
 */
@Stateless
public class PasswordStoreBean implements PasswordStore {

    public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
    public static final String CASSANDRA_HOST = "localhost";
    public static final int CASSANDRA_CQL_PORT = 9042;
    public static final String PASSWORD_TABLE_NAME = "passwords";

    public static final String CREATE_ACCOUNT_STATEMENT = "INSERT INTO "
	    + PASSWORD_TABLE_NAME
	    + " (created, last_modified, email, password, state, activation_key)"
	    + " VALUES (?, ?, ?, ?, '" + AccountState.CREATED.name() + "', ?)";
    public static final String ACTIVATE_ACCOUNT_STATEMENT = "UPDATE "
	    + PASSWORD_TABLE_NAME + " SET last_modified = ?, state = '"
	    + AccountState.ACTIVE.name() + "'" + " WHERE email = ?";
    public static final String CHANGE_PASSWORD_STATEMENT = "UPDATE "
	    + PASSWORD_TABLE_NAME + " SET password = ? WHERE email = ?";
    public static final String RETRIEVE_ACCOUNT_STATEMENT = "SELECT * FROM "
	    + PASSWORD_TABLE_NAME + " WHERE email = ?";

    private static final List<Character> validCharacters = new ArrayList<>();
    static {
	for (char c = '0'; c <= '9'; c++) {
	    validCharacters.add(c);
	}
	for (char c = 'a'; c <= 'z'; c++) {
	    validCharacters.add(c);
	}
	for (char c = 'A'; c <= 'Z'; c++) {
	    validCharacters.add(c);
	}
	validCharacters.add('!');
	validCharacters.add('#');
	validCharacters.add('$');
	validCharacters.add('%');
	validCharacters.add('&');
	validCharacters.add('*');
	validCharacters.add('+');
	validCharacters.add(',');
	validCharacters.add('-');
	validCharacters.add('.');
	validCharacters.add('/');
	validCharacters.add(':');
	validCharacters.add(';');
	validCharacters.add('<');
	validCharacters.add('=');
	validCharacters.add('>');
	validCharacters.add('?');
	validCharacters.add('@');
	validCharacters.add('^');
	validCharacters.add('|');
	validCharacters.add('~');
    }

    @Inject
    private SecurityKeyGenerator securityKeyGenerator;

    @Inject
    private PasswordEncrypter passwordEncrypter;

    @Inject
    private Logger logger;

    @Inject
    private EventLogger eventLogger;

    @Inject
    @PasswordStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    @Override
    public String createAccount(String email, String password)
	    throws AccountCreationException {
	logger.info("An account for '" + email + "' is going to be created...");
	if (!EmailAddressValidator.validate(email)) {
	    Event event = PasswordStoreEvents
		    .createInvalidEmailAddressErrorEvent(email);
	    eventLogger.logEvent(event);
	    throw new AccountCreationException(event.getEventId(),
		    event.getMessage());
	}
	if (!PasswordStrengthCalculator.validate(password)) {
	    Event event = PasswordStoreEvents
		    .createPasswordTooWeakErrorEvent(email);
	    eventLogger.logEvent(event);
	    throw new AccountCreationException(event.getEventId(),
		    event.getMessage());
	}
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, RETRIEVE_ACCOUNT_STATEMENT);
	BoundStatement boundStatement = preparedStatement.bind(email);
	ResultSet result = session.execute(boundStatement);
	if (result.one() != null) {
	    Event event = PasswordStoreEvents
		    .createAccountAlreadyExistsErrorEvent(email);
	    eventLogger.logEvent(event);
	    throw new AccountCreationException(event.getEventId(),
		    event.getMessage());
	}

	String passwordHash;
	try {
	    passwordHash = passwordEncrypter.encryptPassword(password)
		    .toString();
	} catch (PasswordEncryptionException e) {
	    Event event = PasswordStoreEvents
		    .createPasswordEncryptionErrorEvent(email, e);
	    eventLogger.logEvent(event);
	    throw new RuntimeException(event.getMessage(), e);
	}
	String activationKey = securityKeyGenerator.generate();
	Date created = new Date();

	preparedStatement = preparedStatements.getPreparedStatement(session,
		CREATE_ACCOUNT_STATEMENT);
	boundStatement = preparedStatement.bind(created, created, email,
		passwordHash, activationKey);
	session.execute(boundStatement);

	eventLogger.logEvent(PasswordStoreEvents.createAccountCreationEvent(
		email, activationKey));
	return activationKey;
    }

    @Override
    public String activateAccount(String email, String activationKey)
	    throws AccountActivationException {
	logger.info("Account for user '" + email + "' is to be activated...");

	Row account = getUserByEmail(email);
	String stateString = account.getString("state");
	if (!AccountState.CREATED.name().equals(stateString)) {
	    Event event = PasswordStoreEvents
		    .createAccountAlreadyActivatedEvent(email);
	    eventLogger.logEvent(event);
	    throw new AccountActivationException(event.getEventId(),
		    event.getMessage());
	}

	String definedActivationKey = account.getString("activation_key");
	if (!definedActivationKey.equals(activationKey)) {
	    Event event = PasswordStoreEvents
		    .createInvalidActivationKeyErrorEvent(email);
	    eventLogger.logEvent(event);
	    throw new AccountActivationException(event.getEventId(),
		    event.getMessage());
	}

	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, ACTIVATE_ACCOUNT_STATEMENT);
	BoundStatement boundStatement = preparedStatement.bind(new Date(),
		email);
	session.execute(boundStatement);

	eventLogger.logEvent(PasswordStoreEvents.createAccountActivatedEvent(
		email, activationKey));
	return email;
    }

    private Row getUserByEmail(String email) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, RETRIEVE_ACCOUNT_STATEMENT);
	BoundStatement boundStatement = preparedStatement.bind(email);
	ResultSet result = session.execute(boundStatement);
	Row account = result.one();
	return account;
    }

    @Override
    public boolean authenticate(String email, String password) {
	try {
	    logger.info(email, "Authenticating user '" + email + "'...");
	    Row account = getUserByEmail(email);
	    if (account == null) {
		eventLogger
			.logEvent(PasswordStoreEvents
				.createUserAuthenticationFailedAccountNotExistsEvent(email));
		return false;
	    }
	    String stateString = account.getString("state");
	    if (!AccountState.ACTIVE.name().equals(stateString)) {
		eventLogger
			.logEvent(PasswordStoreEvents
				.createUserAuthenticationFailedAccountNotActiveEvent(email));
		return false;
	    }
	    boolean authenticated = passwordEncrypter.checkPassword(password,
		    PasswordData.fromString(account.getString("password")));
	    if (authenticated) {
		eventLogger.logEvent(PasswordStoreEvents
			.createUserAuthenticatedEvent(email));
	    } else {
		eventLogger.logEvent(PasswordStoreEvents
			.createUserAuthenticationFailedEvent(email));
	    }
	    return authenticated;
	} catch (PasswordEncryptionException e) {
	    Event event = PasswordStoreEvents
		    .createPasswordEncryptionErrorEvent(email, e);
	    eventLogger.logEvent(event);
	    throw new RuntimeException(event.getMessage(), e);
	}
    }

    @Override
    public boolean changePassword(String email, String oldPassword,
	    String newPassword) throws PasswordChangeException {
	logger.info("Password for user '" + email
		+ "' is going to be changed...");
	if (!authenticate(email, oldPassword)) {
	    eventLogger.logEvent(PasswordStoreEvents
		    .createPasswordChangeFailedNotAuthenticatedEvent(email));
	    return false;
	}
	if (!PasswordStrengthCalculator.validate(newPassword)) {
	    Event event = PasswordStoreEvents
		    .createPasswordChangeFailedPasswordTooWeakEvent(email);
	    eventLogger.logEvent(event);
	    throw new PasswordChangeException(event.getEventId(),
		    event.getMessage());
	}
	String passwordHash;
	try {
	    passwordHash = passwordEncrypter.encryptPassword(newPassword)
		    .toString();
	} catch (PasswordEncryptionException e) {
	    Event event = PasswordStoreEvents
		    .createPasswordEncryptionErrorEvent(email, e);
	    eventLogger.logEvent(event);
	    throw new RuntimeException(event.getMessage(), e);
	}
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, CHANGE_PASSWORD_STATEMENT);
	BoundStatement boundStatement = preparedStatement.bind(passwordHash,
		email);
	session.execute(boundStatement);

	eventLogger.logEvent(PasswordStoreEvents
		.createPasswordChangedEvent(email));
	return true;
    }

    @Override
    public String resetPassword(String email) throws PasswordResetException {
	logger.info("Password for user '" + email + "' is going to be reset...");
	Row account = getUserByEmail(email);
	if (account == null) {
	    Event event = PasswordStoreEvents
		    .createPasswordResetFailedUnknownAccountEvent(email);
	    eventLogger.logEvent(event);
	    throw new PasswordResetException(event.getEventId(),
		    event.getMessage());
	}
	String password = generatePassword();
	String passwordHash;
	try {
	    passwordHash = passwordEncrypter.encryptPassword(password)
		    .toString();
	} catch (PasswordEncryptionException e) {
	    Event event = PasswordStoreEvents
		    .createPasswordEncryptionErrorEvent(email, e);
	    eventLogger.logEvent(event);
	    throw new RuntimeException(event.getMessage(), e);
	}
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, CHANGE_PASSWORD_STATEMENT);
	BoundStatement boundStatement = preparedStatement.bind(passwordHash,
		email);
	session.execute(boundStatement);

	eventLogger.logEvent(PasswordStoreEvents
		.createPasswordResetEvent(email));
	return password;
    }

    private String generatePassword() {
	Random random = new Random();
	StringBuilder builder;
	do {
	    builder = new StringBuilder();
	    for (int i = 0; i < 8; i++) {
		builder.append(validCharacters.get(random
			.nextInt(validCharacters.size())));
	    }
	} while (!PasswordStrengthCalculator.validate(builder.toString()));
	return builder.toString();
    }
}
