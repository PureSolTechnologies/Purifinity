package com.puresoltechnologies.purifinity.server.preferences;

public class PreferencesDefaults {

	public static final String ANALYSIS_FILE_FILTER_FILE_INCLUDES = "";
	public static final String ANALYSIS_FILE_FILTER_FILE_EXCLUDES = ".*\n*.bak\n*~";
	public static final String ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES = "";
	public static final String ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES = ".*\nbin\ntarget";
	public static final String ANALYSIS_FILE_FILTER_IGNORE_HIDDEN = Boolean
			.toString(true);

	public static final String SYSTEM_AUTHENTICATION_ANONYMOUS_ALLOWED = Boolean
			.toString(false);
}
