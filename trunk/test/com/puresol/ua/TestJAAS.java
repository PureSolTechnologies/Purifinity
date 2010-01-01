package com.puresol.ua;

import java.security.Principal;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;

public class TestJAAS implements UA {

	private static final Logger logger = Logger.getLogger(JAAS.class);

	private String context = "";
	private LoginContext loginContext = null;

	public TestJAAS() {
		System.setProperty("java.security.auth.login.config", getClass()
				.getResource("/config/JAASTest.conf").toString());
	}

	@Override
	public boolean login(String context) {
		try {
			login(context, new PasswordDialogCallbackHandler());
			return true;
		} catch (LoginException e) {
			logger.warn(e.getMessage(), e);
		}
		return false;
	}

	@Override
	public boolean login(String context, String user, String password) {
		try {
			login(context, new DefaultCallbackHandler(user, password));
			return true;
		} catch (LoginException e) {
			logger.warn(e.getMessage(), e);
		}
		return false;
	}

	private void login(String context, CallbackHandler callbackHandler)
			throws LoginException {
		this.context = context;
		loginContext = new LoginContext(context, callbackHandler);
		loginContext.login();

		Set<Principal> principals = loginContext.getSubject().getPrincipals();
		for (Principal principal : principals) {
			System.out.println(principal);
		}
	}

	@Override
	public boolean logout() {
		try {
			loginContext.logout();
			loginContext = null;
			return true;
		} catch (LoginException e) {
			logger.error(e.getMessage(), e);
		}
		loginContext = null;
		return false;
	}

	@Override
	public boolean isLoggedIn() {
		return (loginContext != null);
	}

	@Override
	public String getContext() {
		return context;
	}

	public Subject getSubject() {
		return loginContext.getSubject();
	}
}
