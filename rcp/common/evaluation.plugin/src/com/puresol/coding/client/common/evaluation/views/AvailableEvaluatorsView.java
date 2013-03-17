package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.application.actions.RefreshAction;
import com.puresol.coding.client.application.actions.Refreshable;
import com.puresol.coding.client.application.content.AvailableEvaluatorsTableViewer;
import com.puresol.coding.evaluation.api.EvaluatorFactory;

public class AvailableEvaluatorsView extends ViewPart implements Refreshable {

    public AvailableEvaluatorsView() {
    }

    private Table table;
    private TableViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout());
	table = new Table(parent, SWT.NONE);
	table.setHeaderVisible(true);
	viewer = new AvailableEvaluatorsTableViewer(table);

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
	    Collection<ServiceReference<EvaluatorFactory>> allServiceReferences = bundleContext
		    .getServiceReferences(EvaluatorFactory.class, null);
	    List<EvaluatorFactory> languages = new ArrayList<EvaluatorFactory>();
	    for (ServiceReference<EvaluatorFactory> serviceReference : allServiceReferences) {
		EvaluatorFactory service = bundleContext
			.getService(serviceReference);
		languages.add(service);
		bundleContext.ungetService(serviceReference);
	    }
	    viewer.setInput(languages);
	} catch (InvalidSyntaxException e1) {
	    viewer.setInput(new ArrayList<EvaluatorFactory>());
	}
    }

}
