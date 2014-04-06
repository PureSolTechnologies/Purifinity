package com.puresoltechnologies.purifinity.client.common.evaluation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.MetricParameterSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.MetricParameterSelectionComponent;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.ParetoChartView;
import com.puresoltechnologies.purifinity.client.common.ui.actions.AbstractPartSettingsDialog;

/**
 * This is the settings dialog for pareto charts.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ParetoChartViewSettingsDialog extends AbstractPartSettingsDialog
		implements SelectionListener {

	private MetricParameterSelectionComponent metricParameterSelectionComponent;
	private MetricParameterSelection metricParameterSelection;

	public ParetoChartViewSettingsDialog(ParetoChartView view,
			MetricParameterSelection metricParameterSelection) {
		super(view);
		this.metricParameterSelection = metricParameterSelection;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		metricParameterSelectionComponent = new MetricParameterSelectionComponent(
				container, LevelOfMeasurement.NOMINAL, "Category Selection");
		metricParameterSelectionComponent.addSelectionListener(this);

		metricParameterSelectionComponent
				.setSelection(metricParameterSelection);

		return container;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == metricParameterSelectionComponent) {
			metricParameterSelection = metricParameterSelectionComponent
					.getSelection();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		metricParameterSelectionComponent.widgetDefaultSelected(e);
	}

	public MetricParameterSelection getMetricParameterSelection() {
		return metricParameterSelection;
	}

	@Override
	protected boolean canApply() {
		return metricParameterSelection.isComplete();
	}

	@Override
	public void refresh() {
	}
}
