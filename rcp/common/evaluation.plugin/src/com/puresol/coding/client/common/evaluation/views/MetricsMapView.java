package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.evaluation.contents.ParameterComboViewer;
import com.puresol.coding.client.common.evaluation.utils.EvaluationTool;
import com.puresol.coding.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.components.AreaMapComponent;
import com.puresol.coding.client.common.ui.components.AreaMapData;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.Evaluators;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class MetricsMapView extends ViewPart implements Refreshable,
	ISelectionListener, EvaluationsTarget, SelectionListener {

    private FileAnalysisSelection analysisSelection;
    private EvaluatorFactory metricSelection;
    private Parameter<?> parameterSelection;

    private Text text;
    private Combo combo;
    private AreaMapComponent areaMap;
    private ParameterComboViewer viewer;
    private HashIdFileTree path;

    private final List<Parameter<?>> parameterList = new ArrayList<Parameter<?>>();
    private HashIdFileTree lastSelection;

    public MetricsMapView() {
    }

    /**
     * Create contents of the view part.
     * 
     * @param parent
     */
    @Override
    public void createPartControl(Composite parent) {
	Composite container = new Composite(parent, SWT.NONE);
	container.setLayout(new FormLayout());
	{
	    text = new Text(container, SWT.BORDER);
	    FormData fd_text = new FormData();
	    fd_text.top = new FormAttachment(0, 10);
	    fd_text.left = new FormAttachment(0, 10);
	    fd_text.bottom = new FormAttachment(0, 32);
	    fd_text.right = new FormAttachment(100, -10);
	    text.setLayoutData(fd_text);
	    text.setEditable(false);
	}

	IWorkbenchPartSite site = getSite();
	site.getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);

	areaMap = new AreaMapComponent(container, SWT.NONE);
	FormData fd_areaMap = new FormData();
	fd_areaMap.left = new FormAttachment(text, 0, SWT.LEFT);
	fd_areaMap.bottom = new FormAttachment(100, -10);
	fd_areaMap.right = new FormAttachment(100, -10);
	areaMap.setLayoutData(fd_areaMap);

	combo = new Combo(container, SWT.NONE);
	fd_areaMap.top = new FormAttachment(combo, 6);
	FormData fd_combo = new FormData();
	fd_combo.left = new FormAttachment(text, 0, SWT.LEFT);
	fd_combo.top = new FormAttachment(text, 6);
	fd_combo.right = new FormAttachment(100, -10);
	combo.setLayoutData(fd_combo);
	combo.addSelectionListener(this);

	viewer = new ParameterComboViewer(combo);

	initializeToolBar();
	initializeMenu();
    }

    /**
     * Initialize the toolbar.
     */
    private void initializeToolBar() {
	IToolBarManager toolbarManager = getViewSite().getActionBars()
		.getToolBarManager();
	toolbarManager.add(new RefreshAction(this));
    }

    /**
     * Initialize the menu.
     */
    private void initializeMenu() {
	IMenuManager menuManager = getViewSite().getActionBars()
		.getMenuManager();
    }

    @Override
    public void setFocus() {
	// Set the focus
    }

    @Override
    public void refresh() {
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof FileAnalysisSelection) {
	    analysisSelection = (FileAnalysisSelection) selection;
	    updateEvaluation();
	} else if (selection instanceof MetricSelection) {
	    metricSelection = ((MetricSelection) selection).getMetric();
	    updateEvaluation();
	}
    }

    private void updateEvaluation() {
	if ((analysisSelection != null) && (metricSelection != null)) {
	    AnalysisRun analysisRun = analysisSelection.getAnalysisRun();
	    HashIdFileTree path = analysisSelection.getHashIdFile();

	    EvaluatorFactory evaluatorFactory = Evaluators.createInstance()
		    .getAllMetrics().get(0);

	    if (path.isFile()) {
		path = path.getParent();
	    }
	    if (!path.equals(lastSelection)) {
		lastSelection = path;
		EvaluationTool.showEvaluationAsynchronous(this,
			evaluatorFactory, analysisRun, path);
	    }
	}
    }

    @Override
    public void showEvaluation(HashIdFileTree path) {
	this.path = path;
	text.setText(path.getPathFile(false).getPath());
	AreaMapData data = calculateMapDataAndParameterList(path);
	viewer.setInput(parameterList);
	combo.select(0);
	areaMap.setData(data);
    }

    private AreaMapData calculateMapDataAndParameterList(HashIdFileTree path) {
	parameterList.clear();
	EvaluatorStore store = EvaluatorStoreFactory.getFactory()
		.createInstance(metricSelection.getEvaluatorClass());
	AreaMapData areaData = getAreaData(store, path);
	viewer.setInput(parameterList);
	return areaData;
    }

    private AreaMapData getAreaData(EvaluatorStore store, HashIdFileTree path) {
	List<HashIdFileTree> children = path.getChildren();
	List<AreaMapData> childAreas = new ArrayList<AreaMapData>();
	for (int i = 0; i < children.size(); i++) {
	    AreaMapData areaData = getAreaData(store, children.get(i));
	    if (areaData != null) {
		childAreas.add(areaData);
	    }
	}
	Collections.sort(childAreas);
	MetricResults results;
	if (path.isFile()) {
	    results = store.readFileResults(path.getHashId());
	} else {
	    results = store.readDirectoryResults(path.getHashId());
	}
	if ((results == null) || (results.getValues().size() == 0)) {
	    return processAreaWithoutOwnValues(path,
		    childAreas.toArray(new AreaMapData[childAreas.size()]));
	}
	for (Parameter<?> parameter : results.getParameters()) {
	    if (parameter.getLevelOfMeasurement() == LevelOfMeasurement.RATIO) {
		if (!parameterList.contains(parameter)) {
		    parameterList.add(parameter);
		}
	    }
	}
	if (parameterSelection == null) {
	    parameterSelection = parameterList.get(0);
	}
	List<Map<String, Value<?>>> values = results.getValues();
	Map<String, Value<?>> value = values.get(0);
	double sum = 0.0;
	if (parameterSelection.getType().equals(Double.class)) {
	    sum = (Double) value.get(parameterSelection.getName()).getValue();
	} else if (parameterSelection.getType().equals(Integer.class)) {
	    sum = (Integer) value.get(parameterSelection.getName()).getValue();
	}
	return new AreaMapData(path.getPathFile(false).toString(), sum,
		childAreas.toArray(new AreaMapData[childAreas.size()]));
    }

    private AreaMapData processAreaWithoutOwnValues(HashIdFileTree path,
	    AreaMapData[] childAreas) {
	double sum = 0.0;
	for (AreaMapData childArea : childAreas) {
	    sum += childArea.getValue();
	}
	if (sum > 0.0) {
	    return new AreaMapData(path.getPathFile(false).toString(), sum,
		    childAreas);
	} else {
	    return null;
	}
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
	if (e.getSource() == combo) {
	    StructuredSelection selection = (StructuredSelection) viewer
		    .getSelection();
	    parameterSelection = (Parameter<?>) selection.getFirstElement();
	    EvaluatorStore store = EvaluatorStoreFactory.getFactory()
		    .createInstance(metricSelection.getEvaluatorClass());
	    AreaMapData areaData = getAreaData(store, path);
	    areaMap.setData(areaData);
	}
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
	widgetSelected(e);
    }
}
