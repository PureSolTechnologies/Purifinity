package com.puresoltechnologies.purifinity.server.passwordstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.NotAcceptableException;

import org.junit.Before;
import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.database.cassandra.CassandraClusterHelper;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreEvents;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;

public class PasswordStoreClientIT extends AbstractPasswordStoreClientTest {

    private static final EmailAddress EMAIL_ADDRESS = new EmailAddress(
	    "newaccount@puresol-technologies.com");
    private static final Password VALID_PASSWORD = new Password(
	    "IAmAPassword!:-)3");
    private static final Password TOO_WEAK_PASSWORD = new Password("123456");

    private final PasswordStoreClient restClient = new PasswordStoreClient();

    @Before
    public void setup() {
	assertNotNull(restClient);
	try (Cluster cluster = CassandraClusterHelper.connect()) {
	    PasswordStoreTester.cleanupDatabase(cluster);
	}
    }

    @Test
    public void testCreateAccount() throws PasswordCreationException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
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
    @Test(expected = NotAcceptableException.class)
    public void testCreateAccountDuplicateEmail()
	    throws PasswordCreationException {
	// the first account should be created normally...
	restClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
	// now we expect an error...
	restClient.createPassword(EMAIL_ADDRESS, VALID_PASSWORD);
    }

    /**
     * For a trival password we expect a {@link PasswordCreationException} here
     * with an embedded message about a too weak password.
     * 
     * @throws PasswordCreationException
     */
    @Test(expected = NotAcceptableException.class)
    public void testCreateAccountTooWeakPassword()
	    throws PasswordCreationException {
	try {
	    restClient.createPassword(EMAIL_ADDRESS, TOO_WEAK_PASSWORD);
	} catch (PasswordCreationException e) {
	    assertEquals(
		    PasswordStoreEvents.createPasswordTooWeakErrorEvent(
			    EMAIL_ADDRESS).getMessage(), e.getMessage());
	    throw e;
	}
    }

    @Test
    public void testActivateAccount() throws PasswordActivationException,
	    PasswordCreationException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		new Password("12dqwec1241`S@#R~"));
	assertNotNull(activationKey);
	assertFalse(activationKey.isEmpty());
	assertEquals(EMAIL_ADDRESS,
		restClient.activatePassword(EMAIL_ADDRESS, activationKey));
    }

    @Test(expected = NotAcceptableException.class)
    public void testActivateAccountWithInvalidActivationKey()
	    throws PasswordCreationException, PasswordActivationException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);

	try {
	    restClient
		    .activatePassword(EMAIL_ADDRESS, activationKey + "Wrong!");
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
	String activationInformation = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	restClient.activatePassword(EMAIL_ADDRESS, activationInformation);
	assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongEmail() throws PasswordCreationException,
	    PasswordActivationException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	restClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertFalse(restClient.authenticate(new EmailAddress(EMAIL_ADDRESS
		+ "Wrong"), VALID_PASSWORD));
    }

    @Test
    public void testAuthenticateWrongPassword()
	    throws PasswordCreationException, PasswordActivationException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	restClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertFalse(restClient.authenticate(EMAIL_ADDRESS, new Password(
		VALID_PASSWORD + "Wrong!")));
    }

    @Test
    public void testAuthenticateWrongEmailAndPassword()
	    throws PasswordCreationException, PasswordActivationException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	restClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertFalse(restClient.authenticate(new EmailAddress(EMAIL_ADDRESS
		+ "Wrong"), new Password(VALID_PASSWORD + "Wrong!")));
    }

    @Test
    public void testChangePassword() throws PasswordCreationException,
	    PasswordActivationException, PasswordChangeException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	restClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	assertTrue(restClient.changePassword(EMAIL_ADDRESS, VALID_PASSWORD,
		new Password(VALID_PASSWORD.getPassword() + "New!")));
	assertTrue(restClient.authenticate(EMAIL_ADDRESS, new Password(
		VALID_PASSWORD.getPassword() + "New!")));
    }

    @Test
    public void testChangePasswordWrongEmail()
	    throws PasswordCreationException, PasswordActivationException,
	    PasswordChangeException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	restClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	assertFalse(restClient.changePassword(new EmailAddress(EMAIL_ADDRESS
		+ "Wrong"), VALID_PASSWORD, new Password(VALID_PASSWORD
		+ "New!")));
    }

    @Test
    public void testChangePasswordWrongPassword()
	    throws PasswordCreationException, PasswordActivationException,
	    PasswordChangeException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	restClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	assertFalse(restClient.changePassword(EMAIL_ADDRESS, new Password(
		VALID_PASSWORD + "Wrong!"), new Password(VALID_PASSWORD
		+ "New!")));
    }

    @Test(expected = NotAcceptableException.class)
    public void testChangePasswordTooWeakPassword()
	    throws PasswordCreationException, PasswordActivationException,
	    PasswordChangeException {
	try {
	    String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		    VALID_PASSWORD);
	    restClient.activatePassword(EMAIL_ADDRESS, activationKey);
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
    public void testResetPassword() throws PasswordCreationException,
	    PasswordActivationException, PasswordResetException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	restClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	Password newPassword = restClient.resetPassword(EMAIL_ADDRESS);
	assertTrue(restClient.authenticate(EMAIL_ADDRESS, newPassword));
    }

    @Test(expected = NotAcceptableException.class)
    public void testResetPasswordWrongEmail() throws PasswordResetException {
	restClient.resetPassword(EMAIL_ADDRESS);
    }

    @Test
    public void testRemovePassword() throws PasswordCreationException,
	    PasswordActivationException {
	String activationKey = restClient.createPassword(EMAIL_ADDRESS,
		VALID_PASSWORD);
	assertFalse(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
	restClient.activatePassword(EMAIL_ADDRESS, activationKey);
	assertTrue(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));

	restClient.removePassword(EMAIL_ADDRESS);
	assertFalse(restClient.authenticate(EMAIL_ADDRESS, VALID_PASSWORD));
    }
}
