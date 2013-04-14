package com.puresol.coding.client.common.evaluation.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.AxisFactory;
import com.puresol.coding.client.common.chart.Chart2D;
import com.puresol.coding.client.common.chart.ChartCanvas;
import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter;

public class CorrelationChartView extends AbstractMetricViewPart {

	private ChartCanvas chartCanvas;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		chartCanvas = new ChartCanvas(parent, SWT.NONE);
		Chart2D chart = new Chart2D();
		chart.setTitle("Metrics Correlation");
		chart.setSubTitle("SLOC <-> Maintainability Index");

		Axis<Integer> xAxis = AxisFactory.createIntegerValueAxis(
				AxisDirection.X, SLOCEvaluatorParameter.PHY_LOC, 0, 1000, 100,
				9);
		chart.setxAxis(xAxis);

		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				MaintainabilityIndexEvaluatorParameter.MI, -20, 200, 20, 3);
		chart.setyAxis(yAxis);

		Plot<Integer, Double> plot = new Plot<Integer, Double>(xAxis, yAxis,
				"Correlation");
		plot.add(new DataPoint2D(new Point2D(10, 100)));
		plot.add(new DataPoint2D(new Point2D(20, 50)));
		plot.add(new DataPoint2D(new Point2D(30, 10)));
		chart.addPlot(plot);

		chartCanvas.setChart2D(chart);

		// TODO Auto-generated method stub
		initializeToolBar();
		super.createPartControl(parent);
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new ShowSettingsAction(this));
		toolbarManager.add(new ViewReproductionAction(this));
		toolbarManager.add(new RefreshAction(this));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showSettings() {
		// TODO Auto-generated method stub

	}

	@Override
	public void applySettings() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeSettings() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateEvaluation() {
		// TODO Auto-generated method stub

	}

}
