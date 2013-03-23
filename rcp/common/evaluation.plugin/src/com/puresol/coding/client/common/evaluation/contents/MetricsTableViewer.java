package com.puresol.coding.client.common.evaluation.contents;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.DirectoryResults;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.utils.math.PhysicalValue;

public class MetricsTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final List<PhysicalValue<Double>> metrics = new ArrayList<PhysicalValue<Double>>();
	private Class<? extends Evaluator> metric = null;

	public MetricsTableViewer(Table table) {
		super(table);
		setContentProvider(this);
		setupNameColumn();
		setupValueColumn();
		setupUnitColumn();
	}

	public void setMetric(Class<? extends Evaluator> metric) {
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
				PhysicalValue<Double> metric = (PhysicalValue<Double>) element;
				return metric.getProperty().getName();
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
				PhysicalValue<Double> metric = (PhysicalValue<Double>) element;
				return String.valueOf(metric.getValue());
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
				PhysicalValue<Double> metric = (PhysicalValue<Double>) element;
				return metric.getProperty().getUnit();
			}
		});
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		metrics.clear();
		if (newInput == null) {
			return;
		}
		if (HashIdFileTree.class.isAssignableFrom(newInput.getClass())) {
			HashIdFileTree directory = (HashIdFileTree) newInput;
			if (!directory.isFile()) {
				EvaluatorStore evaluatorStore = EvaluatorStoreFactory
						.getFactory().createInstance(metric);
				for (HashIdFileTree child : directory.getChildren()) {
					if (child.isFile()) {
						// file
						FileResults fileResults = evaluatorStore
								.readFileResults(child.getHashId());
					} else {
						// directory
						DirectoryResults directoryResults = evaluatorStore
								.readDirectoryResults(child.getHashId());
					}
				}
			}
		}
		refresh();
	}

	@Override
	public EvaluatorFactory[] getElements(Object inputElement) {
		return metrics.toArray(new EvaluatorFactory[metrics.size()]);
	}

}
