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

import com.puresoltechnologies.commons.misc.IntrospectionUtilities;
import com.puresoltechnologies.commons.misc.types.EmailAddress;
import com.puresoltechnologies.commons.misc.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.AccountManagerRestInterface;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreEvents;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

public class AccountManagerPasswordStoreIT extends
	AbstractAccountManagerClientTest {

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
    public void testCreateAccount() throws PasswordCreationException {
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
     * @throws PasswordCreationException
     */
    @Test
    public void testCreateAccountDuplicateEmail()
	    throws PasswordCreationException {
	// the first account should be created normally...
	proxy.createAccount(EMAIL_ADDRESS, VALID_PASSWORD);
	try {
	    // now we expect an error...
	    proxy.createAccount(EMAIL_ADDRESS, VALID_PASSWORD);
	} catch (PasswordCreationException e) {
	    assertEquals(PasswordStoreEvents
		    .createAccountAlreadyExistsErrorEvent(EMAIL_ADDRESS)
		    .getMessage(), e.getMessage());
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
	    throws PasswordCreationException {
	try {
	    proxy.createAccount(EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
	} catch (PasswordCreationException e) {
	    assertEquals(
		    PasswordStoreEvents.createPasswordTooWeakErrorEvent(
			    EMAIL_ADDRESS).getMessage(), e.getMessage());
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
	    throws PasswordCreationException {
	try {
	    proxy.createAccount(INVALID_EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
	} catch (PasswordCreationException e) {
	    assertEquals(PasswordStoreEvents
		    .createInvalidEmailAddressErrorEvent(INVALID_EMAIL_ADDRESS)
		    .getMessage(), e.getMessage());
	    throw e;
	}
    }

    @Test
    public void testActivateAccount() throws PasswordActivationException,
	    PasswordCreationException {
	EmailAddress email = new EmailAddress("ludwig@puresol-technologies.com");
	Password password = new Password("12dqwec1241`S@#R~");
	String activationKey = proxy.createAccount(email, password);
	assertNotNull(activationKey);
	assertFalse(activationKey.isEmpty());
	proxy.activateAccount(email, activationKey);
    }

    @Test
    public void testActivateAccountWithInvalidActivationKey()
	    throws PasswordCreationException, PasswordActivationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);

	try {
	    proxy.activateAccount(EMAIL_ADDRESS, activationKey + "Wrong!");
	} catch (PasswordActivationException e) {
	    assertEquals(PasswordStoreEvents
		    .createInvalidActivationKeyErrorEvent(EMAIL_ADDRESS)
		    .getMessage(), e.getMessage());
	    throw e;
	}
    }

    @Test
    public void testAuthenticate() throws PasswordCreationException,
	    PasswordActivationException {
	String activationInformation = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationInformation);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongEmail() throws PasswordCreationException,
	    PasswordActivationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(
		new EmailAddress("Wrong!"), VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongPassword()
	    throws PasswordCreationException, PasswordActivationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		new Password(VALID_PASSWORD + "Wrong!")));
    }

    @Test
    public void testAuthenticateWrongEmailAndPassword()
	    throws PasswordCreationException, PasswordActivationException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertFalse(passwordStoreClient.authenticate(new EmailAddress(
		EMAIL_ADDRESS + "Wrong!"), new Password(VALID_PASSWORD
		+ "Wrong!")));
    }

    @Test
    public void testChangePassword() throws PasswordCreationException,
	    PasswordActivationException, PasswordChangeException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	Password newPassword = new Password(VALID_PASSWORD + "New!");
	assertTrue(proxy.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
		newPassword));
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS, newPassword));
    }

    @Test
    public void testChangePasswordWrongEmail()
	    throws PasswordCreationException, PasswordActivationException,
	    PasswordChangeException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	assertFalse(proxy.changePassword(new EmailAddress(EMAIL_ADDRESS
		+ "Wrong!"), VALID_PASSWORD, new Password(VALID_PASSWORD
		+ "New!")));
    }

    @Test
    public void testChangePasswordWrongPassword()
	    throws PasswordCreationException, PasswordActivationException,
	    PasswordChangeException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	assertFalse(proxy.changePassword(EMAIL_ADDRESS, new Password(
		VALID_PASSWORD + "Wrong!"), new Password(VALID_PASSWORD
		+ "New!")));
    }

    @Test
    public void testChangePasswordTooWeakPassword()
	    throws PasswordCreationException, PasswordActivationException,
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
    public void testResetPassword() throws PasswordCreationException,
	    PasswordActivationException, PasswordResetException {
	String activationKey = proxy.createAccount(EMAIL_ADDRESS,
		VALID_PASSWORD);
	proxy.activateAccount(EMAIL_ADDRESS, activationKey);
	assertTrue(passwordStoreClient.authenticate(EMAIL_ADDRESS,
		VALID_PASSWORD));
	Password newPassword = proxy.resetPassword(EMAIL_ADDRESS);
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
