package com.puresol.coding.client.utils;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import com.puresol.coding.client.preferences.NewAnalysisPreferencePage;
import com.puresol.utils.CodeSearchConfiguration;

public class PreferencesUtils {

    public static CodeSearchConfiguration getFileSearchConfiguration(
	    IPreferenceStore preferenceStore) {
	List<String> locationIncludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.LOCATION_INCLUDES).split("\n"));
	List<String> locationExcludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.LOCATION_EXCLUDES).split("\n"));
	List<String> fileIncludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.FILE_INCLUDES).split("\n"));
	List<String> fileExcludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.FILE_EXCLUDES).split("\n"));
	boolean ignoreHidden = preferenceStore
		.getBoolean(NewAnalysisPreferencePage.IGNORE_HIDDEN);
	CodeSearchConfiguration searchConfiguration = new CodeSearchConfiguration(
		locationIncludes, locationExcludes, fileIncludes, fileExcludes,
		ignoreHidden);
	return searchConfiguration;
    }

}
