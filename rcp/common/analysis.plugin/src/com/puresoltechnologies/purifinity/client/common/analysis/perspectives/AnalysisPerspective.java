package com.puresoltechnologies.purifinity.client.common.analysis.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisProjectsView;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisReportView;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisRunsView;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AvailableAnalyzersView;

public class AnalysisPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(false);
	layout.addView(AnalysisReportView.class.getName(), IPageLayout.RIGHT,
		0.3f, layout.getEditorArea());

	layout.addView(AnalysisProjectsView.class.getName(), IPageLayout.LEFT,
		0.7f, layout.getEditorArea());

	layout.addView(AnalysisRunsView.class.getName(), IPageLayout.BOTTOM,
		0.25f, AnalysisProjectsView.class.getName());

	IFolderLayout folder = layout.createFolder("AnalysisMonitorAreas",
		IPageLayout.BOTTOM, 0.75f, AnalysisRunsView.class.getName());
	folder.addView(IPageLayout.ID_PROGRESS_VIEW);
	folder.addView(AvailableAnalyzersView.class.getName());
    }
}
