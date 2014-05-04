package com.puresoltechnologies.purifinity.client.common.analysis.views;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AvailableAnalyzersTableViewer;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.Refreshable;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresoltechnologies.purifinity.server.analysisservice.client.AnalysisServiceClient;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;

public class AvailableAnalyzersView extends AbstractPureSolTechnologiesView
		implements Refreshable {

	private static final Logger logger = LoggerFactory
			.getLogger(AvailableAnalyzersView.class);

	public AvailableAnalyzersView() {
		super(Activator.getDefault());
	}

	private Table table;
	private TableViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		parent.setLayout(new FillLayout());
		table = new Table(parent, SWT.NONE);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer = new AvailableAnalyzersTableViewer(table);

		initializeToolBar();

		refresh();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new RefreshAction(this));
	}

	@Override
	public void setFocus() {
		table.setFocus();
	}

	@Override
	public void refresh() {
		try (AnalysisServiceClient client = new AnalysisServiceClient()) {
			AvailableAnalyzers analyzers = client.getAnalyzers();
			viewer.setInput(analyzers.getAnalyzers());
		} catch (IOException e) {
			logger.error("Could not retrieve a list of available analyzers.", e);
			viewer.setInput(new ArrayList<ProgrammingLanguageAnalyzer>());
		}
	}
}
