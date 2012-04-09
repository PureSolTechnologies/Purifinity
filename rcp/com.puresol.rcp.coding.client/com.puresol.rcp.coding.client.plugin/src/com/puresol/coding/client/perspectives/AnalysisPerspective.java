package com.puresol.coding.client.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.views.AnalysisReport;
import com.puresol.coding.client.views.AnalysisRunContentBrowserView;
import com.puresol.coding.client.views.AnalysisRunsView;
import com.puresol.coding.client.views.AnalyzesView;

public class AnalysisPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(true);
	layout.addView(AnalysisRunContentBrowserView.ID, IPageLayout.LEFT,
		0.45f, layout.getEditorArea());
	layout.addView(AnalysisReport.ID, IPageLayout.BOTTOM, 0.66f,
		layout.getEditorArea());
	layout.addView(AnalyzesView.ID, IPageLayout.LEFT, 0.3f,
		AnalysisRunContentBrowserView.ID);
	layout.addView(AnalysisRunsView.ID, IPageLayout.BOTTOM, 0.33f,
		AnalyzesView.ID);
    }
}
