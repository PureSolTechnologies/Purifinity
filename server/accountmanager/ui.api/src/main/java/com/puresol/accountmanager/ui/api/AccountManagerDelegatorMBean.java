package com.puresol.accountmanager.ui.api;

import java.io.Serializable;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.puresol.accountmanager.core.api.AccountManagerRemote;

@ManagedBean(name = "accountManagerDelegatorMBean")
@SessionScoped
public class AccountManagerDelegatorMBean implements Serializable {

	private static final long serialVersionUID = 4724997649418867363L;

	@EJB(lookup = "java:global/accountmanager.app/accountmanager.core.impl/AccountManagerBean!com.puresol.accountmanager.core.api.AccountManagerRemote")
	private AccountManagerRemote accountManager;

	public Object getName() {
		return accountManager.getName();
	}

	public boolean isLoggedIn() {
		return accountManager.isLoggedIn();
	}

	public void createAccount(long userId, String email, Locale locale) {
		accountManager.createAccount(userId, email, locale);
	}
}
