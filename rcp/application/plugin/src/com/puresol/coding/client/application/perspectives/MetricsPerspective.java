package com.puresol.coding.client.application.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.application.views.AnalysisProjectsView;
import com.puresol.coding.client.application.views.AnalysisRunContentView;
import com.puresol.coding.client.application.views.AnalysisRunsView;

public class MetricsPerspective implements IPerspectiveFactory {

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
