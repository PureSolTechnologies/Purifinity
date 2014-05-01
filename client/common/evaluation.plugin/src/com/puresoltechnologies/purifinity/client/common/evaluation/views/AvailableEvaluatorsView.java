package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.EvaluatorInformationDialog;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.AvailableEvaluatorsTableViewer;
import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;
import com.puresoltechnologies.purifinity.client.common.ui.actions.InformationAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.InformationProvider;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.Refreshable;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;

public class AvailableEvaluatorsView extends AbstractPureSolTechnologiesView
		implements Refreshable, InformationProvider, ISelectionChangedListener {

	private Table table;
	private TableViewer viewer;
	private EvaluatorInformationDialog evaluatorInformationDialog = null;
	private EvaluatorFactory evaluatorFactorySelection = null;

	public AvailableEvaluatorsView() {
		super(Activator.getDefault());
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		parent.setLayout(new FillLayout());
		table = new Table(parent, SWT.NONE);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer = new AvailableEvaluatorsTableViewer(table);
		viewer.addSelectionChangedListener(this);

		initializeToolBar();

		refresh();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new InformationAction(this));
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
			Collection<ServiceReference<EvaluatorFactory>> allServiceReferences = bundleContext
					.getServiceReferences(EvaluatorFactory.class, null);
			List<EvaluatorFactory> evaluators = new ArrayList<EvaluatorFactory>();
			for (ServiceReference<EvaluatorFactory> serviceReference : allServiceReferences) {
				EvaluatorFactory service = bundleContext
						.getService(serviceReference);
				evaluators.add(service);
				bundleContext.ungetService(serviceReference);
			}
			viewer.setInput(evaluators);
		} catch (InvalidSyntaxException e1) {
			viewer.setInput(new ArrayList<EvaluatorFactory>());
		}
	}

	@Override
	public void showInformation() {
		if (evaluatorInformationDialog == null) {
			evaluatorInformationDialog = new EvaluatorInformationDialog(
					getSite(), evaluatorFactorySelection);
			evaluatorInformationDialog.open();
			evaluatorInformationDialog = null;
		} else {
			evaluatorInformationDialog.close();
			evaluatorInformationDialog = null;
		}
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSource() == viewer) {
			StructuredSelection selection = (StructuredSelection) viewer
					.getSelection();
			evaluatorFactorySelection = (EvaluatorFactory) selection
					.getFirstElement();
			if (evaluatorInformationDialog != null) {
				evaluatorInformationDialog
						.setEvaluatorFactory(evaluatorFactorySelection);
			}
		}
	}

}
