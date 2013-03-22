package com.puresol.coding.client.common.evaluation.contents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import com.puresol.coding.evaluation.api.EvaluatorFactory;

public class MetricsTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final List<EvaluatorFactory> evaluators = new ArrayList<EvaluatorFactory>();

	public MetricsTableViewer(Table table) {
		super(table);
		setContentProvider(this);
		setupNameColumn();
		setupValueColumn();
		setupUnitColumn();
	}

	private void setupNameColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Metric");
		nameColumn.getColumn().setWidth(100);
		// nameColumn.setLabelProvider(new ColumnLabelProvider() {
		// @Override
		// public String getText(Object element) {
		// EvaluatorFactory evaluator = (EvaluatorFactory) element;
		// return evaluator.getName();
		// }
		// });
	}

	private void setupValueColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Value");
		nameColumn.getColumn().setWidth(100);
		// nameColumn.setLabelProvider(new ColumnLabelProvider() {
		// @Override
		// public String getText(Object element) {
		// EvaluatorFactory evaluator = (EvaluatorFactory) element;
		// return evaluator.getDescription();
		// }
		// });
	}

	private void setupUnitColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Value");
		nameColumn.getColumn().setWidth(100);
		// nameColumn.setLabelProvider(new ColumnLabelProvider() {
		// @Override
		// public String getText(Object element) {
		// EvaluatorFactory evaluator = (EvaluatorFactory) element;
		// return evaluator.getDescription();
		// }
		// });
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		evaluators.clear();
		if (newInput == null) {
			return;
		}
		if (Collection.class.isAssignableFrom(newInput.getClass())) {
			@SuppressWarnings("unchecked")
			Collection<EvaluatorFactory> collection = (Collection<EvaluatorFactory>) newInput;
			evaluators.addAll(collection);
		}
		refresh();
	}

	@Override
	public EvaluatorFactory[] getElements(Object inputElement) {
		return evaluators.toArray(new EvaluatorFactory[evaluators.size()]);
	}

}
