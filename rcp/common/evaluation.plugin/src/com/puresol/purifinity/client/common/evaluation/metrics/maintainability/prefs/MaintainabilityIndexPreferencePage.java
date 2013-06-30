package com.puresol.purifinity.client.common.evaluation.metrics.maintainability.prefs;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class MaintainabilityIndexPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    public MaintainabilityIndexPreferencePage() {
	super();
    }

    public MaintainabilityIndexPreferencePage(String title) {
	super(title);
    }

    public MaintainabilityIndexPreferencePage(String title, ImageDescriptor image) {
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
