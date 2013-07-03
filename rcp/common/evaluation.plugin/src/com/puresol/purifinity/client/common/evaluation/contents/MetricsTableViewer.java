package com.puresol.purifinity.client.common.evaluation.contents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.eclipse.swt.widgets.TableColumn;

import com.puresol.commons.utils.HashId;
import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;

public class MetricsTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final EvaluatorFactory metric;

	private final List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

	public MetricsTableViewer(Table table, EvaluatorFactory metric) {
		super(table);
		this.metric = metric;
		setContentProvider(this);
		ColumnViewerToolTipSupport.enableFor(this, ToolTip.NO_RECREATE);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		values.clear();
		for (TableColumn column : getTable().getColumns()) {
			column.dispose();
		}
		if (newInput == null) {
			return;
		}
		if (metric == null) {
			return;
		}
		if (HashIdFileTree.class.isAssignableFrom(newInput.getClass())) {
			HashIdFileTree path = (HashIdFileTree) newInput;
			HashId hashId = path.getHashId();
			EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
					.createInstance(metric.getEvaluatorClass());
			if (path.isFile()) {
				if (evaluatorStore.hasFileResults(hashId)) {
					MetricFileResults results = evaluatorStore
							.readFileResults(hashId);
					if (results != null) {
						setResults(results.getParameters());
						values.addAll(results.getValues());
					}
				}
			} else {
				if (evaluatorStore.hasDirectoryResults(hashId)) {
					MetricDirectoryResults results = evaluatorStore
							.readDirectoryResults(hashId);
					if (results != null) {
						setResults(results.getParameters());
						values.add(results.getValues());
					}
				}
			}
		}
		refresh();
	}

	private void setResults(Set<Parameter<?>> parameters) {
		for (Parameter<?> parameter : parameters) {
			TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
			final String name = parameter.getName();
			final String unit = parameter.getUnit();
			final String description = parameter.getDescription();
			if ((unit != null) && (!unit.isEmpty())) {
				nameColumn.getColumn().setText(name + " [" + unit + "]");
				nameColumn.getColumn().setToolTipText(
						name + " [" + unit + "]" + ":\n\n" + description);
			} else {
				nameColumn.getColumn().setText(name);
				nameColumn.getColumn().setToolTipText(
						name + ":\n\n" + description);
			}
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
					@SuppressWarnings("unchecked")
					Map<String, Value<?>> values = (Map<String, Value<?>>) element;
					Value<?> value = values.get(name);
					if (value == null) {
						return "";
					}
					if ((unit != null) && (!unit.isEmpty())) {
						return name + " [" + unit + "]" + ":\n\n" + description;
					} else {
						return name + ":\n\n" + description;
					}
				}

				@Override
				public String getText(Object element) {
					@SuppressWarnings("unchecked")
					Map<String, Value<?>> values = (Map<String, Value<?>>) element;
					Value<?> value = values.get(name);
					if (value == null) {
						return "";
					}
					return value.getValue().toString();
				}

			};
			nameColumn.setLabelProvider(labelProvider);
		}
	}

	@Override
	public Map<String, Value<?>>[] getElements(Object inputElement) {
		@SuppressWarnings("unchecked")
		Map<String, Value<?>>[] valueArray = values.toArray(new Map[values
				.size()]);
		return valueArray;
	}
}
