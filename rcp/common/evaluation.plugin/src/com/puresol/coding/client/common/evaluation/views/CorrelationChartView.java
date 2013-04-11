package com.puresol.coding.client.common.evaluation.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.Chart2D;
import com.puresol.coding.client.common.chart.ChartCanvas;
import com.puresol.coding.client.common.chart.Tick;
import com.puresol.coding.client.common.chart.TickType;
import com.puresol.coding.client.common.ui.actions.PartSettingsCapability;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.actions.Reproducable;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresol.coding.metrics.sloc.SLOCEvaluatorParameter;

public class CorrelationChartView extends ViewPart implements Refreshable,
		Reproducable, ISelectionListener, PartSettingsCapability {

	private ChartCanvas chartCanvas;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		chartCanvas = new ChartCanvas(parent, SWT.NONE);
		Chart2D chart = new Chart2D();
		chart.setTitle("Metrics Correlation");
		chart.setSubTitle("SLOC <-> Maintainability Index");

		Axis<Integer> xAxis = new Axis<Integer>(AxisDirection.X,
				SLOCEvaluatorParameter.PHY_LOC);
		xAxis.addTick(new Tick<Integer>(TickType.MAJOR, 0, 0.0, "0"));
		xAxis.addTick(new Tick<Integer>(TickType.MAJOR, 100, 100.0, "100"));
		xAxis.addTick(new Tick<Integer>(TickType.MAJOR, 200, 200.0, "200"));
		xAxis.setMinimum(-10.0);
		xAxis.setMaximum(210);

		Axis<Double> yAxis = new Axis<Double>(AxisDirection.Y,
				MaintainabilityIndexEvaluatorParameter.MI);
		yAxis.addTick(new Tick<Double>(TickType.MAJOR, 10d, 0.0, "0"));
		yAxis.addTick(new Tick<Double>(TickType.MAJOR, 50d, 50.0, "50"));
		yAxis.addTick(new Tick<Double>(TickType.MAJOR, 100d, 100.0, "100"));
		yAxis.setMinimum(-10.0);
		yAxis.setMaximum(110);

		chart.setxAxis(xAxis);
		chart.setyAxis(yAxis);

		chartCanvas.setChart2D(chart);

		// TODO Auto-generated method stub
		initializeToolBar();
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
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

}
