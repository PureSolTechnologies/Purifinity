package com.puresol.accountmanager.core.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Locale;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresol.accountmanager.core.api.AccountManager;
import com.puresol.accountmanager.core.api.AccountManagerRemote;
import com.puresol.accountmanager.domain.AccountManagerEvents;
import com.puresol.commons.utils.SupportedLocales;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@Stateful
@Local(AccountManager.class)
@Remote(AccountManagerRemote.class)
public class AccountManagerBean implements Serializable, AccountManager,
		AccountManagerRemote {

	private static final long serialVersionUID = 2254178680686589373L;

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	@Resource
	private SessionContext context;

	@Override
	public String getEmail() {
		Principal principal = context.getCallerPrincipal();
		return principal.getName();
	}

	@Override
	public String getName() {
		try {
			Principal principal = context.getCallerPrincipal();
			Method method = principal.getClass().getMethod("getRealName");
			Object name = method.invoke(principal);
			return name.toString();
		} catch (ClassCastException e) {
			e.printStackTrace();
			return "unauthenticated";
		} catch (SecurityException e) {
			e.printStackTrace();
			return "unauthenticated";
		} catch (NoSuchMethodException e) {
			return "unauthenticated";
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return "unauthenticated";
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return "unauthenticated";
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return "unauthenticated";
		}
	}

	@Override
	public boolean isLoggedIn() {
		try {
			Principal principal = context.getCallerPrincipal();
			Method method = principal.getClass().getMethod("getRealName");
			Object name = method.invoke(principal);
			return !(name.toString().isEmpty());
		} catch (ClassCastException e) {
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Locale[] getAvailableLocales() {
		return SupportedLocales.getLocales();
	}

	@Override
	public Locale getDefaultLocale() {
		return Locale.US;
	}

	@Override
	public void createAccount(long userId, String email, Locale locale) {
		logger.debug("Creating user account for '" + email + "'...");
		// FIXME
		// Person person = new Person(userId, "", "", Gender.FEMALE, "");
		// peopleRegister.addPerson(person);
		eventLogger.logEvent(AccountManagerEvents.createAccountCreationEvent(
				userId, email));
	}
}
