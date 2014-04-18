package com.puresoltechnologies.purifinity.server.passwordstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;

import com.datastax.driver.core.Row;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.AccountState;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreEvents;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public class PasswordStoreBeanIT extends AbstractPasswordStoreServerTest {

	private static final String EMAIL_ADDRESS = "ludwig@puresol-technologies.com";
	private static final String INVALID_EMAIL_ADDRESS = "@puresol-technologies.com";
	private static final String VALID_PASSWORD = "IAmAPassword!:-)3";
	private static final String TOO_WEAK_PASSWORD = "123456";

	@Inject
	private PasswordStore passwordStore;

	@EnhanceDeployment
	public static void additionalDeployment(JavaArchive archive) {
		archive.addClass(PasswordStoreBeanIT.class);
	}

	@Before
	public void setup() throws IOException {
		assertNotNull(passwordStore);
		cleanupPasswordStoreDatabase();
	}

	@Test
	public void testCreateAccount() throws AccountCreationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertNotNull(activationKey);
		assertFalse(activationKey.isEmpty());
		assertEquals(64, activationKey.length());

		Row account = readAccoutFromDatabase(EMAIL_ADDRESS);

		assertEquals(EMAIL_ADDRESS, account.getString("email"));
		assertNotNull(account.getString("password"));
		assertFalse(account.getString("password").isEmpty());
		assertEquals(AccountState.CREATED,
				AccountState.valueOf(account.getString("state")));
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
			assertEquals(PasswordStoreEvents
					.createAccountAlreadyExistsErrorEvent(EMAIL_ADDRESS)
					.getMessage(), e.getMessage());

			Row account = readAccoutFromDatabase(EMAIL_ADDRESS);

			assertNotNull(account);
			assertNotNull(account.getString("password"));
			assertFalse(account.getString("password").isEmpty());
			assertEquals(AccountState.CREATED,
					AccountState.valueOf(account.getString("state")));
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
			assertEquals(
					PasswordStoreEvents.createPasswordTooWeakErrorEvent(
							EMAIL_ADDRESS).getMessage(), e.getMessage());

			Row account = readAccoutFromDatabase(EMAIL_ADDRESS);
			assertNull(account);
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
			Row account = readAccoutFromDatabase(EMAIL_ADDRESS);
			assertNull(account);

			String expectedMessage = PasswordStoreEvents
					.createInvalidEmailAddressErrorEvent(INVALID_EMAIL_ADDRESS)
					.getMessage();
			String actualMessage = e.getMessage();
			assertEquals(expectedMessage, actualMessage);

			throw e;
		}
	}

	@Test
	public void testActivateAccount() throws AccountCreationException,
			AccountActivationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);

		Row account = readAccoutFromDatabase(EMAIL_ADDRESS);

		assertEquals(AccountState.CREATED,
				AccountState.valueOf(account.getString("state")));

		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);

		account = readAccoutFromDatabase(EMAIL_ADDRESS);
		assertEquals(AccountState.ACTIVE,
				AccountState.valueOf(account.getString("state")));
	}

	@Test(expected = AccountActivationException.class)
	public void testActivateAccountWithInvalidActivationKey()
			throws AccountCreationException, AccountActivationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);

		Row account = readAccoutFromDatabase(EMAIL_ADDRESS);

		assertEquals(AccountState.CREATED,
				AccountState.valueOf(account.getString("state")));

		try {
			passwordStore.activateAccount(EMAIL_ADDRESS, activationKey
					+ "Wrong!");
		} catch (AccountActivationException e) {
			assertEquals(PasswordStoreEvents
					.createInvalidActivationKeyErrorEvent(EMAIL_ADDRESS)
					.getMessage(), e.getMessage());
			account = readAccoutFromDatabase(EMAIL_ADDRESS);
			assertNotNull(account);
			assertNotNull(account.getString("password"));
			assertFalse(account.getString("password").isEmpty());
			assertEquals(AccountState.CREATED,
					AccountState.valueOf(account.getString("state")));
			throw e;
		}
	}

	@Test
	public void testAuthenticate() throws AccountCreationException,
			AccountActivationException {
		String activationKey = passwordStore.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activateAccount(EMAIL_ADDRESS, activationKey);
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
			String expectedMessage = PasswordStoreEvents
					.createPasswordChangeFailedPasswordTooWeakEvent(
							EMAIL_ADDRESS).getMessage();
			String actualMessage = e.getMessage();
			assertEquals(expectedMessage, actualMessage);
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
			assertEquals(
					PasswordStoreEvents
							.createPasswordResetFailedUnknownAccountEvent(
									EMAIL_ADDRESS).getMessage(), e.getMessage());
			throw e;
		}
	}

}
