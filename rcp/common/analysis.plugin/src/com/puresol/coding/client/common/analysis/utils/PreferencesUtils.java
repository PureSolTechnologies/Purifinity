package com.puresol.coding.client.common.analysis.utils;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import com.puresol.coding.client.common.analysis.preferences.NewAnalysisPreferencePage;
import com.puresol.utils.FileSearchConfiguration;

public class PreferencesUtils {

	public static FileSearchConfiguration getFileSearchConfiguration(
			IPreferenceStore preferenceStore) {
		List<String> locationIncludes = Arrays.asList(preferenceStore
				.getString(NewAnalysisPreferencePage.LOCATION_INCLUDES).split(
						"\n"));
		List<String> locationExcludes = Arrays.asList(preferenceStore
				.getString(NewAnalysisPreferencePage.LOCATION_EXCLUDES).split(
						"\n"));
		List<String> fileIncludes = Arrays.asList(preferenceStore.getString(
				NewAnalysisPreferencePage.FILE_INCLUDES).split("\n"));
		List<String> fileExcludes = Arrays.asList(preferenceStore.getString(
				NewAnalysisPreferencePage.FILE_EXCLUDES).split("\n"));
		boolean ignoreHidden = preferenceStore
				.getBoolean(NewAnalysisPreferencePage.IGNORE_HIDDEN);
		FileSearchConfiguration searchConfiguration = new FileSearchConfiguration(
				locationIncludes, locationExcludes, fileIncludes, fileExcludes,
				ignoreHidden);
		return searchConfiguration;
	}

}
