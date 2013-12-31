package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;

public class FailedFilesTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final List<AnalysisInformation> files = new ArrayList<AnalysisInformation>();

	private final FailedFilesViewerSorter comparator;

	public FailedFilesTableViewer(Table table) {
		super(table);
		setContentProvider(this);
		comparator = new FailedFilesViewerSorter();
		setSorter(comparator);
		setupNameColumn();
		setupTimeColumn();
		setupMessageColumn();
	}

	private void setupNameColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(200);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalysisInformation analysis = (AnalysisInformation) element;
				return analysis.getSourceLocation()
						.getHumanReadableLocationString();
			}
		});
		nameColumn.getColumn().addSelectionListener(
				getSelectionAdapter(nameColumn.getColumn(), 0));
	}

	private void setupTimeColumn() {
		TableViewerColumn timeColumn = new TableViewerColumn(this, SWT.NONE);
		timeColumn.getColumn().setText("Time");
		timeColumn.getColumn().setWidth(100);
		timeColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalysisInformation analysis = (AnalysisInformation) element;
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date startTime = analysis.getStartTime();
				if (startTime == null) {
					return "n/a";
				}
				return format.format(startTime);
			}
		});
		timeColumn.getColumn().addSelectionListener(
				getSelectionAdapter(timeColumn.getColumn(), 0));
	}

	private void setupMessageColumn() {
		TableViewerColumn messageColumn = new TableViewerColumn(this, SWT.NONE);
		messageColumn.getColumn().setText("Analyzer Error Message");
		messageColumn.getColumn().setWidth(200);
		messageColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalysisInformation analysis = (AnalysisInformation) element;
				if (analysis.wasError()) {
					return analysis.getMessage();
				}
				return "";
			}
		});
		messageColumn.getColumn().addSelectionListener(
				getSelectionAdapter(messageColumn.getColumn(), 0));
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,
			final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				getTable().setSortDirection(dir);
				getTable().setSortColumn(column);
				refresh();
			}
		};
		return selectionAdapter;
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
			List<AnalysisInformation> collection = (List<AnalysisInformation>) newInput;
			files.addAll(collection);
		}
		refresh();
	}

	@Override
	public AnalysisInformation[] getElements(Object inputElement) {
		return files.toArray(new AnalysisInformation[files.size()]);
	}

	public AnalysisInformation getSelectedAnalyzedCode() {
		IStructuredSelection selection = (IStructuredSelection) getSelection();
		return (AnalysisInformation) selection.getFirstElement();
	}

}
