package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.codedepth.prefs;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CodeDepthPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    public CodeDepthPreferencePage() {
	super();
    }

    public CodeDepthPreferencePage(String title) {
	super(title);
    }

    public CodeDepthPreferencePage(String title, ImageDescriptor image) {
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
