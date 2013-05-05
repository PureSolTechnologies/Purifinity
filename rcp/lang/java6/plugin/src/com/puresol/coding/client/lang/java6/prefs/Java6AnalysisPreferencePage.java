package com.puresol.coding.client.lang.java6.prefs;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class Java6AnalysisPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    public Java6AnalysisPreferencePage() {
	super();
    }

    public Java6AnalysisPreferencePage(String title) {
	super(title);
    }

    public Java6AnalysisPreferencePage(String title, ImageDescriptor image) {
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
