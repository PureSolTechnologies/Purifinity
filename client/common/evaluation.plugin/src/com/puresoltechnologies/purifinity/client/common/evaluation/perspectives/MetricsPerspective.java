package com.puresoltechnologies.purifinity.client.common.evaluation.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisProjectsView;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisRunContentView;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisRunsView;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AvailableEvaluatorsView;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.MetricsMapView;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.MetricsTableView;

public class MetricsPerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		layout.addView(AnalysisRunContentView.class.getName(),
				IPageLayout.LEFT, 0.4f, layout.getEditorArea());

		layout.addView(MetricsTableView.class.getName(), IPageLayout.BOTTOM,
				0.3f, layout.getEditorArea());

		layout.addView(AnalysisProjectsView.class.getName(), IPageLayout.LEFT,
				0.5f, AnalysisRunContentView.class.getName());

		layout.addView(AnalysisRunsView.class.getName(), IPageLayout.BOTTOM,
				0.25f, AnalysisProjectsView.class.getName());

		layout.addView(MetricsMapView.class.getName(), IPageLayout.BOTTOM,
				0.6f, AnalysisRunContentView.class.getName());

		IFolderLayout folder = layout.createFolder("EvaluationMonitorAreas",
				IPageLayout.BOTTOM, 0.6f, AnalysisRunsView.class.getName());
		folder.addView(IPageLayout.ID_PROGRESS_VIEW);
		folder.addView(AvailableEvaluatorsView.class.getName());
	}

}
