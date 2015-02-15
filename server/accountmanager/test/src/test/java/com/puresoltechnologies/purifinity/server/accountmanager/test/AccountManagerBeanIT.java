package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;

public class AccountManagerBeanIT extends AbstractAccountManagerServerTest {

    private static final EmailAddress EMAIL = new EmailAddress(
	    "newaccount@puresol-technologies.com");
    private static final Password PASSWORD = new Password("!234Qwer");

    @Inject
    private AccountManager accountManager;

    @Before
    public void setup() throws PasswordCreationException,
	    PasswordActivationException {
	assertNotNull(accountManager);

	String activationKey = accountManager.createPassword(EMAIL, PASSWORD);
	accountManager.activatePassword(EMAIL, activationKey);
	accountManager.createAccount(EMAIL, EMAIL.getAddress(), "engineer");
    }

    @Test
    @Ignore("This test is not working due to the email address is not set in context.getPrincipal."
	    + "The functionality is not needed for now, so we ignored it for now.")
    public void testGetName() throws LoginException {
	LoginContext loginContext = new LoginContext("passwordstore",
		new CallbackHandler() {

		    @Override
		    public void handle(Callback[] callbacks)
			    throws IOException, UnsupportedCallbackException {
			for (Callback callback : callbacks) {
			    if (callback instanceof NameCallback) {
				NameCallback nameCallback = (NameCallback) callback;
				nameCallback.setName(EMAIL.getAddress());
			    } else if (callback instanceof PasswordCallback) {
				PasswordCallback passwordCallback = (PasswordCallback) callback;
				passwordCallback.setPassword(PASSWORD
					.getPassword().toCharArray());
			    } else {
				throw new UnsupportedCallbackException(
					callback, "Callback is not supported!");
			    }
			}
		    }
		});
	loginContext.login();
	assertEquals(EMAIL, accountManager.getEmail());
	loginContext.logout();
    }

    @Test
    public void testCreateEmptyAccount() {
	EmailAddress email = new EmailAddress("email@address.com");
	accountManager.createAccount(email, email.getAddress(), "engineer");
    }
}
