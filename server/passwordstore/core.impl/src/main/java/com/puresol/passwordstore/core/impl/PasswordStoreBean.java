package com.puresol.passwordstore.core.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.puresol.passwordstore.core.api.PasswordData;
import com.puresol.passwordstore.core.api.PasswordEncrypter;
import com.puresol.passwordstore.core.api.PasswordStore;
import com.puresol.passwordstore.core.impl.entities.AccountState;
import com.puresol.passwordstore.core.impl.entities.ActivationKeyDAO;
import com.puresol.passwordstore.core.impl.entities.ActivationKeyEntity;
import com.puresol.passwordstore.core.impl.entities.UserDAO;
import com.puresol.passwordstore.core.impl.entities.UserEntity;
import com.puresol.passwordstore.domain.AccountActivationException;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.domain.PasswordChangeException;
import com.puresol.passwordstore.domain.PasswordEncryptionException;
import com.puresol.passwordstore.domain.PasswordResetException;
import com.puresol.passwordstore.domain.PasswordStrengthCalculator;
import com.puresoltechnologies.purifinity.server.eventlogger.EventLoggerRemote;

/**
 * This is the central implementation of the {@link PasswordStore}.
 * 
 * @author "Rick-Rainer Ludwig"
 * 
 */
@Stateless
public class PasswordStoreBean implements PasswordStore {

	@Inject
	private SecurityKeyGenerator securityKeyGenerator;

	@Inject
	private PasswordEncrypter passwordEncrypter;

	@Inject
	private UserDAO userDAO;

	@Inject
	private ActivationKeyDAO activationKeyDAO;

	@EJB(lookup = "???")
	private EventLoggerRemote eventLogger;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public String createAccount(String email, String password)
			throws AccountCreationException {
		eventLogger.logUserAction(email, "Creating accout for user '" + email
				+ "'...");
		if (!EmailAddressValidator.validate(email)) {
			eventLogger.logUserException(email,
					PasswordStoreExceptionMessage.INVALID_EMAIL_ADDRESS,
					"Creation of account for user '" + email
							+ "' failed! Email address is invalid!");
			throw new AccountCreationException(
					PasswordStoreExceptionMessage.INVALID_EMAIL_ADDRESS);
		}
		if (!PasswordStrengthCalculator.validate(password)) {
			eventLogger.logUserException(email,
					PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK,
					"Creation of account for user '" + email
							+ "' failed! Password is too weak!");
			throw new AccountCreationException(
					PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK);
		}
		if (userDAO.getUserByEmail(email) != null) {
			eventLogger.logUserException(email,
					PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK,
					"Creation of account for user '" + email
							+ "' failed! Account already exists!");
			throw new AccountCreationException(
					PasswordStoreExceptionMessage.ACCOUNT_ALREADY_EXISTS);
		}
		UserEntity user = new UserEntity();
		user.setEmail(email);
		try {
			user.setPwh(passwordEncrypter.encryptPassword(password).toString());
		} catch (PasswordEncryptionException e) {
			eventLogger.logUserException(email, e,
					"Could not encrypt password for user '" + user + "'!");
			eventLogger.logSystemException(e,
					"Could not encrypt password for user '" + user + "'!");
			throw new RuntimeException("Could not encrypt password!", e);
		}
		user.setState(AccountState.CREATED);
		user.setCreated(new Date(new java.util.Date().getTime()));
		entityManager.persist(user);

		ActivationKeyEntity activationKey = new ActivationKeyEntity();
		activationKey.setUserId(user.getUserId());
		activationKey.setActivationKey(securityKeyGenerator.generate());
		entityManager.persist(activationKey);

		String activationKeyString = activationKey.getActivationKey();
		eventLogger
				.logUserAction(email, "Accout for user '" + email
						+ "' created. Activation key is '"
						+ activationKeyString + "'.");
		return activationKeyString;
	}

