package com.puresol.purifinity.client.common.license.content;

import java.util.Set;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import com.puresol.purifinity.license.api.License;

public class LicensesTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private Set<License> licenses = null;

	public LicensesTableViewer(Table parent) {
		super(parent);
		setContentProvider(this);
		ColumnViewerToolTipSupport.enableFor(this, ToolTip.NO_RECREATE);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if ((newInput != null)
				&& (Set.class.isAssignableFrom(newInput.getClass()))) {
			@SuppressWarnings("unchecked")
			Set<License> input = (Set<License>) newInput;
			licenses = input;
			addColumns();
			refresh();
		}
	}

	private void addColumns() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("License Id");
		nameColumn.getColumn().setToolTipText("List of all license ids.");
		nameColumn.getColumn().setWidth(100);
		ColumnLabelProvider labelProvider = new ColumnLabelProvider() {

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 100;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 5000;
			}

			@Override
			public String getToolTipText(Object element) {
				License license = (License) element;
				return "Product: " + license.getProduct()
						+ "; Expiration Date: " + license.getExpirationDate();
			}

			@Override
			public String getText(Object element) {
				License license = (License) element;
				return license.getPublicKey().toString();
			}

		};
		nameColumn.setLabelProvider(labelProvider);

	}

	@Override
	public License[] getElements(Object inputElement) {
		return licenses.toArray(new License[licenses.size()]);
	}

}
