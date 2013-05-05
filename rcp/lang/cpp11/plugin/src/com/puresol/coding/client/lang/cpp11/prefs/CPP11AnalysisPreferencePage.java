package com.puresol.coding.client.lang.cpp11.prefs;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CPP11AnalysisPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    private static final String FILES_INCLUDED = "CPP11.files.included";
    private static final String FILES_EXCLUDED = "CPP11.files.excluded";

    private static final String FILES_INCLUDED_DEFAULTS = "";
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
	// TODO Auto-generated method stub

    }

    @Override
    protected Control createContents(Composite parent) {
	// TODO Auto-generated method stub
	return null;
    }

}
