package com.puresol.coding.client.application.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.application.views.AnalysisProjectsView;
import com.puresol.coding.client.application.views.AnalysisReportView;
import com.puresol.coding.client.application.views.AnalysisRunsView;

public class AnalysisPerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addView(AnalysisReportView.class.getName(), IPageLayout.RIGHT,
				0.5f, layout.getEditorArea());
		layout.addView(AnalysisProjectsView.class.getName(), IPageLayout.LEFT,
				0.5f, layout.getEditorArea());
		layout.addView(AnalysisRunsView.class.getName(), IPageLayout.BOTTOM,
				0.5f, AnalysisProjectsView.class.getName());
	}
}
