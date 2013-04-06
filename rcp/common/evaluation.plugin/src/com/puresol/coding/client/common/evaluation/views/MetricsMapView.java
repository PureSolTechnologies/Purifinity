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
	// Set the focus
    }

    @Override
    public void refresh() {
	// TODO
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
	AreaMapData data = calculateMapDataAndParameterList(path);
	areaMap.setData(data, mapValueSelection.getUnit());
    }

    private AreaMapData calculateMapDataAndParameterList(HashIdFileTree path) {
	EvaluatorStore store = EvaluatorStoreFactory.getFactory()
		.createInstance(mapMetricSelection.getEvaluatorClass());
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
	List<Map<String, Value<?>>> values = results.getValues();
	Map<String, Value<?>> valueMap = values.get(0);
	double sum = 0.0;
	if (mapValueSelection.getType().equals(Double.class)) {
	    Value<?> valueObject = valueMap.get(mapValueSelection.getName());
	    sum = (Double) valueObject.getValue();
	} else if (mapValueSelection.getType().equals(Integer.class)) {
	    Value<?> value = valueMap.get(mapValueSelection.getName());
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
