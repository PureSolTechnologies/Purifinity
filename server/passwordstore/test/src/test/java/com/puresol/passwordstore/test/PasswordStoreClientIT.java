package com.puresol.passwordstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.puresol.passwordstore.client.PasswordStoreClient;
import com.puresol.passwordstore.core.impl.PasswordStoreEvents;
import com.puresol.passwordstore.domain.AccountActivationException;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.domain.PasswordChangeException;
import com.puresol.passwordstore.domain.PasswordResetException;

public class PasswordStoreClientIT extends AbstractPasswordStoreClientTest {

	private static final String EMAIL_ADDRESS = "ludwig@puresol-technologies.com";
	private static final String INVALID_EMAIL_ADDRESS = "@puresol-technologies.com";
	private static final String VALID_PASSWORD = "IAmAPassword!:-)3";
	private static final String TOO_WEAK_PASSWORD = "123456";

	private final PasswordStoreClient restClient = new PasswordStoreClient();

	@Before
	public void setup() throws SQLException, IOException {
		assertNotNull(restClient);
		cleanupPasswordStoreDatabase();
	}

	@Test
	public void testCreateAccount() throws AccountCreationException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertNotNull(activationKey);
		assertFalse(activationKey.isEmpty());
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
		restClient.createAccount(EMAIL_ADDRESS, VALID_PASSWORD);
		try {
			// now we expect an error...
			restClient.createAccount(EMAIL_ADDRESS, VALID_PASSWORD);
		} catch (AccountCreationException e) {
			assertEquals(PasswordStoreEvents
					.createAccountAlreadyExistsErrorEvent(EMAIL_ADDRESS)
					.getMessage(), e.getMessage());
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
			restClient.createAccount(EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
		} catch (AccountCreationException e) {
			assertEquals(
					PasswordStoreEvents.createPasswordTooWeakErrorEvent(
							EMAIL_ADDRESS).getMessage(), e.getMessage());
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
			restClient.createAccount(INVALID_EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
		} catch (AccountCreationException e) {
			assertEquals(PasswordStoreEvents
					.createInvalidEmailAddressErrorEvent(INVALID_EMAIL_ADDRESS)
					.getMessage(), e.getMessage());
			throw e;
		}
	}

	@Test
	public void testActivateAccount() throws AccountActivationException,
			AccountCreationException {
		String activationKey = restClient.createAccount(
				"ludwig@puresol-technologies.com", "12dqwec1241`S@#R~");
		assertNotNull(activationKey);
		assertFalse(activationKey.isEmpty());
		restClient.activateAccount("ludwig@puresol-technologies.com",
				activationKey);
	}

	@Test(expected = AccountActivationException.class)
	public void testActivateAccountWithInvalidActivationKey()
			throws AccountCreationException, AccountActivationException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);

		try {
			restClient.activateAccount(EMAIL_ADDRESS, activationKey + "Wrong!");
		} catch (AccountActivationException e) {
			assertEquals(PasswordStoreEvents
					.createInvalidActivationKeyErrorEvent(EMAIL_ADDRESS)
					.getMessage(), e.getMessage());
			throw e;
		}
	}

	@Test
	public void testAuthenticate() throws AccountCreationException,
			AccountActivationException {
		String activationInformation = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		restClient.activateAccount(EMAIL_ADDRESS, activationInformation);
		assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	}

	@Test
	public void testAuthenticateWrongEmail() throws AccountCreationException,
			AccountActivationException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		restClient.activateAccount(EMAIL_ADDRESS, activationKey);
		assertFalse(restClient.authenticate(EMAIL_ADDRESS + "Wrong!",
				VALID_PASSWORD));
	}

	@Test
	public void testAuthenticateWrongPassword()
			throws AccountCreationException, AccountActivationException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		restClient.activateAccount(EMAIL_ADDRESS, activationKey);
		assertFalse(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD
				+ "Wrong!"));
	}

	@Test
	public void testAuthenticateWrongEmailAndPassword()
			throws AccountCreationException, AccountActivationException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		restClient.activateAccount(EMAIL_ADDRESS, activationKey);
		assertFalse(restClient.authenticate(EMAIL_ADDRESS + "Wrong!",
				VALID_PASSWORD + "Wrong!"));
	}

	@Test
	public void testChangePassword() throws AccountCreationException,
			AccountActivationException, PasswordChangeException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		restClient.activateAccount(EMAIL_ADDRESS, activationKey);
		assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		assertTrue(restClient.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
				VALID_PASSWORD + "New!"));
		assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD
				+ "New!"));
	}

	@Test
	public void testChangePasswordWrongEmail() throws AccountCreationException,
			AccountActivationException, PasswordChangeException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		restClient.activateAccount(EMAIL_ADDRESS, activationKey);
		assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		assertFalse(restClient.changePassword(EMAIL_ADDRESS + "Wrong!",
				VALID_PASSWORD, VALID_PASSWORD + "New!"));
	}

	@Test
	public void testChangePasswordWrongPassword()
			throws AccountCreationException, AccountActivationException,
			PasswordChangeException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		restClient.activateAccount(EMAIL_ADDRESS, activationKey);
		assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		assertFalse(restClient.changePassword(EMAIL_ADDRESS, VALID_PASSWORD
				+ "Wrong!", VALID_PASSWORD + "New!"));
	}

	@Test(expected = PasswordChangeException.class)
	public void testChangePasswordTooWeakPassword()
			throws AccountCreationException, AccountActivationException,
			PasswordChangeException {
		try {
			String activationKey = restClient.createAccount(EMAIL_ADDRESS,
					VALID_PASSWORD);
			restClient.activateAccount(EMAIL_ADDRESS, activationKey);
			assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
			restClient.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
					TOO_WEAK_PASSWORD);
		} catch (PasswordChangeException e) {
			assertEquals(
					PasswordStoreEvents
							.createPasswordChangeFailedPasswordTooWeakEvent(
									EMAIL_ADDRESS).getMessage(), e.getMessage());
			assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
			throw e;
		}
	}

	@Test
	public void testResetPassword() throws AccountCreationException,
			AccountActivationException, PasswordResetException {
		String activationKey = restClient.createAccount(EMAIL_ADDRESS,
				VALID_PASSWORD);
		restClient.activateAccount(EMAIL_ADDRESS, activationKey);
		assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		String newPassword = restClient.resetPassword(EMAIL_ADDRESS);
		assertTrue(restClient.authenticate(EMAIL_ADDRESS, newPassword));
	}

	@Test(expected = PasswordResetException.class)
	public void testResetPasswordWrongEmail() throws PasswordResetException {
		try {
			restClient.resetPassword(EMAIL_ADDRESS);
		} catch (PasswordResetException e) {
			assertEquals(
					PasswordStoreEvents
							.createPasswordResetFailedUnknownAccountEvent(
									EMAIL_ADDRESS).getMessage(), e.getMessage());
			throw e;
		}
	}

}
