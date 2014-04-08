package com.puresol.passwordstore.ui.web;

import java.security.Principal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.i18n4java.Translator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.puresol.accountmanager.ui.api.LanguageSelectorMBean;

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

	private static final Translator TRANSLATOR = Translator
			.getTranslator(LoginMBean.class);

	@ManagedProperty(value = "#{languageSelectorMBean}")
	public LanguageSelectorMBean languageSelector;

	public void setLanguageSelector(LanguageSelectorMBean languageSelector) {
		this.languageSelector = languageSelector;
	}

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
		return TRANSLATOR.i18n("email:", languageSelector.getCurrentLocale());
	}

	public String getPasswordTranslated() {
		return TRANSLATOR
				.i18n("password:", languageSelector.getCurrentLocale());
	}

	public String getLoginTranslated() {
		return TRANSLATOR.i18n("login", languageSelector.getCurrentLocale());
	}

	public String getLogoutTranslated() {
		return TRANSLATOR.i18n("logout", languageSelector.getCurrentLocale());
	}

	public String getResetTranslated() {
		return TRANSLATOR.i18n("reset", languageSelector.getCurrentLocale());
	}

	public String getRestrictedAccessMessageTranslated() {
		return TRANSLATOR.i18n(
				"You enter a restricted page, please login to proceed...",
				languageSelector.getCurrentLocale());
	}

	public String getLoginToExistingAccountTranslated() {
		return TRANSLATOR.i18n("Login into Existing Account",
				languageSelector.getCurrentLocale());
	}
}
