package com.puresol.passwordstore.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresol.commons.utils.EmailAddressValidator;
import com.puresol.passwordstore.core.api.PasswordData;
import com.puresol.passwordstore.core.api.PasswordEncrypter;
import com.puresol.passwordstore.core.api.PasswordStore;
import com.puresol.passwordstore.domain.AccountActivationException;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.domain.PasswordChangeException;
import com.puresol.passwordstore.domain.PasswordEncryptionException;
import com.puresol.passwordstore.domain.PasswordResetException;
import com.puresol.passwordstore.domain.PasswordStrengthCalculator;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.MigrationException;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

/**
 * This is the central implementation of the {@link PasswordStore}.
 * 
 * @author "Rick-Rainer Ludwig"
 * 
 */
@Stateless
public class PasswordStoreBean implements PasswordStore {

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "system_monitor";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	@Inject
	private SecurityKeyGenerator securityKeyGenerator;

	@Inject
	private PasswordEncrypter passwordEncrypter;

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	private Cluster cluster = null;
	private Session session = null;
	private PreparedStatement createAccountStatement = null;
	private PreparedStatement activateAccountStatement = null;
	private PreparedStatement changePasswordStatement = null;
	private PreparedStatement retrieveUserByEmailStatement = null;

	@PostConstruct
	public void connectAndInitialize() {
		try {
			connectToCassandra();
			createKeyspaceAndConnectToIt();
			createPreparedStatements();
			eventLogger.logEvent(PasswordStoreEvents.createStartEvent());
		} catch (MigrationException e) {
			throw new RuntimeException("Cassandra could not be migrated.", e);
		}
	}

	private void connectToCassandra() {
		logger.debug("Connect PasswordStore to Cassandra...");
		cluster = Cluster.builder().addContactPoints(CASSANDRA_HOST)
				.withPort(CASSANDRA_CQL_PORT).build();
		logger.info("PasswordStore connected to Cassandra.");
	}

	private void createKeyspaceAndConnectToIt() throws MigrationException {
		logger.debug("Initialize migration and check schema...");
		CassandraMigration.initialize(cluster);
		checkAndCreateKeyspace();
		session = cluster.connect(PASSWORD_STORE_KEYSPACE_NAME);
		checkAndCreateTables();
		logger.info("PasswordStore schema is ok.");
	}

	private void checkAndCreateKeyspace() throws MigrationException {
		CassandraMigration.createKeyspace(cluster,
				PASSWORD_STORE_KEYSPACE_NAME, "1.0.0", "Rick-Rainer Ludwig",
				"Keeps the event log.", ReplicationStrategy.SIMPLE_STRATEGY, 3);
	}

	private void checkAndCreateTables() throws MigrationException {
		String description = "This table contains the authentication data and the state of the account.";
		CassandraMigration.createTable(cluster, PASSWORD_STORE_KEYSPACE_NAME,
				"1.0.0", "Rick-Rainer Ludwig", description, "CREATE TABLE "
						+ PASSWORD_TABLE_NAME//
						+ " (created timestamp, " //
						+ "last_modified timestamp, " //
						+ "email varchar," //
						+ "password ascii, " //
						+ "state ascii, "
						+ "activation_key ascii, "//
						+ "PRIMARY KEY (email))" + "WITH comment='"
						+ description + "';");
		CassandraMigration.createIndex(cluster, PASSWORD_STORE_KEYSPACE_NAME,
				"1.0.0", "Rick-Rainer Ludwig", "Secondary index on state.",
				PASSWORD_TABLE_NAME, "state");
	}

	private void createPreparedStatements() {
		createAccountStatement = session
				.prepare("INSERT INTO "
						+ PASSWORD_TABLE_NAME
						+ " (created, last_modified, email, password, state, activation_key)"
						+ "VALUES (?, ?, ?, ?, ?, "
						+ AccountState.CREATED.name() + ", ?)");
		activateAccountStatement = session.prepare("INSERT INTO "
				+ PASSWORD_TABLE_NAME + " (last_modified, state)"
				+ "VALUES (?, " + AccountState.ACTIVE.name()
				+ ") WHERE email = ?");
		changePasswordStatement = session.prepare("UPDATE "
				+ PASSWORD_TABLE_NAME + " SET password = ? WHERE email = ?");
		retrieveUserByEmailStatement = session.prepare("SELECT * FROM "
				+ PASSWORD_TABLE_NAME + " WHERE email = ?");
	}

	@PreDestroy
	public void disconnect() {
		eventLogger.logEvent(PasswordStoreEvents.createStopEvent());
		session.close();
		cluster.close();
		logger.info("PasswordStore disconnected.");
	}

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
		BoundStatement boundStatement = retrieveUserByEmailStatement
				.bind(email);
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

		// FIXME userId needs to come from counter!
		boundStatement = createAccountStatement.bind(created, created, -1,
				email, passwordHash, activationKey);
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

		BoundStatement boundStatement = activateAccountStatement.bind(
				new Date(), email);
		session.execute(boundStatement);

		eventLogger.logEvent(PasswordStoreEvents.createAccountActivatedEvent(
				email, activationKey));
		return email;
	}

	private Row getUserByEmail(String email) {
		BoundStatement boundStatement = retrieveUserByEmailStatement
				.bind(email);
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
			if (!AccountState.ACTIVE.equals(stateString)) {
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
		changePasswordStatement.bind(passwordHash, email);

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
		changePasswordStatement.bind(passwordHash, email);

		eventLogger.logEvent(PasswordStoreEvents
				.createPasswordResetEvent(email));
		return password;
	}

	private String generatePassword() {
		List<Character> validCharacters = new ArrayList<Character>();
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
