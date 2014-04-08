package com.puresol.passwordstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;

import com.puresol.jboss.test.arquillian.EnhanceDeployment;
import com.puresol.passwordstore.core.api.PasswordStore;
import com.puresol.passwordstore.core.impl.entities.AccountState;
import com.puresol.passwordstore.core.impl.entities.UserDAO;
import com.puresol.passwordstore.core.impl.entities.UserEntity;
import com.puresol.passwordstore.domain.AccountActivationException;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.domain.PasswordChangeException;
import com.puresol.passwordstore.domain.PasswordResetException;
import com.puresol.passwordstore.domain.PasswordStoreExceptionMessage;

public class PasswordStoreBeanIT extends AbstractPasswordStoreServerTest {

	private static final String EMAIL_ADDRESS = "ludwig@puresol-technologies.com";
	private static final String INVALID_EMAIL_ADDRESS = "@puresol-technologies.com";
	private static final String VALID_PASSWORD = "IAmAPassword!:-)3";
	private static final String TOO_WEAK_PASSWORD = "123456";

	@Inject
	private PasswordStore passwordStore;

	@Inject
	private UserDAO userDAO;

	@EnhanceDeployment
	public static void additionalDeployment(JavaArchive archive) {
		archive.addClass(PasswordStoreBeanIT.class);
	}

	@Before
	public void setup() throws SQLException, IOException {
		assertNotNull(passwordStore);
		assertNotNull(userDAO);
		cleanupPasswordStoreDatabase();
	}

	@Test
	public void testCreateAccount() throws AccountCreationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertNotNull(activationKey);
		assertFalse(activationKey.isEmpty());
		assertEquals(64, activationKey.length());

