package com.puresol.coding.client.application.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.application.views.AnalysisReportView;
import com.puresol.coding.client.application.views.AnalysisRunContentExplorerView;
import com.puresol.coding.client.application.views.AnalysisRunsView;
import com.puresol.coding.client.application.views.AnalysisProjectsView;

public class AnalysisPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(true);
	layout.addView(AnalysisRunContentExplorerView.class.getName(),
		IPageLayout.LEFT, 0.45f, layout.getEditorArea());
	layout.addView(AnalysisReportView.class.getName(), IPageLayout.BOTTOM,
		0.66f, layout.getEditorArea());
	layout.addView(AnalysisProjectsView.class.getName(), IPageLayout.LEFT, 0.3f,
		AnalysisRunContentExplorerView.class.getName());
	layout.addView(AnalysisRunsView.class.getName(), IPageLayout.BOTTOM,
		0.33f, AnalysisProjectsView.class.getName());
    }
}
