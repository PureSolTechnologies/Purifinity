package com.puresol.ua;

import java.security.Principal;
import java.util.Set;
import java.util.TreeSet;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;

/**
 * This UA module is for JAAS usage. JAAS framework is used completely. The
 * configuration is done in "/config/JAAS.conf".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JAAS implements UA {

	private static final Logger logger = Logger.getLogger(JAAS.class);

	private String context = "";
	private LoginContext loginContext = null;

	protected JAAS(String configFile) {
		System.setProperty("java.security.auth.login.config", configFile);
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

	@Override
	public Subject getSubject() {
		return loginContext.getSubject();
	}

	@Override
	public Set<Principal> getPrincipals() {
		return loginContext.getSubject().getPrincipals();
	}

	@Override
	public Set<Principal> getPrincipals(Class<? extends Principal> principal) {
		Set<Principal> principals = new TreeSet<Principal>();
		for (Principal p : loginContext.getSubject().getPrincipals()) {
			if (p.getClass().equals(principal)) {
				principals.add(p);
			}
		}
		return principals;
	}

	@Override
	public SubjectInformation getInformation() {
		for (Principal principal : getPrincipals()) {
			if (principal.getClass().equals(SubjectInformation.class)) {
				return (SubjectInformation) principal;
			}
		}
		return null;
	}
}
