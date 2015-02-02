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
import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.IntrospectionUtilities;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreEvents;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountState;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreDatabaseHelper;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public class PasswordStoreBeanIT extends AbstractPasswordStoreServerTest {

	private static final EmailAddress EMAIL_ADDRESS = new EmailAddress(
			"ludwig@puresol-technologies.com");
	private static final EmailAddress INVALID_EMAIL_ADDRESS = new EmailAddress(
			"ludwig@puresol-technologies.com");
	static {
		try {
			IntrospectionUtilities.setField(INVALID_EMAIL_ADDRESS, "localPart",
					"");
			IntrospectionUtilities.setField(INVALID_EMAIL_ADDRESS, "address",
					"puresol-technologies.com");
		} catch (SecurityException | NoSuchFieldException
				| IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("Could not initialize test!", e);
		}

	}
	private static final Password VALID_PASSWORD = new Password(
			"IAmAPassword!:-)3");
	private static final Password TOO_WEAK_PASSWORD = new Password("123456");

	@Inject
	private PasswordStore passwordStore;

	@EnhanceDeployment
	public static void additionalDeployment(JavaArchive archive) {
		archive.addClass(PasswordStoreBeanIT.class);
	}

	@Before
	public void setup() throws IOException {
		assertNotNull(passwordStore);
		PasswordStoreDatabaseHelper.cleanPasswordStore(getCluster());
	}

	@Test
	public void testCreateAccount() throws PasswordCreationException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertNotNull(activationKey);
		assertFalse(activationKey.isEmpty());
		assertEquals(64, activationKey.length());

		Row account = readAccoutFromDatabase(EMAIL_ADDRESS);

		assertEquals(EMAIL_ADDRESS.getAddress(), account.getString("email"));
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
	 * @throws PasswordCreationException
	 */
	@Test(expected = PasswordCreationException.class)
	public void testCreateAccountDuplicateEmail()
			throws PasswordCreationException {
		// the first account should be created normally...
		passwordStore.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
		try {
			// now we expect an error...
			passwordStore.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
		} catch (PasswordCreationException e) {
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
	 * For a trival password we expect a {@link PasswordCreationException} here
	 * with an embedded message about a too weak password.
	 * 
	 * @throws PasswordCreationException
	 */
	@Test(expected = PasswordCreationException.class)
	public void testCreateAccountTooWeakPassword()
			throws PasswordCreationException {
		try {
			passwordStore.createPassword(EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
		} catch (PasswordCreationException e) {
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
	 * {@link PasswordCreationException} with an embedded message about the
	 * invalid email address.
	 * 
	 * @throws PasswordCreationException
	 */
	@Test(expected = PasswordCreationException.class)
	public void testCreateAccountWithInvalidEmailAddress()
			throws PasswordCreationException {
		try {
			passwordStore.createPassword(INVALID_EMAIL_ADDRESS,
					TOO_WEAK_PASSWORD);
		} catch (PasswordCreationException e) {
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
	public void testActivateAccount() throws PasswordCreationException,
			PasswordActivationException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);

		Row account = readAccoutFromDatabase(EMAIL_ADDRESS);

		assertEquals(AccountState.CREATED,
				AccountState.valueOf(account.getString("state")));

		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);

		account = readAccoutFromDatabase(EMAIL_ADDRESS);
		assertEquals(AccountState.ACTIVE,
				AccountState.valueOf(account.getString("state")));
	}

	@Test(expected = PasswordActivationException.class)
	public void testActivateAccountWithInvalidActivationKey()
			throws PasswordCreationException, PasswordActivationException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);

		Row account = readAccoutFromDatabase(EMAIL_ADDRESS);

		assertEquals(AccountState.CREATED,
				AccountState.valueOf(account.getString("state")));

		try {
			passwordStore.activatePassword(EMAIL_ADDRESS, activationKey
					+ "Wrong!");
		} catch (PasswordActivationException e) {
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
	public void testAuthenticate() throws PasswordCreationException,
			PasswordActivationException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	}

	@Test
	public void testAuthenticateWrongEmail() throws PasswordCreationException,
			PasswordActivationException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
		assertFalse(passwordStore.authenticate(new EmailAddress(EMAIL_ADDRESS
				+ "Wrong"), VALID_PASSWORD));
	}

	@Test
	public void testAuthenticateWrongPassword()
			throws PasswordCreationException, PasswordActivationException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, new Password(
				VALID_PASSWORD.getPassword() + "Wrong!")));
	}

	@Test
	public void testAuthenticateWrongEmailAndPassword()
			throws PasswordCreationException, PasswordActivationException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
		assertFalse(passwordStore.authenticate(new EmailAddress(EMAIL_ADDRESS
				+ "Wrong"), new Password(VALID_PASSWORD.getPassword()
				+ "Wrong!")));
	}

	@Test
	public void testChangePassword() throws PasswordCreationException,
			PasswordActivationException, PasswordChangeException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		Password newPassword = new Password(VALID_PASSWORD.getPassword()
				+ "New!");
		assertTrue(passwordStore.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
				newPassword));
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, newPassword));
	}

	@Test
	public void testChangePasswordWrongEmail()
			throws PasswordCreationException, PasswordActivationException,
			PasswordChangeException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		Password newPassword = new Password(VALID_PASSWORD.getPassword()
				+ "New!");
		assertFalse(passwordStore.changePassword(new EmailAddress(EMAIL_ADDRESS
				+ "Wrong"), VALID_PASSWORD, newPassword));
	}

	@Test
	public void testChangePasswordWrongPassword()
			throws PasswordCreationException, PasswordActivationException,
			PasswordChangeException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		Password newPassword = new Password(VALID_PASSWORD.getPassword()
				+ "New!");
		assertFalse(passwordStore.changePassword(EMAIL_ADDRESS, new Password(
				VALID_PASSWORD.getPassword() + "Wrong!"), newPassword));
	}

	@Test(expected = PasswordChangeException.class)
	public void testChangePasswordTooWeakPassword()
			throws PasswordCreationException, PasswordActivationException,
			PasswordChangeException {
		try {
			String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
					VALID_PASSWORD);
			assertFalse(passwordStore.authenticate(EMAIL_ADDRESS,
					VALID_PASSWORD));
			passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
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
	public void testResetPassword() throws PasswordCreationException,
			PasswordActivationException, PasswordResetException {
		String activationKey = passwordStore.createPassword(EMAIL_ADDRESS,
				VALID_PASSWORD);
		assertFalse(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		passwordStore.activatePassword(EMAIL_ADDRESS, activationKey);
		assertTrue(passwordStore.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
		Password newPassword = passwordStore.resetPassword(EMAIL_ADDRESS);
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
