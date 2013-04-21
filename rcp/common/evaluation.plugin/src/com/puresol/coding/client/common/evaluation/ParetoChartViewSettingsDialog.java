package com.puresol.coding.client.common.evaluation;

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

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.client.common.analysis.controls.CodeRangeTypeComboViewer;
import com.puresol.coding.client.common.evaluation.contents.MetricComboViewer;
import com.puresol.coding.client.common.evaluation.contents.ParameterComboViewer;
import com.puresol.coding.client.common.evaluation.views.ParetoChartView;
import com.puresol.coding.client.common.ui.actions.AbstractPartSettingsDialog;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;

public class ParetoChartViewSettingsDialog extends AbstractPartSettingsDialog
		implements SelectionListener {

	private Combo metricCombo;
	private Combo parameterCombo;
	private Combo codeRangeTypeCombo;

	private MetricComboViewer metricComboViewer;
	private ParameterComboViewer parameterComboViewer;
	private CodeRangeTypeComboViewer codeRangeTypeComboViewer;

	private EvaluatorFactory metricSelection = null;
	private Parameter<?> parameterSelection = null;
	private CodeRangeType codeRangeTypeSelection = null;

	public ParetoChartViewSettingsDialog(ParetoChartView view,
			EvaluatorFactory metricSelection, Parameter<?> parameterSelection,
			CodeRangeType codeRangeTypeSelection) {
		super(view);
		this.metricSelection = metricSelection;
		this.parameterSelection = parameterSelection;
		this.codeRangeTypeSelection = codeRangeTypeSelection;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		Group settingsGroup = new Group(container, SWT.NONE);
		{
			settingsGroup.setText("Settings");
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
				// fd_parameterCombo.bottom = new FormAttachment(100, -10);
				parameterCombo.setLayoutData(fd_parameterCombo);
				parameterCombo.setEnabled(true);
				parameterCombo.addSelectionListener(this);
				parameterComboViewer = new ParameterComboViewer(parameterCombo);
			}

			codeRangeTypeCombo = new Combo(settingsGroup, SWT.READ_ONLY);
			{
				FormData fd_codeRangeTypeCombo = new FormData();
				fd_codeRangeTypeCombo.left = new FormAttachment(parameterCombo,
						0, SWT.LEFT);
				fd_codeRangeTypeCombo.right = new FormAttachment(
						parameterCombo, 0, SWT.RIGHT);
				fd_codeRangeTypeCombo.top = new FormAttachment(parameterCombo,
						10);
				fd_codeRangeTypeCombo.bottom = new FormAttachment(100, -10);
				codeRangeTypeCombo.setLayoutData(fd_codeRangeTypeCombo);
				codeRangeTypeCombo.setEnabled(true);
				codeRangeTypeCombo.addSelectionListener(this);
				codeRangeTypeComboViewer = new CodeRangeTypeComboViewer(
						codeRangeTypeCombo);
			}
		}
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
		codeRangeTypeComboViewer.setSelection(new StructuredSelection(
				codeRangeTypeSelection));
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == metricCombo) {
			metricChanged();
		} else if (e.getSource() == parameterCombo) {
			parameterChanged();
		} else if (e.getSource() == codeRangeTypeCombo) {
			codeRangeTypeChanged();
		}
	}

	private void metricChanged() {
		StructuredSelection selection = (StructuredSelection) metricComboViewer
				.getSelection();
		metricSelection = (EvaluatorFactory) selection.getFirstElement();
		Set<Parameter<?>> allParameters = metricSelection.getParameters();
		Set<Parameter<?>> comboParameters = new HashSet<Parameter<?>>();
		for (Parameter<?> parameter : allParameters) {
			if (parameter.getLevelOfMeasurement().isAtLeast(
					LevelOfMeasurement.ORDINAL)
					&& parameter.isNumeric()) {
				comboParameters.add(parameter);
			}
		}
		parameterComboViewer.setInput(comboParameters);
	}

	private void parameterChanged() {
		StructuredSelection selection = (StructuredSelection) parameterComboViewer
				.getSelection();
		parameterSelection = (Parameter<?>) selection.getFirstElement();
	}

	private void codeRangeTypeChanged() {
		StructuredSelection selection = (StructuredSelection) codeRangeTypeComboViewer
				.getSelection();
		codeRangeTypeSelection = (CodeRangeType) selection.getFirstElement();
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

	public CodeRangeType getCodeRangeType() {
		return codeRangeTypeSelection;
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
