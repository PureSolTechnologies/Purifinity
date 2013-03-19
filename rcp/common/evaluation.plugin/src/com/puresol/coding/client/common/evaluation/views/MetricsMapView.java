package com.puresol.coding.client.common.evaluation.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;

public class MetricsMapView extends ViewPart implements Refreshable,
	ISelectionListener {
    private Text text;

    public MetricsMapView() {
    }

    /**
     * Create contents of the view part.
     * 
     * @param parent
     */
    @Override
    public void createPartControl(Composite parent) {
	Composite container = new Composite(parent, SWT.NONE);
	container.setLayout(new FillLayout(SWT.HORIZONTAL));
	{
	    text = new Text(container, SWT.BORDER);
	    text.setEditable(false);
	}

	IWorkbenchPartSite site = getSite();
	site.getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);

	initializeToolBar();
	initializeMenu();
    }

    /**
     * Initialize the toolbar.
     */
    private void initializeToolBar() {
	IToolBarManager toolbarManager = getViewSite().getActionBars()
		.getToolBarManager();
	toolbarManager.add(new RefreshAction(this));
    }

    /**
     * Initialize the menu.
     */
    private void initializeMenu() {
	IMenuManager menuManager = getViewSite().getActionBars()
		.getMenuManager();
    }

    @Override
    public void setFocus() {
	// Set the focus
    }

    @Override
    public void refresh() {
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof FileAnalysisSelection) {
	    FileAnalysisSelection analysisSelection = (FileAnalysisSelection) selection;
	    HashIdFileTree path = analysisSelection.getHashIdFile();
	    text.setText(path.getPathFile(true).getPath());
	}
    }
}
