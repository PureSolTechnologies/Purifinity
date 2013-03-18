package com.puresol.coding.client.common.evaluation.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.common.analysis.views.AnalysisProjectsView;
import com.puresol.coding.client.common.analysis.views.AnalysisRunsView;
import com.puresol.coding.client.common.analysis.views.AvailableLanguagesView;

public class QualityDashboardPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(false);

	layout.addView(AnalysisProjectsView.class.getName(), IPageLayout.LEFT,
		0.5f, layout.getEditorArea());

	layout.addView(AnalysisRunsView.class.getName(), IPageLayout.BOTTOM,
		0.25f, AnalysisProjectsView.class.getName());

	IFolderLayout folder = layout.createFolder("EvaluationMonitorAreas",
		IPageLayout.BOTTOM, 0.75f, layout.getEditorArea());
	folder.addView(IPageLayout.ID_PROGRESS_VIEW);
	folder.addView(AvailableLanguagesView.class.getName());

    }

}
