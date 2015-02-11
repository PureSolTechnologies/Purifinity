package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerException;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

public class AccountManagerBeanPasswordStoreIT extends
	AbstractAccountManagerServerTest {

    private static final EmailAddress EMAIL_ADDRESS = new EmailAddress(
	    "newaccount@puresol-technologies.com");

    private static final Password VALID_PASSWORD = new Password(
	    "IAmAPassword!:-)3");
    private static final Password TOO_WEAK_PASSWORD = new Password("123456");

    private PasswordStoreClient passwordStoreClient;

    @Inject
    private AccountManager accountManager;

    @Before
    public void setup() {
	passwordStoreClient = new PasswordStoreClient();
	assertNotNull(accountManager);
    }

    @After
    public void tearDown() {
	passwordStoreClient.close();
    }

    @Test
    public void testCreateAccount() throws AccountManagerException,
	    PasswordCreationException, PasswordActivationException {
	String activationKey = accountManager.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	accountManager.activatePassword(EMAIL_ADDRESS, activationKey);
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
    }

    /**
     * We create here two account with the same email address. We expect here an
     * AccountCreationException with an embedded message about the duplicate
     * email address.
     * 
     * @throws PasswordCreationException
     * @throws PasswordActivationException
     */
    @Test(expected = AccountManagerException.class)
    public void testCreateAccountDuplicateEmail()
	    throws AccountManagerException, PasswordCreationException,
	    PasswordActivationException {
	String activationKey = accountManager.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	accountManager.activatePassword(EMAIL_ADDRESS, activationKey);
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
	// now we expect an error...
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
    }

    /**
     * For a trival password we expect a {@link PasswordCreationException} here
     * with an embedded message about a too weak password.
     * 
     * @throws PasswordCreationException
     */
    @Test(expected = PasswordCreationException.class)
    public void testCreateAccountTooWeakPassword()
	    throws AccountManagerException, PasswordCreationException {
	accountManager.createPassword(EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
    }

    @Test
    public void testAuthenticate() throws AccountManagerException,
	    PasswordCreationException, PasswordActivationException {
	String activationKey = accountManager.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	accountManager.activatePassword(EMAIL_ADDRESS, activationKey);
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
	assertTrue(accountManager.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongPassword() throws AccountManagerException,
	    PasswordCreationException, PasswordActivationException {
	String activationKey = accountManager.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	accountManager.activatePassword(EMAIL_ADDRESS, activationKey);
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
	assertFalse(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		new Password(VALID_PASSWORD.getPassword() + "Wrong!")));
    }

    @Test
    public void testChangePassword() throws AccountManagerException,
	    PasswordChangeException, PasswordCreationException,
	    PasswordActivationException {
	String activationKey = accountManager.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	accountManager.activatePassword(EMAIL_ADDRESS, activationKey);
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	String newPassword = VALID_PASSWORD.getPassword() + "New!";
	assertTrue(accountManager.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
		new Password(newPassword)));
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		new Password(newPassword)));
    }

    @Test
    public void testChangePasswordWrongPassword()
	    throws AccountManagerException, PasswordChangeException,
	    PasswordCreationException, PasswordActivationException {
	String activationKey = accountManager.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	accountManager.activatePassword(EMAIL_ADDRESS, activationKey);
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	assertFalse(accountManager.changePassword(EMAIL_ADDRESS, new Password(
		VALID_PASSWORD.getPassword() + "Wrong!"), new Password(
		VALID_PASSWORD.getPassword() + "New!")));
    }

    @Test(expected = PasswordChangeException.class)
    public void testChangePasswordTooWeakPassword()
	    throws AccountManagerException, PasswordChangeException,
	    PasswordCreationException, PasswordActivationException {
	String activationKey = accountManager.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	accountManager.activatePassword(EMAIL_ADDRESS, activationKey);
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	accountManager.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
		TOO_WEAK_PASSWORD);
    }

    @Test
    public void testResetPassword() throws AccountManagerException,
	    PasswordResetException, PasswordCreationException,
	    PasswordActivationException {
	String activationKey = accountManager.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	accountManager.activatePassword(EMAIL_ADDRESS, activationKey);
	accountManager.createAccount(EMAIL_ADDRESS, "engineer");
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	Password newPassword = accountManager.resetPassword(EMAIL_ADDRESS);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, newPassword));
    }

    @Test(expected = PasswordResetException.class)
    public void testResetPasswordWrongEmail() throws PasswordResetException {
	accountManager.resetPassword(EMAIL_ADDRESS);
    }

}
