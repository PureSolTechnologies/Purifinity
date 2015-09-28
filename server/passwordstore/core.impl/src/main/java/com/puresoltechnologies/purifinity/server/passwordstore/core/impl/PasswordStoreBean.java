package com.puresoltechnologies.purifinity.server.passwordstore.core.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordEncryptionException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordState;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

/**
 * This is the central implementation of the {@link PasswordStore}.
 * 
 * @author "Rick-Rainer Ludwig"
 * 
 */
@Stateless
public class PasswordStoreBean implements PasswordStore {

    public static final String PASSWORD_TABLE_NAME = "password_store_passwords";

    public static final String CREATE_ACCOUNT_STATEMENT = "UPSERT INTO " + PASSWORD_TABLE_NAME
	    + " (created, last_modified, email, password, state, activation_key)" + " VALUES (?, ?, ?, ?, '"
	    + PasswordState.CREATED.name() + "', ?)";
    public static final String ACTIVATE_ACCOUNT_STATEMENT = "UPSERT INTO " + PASSWORD_TABLE_NAME
	    + " (last_modified, email, state) VALUES (?, ?, '" + PasswordState.ACTIVE.name() + "')";
    public static final String CHANGE_PASSWORD_STATEMENT = "UPSERT INTO " + PASSWORD_TABLE_NAME
	    + " (password, email) VALUES (?, ?)";
    public static final String RETRIEVE_ACCOUNT_STATEMENT = "SELECT * FROM " + PASSWORD_TABLE_NAME + " WHERE email = ?";
    public static final String DELETE_ACCOUNT_STATEMENT = "DELETE FROM " + PASSWORD_TABLE_NAME + " WHERE email = ?";

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
    private EventLoggerRemote eventLogger;

    @Inject
    @PasswordStoreConnection
    private Connection connection;

