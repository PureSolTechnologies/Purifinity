package com.puresol.passwordstore.ui.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.puresol.passwordstore.client.PasswordStoreClient;

@ManagedBean
@RequestScoped
public class CreateAccountMBean {

	private String email;
	private String password;

	public String createAccount() {
		try {
			PasswordStoreClient.createInstance().createAccount(email, password);
			return "account_created";
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(
							"Creation of account failed. Error message: ", e
									.getMessage()));
			return "create_account_error";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
