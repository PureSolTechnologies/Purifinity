package com.puresol.passwordstore.ui.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.puresol.accountmanager.ui.api.AccountManagerDelegatorMBean;
import com.puresol.passwordstore.client.PasswordStoreClient;

@ManagedBean
@RequestScoped
public class ActivateAccountMBean {

	private String email;
	private String activationKey;

	@ManagedProperty(value = "#{accountManagerDelegatorMBean}")
	private AccountManagerDelegatorMBean accountManager;

	public void setAccountManager(AccountManagerDelegatorMBean accountManager) {
		this.accountManager = accountManager;
	}

	public String activateAccount() {
		try {
			PasswordStoreClient passwordStore = PasswordStoreClient
					.createInstance();
			long userId = passwordStore.activateAccount(email, activationKey);
			accountManager.createAccount(userId, email);
			return "account_activated";
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(
							"Creation of account failed. Error message: ", e
									.getMessage()));
			return "activate_account_error";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

}
