package com.puresol.coding.client.lang.c11.prefs;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class C11AnalysisPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    public C11AnalysisPreferencePage() {
	super();
    }

    public C11AnalysisPreferencePage(String title) {
	super(title);
    }

    public C11AnalysisPreferencePage(String title, ImageDescriptor image) {
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
