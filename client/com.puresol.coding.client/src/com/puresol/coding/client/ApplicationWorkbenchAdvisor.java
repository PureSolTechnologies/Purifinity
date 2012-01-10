package com.puresol.coding.client;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.puresol.coding.client.perspectives.PerspectiveIds;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
	    IWorkbenchWindowConfigurer configurer) {
	return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

    @Override
    public String getInitialWindowPerspectiveId() {
	return PerspectiveIds.ANALYSIS_PERSPECTIVE;
    }
}
