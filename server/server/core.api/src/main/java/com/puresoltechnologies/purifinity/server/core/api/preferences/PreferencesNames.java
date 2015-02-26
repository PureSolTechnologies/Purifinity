package com.puresoltechnologies.purifinity.server.core.api.preferences;

/**
 * This class contains the names of the preferences as stored in the preferences
 * store.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PreferencesNames {

	public static final String DEFAULT_GROUP = "default";

	public static final String ANALYSIS_FILE_FILTER_FILE_INCLUDES = "analysis.filter.files.file.includes";
	public static final String ANALYSIS_FILE_FILTER_FILE_EXCLUDES = "analysis.filter.files.file.excludes";
	public static final String ANALYSIS_FILE_FILTER_DIRECTORY_INCLUDES = "analysis.filter.files.directory.includes";
	public static final String ANALYSIS_FILE_FILTER_DIRECTORY_EXCLUDES = "analysis.filter.files.directory.excludes";
	public static final String ANALYSIS_FILE_FILTER_IGNORE_HIDDEN = "analysis.filter.files.hidden.ignore";

	public static final String SYSTEM_WEB_UI_THEME = "system.ui.web.theme";

	public static final String SYSTEM_GROUP = "system";

	public static final String SYSTEM_AUTHENTICATION_ANONYMOUS_ALLOWED = "system.authentication.anonymous.allowed";

	/**
	 * Private default constructor to avoid instantiation.
	 */
	private PreferencesNames() {
	}
}
