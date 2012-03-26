package com.puresol.coding.client.utils;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import com.puresol.coding.client.preferences.NewAnalysisPreferencePage;
import com.puresol.utils.FileSearchConfiguration;

public class PreferencesUtils {

    public static FileSearchConfiguration getFileSearchConfiguration(
	    IPreferenceStore preferenceStore) {
	List<String> dirIncludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.DIRECTORY_INCLUDES).split("\n"));
	List<String> dirExcludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.DIRECTORY_EXCLUDES).split("\n"));
	List<String> fileIncludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.FILE_INCLUDES).split("\n"));
	List<String> fileExcludes = Arrays.asList(preferenceStore.getString(
		NewAnalysisPreferencePage.FILE_EXCLUDES).split("\n"));
	boolean ignoreHidden = preferenceStore
		.getBoolean(NewAnalysisPreferencePage.IGNORE_HIDDEN);
	FileSearchConfiguration searchConfiguration = new FileSearchConfiguration(
		dirIncludes, dirExcludes, fileIncludes, fileExcludes,
		ignoreHidden);
	return searchConfiguration;
    }

}
