package com.puresol.coding.client.application.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.application.views.AnalysisReport;
import com.puresol.coding.client.application.views.AnalysisRunContentBrowserView;
import com.puresol.coding.client.application.views.AnalysisRunsView;
import com.puresol.coding.client.application.views.AnalyzesView;

public class AnalysisPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(true);
	layout.addView(AnalysisRunContentBrowserView.class.getName(),
		IPageLayout.LEFT, 0.45f, layout.getEditorArea());
	layout.addView(AnalysisReport.class.getName(), IPageLayout.BOTTOM,
		0.66f, layout.getEditorArea());
	layout.addView(AnalyzesView.class.getName(), IPageLayout.LEFT, 0.3f,
		AnalysisRunContentBrowserView.class.getName());
	layout.addView(AnalysisRunsView.class.getName(), IPageLayout.BOTTOM,
		0.33f, AnalyzesView.class.getName());
    }
}
