package com.puresol.accountmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.junit.Before;
import org.junit.Test;

import com.puresol.accountmanager.core.api.AccountManager;
import com.puresol.passwordstore.client.PasswordStoreClient;
import com.puresol.passwordstore.domain.AccountActivationException;
import com.puresol.passwordstore.domain.AccountCreationException;

public class AccountManagerBeanIT extends AbstractAccountManagerTest {

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
		try {
			InitialContext initialContext = new InitialContext();
			AccountManager accountManager = (AccountManager) initialContext
					.lookup("java:app/accountmanager.core.impl/AccountManagerBean!com.puresol.accountmanager.core.api.AccountManager");
			assertEquals(EMAIL, accountManager.getEmail());
		} catch (NamingException e) {
			fail("Could not lookup AccountManager with message '"
					+ e.getMessage() + "'!");
		}
		loginContext.logout();
	}

	@Test
	public void testCreateEmptyAccount() {
		String email = "email@address.com";
		accountManager.createAccount(email);
	}
}
