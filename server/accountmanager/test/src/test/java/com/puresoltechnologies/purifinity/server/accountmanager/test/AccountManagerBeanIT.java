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

import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountCreationException;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Ignore
public class AccountManagerBeanIT extends AbstractAccountManagerServerTest {

    private static final String EMAIL = "ludwig@puresol-technologies.com";
    private static final String PASSWORD = "!234Qwer";

    @Inject
    private AccountManager accountManager;

    @Before
    public void setup() throws AccountCreationException,
	    AccountActivationException {
	PasswordStoreClient passwordStoreClient = new PasswordStoreClient();
	String activationKey = passwordStoreClient.createAccount(EMAIL,
		PASSWORD);
	passwordStoreClient.activateAccount(EMAIL, activationKey);

	assertNotNull(accountManager);
	cleanupPasswordStoreDatabase();
    }

    @Test
    public void testGetName() throws LoginException {
	LoginContext loginContext = new LoginContext("passwordstore",
		new CallbackHandler() {

		    @Override
		    public void handle(Callback[] callbacks)
			    throws IOException, UnsupportedCallbackException {
			for (Callback callback : callbacks) {
			    if (callback instanceof NameCallback) {
				((NameCallback) callback).setName(EMAIL);
			    } else if (callback instanceof PasswordCallback) {
				((PasswordCallback) callback)
					.setPassword(PASSWORD.toCharArray());
			    } else {
				throw new UnsupportedCallbackException(
					callback, "Callback is not supported!");
			    }
			}
		    }
		});
	loginContext.login();
	AccountManager accountManager = JndiUtils
		.createRemoteEJBInstance(
			AccountManager.class,
			"java:app/accountmanager.core.impl/AccountManagerBean!com.puresol.accountmanager.core.api.AccountManager");
	assertEquals(EMAIL, accountManager.getEmail());
	loginContext.logout();
    }

    @Test
    public void testCreateEmptyAccount() {
	String email = "email@address.com";
	accountManager.createAccount(email);
    }
}
