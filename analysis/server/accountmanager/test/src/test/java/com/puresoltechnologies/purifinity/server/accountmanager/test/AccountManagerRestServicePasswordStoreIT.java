package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
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
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

public class AccountManagerRestServicePasswordStoreIT extends AbstractAccountManagerClientTest {

    private static final String EMAIL_ADDRESS = "newaccount@puresol-technologies.com";
    private static final String INVALID_EMAIL_ADDRESS = "@puresol-technologies.com";

    private static final String VALID_PASSWORD = "IAmAPassword!:-)3";
    private static final String TOO_WEAK_PASSWORD = "123456";

    private static PasswordStoreClient passwordStoreClient;
    private static ResteasyClient client;
    private static ResteasyWebTarget webTarget;
    private AccountManagerRestInterface proxy;

    @BeforeClass
    public static void createRestClient() {
	passwordStoreClient = new PasswordStoreClient();

	client = new ResteasyClientBuilder().build();
	webTarget = client.target("http://localhost:8080/accountmanager/rest");
    }

    @AfterClass
    public static void destroyRestClient() {
	client.close();
	passwordStoreClient.close();
    }

    @Before
    public void setup() {
	proxy = webTarget.proxy(AccountManagerRestInterface.class);
    }

    @Test
    public void testCreateAccount() throws AccountManagerException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
    }

    /**
     * We create here two account with the same email address. We expect here an
     * AccountCreationException with an embedded message about the duplicate
     * email address.
     * 
     * @throws AccountManagerException
     *             is thrown in case of account issues.
     */
    @Test
    public void testCreateAccountDuplicateEmail() throws AccountManagerException {
	// the first account should be created normally...
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
	// now we expect an error...
	response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	int status = response.getStatus();
	response.close();
	assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, status);
    }

    /**
     * For a trival password we expect a {@link PasswordCreationException} here
     * with an embedded message about a too weak password.
     * 
     * @throws AccountManagerException
     *             is thrown in case of account issues.
     */
    @Test
    public void testCreateAccountTooWeakPassword() throws AccountManagerException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, TOO_WEAK_PASSWORD, "engineer"));
	int status = response.getStatus();
	response.close();
	assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, status);
    }

    /**
     * For an invalid email address we expect an
     * {@link PasswordCreationException} with an embedded message about the
     * invalid email address.
     * 
     * @throws AccountManagerException
     *             is thrown in case of account issues.
     */
    @Test
    public void testCreateAccountWithInvalidEmailAddress() throws AccountManagerException {
	Response response = proxy
		.createAccount(new CreateAccountEntity(INVALID_EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	int status = response.getStatus();
	response.close();
	assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, status);
    }

    @Test
    public void testAuthenticate() throws AccountManagerException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
    }

    @Test
    public void testAuthenticateWrongPassword() throws AccountManagerException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
	assertFalse(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS),
		new Password(VALID_PASSWORD + "Wrong!")));
    }

    @Test
    public void testChangePassword() throws AccountManagerException, PasswordChangeException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	String newPassword = VALID_PASSWORD + "New!";
	assertTrue(proxy.changePassword(EMAIL_ADDRESS, new ChangePasswordEntity(VALID_PASSWORD, newPassword)));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS), new Password(newPassword)));
    }

    @Test(expected = NotAcceptableException.class)
    public void testChangePasswordWrongEmail() throws AccountManagerException, PasswordChangeException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	assertFalse(proxy.changePassword(EMAIL_ADDRESS + "Wrong!",
		new ChangePasswordEntity(VALID_PASSWORD, VALID_PASSWORD + "New!")));
    }

    @Test
    public void testChangePasswordWrongPassword() throws AccountManagerException, PasswordChangeException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	assertFalse(proxy.changePassword(EMAIL_ADDRESS,
		new ChangePasswordEntity(VALID_PASSWORD + "Wrong!", VALID_PASSWORD + "New!")));
    }

    @Test(expected = NotAcceptableException.class)
    public void testChangePasswordTooWeakPassword() throws AccountManagerException, PasswordChangeException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	proxy.changePassword(EMAIL_ADDRESS, new ChangePasswordEntity(VALID_PASSWORD, TOO_WEAK_PASSWORD));
    }

    @Test
    public void testResetPassword() throws AccountManagerException, PasswordResetException {
	Response response = proxy.createAccount(new CreateAccountEntity(EMAIL_ADDRESS, VALID_PASSWORD, "engineer"));
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	response.close();
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS), new Password(VALID_PASSWORD)));
	Password newPassword = new Password(proxy.resetPassword(EMAIL_ADDRESS));
	assertTrue(passwordStoreClient.authenticate(new EmailAddress(EMAIL_ADDRESS), newPassword));
    }

    @Test(expected = NotAcceptableException.class)
    public void testResetPasswordWrongEmail() throws PasswordResetException {
	proxy.resetPassword(EMAIL_ADDRESS);
    }

}
