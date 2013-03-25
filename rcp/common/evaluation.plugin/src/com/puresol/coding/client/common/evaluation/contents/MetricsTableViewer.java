package com.puresol.coding.client.common.evaluation.contents;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.utils.math.Value;

public class MetricsTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private EvaluatorFactory metric = null;
	private MetricResults results = null;

	public MetricsTableViewer(Table table) {
		super(table);
		setContentProvider(this);
		setupNameColumn();
		setupValueColumn();
		setupUnitColumn();
	}

	public void setMetric(EvaluatorFactory metric) {
		this.metric = metric;
	}

	private void setupNameColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Metric");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				@SuppressWarnings("unchecked")
				Map<String, Value<?>> metric = (Map<String, Value<?>>) element;
				return "Metric";
			}
		});
	}

	private void setupValueColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Value");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				@SuppressWarnings("unchecked")
				Map<String, Value<?>> metric = (Map<String, Value<?>>) element;
				return "Value";
			}
		});
	}

	private void setupUnitColumn() {
		TableViewerColumn unitColumn = new TableViewerColumn(this, SWT.NONE);
		unitColumn.getColumn().setText("Unit");
		unitColumn.getColumn().setWidth(100);
		unitColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				@SuppressWarnings("unchecked")
				Map<String, Value<?>> metric = (Map<String, Value<?>>) element;
				return "Unit";
			}
		});
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		results = null;
		if (newInput == null) {
			return;
		}
		if (metric == null) {
			return;
		}
		if (HashIdFileTree.class.isAssignableFrom(newInput.getClass())) {
			HashIdFileTree directory = (HashIdFileTree) newInput;
			EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
					.createInstance(metric.getEvaluatorClass());
			if (directory.isFile()) {
				results = evaluatorStore.readFileResults(directory.getHashId());
			} else {
				results = evaluatorStore.readDirectoryResults(directory
						.getHashId());
			}
		}
		refresh();
	}

	@Override
	public Map<String, Value<?>>[] getElements(Object inputElement) {
		List<Map<String, Value<?>>> values = results.getValues();
		@SuppressWarnings("unchecked")
		Map<String, Value<?>>[] valueArray = values.toArray(new Map[values
				.size()]);
		return valueArray;
	}
}
