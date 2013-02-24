package com.puresol.coding.client.application.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.application.views.AnalysisReportView;

public class EvaluationPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.addView(AnalysisReportView.class.getName(), IPageLayout.BOTTOM,
		0.8f, layout.getEditorArea());
    }

}
