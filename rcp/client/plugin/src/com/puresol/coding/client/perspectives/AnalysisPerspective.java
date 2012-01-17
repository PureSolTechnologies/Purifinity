package com.puresol.coding.client.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.puresol.coding.client.views.ViewIds;

public class AnalysisPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
	layout.addView(ViewIds.ANALYSIS_NAVIGATOR, IPageLayout.LEFT, 0.3f,
		layout.getEditorArea());
	layout.addView(ViewIds.SOURCE_NAVIGATOR, IPageLayout.BOTTOM, 0.5f,
		ViewIds.ANALYSIS_NAVIGATOR);
	layout.addView(ViewIds.ANALYSIS_REPORT, IPageLayout.BOTTOM, 0.8f,
		layout.getEditorArea());
    }
}
