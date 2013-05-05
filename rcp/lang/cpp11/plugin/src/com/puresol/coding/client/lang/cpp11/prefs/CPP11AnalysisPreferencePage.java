package com.puresol.coding.client.lang.cpp11.prefs;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.puresol.coding.client.lang.cpp11.Activator;
import com.puresol.coding.lang.cpp.CPP;

public class CPP11AnalysisPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    private static final String FILES_INCLUDED = "CPP11.files.included";
    private static final String FILES_EXCLUDED = "CPP11.files.excluded";

    private static final String[] FILES_INCLUDED_DEFAULTS = CPP.FILE_SUFFIXES;
    private static final String FILES_EXCLUDED_DEFAULTS = "";

    public CPP11AnalysisPreferencePage() {
	super();
    }

    public CPP11AnalysisPreferencePage(String title) {
	super(title);
    }

    public CPP11AnalysisPreferencePage(String title, ImageDescriptor image) {
	super(title, image);
    }

    @Override
    public void init(IWorkbench workbench) {
	IPreferenceStore preferenceStore = Activator.getDefault()
		.getPreferenceStore();
	setPreferenceStore(preferenceStore);
	setDefaultValuesToPreferencesStore(preferenceStore);
    }

    public static void setDefaultValuesToPreferencesStore(
	    IPreferenceStore preferenceStore) {
	StringBuilder builder = new StringBuilder();
	for (String suffix : FILES_INCLUDED_DEFAULTS) {
	    if (builder.length() > 0) {
		builder.append("\n");
	    }
	    builder.append("*" + suffix);
	}
	preferenceStore.setDefault(FILES_INCLUDED, builder.toString());
	preferenceStore.setDefault(FILES_EXCLUDED, FILES_EXCLUDED_DEFAULTS);
    }

    @Override
    protected Control createContents(Composite parent) {
	// TODO Auto-generated method stub
	return null;
    }

}
