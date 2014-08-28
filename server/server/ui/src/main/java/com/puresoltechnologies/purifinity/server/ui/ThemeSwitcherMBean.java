package com.puresoltechnologies.purifinity.server.ui;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.preferences.PreferencesNames;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesValue;

@ManagedBean
@ViewScoped
public class ThemeSwitcherMBean implements Serializable {

	private static final long serialVersionUID = -4611799892344805652L;

	private String themeName;
	private List<Theme> themes;

	@Inject
	private PreferencesStore preferencesStore;

	@ManagedProperty("#{themeService}")
	private ThemeService service;

	@PostConstruct
	public void init() {
		themes = service.getThemes();
		setDefaultTheme();
	}

	private void setDefaultTheme() {
		PreferencesValue themeName = preferencesStore.getValue("default",
				PreferencesNames.SYSTEM_WEB_UI_THEME, "sunny");
		for (Theme theme : themes) {
			if (themeName.getValue().equals(theme.getName())) {
				this.themeName = theme.getName();
				break;
			}
		}
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setService(ThemeService service) {
		this.service = service;
	}

	public String getThemeName() {
		if (themeName == null) {
			setDefaultTheme();
		}
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public void save() {
		if (themeName != null) {
			preferencesStore.setValue(PreferencesNames.DEFAULT_GROUP,
					PreferencesNames.SYSTEM_WEB_UI_THEME, themeName);
		}
	}
}