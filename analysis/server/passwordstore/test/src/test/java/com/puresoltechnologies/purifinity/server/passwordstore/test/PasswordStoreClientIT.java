package com.puresoltechnologies.purifinity.server.passwordstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;

public class PasswordStoreClientIT extends AbstractPasswordStoreClientTest {

    private static final EmailAddress EMAIL_ADDRESS = new EmailAddress("newaccount@puresol-technologies.com");
    private static final Password VALID_PASSWORD = new Password("IAmAPassword!:-)3");
    private static final Password TOO_WEAK_PASSWORD = new Password("123456");

    private PasswordStoreClient passwordStoreClient;

    @Before
    public void setup() throws SQLException {
	passwordStoreClient = new PasswordStoreClient();
	PasswordStoreTester.cleanupDatabase();
    }

    @After
    public void destroy() {
	passwordStoreClient.close();
    }

    @Test
    public void testCreateAccount() throws PasswordCreationException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	assertNotNull(activationKey);
	assertFalse(activationKey.isEmpty());
    }

    /**
     * We create here two account with the same email address. We expect here an
     * AccountCreationException with an embedded message about the duplicate
     * email address.
     * 
     * @throws PasswordCreationException
     *             is thrown in case of password creation issues.
     */
    @Test(expected = PasswordCreationException.class)
    public void testCreateAccountDuplicateEmail() throws PasswordCreationException {
	// the first account should be created normally...
	passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	// now we expect an error...
	passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
    }

    /**
     * For a trival password we expect a {@link PasswordCreationException} here
     * with an embedded message about a too weak password.
     * 
     * @throws PasswordCreationException
     *             is thrown in case of password creation issues.
     */
    @Test(expected = PasswordCreationException.class)
    public void testCreateAccountTooWeakPassword() throws PasswordCreationException {
	passwordStoreClient.createPassword(EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
    }

    @Test
    public void testActivateAccount() throws PasswordActivationException, PasswordCreationException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, new Password("12dqwec1241`S@#R~"));
	assertNotNull(activationKey);
	assertFalse(activationKey.isEmpty());
	assertEquals(EMAIL_ADDRESS, passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey));
    }

    @Test(expected = PasswordActivationException.class)
    public void testActivateAccountWithInvalidActivationKey()
	    throws PasswordCreationException, PasswordActivationException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey + "Wrong!");
    }

    @Test
    public void testAuthenticate() throws PasswordCreationException, PasswordActivationException {
	String activationInformation = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationInformation);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongEmail() throws PasswordCreationException, PasswordActivationException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS + "Wrong"), VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongPassword() throws PasswordCreationException, PasswordActivationException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(EMAIL_ADDRESS, new Password(VALID_PASSWORD + "Wrong!")));
    }

    @Test
    public void testAuthenticateWrongEmailAndPassword() throws PasswordCreationException, PasswordActivationException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS + "Wrong"),
		new Password(VALID_PASSWORD + "Wrong!")));
    }

    @Test
    public void testChangePassword()
	    throws PasswordCreationException, PasswordActivationException, PasswordChangeException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	assertTrue(passwordStoreClient.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
		new Password(VALID_PASSWORD.getPassword() + "New!")));
	assertTrue(
		passwordStoreClient.authenticate(EMAIL_ADDRESS, new Password(VALID_PASSWORD.getPassword() + "New!")));
    }

    @Test
    public void testChangePasswordWrongEmail()
	    throws PasswordCreationException, PasswordActivationException, PasswordChangeException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	assertFalse(passwordStoreClient.changePassword(new EmailAddress(EMAIL_ADDRESS + "Wrong"), VALID_PASSWORD,
		new Password(VALID_PASSWORD + "New!")));
    }

    @Test
    public void testChangePasswordWrongPassword()
	    throws PasswordCreationException, PasswordActivationException, PasswordChangeException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	assertFalse(passwordStoreClient.changePassword(EMAIL_ADDRESS, new Password(VALID_PASSWORD + "Wrong!"),
		new Password(VALID_PASSWORD + "New!")));
    }

    @Test(expected = PasswordChangeException.class)
    public void testChangePasswordTooWeakPassword()
	    throws PasswordCreationException, PasswordActivationException, PasswordChangeException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	passwordStoreClient.changePassword(EMAIL_ADDRESS, VALID_PASSWORD, TOO_WEAK_PASSWORD);
    }

    @Test
    public void testResetPassword()
	    throws PasswordCreationException, PasswordActivationException, PasswordResetException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	Password newPassword = passwordStoreClient.resetPassword(EMAIL_ADDRESS);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, newPassword));
    }

    @Test(expected = PasswordResetException.class)
    public void testResetPasswordWrongEmail() throws PasswordResetException {
	passwordStoreClient.resetPassword(EMAIL_ADDRESS);
    }

    @Test
    public void testRemovePassword() throws PasswordCreationException, PasswordActivationException {
	String activationKey = passwordStoreClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	assertFalse(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	passwordStoreClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));

	passwordStoreClient.deletePassword(EMAIL_ADDRESS);
	assertFalse(passwordStoreClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
    }
}
