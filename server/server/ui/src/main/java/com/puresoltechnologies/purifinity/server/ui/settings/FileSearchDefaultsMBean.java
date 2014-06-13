package com.puresoltechnologies.purifinity.server.ui.settings;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesDefaults;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesNames;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.preferences.PreferencesValue;

@ViewScoped
@ManagedBean
public class FileSearchDefaultsMBean {

	@Inject
	private PreferencesStore preferencesStore;

	private PreferencesValue directoryIncludesPreference = null;
	private PreferencesValue directoryExcludesPreference = null;
	private PreferencesValue fileIncludesPreference = null;
	private PreferencesValue fileExcludesPreference = null;
	private PreferencesValue ignoreHiddenPreference = null;

	private String directoryIncludes = null;
	private String directoryExcludes = null;
	private String fileIncludes = null;
	private String fileExcludes = null;
	private Boolean ignoreHidden = null;

	public String getDirectoryIncludes() {
		if (directoryIncludes == null) {
			directoryIncludesPreference = preferencesStore
					.getValue(
							PreferencesNames.DEFAULT_GROUP,
							PreferencesNames.ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES,
							PreferencesDefaults.ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES);
			directoryIncludes = directoryIncludesPreference.getValue();
		}
		return directoryIncludes;
	}

	public void setDirectoryIncludes(String directoryIncludes) {
		this.directoryIncludes = directoryIncludes;
	}

	public String getDirectoryExcludes() {
		if (directoryExcludes == null) {
			directoryExcludesPreference = preferencesStore
					.getValue(
							PreferencesNames.DEFAULT_GROUP,
							PreferencesNames.ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES,
							PreferencesDefaults.ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES);
			directoryExcludes = directoryExcludesPreference.getValue();
		}
		return directoryExcludes;
	}

	public void setDirectoryExcludes(String directoryExcludes) {
		this.directoryExcludes = directoryExcludes;
	}

	public String getFileIncludes() {
		if (fileIncludes == null) {
			fileIncludesPreference = preferencesStore.getValue(
					PreferencesNames.DEFAULT_GROUP,
					PreferencesNames.ANALYSIS_FILE_FILTER_FILE_INCLUDES,
					PreferencesDefaults.ANALYSIS_FILE_FILTER_FILE_INCLUDES);
			fileIncludes = fileIncludesPreference.getValue();
		}
		return fileIncludes;
	}

	public void setFileIncludes(String fileIncludes) {
		this.fileIncludes = fileIncludes;
	}

	public String getFileExcludes() {
		if (fileExcludes == null) {
			fileExcludesPreference = preferencesStore.getValue(
					PreferencesNames.DEFAULT_GROUP,
					PreferencesNames.ANALYSIS_FILE_FILTER_FILE_EXCLUDES,
					PreferencesDefaults.ANALYSIS_FILE_FILTER_FILE_EXCLUDES);
			fileExcludes = fileExcludesPreference.getValue();
		}
		return fileExcludes;
	}

	public void setFileExcludes(String fileExcludes) {
		this.fileExcludes = fileExcludes;
	}

	public boolean isIgnoreHidden() {
		if (ignoreHidden == null) {
			ignoreHiddenPreference = preferencesStore.getValue(
					PreferencesNames.DEFAULT_GROUP,
					PreferencesNames.ANALYSIS_FILE_FILTER_IGNORE_HIDDEN,
					PreferencesDefaults.ANALYSIS_FILE_FILTER_IGNORE_HIDDEN);
			ignoreHidden = Boolean.valueOf(ignoreHiddenPreference.getValue());
		}
		return ignoreHidden;
	}

	public void setIgnoreHidden(boolean ignoreHidden) {
		this.ignoreHidden = ignoreHidden;
	}

	public void save() {
		preferencesStore.setValue(PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES,
				directoryIncludes);
		preferencesStore.setValue(PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES,
				directoryExcludes);
		preferencesStore.setValue(PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_FILE_INCLUDES,
				fileIncludes);
		preferencesStore.setValue(PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_FILE_EXCLUDES,
				fileExcludes);
		preferencesStore.setValue(PreferencesNames.DEFAULT_GROUP,
				PreferencesNames.ANALYSIS_FILE_FILTER_IGNORE_HIDDEN,
				ignoreHidden.toString());
	}

	public void refresh() {
		directoryIncludesPreference = null;
		directoryExcludesPreference = null;
		fileIncludesPreference = null;
		fileExcludesPreference = null;
		ignoreHiddenPreference = null;
		directoryIncludes = null;
		directoryExcludes = null;
		fileIncludes = null;
		fileExcludes = null;
		ignoreHidden = null;
	}
}
