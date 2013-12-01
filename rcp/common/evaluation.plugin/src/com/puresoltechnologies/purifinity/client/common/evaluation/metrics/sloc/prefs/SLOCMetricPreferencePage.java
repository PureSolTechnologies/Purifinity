package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.sloc.prefs;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class SLOCMetricPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    public SLOCMetricPreferencePage() {
	super();
    }

    public SLOCMetricPreferencePage(String title) {
	super(title);
    }

    public SLOCMetricPreferencePage(String title, ImageDescriptor image) {
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
