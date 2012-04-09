package com.puresol.coding.client.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.views.AnalysisReport;

public class EvaluationPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.addView(AnalysisReport.ID, IPageLayout.BOTTOM, 0.8f,
		layout.getEditorArea());
    }

}
