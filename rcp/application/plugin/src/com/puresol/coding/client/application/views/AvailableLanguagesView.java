package com.puresol.coding.client.application.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.application.content.AvailableLanguagesTableViewer;

public class AvailableLanguagesView extends ViewPart implements
	SelectionListener {
    public AvailableLanguagesView() {
    }

    private Table table;
    private TableViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FormLayout());
	table = new Table(parent, SWT.NONE);
	FormData fd_table = new FormData();
	fd_table.bottom = new FormAttachment(100);
	fd_table.left = new FormAttachment(0);
	fd_table.right = new FormAttachment(100);
	table.setLayoutData(fd_table);
	table.setHeaderVisible(true);
	viewer = new AvailableLanguagesTableViewer(table);

	ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
	fd_table.top = new FormAttachment(toolBar, 6);
	FormData fd_toolBar = new FormData();
	fd_toolBar.left = new FormAttachment(0);
	fd_toolBar.right = new FormAttachment(100);
	fd_toolBar.top = new FormAttachment(0);
	toolBar.setLayoutData(fd_toolBar);

	ToolItem tltmRefresh = new ToolItem(toolBar, SWT.NONE);
	tltmRefresh.setText("Refresh");
	tltmRefresh.addSelectionListener(this);
    }

    @Override
    public void setFocus() {
	table.setFocus();
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
	widgetDefaultSelected(e);
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
	try {
	    BundleContext bundleContext = Activator.getDefault().getBundle()
		    .getBundleContext();
	    Collection<ServiceReference<AnalyzableProgrammingLanguage>> allServiceReferences = bundleContext
		    .getServiceReferences(AnalyzableProgrammingLanguage.class,
			    null);
	    List<AnalyzableProgrammingLanguage> languages = new ArrayList<AnalyzableProgrammingLanguage>();
	    for (ServiceReference<AnalyzableProgrammingLanguage> serviceReference : allServiceReferences) {
		AnalyzableProgrammingLanguage service = bundleContext
			.getService(serviceReference);
		languages.add(service);
		bundleContext.ungetService(serviceReference);
	    }
	    viewer.setInput(languages);
	} catch (InvalidSyntaxException e1) {
	    viewer.setInput(new ArrayList<AnalyzableProgrammingLanguage>());
	}
    }
}