		UserEntity userEntity = userDAO.getUserByEmail(EMAIL_ADDRESS);
		assertEquals(EMAIL_ADDRESS, userEntity.getEmail());
		assertNotNull(userEntity.getPwh());
		assertFalse(userEntity.getPwh().isEmpty());
		assertEquals(AccountState.CREATED, userEntity.getState());
	}

	/**
	 * We create here two account with the same email address. We expect here an
	 * AccountCreationException with an embedded message about the duplicate
	 * email address.
	 * 
	 * @throws AccountCreationException
	 */
	@Test(expected = AccountCreationException.class)
	public void testCreateAccountDuplicateEmail()
			throws AccountCreationException {
		// the first account should be created normally...
		passwordStore.createAccount(EMAIL_ADDRESS, VALID_PASSWORD);
		try {
			// now we expect an error...
			passwordStore.createAccount(EMAIL_ADDRESS, VALID_PASSWORD);
		} catch (AccountCreationException e) {
			assertEquals(PasswordStoreExceptionMessage.ACCOUNT_ALREADY_EXISTS,
					e.getExceptionMessage());
			UserEntity userEntity = userDAO.getUserByEmail(EMAIL_ADDRESS);
			assertNotNull(userEntity);
			assertNotNull(userEntity.getPwh());
			assertFalse(userEntity.getPwh().isEmpty());
			assertEquals(AccountState.CREATED, userEntity.getState());
			throw e;
		}
	}

	/**
	 * For a trival password we expect a {@link AccountCreationException} here
	 * with an embedded message about a too weak password.
	 * 
	 * @throws AccountCreationException
	 */
	@Test(expected = AccountCreationException.class)
	public void testCreateAccountTooWeakPassword()
			throws AccountCreationException {
		try {
			passwordStore.createAccount(EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
		} catch (AccountCreationException e) {
			assertEquals(PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK,
					e.getExceptionMessage());
			assertNull(userDAO.getUserByEmail(EMAIL_ADDRESS));
			throw e;
		}
	}

	/**
	 * For an invalid email address we expect an
	 * {@link AccountCreationException} with an embedded message about the
	 * invalid email address.
	 * 
	 * @throws AccountCreationException
	 */
	@Test(expected = AccountCreationException.class)
	public void testCreateAccountWithInvalidEmailAddress()
			throws AccountCreationException {
		try {
			passwordStore.createAccount(INVALID_EMAIL_ADDRESS,
					TOO_WEAK_PASSWORD);
		} catch (AccountCreationException e) {
			assertEquals(PasswordStoreExceptionMessage.INVALID_EMAIL_ADDRESS,
					e.getExceptionMessage());
			assertNull(userDAO.getUserByEmail(EMAIL_ADDRESS));
			throw e;
		}
	}

	@Test
	public void testActivateAccount() throws AccountCreationException,
			AccountActivationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		UserEntity userEntity = userDAO.getUserByEmail(EMAIL_ADDRESS);
		assertEquals(AccountState.CREATED, userEntity.getState());

		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
		userEntity = userDAO.getUserByEmail(EMAIL_ADDRESS);
		assertEquals(AccountState.ACTIVE, userEntity.getState());
	}

	@Test(expected = AccountActivationException.class)
	public void testActivateAccountWithInvalidActivationKey()
			throws AccountCreationException, AccountActivationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		UserEntity userEntity = userDAO.getUserByEmail(EMAIL_ADDRESS);
		assertEquals(AccountState.CREATED, userEntity.getState());

		try {
			passwordStore.activateAccount(EMAIL_ADDRESS, activationKey
					+ "Wrong!");
		} catch (AccountActivationException e) {
			assertEquals(PasswordStoreExceptionMessage.INVALID_ACTIVATION_KEY,
					e.getExceptionMessage());
			userEntity = userDAO.getUserByEmail(EMAIL_ADDRESS);
			assertNotNull(userEntity);
			assertNotNull(userEntity.getPwh());
			assertFalse(userEntity.getPwh().isEmpty());
			assertEquals(AccountState.CREATED, userEntity.getState());
			throw e;
		}
	}

	@Test
	public void testAuthenticate() throws AccountCreationException,
			AccountActivationException {
		String activationInformation = passwordStore.createAccount(
				EMAIL_ADDRESS, VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationInformation);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	}

	@Test
	public void testAuthenticateWrongEmail() throws AccountCreationException,
			AccountActivationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS + "Wrong!",
				VALID_PASSWORD));
	}

	@Test
	public void testAuthenticateWrongPassword()
			throws AccountCreationException, AccountActivationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD
				+ "Wrong!"));
	}

	@Test
	public void testAuthenticateWrongEmailAndPassword()
			throws AccountCreationException, AccountActivationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS + "Wrong!",
				VALID_PASSWORD + "Wrong!"));
	}

	@Test
	public void testChangePassword() throws AccountCreationException,
			AccountActivationException, PasswordChangeException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		assertTrue(passwordStore.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
				VALID_PASSWORD + "New!"));
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD
				+ "New!"));
	}

	@Test
	public void testChangePasswordWrongEmail() throws AccountCreationException,
			AccountActivationException, PasswordChangeException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		assertFalse(passwordStore.changePassword(EMAIL_ADDRESS + "Wrong!",
				VALID_PASSWORD, VALID_PASSWORD + "New!"));
	}

	@Test
	public void testChangePasswordWrongPassword()
			throws AccountCreationException, AccountActivationException,
			PasswordChangeException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		assertFalse(passwordStore.changePassword(EMAIL_ADDRESS, VALID_PASSWORD
				+ "Wrong!", VALID_PASSWORD + "New!"));
	}

	@Test(expected = PasswordChangeException.class)
	public void testChangePasswordTooWeakPassword()
			throws AccountCreationException, AccountActivationException,
			PasswordChangeException {
		try {
			String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
					VALID_PASSWORD);
			assertFalse(passwordStore.authenticate(EMAIL_ADDRESS,
					VALID_PASSWORD));
			passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
			assertTrue(passwordStore
					.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
			passwordStore.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
					TOO_WEAK_PASSWORD);
		} catch (PasswordChangeException e) {
			assertEquals(PasswordStoreExceptionMessage.PASSWORD_TOO_WEAK,
					e.getExceptionMessage());
			assertTrue(passwordStore
					.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
			throw e;
		}
	}

	@Test
	public void testResetPassword() throws AccountCreationException,
			AccountActivationException, PasswordResetException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		String newPassword = passwordStore.resetPassword(EMAIL_ADDRESS);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, newPassword));
	}

	@Test(expected = PasswordResetException.class)
	public void testResetPasswordWrongEmail() throws PasswordResetException {
		try {
			passwordStore.resetPassword(EMAIL_ADDRESS);
		} catch (PasswordResetException e) {
			assertEquals(PasswordStoreExceptionMessage.UNKNOWN_EMAIL_ADDRESS,
					e.getExceptionMessage());
			throw e;
		}
	}

}
