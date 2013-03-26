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
import org.eclipse.swt.widgets.TableColumn;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.utils.HashId;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class MetricsTableViewer extends TableViewer implements
	IStructuredContentProvider {

    private final EvaluatorFactory metric;
    private MetricResults results = null;

    public MetricsTableViewer(Table table, EvaluatorFactory metric) {
	super(table);
	setContentProvider(this);
	this.metric = metric;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	results = null;
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
		    results = evaluatorStore.readFileResults(hashId);
		}
	    } else {
		if (evaluatorStore.hasDirectoryResults(hashId)) {
		    results = evaluatorStore.readDirectoryResults(hashId);
		}
	    }
	    if (results != null) {
		setResults(results);
	    }
	}
	refresh();
    }

    private void setResults(MetricResults results) {
	for (Parameter<?> parameter : results.getParameters()) {
	    TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
	    final String name = parameter.getName();
	    nameColumn.getColumn().setText(name);
	    nameColumn.getColumn().setWidth(100);
	    nameColumn.setLabelProvider(new ColumnLabelProvider() {
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
	    });
	}
    }

    @Override
    public Map<String, Value<?>>[] getElements(Object inputElement) {
	if (results == null) {
	    @SuppressWarnings("unchecked")
	    Map<String, Value<?>>[] emptyResult = new Map[0];
	    return emptyResult;
	}
	List<Map<String, Value<?>>> values = results.getValues();
	@SuppressWarnings("unchecked")
	Map<String, Value<?>>[] valueArray = values.toArray(new Map[values
		.size()]);
	return valueArray;
    }
}
