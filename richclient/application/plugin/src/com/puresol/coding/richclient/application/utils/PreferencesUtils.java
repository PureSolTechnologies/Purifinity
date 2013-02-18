package com.puresol.coding.richclient.application.utils;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import com.puresol.coding.richclient.application.prefs.NewAnalysisPreferencePage;
import com.puresol.utils.FileSearchConfiguration;

public class PreferencesUtils {

	public static FileSearchConfiguration getFileSearchConfiguration(
			IEclipsePreferences preferenceStore) {
		List<String> locationIncludes = Arrays.asList(preferenceStore.get(
				NewAnalysisPreferencePage.LOCATION_INCLUDES,
				NewAnalysisPreferencePage.LOCATION_INCLUDES_DEFAULT)
				.split("\n"));
		List<String> locationExcludes = Arrays.asList(preferenceStore.get(
				NewAnalysisPreferencePage.LOCATION_EXCLUDES,
				NewAnalysisPreferencePage.LOCATION_EXCLUDES_DEFAULT)
				.split("\n"));
		List<String> fileIncludes = Arrays.asList(preferenceStore.get(
				NewAnalysisPreferencePage.FILE_INCLUDES,
				NewAnalysisPreferencePage.FILE_INCLUDES_DEFAULT).split("\n"));
		List<String> fileExcludes = Arrays.asList(preferenceStore.get(
				NewAnalysisPreferencePage.FILE_EXCLUDES,
				NewAnalysisPreferencePage.FILE_EXCLUDES_DEFAULT).split("\n"));
		boolean ignoreHidden = preferenceStore.getBoolean(
				NewAnalysisPreferencePage.IGNORE_HIDDEN,
				NewAnalysisPreferencePage.IGNORE_HIDDEN_DEFAULT);
		FileSearchConfiguration searchConfiguration = new FileSearchConfiguration(
				locationIncludes, locationExcludes, fileIncludes, fileExcludes,
				ignoreHidden);
		return searchConfiguration;
	}
}
