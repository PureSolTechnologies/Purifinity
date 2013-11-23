package com.puresol.purifinity.client.common.analysis.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.purifinity.client.common.analysis.Activator;
import com.puresol.purifinity.client.common.analysis.contents.AvailableAnalyzersTableViewer;
import com.puresol.purifinity.client.common.ui.actions.RefreshAction;
import com.puresol.purifinity.client.common.ui.actions.Refreshable;
import com.puresol.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguageAnalyzer;

public class AvailableAnalyzersView extends AbstractPureSolTechnologiesView
		implements Refreshable {

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
		try {
			BundleContext bundleContext = Activator.getDefault().getBundle()
					.getBundleContext();
			Collection<ServiceReference<ProgrammingLanguageAnalyzer>> allServiceReferences = bundleContext
					.getServiceReferences(ProgrammingLanguageAnalyzer.class,
							null);
			List<ProgrammingLanguageAnalyzer> languages = new ArrayList<ProgrammingLanguageAnalyzer>();
			for (ServiceReference<ProgrammingLanguageAnalyzer> serviceReference : allServiceReferences) {
				ProgrammingLanguageAnalyzer service = bundleContext
						.getService(serviceReference);
				languages.add(service);
				bundleContext.ungetService(serviceReference);
			}
			viewer.setInput(languages);
		} catch (InvalidSyntaxException e1) {
			viewer.setInput(new ArrayList<ProgrammingLanguageAnalyzer>());
		}
	}
}
