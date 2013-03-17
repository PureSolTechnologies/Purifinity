package com.puresol.coding.client.common.evaluation.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.common.analysis.views.AnalysisProjectsView;
import com.puresol.coding.client.common.analysis.views.AnalysisRunContentView;
import com.puresol.coding.client.common.analysis.views.AnalysisRunsView;

public class EvaluationPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(true);
	layout.addView(AnalysisRunContentView.class.getName(),
		IPageLayout.LEFT, 0.45f, layout.getEditorArea());
	layout.addView(AnalysisProjectsView.class.getName(), IPageLayout.LEFT,
		0.3f, AnalysisRunContentView.class.getName());
	layout.addView(AnalysisRunsView.class.getName(), IPageLayout.BOTTOM,
		0.33f, AnalysisProjectsView.class.getName());
    }

}
