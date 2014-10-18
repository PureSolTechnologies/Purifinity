package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreEvents;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

public class AccountManagerPasswordStoreIT extends
	AbstractAccountManagerClientTest {

    private static final String EMAIL_ADDRESS = "ludwig@puresol-technologies.com";
    private static final String INVALID_EMAIL_ADDRESS = "@puresol-technologies.com";
    private static final String VALID_PASSWORD = "IAmAPassword!:-)3";
    private static final String TOO_WEAK_PASSWORD = "123456";

    private static final PasswordStoreClient passwordStoreClient = new PasswordStoreClient();
    private static AccountManagerRestInterface proxy = null;

    @BeforeClass
    public static void createRestClient() {
	ResteasyClient client = new ResteasyClientBuilder().build();
	ResteasyWebTarget webTarget = client
		.target("http://localhost:8080/accountmanager/rest");
	proxy = webTarget.proxy(AccountManagerRestInterface.class);
    }

    @Before
    public void setup() throws SQLException, IOException {
	assertNotNull(proxy);
	cleanupPasswordStoreDatabase();
    }

    @Test
    public void testCreateAccount() throws AccountCreationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
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
    @Test
    public void testCreateAccountDuplicateEmail()
	    throws AccountCreationException {
	// the first account should be created normally...
	proxy.createAccount(EMAIL_ADDRESS, VALID_PASSWORD);
	try {
	    // now we expect an error...
	    proxy.createAccount(EMAIL_ADDRESS, VALID_PASSWORD);
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
    @Test
    public void testCreateAccountTooWeakPassword()
	    throws AccountCreationException {
	try {
	    proxy.createAccount(EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
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
    @Test
    public void testCreateAccountWithInvalidEmailAddress()
	    throws AccountCreationException {
	try {
	    proxy.createAccount(INVALID_EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
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
	String activationKey = proxy.createAccount(
		"ludwig@puresol-technologies.com", "12dqwec1241`S@#R~");
	assertNotNull(activationKey);
	assertFalse(activationKey.isEmpty());
	proxy.activateAccount("ludwig@puresol-technologies.com", activationKey);
    }

    @Test
    public void testActivateAccountWithInvalidActivationKey()
	    throws AccountCreationException, AccountActivationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);

	try {
	    proxy.activateAccount(EMAIL_ADDRESS, activationKey + "Wrong!");
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
	String activationInformation = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationInformation);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongEmail() throws AccountCreationException,
	    AccountActivationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(EMAIL_ADDRESS + "Wrong!",
		VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongPassword()
	    throws AccountCreationException, AccountActivationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD + "Wrong!"));
    }

    @Test
    public void testAuthenticateWrongEmailAndPassword()
	    throws AccountCreationException, AccountActivationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(EMAIL_ADDRESS + "Wrong!",
		VALID_PASSWORD + "Wrong!"));
    }

    @Test
    public void testChangePassword() throws AccountCreationException,
	    AccountActivationException, PasswordChangeException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	assertTrue(proxy.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
		VALID_PASSWORD + "New!"));
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD + "New!"));
    }

    @Test
    public void testChangePasswordWrongEmail() throws AccountCreationException,
	    AccountActivationException, PasswordChangeException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	assertFalse(proxy.changePassword(EMAIL_ADDRESS + "Wrong!",
		VALID_PASSWORD, VALID_PASSWORD + "New!"));
    }

    @Test
    public void testChangePasswordWrongPassword()
	    throws AccountCreationException, AccountActivationException,
	    PasswordChangeException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	assertFalse(proxy.changePassword(EMAIL_ADDRESS, VALID_PASSWORD
		+ "Wrong!", VALID_PASSWORD + "New!"));
    }

    @Test
    public void testChangePasswordTooWeakPassword()
	    throws AccountCreationException, AccountActivationException,
	    PasswordChangeException {
	try {
	    String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		    VALID_PASSWORD);
	    proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	    assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		    VALID_PASSWORD));
	    proxy.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
		    TOO_WEAK_PASSWORD);
	} catch (PasswordChangeException e) {
	    assertEquals(
		    PasswordStoreEvents
			    .createPasswordChangeFailedPasswordTooWeakEvent(
				    EMAIL_ADDRESS).getMessage(), e.getMessage());
	    assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		    VALID_PASSWORD));
	    throw e;
	}
    }

    @Test
    public void testResetPassword() throws AccountCreationException,
	    AccountActivationException, PasswordResetException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	String newPassword = proxy.resetPassword(EMAIL_ADDRESS);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, newPassword));
    }

    @Test
    public void testResetPasswordWrongEmail() throws PasswordResetException {
	try {
	    proxy.resetPassword(EMAIL_ADDRESS);
	} catch (PasswordResetException e) {
	    assertEquals(
		    PasswordStoreEvents
			    .createPasswordResetFailedUnknownAccountEvent(
				    EMAIL_ADDRESS).getMessage(), e.getMessage());
	    throw e;
	}
    }

}
