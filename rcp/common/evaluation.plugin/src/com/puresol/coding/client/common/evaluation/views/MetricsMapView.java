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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.evaluation.contents.MetricComboViewer;
import com.puresol.coding.client.common.evaluation.contents.ParameterComboViewer;
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

    private Label label;
    private Combo mapMetricCombo;
    private Combo mapValueCombo;
    private Combo colorMetricCombo;
    private Combo colorValueCombo;

    private AreaMapComponent areaMap;

    private ParameterComboViewer mapValueComboViewer;
    private ParameterComboViewer colorValueComboViewer;
    private MetricComboViewer mapMetricComboViewer;
    private MetricComboViewer colorMetricComboViewer;

    private HashIdFileTree path;

    private final List<Parameter<?>> parameterList = new ArrayList<Parameter<?>>();
    private HashIdFileTree lastPathSelection;
    private EvaluatorFactory lastMetricSelection;

    public MetricsMapView() {
    }

    /**
     * Create contents of the view part.
     * 
     * @param parent
     */
    @Override
    public void createPartControl(Composite parent) {
	Composite container = new Composite(parent, SWT.BORDER);
	container.setLayout(new FormLayout());

	label = new Label(container, SWT.BORDER);
	{
	    FormData fd_label = new FormData();
	    fd_label.left = new FormAttachment(0, 10);
	    fd_label.right = new FormAttachment(100, -10);
	    fd_label.top = new FormAttachment(0, 10);
	    label.setLayoutData(fd_label);
	}

	Composite settings = new Composite(container, SWT.NONE);
	{
	    settings.setLayout(new FillLayout(SWT.HORIZONTAL));
	    FormData fd_settings = new FormData();
	    fd_settings.left = new FormAttachment(label, 0, SWT.LEFT);
	    fd_settings.right = new FormAttachment(label, 0, SWT.RIGHT);
	    fd_settings.top = new FormAttachment(label, 10);
	    settings.setLayoutData(fd_settings);
	}

	areaMap = new AreaMapComponent(container, SWT.NONE);
	{
	    FormData fd_areaMap = new FormData();
	    fd_areaMap.left = new FormAttachment(label, 0, SWT.LEFT);
	    fd_areaMap.right = new FormAttachment(label, 0, SWT.RIGHT);
	    fd_areaMap.top = new FormAttachment(settings, 10);
	    fd_areaMap.bottom = new FormAttachment(100, -10);
	    areaMap.setLayoutData(fd_areaMap);
	}

	Group mapSettingsGroup = new Group(settings, SWT.NONE);
	{
	    mapSettingsGroup.setText("Map Settings");
	    mapSettingsGroup.setLayout(new FormLayout());

	    mapMetricCombo = new Combo(mapSettingsGroup, SWT.NONE);
	    {
		FormData fd_mapMetricsCombo = new FormData();
		fd_mapMetricsCombo.left = new FormAttachment(0, 10);
		fd_mapMetricsCombo.right = new FormAttachment(100, -10);
		fd_mapMetricsCombo.top = new FormAttachment(0, 10);
		mapMetricCombo.setLayoutData(fd_mapMetricsCombo);
		mapMetricCombo.setEnabled(true);
		mapMetricCombo.addSelectionListener(this);
		mapMetricComboViewer = new MetricComboViewer(mapMetricCombo);
	    }

	    mapValueCombo = new Combo(mapSettingsGroup, SWT.NONE);
	    {
		FormData fd_mapValueCombo = new FormData();
		fd_mapValueCombo.left = new FormAttachment(mapMetricCombo, 0,
			SWT.LEFT);
		fd_mapValueCombo.right = new FormAttachment(mapMetricCombo, 0,
			SWT.RIGHT);
		fd_mapValueCombo.top = new FormAttachment(mapMetricCombo, 10);
		fd_mapValueCombo.bottom = new FormAttachment(100, -10);
		mapValueCombo.setLayoutData(fd_mapValueCombo);
		mapValueCombo.setEnabled(true);
		mapValueCombo.addSelectionListener(this);
		mapValueComboViewer = new ParameterComboViewer(mapValueCombo);
	    }

	    Group colorSettingsGroup = new Group(settings, SWT.NONE);
	    {
		colorSettingsGroup.setText("Color Settings");
		colorSettingsGroup.setLayout(new FormLayout());
	    }

	    colorMetricCombo = new Combo(colorSettingsGroup, SWT.NONE);
	    {
		FormData fd_colorMetricCombo = new FormData();
		fd_colorMetricCombo.left = new FormAttachment(0, 10);
		fd_colorMetricCombo.right = new FormAttachment(100, -10);
		fd_colorMetricCombo.top = new FormAttachment(0, 10);
		colorMetricCombo.setLayoutData(fd_colorMetricCombo);
		colorMetricCombo.setEnabled(true);
		colorMetricCombo.addSelectionListener(this);
		colorMetricComboViewer = new MetricComboViewer(colorMetricCombo);
	    }

	    colorValueCombo = new Combo(colorSettingsGroup, SWT.NONE);
	    {
		FormData fd_colorValueCombo = new FormData();
		fd_colorValueCombo.left = new FormAttachment(colorMetricCombo,
			0, SWT.LEFT);
		fd_colorValueCombo.right = new FormAttachment(colorMetricCombo,
			0, SWT.RIGHT);
		fd_colorValueCombo.top = new FormAttachment(colorMetricCombo,
			10);
		fd_colorValueCombo.bottom = new FormAttachment(100, -10);
		colorValueCombo.setLayoutData(fd_colorValueCombo);
		colorValueCombo.setEnabled(true);
		colorValueCombo.addSelectionListener(this);
		colorValueComboViewer = new ParameterComboViewer(
			colorValueCombo);
	    }
	}

	IWorkbenchPartSite site = getSite();
	site.getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);

	initializeToolBar();
	initializeMenu();
	populateCombos();
    }

    private void populateCombos() {
	List<EvaluatorFactory> allMetrics = Evaluators.createInstance()
		.getAllMetrics();
	mapMetricComboViewer.setInput(allMetrics);
	colorMetricComboViewer.setInput(allMetrics);
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
	populateCombos();
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof FileAnalysisSelection) {
	    analysisSelection = (FileAnalysisSelection) selection;
	    updateEvaluation();
	}
    }

    private void updateEvaluation() {
	if ((analysisSelection != null) && (metricSelection != null)) {
	    HashIdFileTree path = analysisSelection.getHashIdFile();

	    if (path.isFile()) {
		path = path.getParent();
	    }
	    if ((!path.equals(lastPathSelection))
		    || (!metricSelection.equals(lastMetricSelection))) {
		lastPathSelection = path;
		lastMetricSelection = metricSelection;
		showEvaluation(path);
	    }
	}
    }

    @Override
    public void showEvaluation(HashIdFileTree path) {
	this.path = path;
	label.setText(path.getPathFile(false).getPath());
	AreaMapData data = calculateMapDataAndParameterList(path);
	mapValueComboViewer.setInput(parameterList);
	mapValueCombo.select(0);
	if (parameterList.size() > 0) {
	    areaMap.setData(data, parameterList.get(0).getUnit());
	} else {
	    areaMap.setData(data, "");
	}
    }

    private AreaMapData calculateMapDataAndParameterList(HashIdFileTree path) {
	parameterList.clear();
	EvaluatorStore store = EvaluatorStoreFactory.getFactory()
		.createInstance(metricSelection.getEvaluatorClass());
	return getAreaData(store, path);
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
	Map<String, Value<?>> valueMap = values.get(0);
	double sum = 0.0;
	if (parameterSelection.getType().equals(Double.class)) {
	    Value<?> valueObject = valueMap.get(parameterSelection.getName());
	    sum = (Double) valueObject.getValue();
	} else if (parameterSelection.getType().equals(Integer.class)) {
	    Value<?> value = valueMap.get(parameterSelection.getName());
	    if (value != null) {
		sum = (Integer) value.getValue();
	    }
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
	if (e.getSource() == mapMetricCombo) {
	    StructuredSelection selection = (StructuredSelection) mapMetricComboViewer
		    .getSelection();
	    parameterSelection = (Parameter<?>) selection.getFirstElement();
	    EvaluatorStore store = EvaluatorStoreFactory.getFactory()
		    .createInstance(metricSelection.getEvaluatorClass());
	    AreaMapData areaData = getAreaData(store, path);
	    areaMap.setData(areaData, parameterSelection.getUnit());
	}
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
	widgetSelected(e);
    }
}
