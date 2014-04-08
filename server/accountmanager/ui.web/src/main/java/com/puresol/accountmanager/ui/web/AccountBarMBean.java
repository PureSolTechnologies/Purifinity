package com.puresol.accountmanager.ui.web;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.puresol.accountmanager.ui.api.AccountManagerDelegatorMBean;

@ManagedBean
@SessionScoped
public class AccountBarMBean implements Serializable {

	private static final long serialVersionUID = 3996802747680795772L;

	@ManagedProperty(value = "#{accountManagerDelegatorMBean}")
	private AccountManagerDelegatorMBean accountManager;

	public void setAccountManager(AccountManagerDelegatorMBean accountManager) {
		this.accountManager = accountManager;
	}

	public String getHelloMessageTranslated() {
		return MessageFormat.format("Welcome, {0}!", accountManager.getName());
	}

	public String getPleaseLoginTranslated() {
		return "Welcome! Please feel free to create a new account or to login.";
	}

	public String getHomeTranslated() {
		return "home";
	}

	public String getLoginTranslated() {
		return "login";
	}

	public String getLogoutTranslated() {
		return "logout";
	}

	public String getMyAccountTranslated() {
		return "my account";
	}

	public String getCreateAccountTranslated() {
		return "create account";
	}

}
