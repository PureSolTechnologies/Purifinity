package com.puresoltechnologies.purifinity.server.passwordstore.ui.web;

import java.security.Principal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * This managed bean is used for two main reasons:
 * 
 * 1) It is used to check whether a user is already logged in or not.
 * 
 * 2) It's used to do a programmatic login if the user requests so.
 * 
 * @author Rick-Rainer Ludwig
 */
@ManagedBean
@RequestScoped
public class LoginMBean {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method performs the actual login.
	 * 
	 * @return
	 */
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();
			request.login(this.username, this.password);
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Login failed."));
			return "";
		}
		return "";
	}

	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		try {
			request.logout();
		} catch (ServletException e) {
			context.addMessage(null, new FacesMessage("Logout failed."));
		}
	}

	public boolean isLoggedIn() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		Principal userPrincipal = request.getUserPrincipal();
		return userPrincipal != null;
	}

	public String getEmailTranslated() {
		return "email:";
	}

	public String getPasswordTranslated() {
		return "password:";
	}

	public String getLoginTranslated() {
		return "login";
	}

	public String getLogoutTranslated() {
		return "logout";
	}

	public String getResetTranslated() {
		return "reset";
	}

	public String getRestrictedAccessMessageTranslated() {
		return "You enter a restricted page, please login to proceed...";
	}

	public String getLoginToExistingAccountTranslated() {
		return "Login into Existing Account";
	}
}
