package com.puresoltechnologies.purifinity.server.ui.settings;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.preferences.PreferencesDefaults;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesNames;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesStore;

@SessionScoped
@ManagedBean
public class AuthenticationSettingsMBean {

	private boolean anonymousUsageAllowed;

	@Inject
	private PreferencesStore preferencesStore;

	@PostConstruct
	public void construct() {
		refresh();
	}

	public boolean isAnonymousUsageAllowed() {
		return anonymousUsageAllowed;
	}

	public void setAnonymousUsageAllowed(boolean anonymousUsageAllowed) {
		this.anonymousUsageAllowed = anonymousUsageAllowed;
	}

	public void save() {
		preferencesStore.setValue(PreferencesNames.SYSTEM_GROUP,
				PreferencesNames.SYSTEM_AUTHENTICATION_ANONYMOUS_ALLOWED,
				anonymousUsageAllowed);
	}

	public void refresh() {
		anonymousUsageAllowed = preferencesStore.getBoolean(
				PreferencesNames.SYSTEM_GROUP,
				PreferencesNames.SYSTEM_AUTHENTICATION_ANONYMOUS_ALLOWED,
				PreferencesDefaults.SYSTEM_AUTHENTICATION_ANONYMOUS_ALLOWED);
	}
}
