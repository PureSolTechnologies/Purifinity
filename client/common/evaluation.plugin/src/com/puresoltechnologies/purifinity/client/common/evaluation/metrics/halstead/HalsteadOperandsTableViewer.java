package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.halstead;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class HalsteadOperandsTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final List<Entry<String, Integer>> operands = new ArrayList<Entry<String, Integer>>();
	private final OperatorsAndOperandsViewerSorter comparator;

	public HalsteadOperandsTableViewer(Table table) {
		super(table);
		setContentProvider(this);
		comparator = new OperatorsAndOperandsViewerSorter();
		setComparator(comparator);
		setupNameColumn();
		setupNumberColumn();
	}

	private void setupNameColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				@SuppressWarnings("unchecked")
				Entry<String, Integer> operand = (Entry<String, Integer>) element;
				return operand.getKey();
			}
		});
		nameColumn.getColumn().addSelectionListener(
				getSelectionAdapter(nameColumn.getColumn(), 0));
	}

	private void setupNumberColumn() {
		TableViewerColumn numberColumn = new TableViewerColumn(this, SWT.NONE);
		numberColumn.getColumn().setText("Number");
		numberColumn.getColumn().setWidth(100);
		numberColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				@SuppressWarnings("unchecked")
				Entry<String, Integer> operand = (Entry<String, Integer>) element;
				return String.valueOf(operand.getValue());
			}
		});
		numberColumn.getColumn().addSelectionListener(
				getSelectionAdapter(numberColumn.getColumn(), 1));
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
		// intentionally left empty
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		operands.clear();
		if (newInput != null) {
			if (Collection.class.isAssignableFrom(newInput.getClass())) {
				@SuppressWarnings("unchecked")
				Collection<Entry<String, Integer>> collection = (Collection<Entry<String, Integer>>) newInput;
				operands.addAll(collection);
			}
		}
		refresh();
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return operands.toArray();
	}

}
