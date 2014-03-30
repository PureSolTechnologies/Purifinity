package com.puresoltechnologies.purifinity.client.common.evaluation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.MetricComboViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.ParameterComboViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.MetricParameterSelectionComponent;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.HistogramChartView;
import com.puresoltechnologies.purifinity.client.common.ui.actions.AbstractPartSettingsDialog;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.Evaluators;

public class HistogramChartViewSettingsDialog extends
		AbstractPartSettingsDialog implements SelectionListener {

	private Combo metricCombo;
	private Combo parameterCombo;

	private MetricComboViewer metricComboViewer;
	private ParameterComboViewer parameterComboViewer;

	private EvaluatorFactory metricSelection = null;
	private Parameter<?> parameterSelection = null;

	public HistogramChartViewSettingsDialog(HistogramChartView view,
			EvaluatorFactory metricSelection, Parameter<?> parameterSelection) {
		super(view);
		this.metricSelection = metricSelection;
		this.parameterSelection = parameterSelection;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		Group settingsGroup = new Group(container, SWT.NONE);
		{
			settingsGroup.setText("Category Settings");
			settingsGroup.setLayout(new FormLayout());

			metricCombo = new Combo(settingsGroup, SWT.READ_ONLY);
			{
				FormData fd_metricsCombo = new FormData();
				fd_metricsCombo.left = new FormAttachment(0, 10);
				fd_metricsCombo.right = new FormAttachment(100, -10);
				fd_metricsCombo.top = new FormAttachment(0, 10);
				metricCombo.setLayoutData(fd_metricsCombo);
				metricCombo.setEnabled(true);
				metricCombo.addSelectionListener(this);
				metricComboViewer = new MetricComboViewer(metricCombo);
			}

			parameterCombo = new Combo(settingsGroup, SWT.READ_ONLY);
			{
				FormData fd_parameterCombo = new FormData();
				fd_parameterCombo.left = new FormAttachment(metricCombo, 0,
						SWT.LEFT);
				fd_parameterCombo.right = new FormAttachment(metricCombo, 0,
						SWT.RIGHT);
				fd_parameterCombo.top = new FormAttachment(metricCombo, 10);
				fd_parameterCombo.bottom = new FormAttachment(100, -10);
				parameterCombo.setLayoutData(fd_parameterCombo);
				parameterCombo.setEnabled(true);
				parameterCombo.addSelectionListener(this);
				parameterComboViewer = new ParameterComboViewer(parameterCombo);
			}
		}
		new MetricParameterSelectionComponent(container,
				LevelOfMeasurement.INTERVAL, "Category Settings");
		populateCombos();
		return container;
	}

	private void populateCombos() {
		List<EvaluatorFactory> allMetrics = Evaluators.createInstance()
				.getAllMetrics();
		metricComboViewer.setInput(allMetrics);
		if (metricSelection != null) {
			parameterComboViewer.setInput(metricSelection.getParameters());
			metricComboViewer.setSelection(new StructuredSelection(
					metricSelection));
			if (parameterSelection != null) {
				parameterComboViewer.setSelection(new StructuredSelection(
						parameterSelection));
			}
		} else {
			parameterComboViewer.setInput(null);
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == metricCombo) {
			categoryMetricChanged();
		} else if (e.getSource() == parameterCombo) {
			categoryParameterChanged();
		}
	}

	private void categoryMetricChanged() {
		StructuredSelection selection = (StructuredSelection) metricComboViewer
				.getSelection();
		metricSelection = (EvaluatorFactory) selection.getFirstElement();
		Set<Parameter<?>> allParameters = metricSelection.getParameters();
		Set<Parameter<?>> comboParameters = new HashSet<Parameter<?>>();
		for (Parameter<?> parameter : allParameters) {
			comboParameters.add(parameter);
		}
		parameterComboViewer.setInput(comboParameters);
	}

	private void categoryParameterChanged() {
		StructuredSelection selection = (StructuredSelection) parameterComboViewer
				.getSelection();
		parameterSelection = (Parameter<?>) selection.getFirstElement();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		populateCombos();
	}

	public EvaluatorFactory getMetric() {
		return metricSelection;
	}

	public Parameter<?> getParameter() {
		return parameterSelection;
	}

	@Override
	protected boolean canApply() {
		if (metricSelection == null) {
			return false;
		}
		if (parameterSelection == null) {
			return false;
		}
		return true;
	}

	@Override
	public void refresh() {
		populateCombos();
	}
}
