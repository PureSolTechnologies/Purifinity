package com.puresoltechnologies.purifinity.client.common.evaluation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IMemento;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.MetricParameterSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.MetricParameterSelectionComponent;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.HistogramChartView;
import com.puresoltechnologies.purifinity.client.common.ui.actions.AbstractPartSettingsDialog;

public class HistogramChartViewSettingsDialog extends
		AbstractPartSettingsDialog implements SelectionListener {

	public static MetricParameterSelection init(IMemento memento) {
		return MetricParameterSelectionComponent.init(memento);
	}

	public static void saveState(IMemento memento,
			MetricParameterSelection metricParameterSelection) {
		MetricParameterSelectionComponent.saveState(memento,
				metricParameterSelection);
	}

	private MetricParameterSelectionComponent metricParameterSelectionComponent;
	private MetricParameterSelection metricParameterSelection;

	public HistogramChartViewSettingsDialog(HistogramChartView view,
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
