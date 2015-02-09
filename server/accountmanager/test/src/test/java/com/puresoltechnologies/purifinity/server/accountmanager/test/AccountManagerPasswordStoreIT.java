package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerException;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.ChangePasswordEntity;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.CreateAccountEntity;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreEvents;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

public class AccountManagerPasswordStoreIT extends
	AbstractAccountManagerClientTest {

    private static final String EMAIL_ADDRESS = "newaccount@puresol-technologies.com";
    private static final String INVALID_EMAIL_ADDRESS = "@puresol-technologies.com";

    private static final String VALID_PASSWORD = "IAmAPassword!:-)3";
    private static final String TOO_WEAK_PASSWORD = "123456";

    private static PasswordStoreClient passwordStoreClient;
    private static ResteasyClient client;
    private static AccountManagerRestInterface proxy;

    @BeforeClass
    public static void createRestClient() {
	passwordStoreClient = new PasswordStoreClient();

	client = new ResteasyClientBuilder().build();
	ResteasyWebTarget webTarget = client
		.target("http://localhost:8080/accountmanager/rest");
	proxy = webTarget.proxy(AccountManagerRestInterface.class);
    }

    @AfterClass
    public static void destroyRestClient() {
	client.close();
	passwordStoreClient.close();
    }

    @Before
    public void setup() {
	assertNotNull(proxy);
    }

    @Test
    public void testCreateAccount() throws AccountManagerException {
	proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		VALID_PASSWORD, "engineer"));
    }

    /**
     * We create here two account with the same email address. We expect here an
     * AccountCreationException with an embedded message about the duplicate
     * email address.
     * 
     * @throws PasswordCreationException
     */
    @Test
    public void testCreateAccountDuplicateEmail()
	    throws AccountManagerException {
	// the first account should be created normally...
	proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		VALID_PASSWORD, "engineer"));
	try {
	    // now we expect an error...
	    proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		    VALID_PASSWORD, "engineer"));
	} catch (AccountManagerException e) {
	    assertEquals(
		    PasswordStoreEvents.createAccountAlreadyExistsErrorEvent(
			    new EmailAddress(EMAIL_ADDRESS)).getMessage(),
		    e.getMessage());
	    throw e;
	}
    }

    /**
     * For a trival password we expect a {@link PasswordCreationException} here
     * with an embedded message about a too weak password.
     * 
     * @throws PasswordCreationException
     */
    @Test
    public void testCreateAccountTooWeakPassword()
	    throws AccountManagerException {
	try {
	    proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		    TOO_WEAK_PASSWORD, "engineer"));
	} catch (AccountManagerException e) {
	    assertEquals(
		    PasswordStoreEvents.createPasswordTooWeakErrorEvent(
			    new EmailAddress(EMAIL_ADDRESS)).getMessage(),
		    e.getMessage());
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
    @Test
    public void testCreateAccountWithInvalidEmailAddress()
	    throws AccountManagerException {
	try {
	    proxy.createAccount(new CreateAccountEntity(INVALID_EMAIL_ADDRESS,
		    VALID_PASSWORD, "engineer"));
	} catch (AccountManagerException e) {
	    assertEquals(
		    PasswordStoreEvents.createInvalidEmailAddressErrorEvent(
			    new EmailAddress(INVALID_EMAIL_ADDRESS))
			    .getMessage(), e.getMessage());
	    throw e;
	}
    }

    @Test
    public void testAuthenticate() throws AccountManagerException {
	proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		VALID_PASSWORD, "engineer"));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
    }

    @Test
    public void testAuthenticateWrongPassword() throws AccountManagerException {
	proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		VALID_PASSWORD, "engineer"));
	assertFalse(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS), new Password(VALID_PASSWORD + "Wrong!")));
    }

    @Test
    public void testChangePassword() throws AccountManagerException,
	    PasswordChangeException {
	proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		VALID_PASSWORD, "engineer"));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	String newPassword = VALID_PASSWORD + "New!";
	assertTrue(proxy.changePassword(EMAIL_ADDRESS,
		new ChangePasswordEntity(VALID_PASSWORD, newPassword)));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS), new Password(newPassword)));
    }

    @Test
    public void testChangePasswordWrongEmail() throws AccountManagerException,
	    PasswordChangeException {
	proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		VALID_PASSWORD, "engineer"));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	assertFalse(proxy.changePassword(EMAIL_ADDRESS + "Wrong!",
		new ChangePasswordEntity(VALID_PASSWORD, VALID_PASSWORD
			+ "New!")));
    }

    @Test
    public void testChangePasswordWrongPassword()
	    throws AccountManagerException, PasswordChangeException {
	proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		VALID_PASSWORD, "engineer"));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	assertFalse(proxy.changePassword(EMAIL_ADDRESS,
		new ChangePasswordEntity(VALID_PASSWORD + "Wrong!",
			VALID_PASSWORD + "New!")));
    }

    @Test
    public void testChangePasswordTooWeakPassword()
	    throws AccountManagerException, PasswordChangeException {
	try {
	    proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		    VALID_PASSWORD, "engineer"));
	    assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		    EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	    proxy.changePassword(EMAIL_ADDRESS, new ChangePasswordEntity(
		    VALID_PASSWORD, TOO_WEAK_PASSWORD));
	} catch (PasswordChangeException e) {
	    assertEquals(
		    PasswordStoreEvents
			    .createPasswordChangeFailedPasswordTooWeakEvent(
				    new EmailAddress(EMAIL_ADDRESS))
			    .getMessage(), e.getMessage());
	    assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		    EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	    throw e;
	}
    }

    @Test
    public void testResetPassword() throws AccountManagerException,
	    PasswordResetException {
	proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS,
		VALID_PASSWORD, "engineer"));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	Password newPassword = new Password(proxy.resetPassword(EMAIL_ADDRESS));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS), newPassword));
    }

    @Test
    public void testResetPasswordWrongEmail() throws PasswordResetException {
	try {
	    proxy.resetPassword(EMAIL_ADDRESS);
	} catch (PasswordResetException e) {
	    assertEquals(
		    PasswordStoreEvents
			    .createPasswordResetFailedUnknownAccountEvent(
				    new EmailAddress(EMAIL_ADDRESS))
			    .getMessage(), e.getMessage());
	    throw e;
	}
    }

}
