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

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.MetricComboViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.ParameterComboViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.CorrelationChartView;
import com.puresoltechnologies.purifinity.client.common.ui.actions.AbstractPartSettingsDialog;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.Evaluators;

public class CorrelationChartViewSettingsDialog extends
		AbstractPartSettingsDialog implements SelectionListener {

	private Combo xMetricCombo;
	private Combo xParameterCombo;
	private Combo yMetricCombo;
	private Combo yParameterCombo;

	private ParameterComboViewer xParameterComboViewer;
	private ParameterComboViewer yParameterComboViewer;
	private MetricComboViewer xMetricComboViewer;
	private MetricComboViewer yMetricComboViewer;

	private EvaluatorFactory xMetricSelection = null;
	private Parameter<?> xParameterSelection = null;
	private EvaluatorFactory yMetricSelection = null;
	private Parameter<?> yParameterSelection = null;

	public CorrelationChartViewSettingsDialog(CorrelationChartView view,
			EvaluatorFactory xMetricSelection,
			Parameter<?> xParameterSelection,
			EvaluatorFactory yMetricSelection, Parameter<?> yParameterSelection) {
		super(view);
		this.xMetricSelection = xMetricSelection;
		this.xParameterSelection = xParameterSelection;
		this.yMetricSelection = yMetricSelection;
		this.yParameterSelection = yParameterSelection;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		Group xSettingsGroup = new Group(container, SWT.NONE);
		{
			xSettingsGroup.setText("X Settings");
			xSettingsGroup.setLayout(new FormLayout());

			xMetricCombo = new Combo(xSettingsGroup, SWT.READ_ONLY);
			{
				FormData fd_xMetricsCombo = new FormData();
				fd_xMetricsCombo.left = new FormAttachment(0, 10);
				fd_xMetricsCombo.right = new FormAttachment(100, -10);
				fd_xMetricsCombo.top = new FormAttachment(0, 10);
				xMetricCombo.setLayoutData(fd_xMetricsCombo);
				xMetricCombo.setEnabled(true);
				xMetricCombo.addSelectionListener(this);
				xMetricComboViewer = new MetricComboViewer(xMetricCombo);
			}

			xParameterCombo = new Combo(xSettingsGroup, SWT.READ_ONLY);
			{
				FormData fd_xParameterCombo = new FormData();
				fd_xParameterCombo.left = new FormAttachment(xMetricCombo, 0,
						SWT.LEFT);
				fd_xParameterCombo.right = new FormAttachment(xMetricCombo, 0,
						SWT.RIGHT);
				fd_xParameterCombo.top = new FormAttachment(xMetricCombo, 10);
				fd_xParameterCombo.bottom = new FormAttachment(100, -10);
				xParameterCombo.setLayoutData(fd_xParameterCombo);
				xParameterCombo.setEnabled(true);
				xParameterCombo.addSelectionListener(this);
				xParameterComboViewer = new ParameterComboViewer(
						xParameterCombo);
			}
		}
		Group ySettingsGroup = new Group(container, SWT.NONE);
		{
			{
				ySettingsGroup.setText("Y Settings");
				ySettingsGroup.setLayout(new FormLayout());
			}

			yMetricCombo = new Combo(ySettingsGroup, SWT.READ_ONLY);
			{
				FormData fd_yMetricCombo = new FormData();
				fd_yMetricCombo.left = new FormAttachment(0, 10);
				fd_yMetricCombo.right = new FormAttachment(100, -10);
				fd_yMetricCombo.top = new FormAttachment(0, 10);
				yMetricCombo.setLayoutData(fd_yMetricCombo);
				yMetricCombo.setEnabled(true);
				yMetricCombo.addSelectionListener(this);
				yMetricComboViewer = new MetricComboViewer(yMetricCombo);
			}

			yParameterCombo = new Combo(ySettingsGroup, SWT.READ_ONLY);
			{
				FormData fd_yParameterCombo = new FormData();
				fd_yParameterCombo.left = new FormAttachment(yMetricCombo, 0,
						SWT.LEFT);
				fd_yParameterCombo.right = new FormAttachment(yMetricCombo, 0,
						SWT.RIGHT);
				fd_yParameterCombo.top = new FormAttachment(yMetricCombo, 10);
				fd_yParameterCombo.bottom = new FormAttachment(100, -10);
				yParameterCombo.setLayoutData(fd_yParameterCombo);
				yParameterCombo.setEnabled(true);
				yParameterCombo.addSelectionListener(this);
				yParameterComboViewer = new ParameterComboViewer(
						yParameterCombo);
			}
		}
		populateCombos();
		return container;
	}

	private void populateCombos() {
		List<EvaluatorFactory> allMetrics = Evaluators.createInstance()
				.getAllMetrics();
		xMetricComboViewer.setInput(allMetrics);
		if (xMetricSelection != null) {
			xParameterComboViewer.setInput(xMetricSelection.getParameters());
			xMetricComboViewer.setSelection(new StructuredSelection(
					xMetricSelection));
			if (xParameterSelection != null) {
				xParameterComboViewer.setSelection(new StructuredSelection(
						xParameterSelection));
			}
		} else {
			xParameterComboViewer.setInput(null);
		}
		yMetricComboViewer.setInput(allMetrics);
		if (yMetricSelection != null) {
			yParameterComboViewer.setInput(yMetricSelection.getParameters());
			yMetricComboViewer.setSelection(new StructuredSelection(
					yMetricSelection));
			if (yParameterSelection != null) {
				yParameterComboViewer.setSelection(new StructuredSelection(
						yParameterSelection));
			}
		} else {
			yParameterComboViewer.setInput(null);
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == xMetricCombo) {
			xMetricChanged();
		} else if (e.getSource() == xParameterCombo) {
			xParameterChanged();
		} else if (e.getSource() == yMetricCombo) {
			yMetricChanged();
		} else if (e.getSource() == yParameterCombo) {
			yParameterChanged();
		}
	}

	private void xMetricChanged() {
		StructuredSelection selection = (StructuredSelection) xMetricComboViewer
				.getSelection();
		xMetricSelection = (EvaluatorFactory) selection.getFirstElement();
		Set<Parameter<?>> allParameters = xMetricSelection.getParameters();
		Set<Parameter<?>> comboParameters = new HashSet<Parameter<?>>();
		for (Parameter<?> parameter : allParameters) {
			if (parameter.isNumeric()) {
				comboParameters.add(parameter);
			}
		}
		xParameterComboViewer.setInput(comboParameters);
	}

	private void xParameterChanged() {
		StructuredSelection selection = (StructuredSelection) xParameterComboViewer
				.getSelection();
		xParameterSelection = (Parameter<?>) selection.getFirstElement();
	}

	private void yMetricChanged() {
		StructuredSelection selection = (StructuredSelection) yMetricComboViewer
				.getSelection();
		yMetricSelection = (EvaluatorFactory) selection.getFirstElement();
		Set<Parameter<?>> allParameters = yMetricSelection.getParameters();
		Set<Parameter<?>> comboParameters = new HashSet<Parameter<?>>();
		for (Parameter<?> parameter : allParameters) {
			if (parameter.isNumeric()) {
				comboParameters.add(parameter);
			}
		}
		yParameterComboViewer.setInput(comboParameters);
	}

	private void yParameterChanged() {
		StructuredSelection selection = (StructuredSelection) yParameterComboViewer
				.getSelection();
		yParameterSelection = (Parameter<?>) selection.getFirstElement();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		populateCombos();
	}

	public EvaluatorFactory getXMetric() {
		return xMetricSelection;
	}

	public Parameter<?> getXParameter() {
		return xParameterSelection;
	}

	public EvaluatorFactory getYMetric() {
		return yMetricSelection;
	}

	public Parameter<?> getYParameter() {
		return yParameterSelection;
	}

	@Override
	protected boolean canApply() {
		if (xMetricSelection == null) {
			return false;
		}
		if (xParameterSelection == null) {
			return false;
		}
		if (yMetricSelection == null) {
			return false;
		}
		if (yParameterSelection == null) {
			return false;
		}
		return true;
	}

	@Override
	public void refresh() {
		populateCombos();
	}
}
