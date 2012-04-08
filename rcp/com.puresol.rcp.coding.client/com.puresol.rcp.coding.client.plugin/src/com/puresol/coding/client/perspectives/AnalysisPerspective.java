package com.puresol.coding.client.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.views.ViewIds;

public class AnalysisPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.setEditorAreaVisible(true);
	layout.addView(ViewIds.SOURCE_NAVIGATOR, IPageLayout.LEFT, 0.45f,
		layout.getEditorArea());
	layout.addView(ViewIds.ANALYSIS_REPORT, IPageLayout.BOTTOM, 0.66f,
		layout.getEditorArea());
	layout.addView(ViewIds.ANALYSIS_NAVIGATOR, IPageLayout.LEFT, 0.3f,
		ViewIds.SOURCE_NAVIGATOR);
	layout.addView(ViewIds.ANALYSIS_RUN_NAVIGATOR, IPageLayout.BOTTOM,
		0.33f, ViewIds.ANALYSIS_NAVIGATOR);
    }
}