	@Override
	public long activateAccount(String email, String activationKey)
			throws AccountActivationException {
		eventLogger.logUserAction(email, "Activating accout for user '" + email
				+ "'...");
		UserEntity userEntity = userDAO.getUserByEmail(email);
		if (userEntity.getState() != AccountState.CREATED) {
			eventLogger.logUserException(email,
					PasswordStoreExceptionMessage.ACCOUNT_ALREADY_ACTIVATED,
					"Activation of account for user '" + email
							+ "' was failed! Account was already activated!");
			throw new AccountActivationException(
					PasswordStoreExceptionMessage.ACCOUNT_ALREADY_ACTIVATED);
		}

		ActivationKeyEntity activationKeyEntity = activationKeyDAO
				.getActivationKeyByUserId(userEntity.getUserId());

		if (!activationKeyEntity.getActivationKey().equals(activationKey)) {
			eventLogger.logUserException(email,
					PasswordStoreExceptionMessage.ACCOUNT_ALREADY_ACTIVATED,
					"Activation of account for user '" + email
							+ "' was failed! Activation key is not valied!");
			throw new AccountActivationException(
					PasswordStoreExceptionMessage.INVALID_ACTIVATION_KEY);
		}
		userEntity.setState(AccountState.ACTIVE);
		entityManager.merge(userEntity);
		eventLogger.logUserAction(email, "Account for user '" + email
				+ "' was activated.");
		return userEntity.getUserId();
	}

	@Override
	public boolean authenticate(String email, String password) {
		try {
			eventLogger.logUserAction(email, "Authenticating user '" + email
					+ "'...");
			UserEntity userEntity = userDAO.getUserByEmail(email);
			if (userEntity == null) {
				eventLogger.logUserAction(email, "Authentication for user '"
						+ email + "' failed! Account does not exist!");
				return false;
			}
			if (userEntity.getState() != AccountState.ACTIVE) {
				eventLogger.logUserAction(email, "Authentication for user '"
						+ email + "' failed! Account is not active!");
				return false;
			}
			boolean authenticated = passwordEncrypter.checkPassword(password,
					PasswordData.fromString(userEntity.getPwh()));
			if (authenticated) {
				eventLogger.logUserAction(email, "Authentication for user '"
						+ email + "' successful!");
			} else {
				eventLogger.logUserAction(email, "Authentication for user '"
						+ email + "' failed! Password does not match!");
			}
			return authenticated;
		} catch (PasswordEncryptionException e) {
			eventLogger.logUserException(email, e,
					"Could not encrypt password for user '" + email + "'!");
			eventLogger.logSystemException(e,
					"Could not encrypt password for user '" + email + "'!");
			throw new RuntimeException("Could not encrypt password!", e);
		}
	}

	@Override
	public boolean changePassword(String email, String oldPassword,
			String newPassword) throws PasswordChangeException {
		eventLogger.logUserAction(email, "Change password for user '" + email
				+ "'...");
		if (!authenticate(email, oldPassword)) {
			eventLogger.logUserAction(email,
					"Password change not possible for user '" + email
							+ "' due to failed authentication!");
			return false;
		}
		if (!PasswordStrengthCalculator.validate(newPassword)) {
			eventLogger.logUserException(email,
					PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK,
					"Password change not possible for user '" + email
							+ "' due to too weak password.");
			throw new PasswordChangeException(
					PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK);
		}
		UserEntity user = userDAO.getUserByEmail(email);
		try {
			user.setPwh(passwordEncrypter.encryptPassword(newPassword)
					.toString());
		} catch (PasswordEncryptionException e) {
			eventLogger.logUserException(email, e,
					"Could not encrypt password for user '" + user + "'!");
			eventLogger.logSystemException(e,
					"Could not encrypt password for user '" + user + "'!");
			throw new RuntimeException("Could not encrypt password!", e);
		}
		entityManager.merge(user);
		eventLogger.logUserAction(email, "Password for user '" + email
				+ "' was changed.");
		return true;
	}

	@Override
	public String resetPassword(String email) throws PasswordResetException {
		eventLogger.logUserAction(email, "Reseting password for user '" + email
				+ "'...");
		UserEntity user = userDAO.getUserByEmail(email);
		if (user == null) {
			eventLogger.logUserException(email,
					PasswordStoreExceptionMessage.UNKNOWN_EMAIL_ADDRESS,
					"Password reset not possible for user '" + user
							+ "' due to non exsiting account!");
			throw new PasswordResetException(
					PasswordStoreExceptionMessage.UNKNOWN_EMAIL_ADDRESS);
		}
		String password = generatePassword();
		try {
			user.setPwh(passwordEncrypter.encryptPassword(password).toString());
		} catch (PasswordEncryptionException e) {
			eventLogger.logUserException(email, e,
					"Could not encrypt password for user '" + user + "'!");
			eventLogger.logSystemException(e,
					"Could not encrypt password for user '" + user + "'!");
			throw new RuntimeException("Could not encrypt password!", e);
		}
		entityManager.merge(user);
		eventLogger.logUserAction(email, "Password for user '" + email
				+ "' was reset.");
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
