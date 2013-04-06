package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.evaluation.MetricsMapViewSettingsDialog;
import com.puresol.coding.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.coding.client.common.ui.actions.PartSettingsCapability;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.components.AreaMapComponent;
import com.puresol.coding.client.common.ui.components.AreaMapData;
import com.puresol.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class MetricsMapView extends ViewPart implements Refreshable,
	ISelectionListener, EvaluationsTarget, PartSettingsCapability {

    private FileAnalysisSelection analysisSelection;

    private Label label;

    private Composite container;
    private AreaMapComponent areaMap;

    private MetricsMapViewSettingsDialog settingsDialog;

    private EvaluatorFactory mapMetricSelection;
    private Parameter<?> mapValueSelection;
    private EvaluatorFactory colorMetricSelection = null;
    private Parameter<?> colorValueSelection = null;

    public MetricsMapView() {
	super();
    }

    /**
     * Create contents of the view part.
     * 
     * @param parent
     */
    @Override
    public void createPartControl(Composite parent) {
	container = new Composite(parent, SWT.BORDER);
	container.setLayout(new FormLayout());

	label = new Label(container, SWT.BORDER);
	{
	    FormData fd_label = new FormData();
	    fd_label.left = new FormAttachment(0, 10);
	    fd_label.right = new FormAttachment(100, -10);
	    fd_label.top = new FormAttachment(0, 10);
	    label.setLayoutData(fd_label);
	}

	areaMap = new AreaMapComponent(container, SWT.NONE);
	{
	    FormData fd_areaMap = new FormData();
	    fd_areaMap.left = new FormAttachment(label, 0, SWT.LEFT);
	    fd_areaMap.right = new FormAttachment(label, 0, SWT.RIGHT);
	    fd_areaMap.top = new FormAttachment(label, 10);
	    fd_areaMap.bottom = new FormAttachment(100, -10);
	    areaMap.setLayoutData(fd_areaMap);
	}

	IWorkbenchPartSite site = getSite();
	site.getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);

	initializeToolBar();
	initializeMenu();
    }

    /**
     * Initialize the toolbar.
     */
    private void initializeToolBar() {
	IToolBarManager toolbarManager = getViewSite().getActionBars()
		.getToolBarManager();
	toolbarManager.add(new ShowSettingsAction(this));
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
	areaMap.setFocus();
    }

    @Override
    public void refresh() {
	settingsDialog.refresh();
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof FileAnalysisSelection) {
	    analysisSelection = (FileAnalysisSelection) selection;
	    updateEvaluation();
	}
    }

    private void updateEvaluation() {
	if ((analysisSelection != null) && (mapMetricSelection != null)
		&& (mapValueSelection != null)) {
	    HashIdFileTree path = analysisSelection.getHashIdFile();
	    if (path.isFile()) {
		path = path.getParent();
	    }
	    showEvaluation(path);
	}
    }

    @Override
    public void showEvaluation(HashIdFileTree path) {
	label.setText(path.getPathFile(false).getPath());
	EvaluatorStore mapStore = EvaluatorStoreFactory.getFactory()
		.createInstance(mapMetricSelection.getEvaluatorClass());
	AreaMapData data = getAreaData(mapStore, path);
	areaMap.setData(data, mapValueSelection.getUnit());
    }

    private AreaMapData getAreaData(EvaluatorStore mapStore, HashIdFileTree path) {
	List<AreaMapData> childAreas = calculateChildAreaMaps(mapStore, path);
	MetricResults results;
	if (path.isFile()) {
	    results = mapStore.readFileResults(path.getHashId());
	} else {
	    results = mapStore.readDirectoryResults(path.getHashId());
	}
	if ((results == null) || (results.getValues().size() == 0)) {
	    return processAreaWithoutOwnValues(path,
		    childAreas.toArray(new AreaMapData[childAreas.size()]));
	}
	List<Map<String, Value<?>>> values = results.getValues();
	Map<String, Value<?>> valueMap = null;
	if (path.isFile()) {
	    String codeRangeTypeParameterName = CodeRangeTypeParameter
		    .getInstance().getName();
	    if (values.size() == 1) {
		valueMap = values.get(0);
	    } else {
		for (Map<String, Value<?>> value : values) {
		    if (value.get(codeRangeTypeParameterName).getValue()
			    .equals(CodeRangeType.FILE)) {
			valueMap = value;
			break;
		    }
		}
		if (valueMap == null) {
		    throw new RuntimeException("File '"
			    + path.getPathFile(false)
			    + "' contains no FILE result for evaluator '"
			    + mapMetricSelection.getName()
			    + "' and contains multiple values!");
		}
	    }
	} else {
	    if (values.size() != 1) {
		throw new RuntimeException("Directory '"
			+ path.getPathFile(false)
			+ "' contains more than one result for evaluator '"
			+ mapMetricSelection.getName() + "'!");
	    }
	    valueMap = values.get(0);
	}
	double sum = 0.0;
	Value<?> value = valueMap.get(mapValueSelection.getName());
	if (Number.class.isAssignableFrom(mapValueSelection.getType())) {
	    sum = ((Number) value.getValue()).doubleValue();
	} else {
	    throw new RuntimeException("Value '" + value
		    + "' is not a number for '" + path.getPathFile(false)
		    + "'!");
	}
	return new AreaMapData(path.getPathFile(false).toString(), sum,
		childAreas.toArray(new AreaMapData[childAreas.size()]));
    }

    private List<AreaMapData> calculateChildAreaMaps(EvaluatorStore store,
	    HashIdFileTree path) {
	List<HashIdFileTree> children = path.getChildren();
	List<AreaMapData> childAreas = new ArrayList<AreaMapData>();
	for (int i = 0; i < children.size(); i++) {
	    AreaMapData areaData = getAreaData(store, children.get(i));
	    if (areaData != null) {
		childAreas.add(areaData);
	    }
	}
	Collections.sort(childAreas);
	return childAreas;
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
    public void showSettings() {
	if (settingsDialog == null) {
	    settingsDialog = new MetricsMapViewSettingsDialog(this,
		    mapMetricSelection, mapValueSelection,
		    colorMetricSelection, colorValueSelection);
	    settingsDialog.open();
	} else {
	    settingsDialog.close();
	    settingsDialog = null;
	}
    }

    @Override
    public void closeSettings() {
	settingsDialog = null;
    }

    @Override
    public void applySettings() {
	mapMetricSelection = settingsDialog.getMapMetric();
	mapValueSelection = settingsDialog.getMapValue();
	colorMetricSelection = settingsDialog.getColorMetric();
	colorValueSelection = settingsDialog.getColorValue();
	updateEvaluation();
    }
}
