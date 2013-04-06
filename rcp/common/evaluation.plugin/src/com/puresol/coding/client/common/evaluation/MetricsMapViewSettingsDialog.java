package com.puresol.coding.client.common.evaluation;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

import com.puresol.coding.client.common.evaluation.contents.MetricComboViewer;
import com.puresol.coding.client.common.evaluation.contents.ParameterComboViewer;
import com.puresol.coding.client.common.evaluation.views.MetricsMapView;
import com.puresol.coding.client.common.ui.actions.AbstractPartSettingsDialog;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;

public class MetricsMapViewSettingsDialog extends AbstractPartSettingsDialog
	implements SelectionListener {

    private Combo mapMetricCombo;
    private Combo mapValueCombo;
    private Combo colorMetricCombo;
    private Combo colorValueCombo;

    private ParameterComboViewer mapValueComboViewer;
    private ParameterComboViewer colorValueComboViewer;
    private MetricComboViewer mapMetricComboViewer;
    private MetricComboViewer colorMetricComboViewer;

    private EvaluatorFactory mapMetricSelection = null;
    private Parameter<?> mapValueSelection = null;
    private EvaluatorFactory colorMetricSelection = null;
    private Parameter<?> colorValueSelection = null;

    public MetricsMapViewSettingsDialog(MetricsMapView view,
	    EvaluatorFactory mapMetricSelection,
	    Parameter<?> mapValueSelection,
	    EvaluatorFactory colorMetricSelection,
	    Parameter<?> colorValueSelection) {
	super(view);
	this.mapMetricSelection = mapMetricSelection;
	this.mapValueSelection = mapValueSelection;
	this.colorMetricSelection = colorMetricSelection;
	this.colorValueSelection = colorValueSelection;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
	Composite container = new Composite(parent, SWT.NONE);
	container.setLayout(new FillLayout());

	Group mapSettingsGroup = new Group(container, SWT.NONE);
	{
	    mapSettingsGroup.setText("Map Settings");
	    mapSettingsGroup.setLayout(new FormLayout());

	    mapMetricCombo = new Combo(mapSettingsGroup, SWT.READ_ONLY);
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

	    mapValueCombo = new Combo(mapSettingsGroup, SWT.READ_ONLY);
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
	}
	Group colorSettingsGroup = new Group(container, SWT.NONE);
	{
	    {
		colorSettingsGroup.setText("Color Settings");
		colorSettingsGroup.setLayout(new FormLayout());
	    }

	    colorMetricCombo = new Combo(colorSettingsGroup, SWT.READ_ONLY);
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

	    colorValueCombo = new Combo(colorSettingsGroup, SWT.READ_ONLY);
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
	populateCombos();
	return container;
    }

    private void populateCombos() {
	List<EvaluatorFactory> allMetrics = Evaluators.createInstance()
		.getAllMetrics();
	mapMetricComboViewer.setInput(allMetrics);
	if (mapMetricSelection != null) {
	    mapValueComboViewer.setInput(mapMetricSelection.getParameters());
	    mapMetricComboViewer.setSelection(new StructuredSelection(
		    mapMetricSelection));
	    if (mapValueSelection != null) {
		mapValueComboViewer.setSelection(new StructuredSelection(
			mapValueSelection));
	    }
	} else {
	    mapValueComboViewer.setInput(null);
	}
	colorMetricComboViewer.setInput(allMetrics);
	if (colorMetricSelection != null) {
	    colorValueComboViewer
		    .setInput(colorMetricSelection.getParameters());
	    colorMetricComboViewer.setSelection(new StructuredSelection(
		    colorMetricSelection));
	    if (colorValueSelection != null) {
		colorValueComboViewer.setSelection(new StructuredSelection(
			colorValueSelection));
	    }
	} else {
	    colorValueComboViewer.setInput(null);
	}
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
	if (e.getSource() == mapMetricCombo) {
	    mapMetricChanged();
	} else if (e.getSource() == mapValueCombo) {
	    mapValueChanged();
	} else if (e.getSource() == colorMetricCombo) {
	    colorMetricChanged();
	} else if (e.getSource() == colorValueCombo) {
	    colorValueChange();
	}
    }

    private void mapMetricChanged() {
	StructuredSelection selection = (StructuredSelection) mapMetricComboViewer
		.getSelection();
	mapMetricSelection = (EvaluatorFactory) selection.getFirstElement();
	List<Parameter<?>> allParameters = mapMetricSelection.getParameters();
	List<Parameter<?>> comboParameters = new ArrayList<Parameter<?>>();
	for (Parameter<?> parameter : allParameters) {
	    if (parameter.getLevelOfMeasurement() == LevelOfMeasurement.RATIO) {
		comboParameters.add(parameter);
	    }
	}
	mapValueComboViewer.setInput(comboParameters);
    }

    private void mapValueChanged() {
	StructuredSelection selection = (StructuredSelection) mapValueComboViewer
		.getSelection();
	mapValueSelection = (Parameter<?>) selection.getFirstElement();
    }

    private void colorMetricChanged() {
	StructuredSelection selection = (StructuredSelection) colorMetricComboViewer
		.getSelection();
	colorMetricSelection = (EvaluatorFactory) selection.getFirstElement();
	List<Parameter<?>> allParameters = colorMetricSelection.getParameters();
	List<Parameter<?>> comboParameters = new ArrayList<Parameter<?>>();
	for (Parameter<?> parameter : allParameters) {
	    if ((parameter.getLevelOfMeasurement() == LevelOfMeasurement.ORDINAL)
		    || (parameter.getLevelOfMeasurement() == LevelOfMeasurement.INTERVAL)
		    || (parameter.getLevelOfMeasurement() == LevelOfMeasurement.RATIO)) {
		comboParameters.add(parameter);
	    }
	}
	colorValueComboViewer.setInput(comboParameters);
    }

    private void colorValueChange() {
	StructuredSelection selection = (StructuredSelection) colorValueComboViewer
		.getSelection();
	colorValueSelection = (Parameter<?>) selection.getFirstElement();
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
	populateCombos();
    }

    public EvaluatorFactory getMapMetric() {
	return mapMetricSelection;
    }

    public Parameter<?> getMapValue() {
	return mapValueSelection;
    }

    public EvaluatorFactory getColorMetric() {
	return colorMetricSelection;
    }

    public Parameter<?> getColorValue() {
	return colorValueSelection;
    }

    @Override
    protected boolean canApply() {
	if (mapMetricSelection == null) {
	    return false;
	}
	if (mapValueSelection == null) {
	    return false;
	}
	if (colorMetricSelection == null) {
	    return false;
	}
	if (colorValueSelection == null) {
	    return false;
	}
	return true;
    }

    @Override
    public void refresh() {
	populateCombos();
    }
}
