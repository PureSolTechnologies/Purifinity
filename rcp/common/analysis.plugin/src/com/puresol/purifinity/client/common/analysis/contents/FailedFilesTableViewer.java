package com.puresol.purifinity.client.common.analysis.contents;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;

public class FailedFilesTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final List<AnalyzedCode> files = new ArrayList<AnalyzedCode>();

	public FailedFilesTableViewer(Table table) {
		super(table);
		setContentProvider(this);
		setupNameColumn();
		setupTimeColumn();
	}

	private void setupNameColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalyzedCode analysis = (AnalyzedCode) element;
				return analysis.getSourceLocation()
						.getHumanReadableLocationString();
			}
		});
	}

	private void setupTimeColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Time");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalyzedCode analysis = (AnalyzedCode) element;
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date startTime = analysis.getStartTime();
				if (startTime == null) {
					return "n/a";
				}
				return format.format(startTime);
			}
		});
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		files.clear();
		if (newInput == null) {
			return;
		}
		if (Collection.class.isAssignableFrom(newInput.getClass())) {
			@SuppressWarnings("unchecked")
			List<AnalyzedCode> collection = (List<AnalyzedCode>) newInput;
			files.addAll(collection);
		}
		refresh();
	}

	@Override
	public AnalyzedCode[] getElements(Object inputElement) {
		return files.toArray(new AnalyzedCode[files.size()]);
	}

}