    @Override
    public String createPassword(EmailAddress email, Password password) throws PasswordCreationException {
	try {
	    logger.info("An account for '" + email + "' is going to be created...");
	    if (!PasswordStrengthCalculator.validate(password.getPassword())) {
		Event event = PasswordStoreEvents.createPasswordTooWeakErrorEvent(email);
		eventLogger.logEvent(event);
		throw new PasswordCreationException(event.getMessage());
	    }
	    PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_ACCOUNT_STATEMENT);
	    preparedStatement.setString(1, email.getAddress());
	    ResultSet result = preparedStatement.executeQuery();
	    if (result.next()) {
		Event event = PasswordStoreEvents.createAccountAlreadyExistsErrorEvent(email);
		eventLogger.logEvent(event);
		throw new PasswordCreationException(event.getMessage());
	    }

	    String passwordHash;
	    try {
		passwordHash = passwordEncrypter.encryptPassword(password.getPassword()).toString();
	    } catch (PasswordEncryptionException e) {
		Event event = PasswordStoreEvents.createPasswordEncryptionErrorEvent(email, e);
		eventLogger.logEvent(event);
		throw new RuntimeException(event.getMessage(), e);
	    }
	    String activationKey = securityKeyGenerator.generate();
	    Date created = new Date();

	    preparedStatement = connection.prepareStatement(CREATE_ACCOUNT_STATEMENT);
	    preparedStatement.setTime(1, new Time(created.getTime()));
	    preparedStatement.setTime(2, new Time(created.getTime()));
	    preparedStatement.setString(3, email.getAddress());
	    preparedStatement.setString(4, passwordHash);
	    preparedStatement.setString(5, activationKey);
	    preparedStatement.execute();
	    connection.commit();

	    eventLogger.logEvent(PasswordStoreEvents.createAccountCreationEvent(email, activationKey));
	    return activationKey;
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback transaction.", e1);
	    }
	    throw new PasswordCreationException("Could not create password for user '" + email + "'.", e);
	}
    }

    @Override
    public EmailAddress activatePassword(EmailAddress email, String activationKey) throws PasswordActivationException {
	try {
	    logger.info("Account for user '" + email + "' is to be activated...");

	    ResultSet account = getUserByEmail(email);
	    if (account == null) {
		Event event = PasswordStoreEvents.createInvalidEmailAddressErrorEvent(email);
		eventLogger.logEvent(event);
		throw new PasswordActivationException(event.getMessage());
	    }
	    String stateString = account.getString("state");
	    if (!PasswordState.CREATED.name().equals(stateString)) {
		Event event = PasswordStoreEvents.createAccountAlreadyActivatedEvent(email);
		eventLogger.logEvent(event);
		throw new PasswordActivationException(event.getMessage());
	    }

	    String definedActivationKey = account.getString("activation_key");
	    if (!definedActivationKey.equals(activationKey)) {
		Event event = PasswordStoreEvents.createInvalidActivationKeyErrorEvent(email);
		eventLogger.logEvent(event);
		throw new PasswordActivationException(event.getMessage());
	    }

	    PreparedStatement preparedStatement = connection.prepareStatement(ACTIVATE_ACCOUNT_STATEMENT);
	    preparedStatement.setTime(1, new Time(new Date().getTime()));
	    preparedStatement.setString(2, email.getAddress());
	    preparedStatement.execute();
	    connection.commit();
	    eventLogger.logEvent(PasswordStoreEvents.createAccountActivatedEvent(email, activationKey));
	    return email;
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback password activation for user '" + email + "'.", e1);
	    }
	    throw new PasswordActivationException("Could not activate password activation for user '" + email + "'.",
		    e);
	}
    }

    private ResultSet getUserByEmail(EmailAddress email) throws SQLException {
	PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_ACCOUNT_STATEMENT);
	preparedStatement.setString(1, email.getAddress());
	ResultSet resultSet = preparedStatement.executeQuery();
	if (resultSet.next()) {
	    return resultSet;
	} else {
	    return null;
	}
    }

    @Override
    public boolean authenticate(EmailAddress email, Password password) {
	try {
	    logger.info("Authenticating user '" + email + "'...");
	    ResultSet account = getUserByEmail(email);
	    if (account == null) {
		eventLogger.logEvent(PasswordStoreEvents.createUserAuthenticationFailedAccountNotExistsEvent(email));
		return false;
	    }
	    String stateString = account.getString("state");
	    if (!PasswordState.ACTIVE.name().equals(stateString)) {
		eventLogger.logEvent(PasswordStoreEvents.createUserAuthenticationFailedAccountNotActiveEvent(email));
		return false;
	    }
	    boolean authenticated = passwordEncrypter.checkPassword(password.getPassword(),
		    PasswordData.fromString(account.getString("password")));
	    if (authenticated) {
		eventLogger.logEvent(PasswordStoreEvents.createUserAuthenticatedEvent(email));
	    } else {
		eventLogger.logEvent(PasswordStoreEvents.createUserAuthenticationFailedEvent(email));
	    }
	    return authenticated;
	} catch (SQLException | PasswordEncryptionException e) {
	    Event event = PasswordStoreEvents.createPasswordEncryptionErrorEvent(email, e);
	    eventLogger.logEvent(event);
	    throw new RuntimeException(event.getMessage(), e);
	}
    }

    @Override
    public boolean changePassword(EmailAddress email, Password oldPassword, Password newPassword)
	    throws PasswordChangeException {
	try {
	    logger.info("Password for user '" + email + "' is going to be changed...");
	    if (!authenticate(email, oldPassword)) {
		eventLogger.logEvent(PasswordStoreEvents.createPasswordChangeFailedNotAuthenticatedEvent(email));
		return false;
	    }
	    if (!PasswordStrengthCalculator.validate(newPassword.getPassword())) {
		Event event = PasswordStoreEvents.createPasswordChangeFailedPasswordTooWeakEvent(email);
		eventLogger.logEvent(event);
		throw new PasswordChangeException(event.getMessage());
	    }
	    String passwordHash;
	    try {
		passwordHash = passwordEncrypter.encryptPassword(newPassword.getPassword()).toString();
	    } catch (PasswordEncryptionException e) {
		Event event = PasswordStoreEvents.createPasswordEncryptionErrorEvent(email, e);
		eventLogger.logEvent(event);
		throw new RuntimeException(event.getMessage(), e);
	    }
	    PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD_STATEMENT);
	    preparedStatement.setString(1, passwordHash);
	    preparedStatement.setString(2, email.getAddress());
	    preparedStatement.execute();
	    connection.commit();

	    eventLogger.logEvent(PasswordStoreEvents.createPasswordChangedEvent(email));
	    return true;
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback password change for user '" + email + "'.", e1);
	    }
	    throw new RuntimeException("Could not change password for user '" + email + "'.", e);
	}
    }

    @Override
    public Password resetPassword(EmailAddress email) throws PasswordResetException {
	try {
	    logger.info("Password for user '" + email + "' is going to be reset...");
	    ResultSet account = getUserByEmail(email);
	    if (account == null) {
		Event event = PasswordStoreEvents.createPasswordResetFailedUnknownAccountEvent(email);
		eventLogger.logEvent(event);
		throw new PasswordResetException(event.getMessage());
	    }
	    String password = generatePassword();
	    String passwordHash;
	    try {
		passwordHash = passwordEncrypter.encryptPassword(password).toString();
	    } catch (PasswordEncryptionException e) {
		Event event = PasswordStoreEvents.createPasswordEncryptionErrorEvent(email, e);
		eventLogger.logEvent(event);
		throw new RuntimeException(event.getMessage(), e);
	    }
	    PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD_STATEMENT);
	    preparedStatement.setString(1, passwordHash);
	    preparedStatement.setString(2, email.getAddress());
	    preparedStatement.execute();
	    connection.commit();

	    eventLogger.logEvent(PasswordStoreEvents.createPasswordResetEvent(email));
	    return new Password(password);
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback password change for user '" + email + "'.", e1);
	    }
	    throw new RuntimeException("Could not change password for user '" + email + "'.", e);
	}
    }

    private String generatePassword() {
	Random random = new Random();
	StringBuilder builder;
	do {
	    builder = new StringBuilder();
	    for (int i = 0; i < 8; i++) {
		builder.append(validCharacters.get(random.nextInt(validCharacters.size())));
	    }
	} while (!PasswordStrengthCalculator.validate(builder.toString()));
	return builder.toString();
    }

    @Override
    public void deletePassword(EmailAddress email) {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_STATEMENT);
	    preparedStatement.setString(1, email.getAddress());
	    preparedStatement.execute();
	    connection.commit();
	    eventLogger.logEvent(PasswordStoreEvents.createPasswordDeleteEvent(email));
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback account deletion for user '" + email + "'.", e1);
	    }
	    throw new RuntimeException("Could not delete account for user '" + email + "'.", e);
	}
    }
}
