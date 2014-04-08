package com.puresol.accountmanager.ui.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AccountSettingsMBean {

	public String getPageTitleTranslated() {
		return "Account Settings";
	}

	public String getTitleTranslated() {
		return "title";
	}

	public String getFirstNameTranslated() {
		return "first name";
	}

	public String getLastNameTranslated() {
		return "last name";
	}

	public String getDateOfBirthTranslated() {
		return "date of birth";
	}

	public String getPreferredLanguageTranslated() {
		return "preferred language";
	}

	public String getChangeSettingsTranslated() {
		return "change settings";
	}

	public String getResetFormTranslated() {
		return "reset form";
	}

	public String getStarExplanationTranslated() {
		return "Fields marked with a star (*) are mandatory.";
	}
}
