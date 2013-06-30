package com.puresol.purifinity.client.common.evaluation.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.purifinity.client.common.analysis.views.AnalysisProjectsView;
import com.puresol.purifinity.client.common.analysis.views.AnalysisRunContentView;
import com.puresol.purifinity.client.common.analysis.views.AnalysisRunsView;
import com.puresol.purifinity.client.common.analysis.views.AvailableLanguagesView;
import com.puresol.purifinity.client.common.evaluation.views.DefectDensityMapView;

public class EvaluationPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(true);

	layout.addView(AnalysisRunContentView.class.getName(),
		IPageLayout.LEFT, 0.4f, layout.getEditorArea());

	layout.addView(AnalysisProjectsView.class.getName(), IPageLayout.LEFT,
		0.5f, AnalysisRunContentView.class.getName());

	layout.addView(AnalysisRunsView.class.getName(), IPageLayout.BOTTOM,
		0.25f, AnalysisProjectsView.class.getName());

	layout.addView(DefectDensityMapView.class.getName(),
		IPageLayout.BOTTOM, 0.6f,
		AnalysisRunContentView.class.getName());

	IFolderLayout folder = layout.createFolder("EvaluationMonitorAreas",
		IPageLayout.BOTTOM, 0.75f, AnalysisRunsView.class.getName());
	folder.addView(IPageLayout.ID_PROGRESS_VIEW);
	folder.addView(AvailableLanguagesView.class.getName());
    }

}
