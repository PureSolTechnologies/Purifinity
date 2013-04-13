package com.puresol.coding.client.common.evaluation.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.AxisFactory;
import com.puresol.coding.client.common.chart.Chart2D;
import com.puresol.coding.client.common.chart.ChartCanvas;
import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.renderer.BarMarkRenderer;
import com.puresol.coding.client.common.chart.renderer.ConstantColorProvider;
import com.puresol.coding.client.common.ui.actions.PartSettingsCapability;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.actions.Reproducable;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.ParameterWithArbitraryUnit;

public class HistorgramChartView extends ViewPart implements Refreshable,
		Reproducable, ISelectionListener, PartSettingsCapability {

	private ChartCanvas chartCanvas;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		chartCanvas = new ChartCanvas(parent, SWT.NONE);
		Chart2D chart = new Chart2D();
		chart.setTitle("Line Length Histogram");

		Axis<String> xAxis = AxisFactory.createCategoryAxis(AxisDirection.X,
				new ParameterWithArbitraryUnit<String>("File", "",
						LevelOfMeasurement.NOMINAL, "", String.class), "File1",
				"File2", "File3", "File4", "File5");
		chart.setxAxis(xAxis);

		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				MaintainabilityIndexEvaluatorParameter.MI, 0, 200, 20, 3);
		chart.setyAxis(yAxis);

		Plot<String, Double> plot = new Plot<String, Double>(xAxis, yAxis,
				"Correlation");
		plot.add(new DataPoint2D(new Point2D(0.0, 5)));
		plot.add(new DataPoint2D(new Point2D(1.0, 25)));
		plot.add(new DataPoint2D(new Point2D(2.0, 50)));
		plot.add(new DataPoint2D(new Point2D(3.0, 20)));
		plot.add(new DataPoint2D(new Point2D(4.0, 10)));
		chart.addPlot(plot);

		chartCanvas.setChart2D(chart);
		chartCanvas.setMarkRenderer(plot, new BarMarkRenderer(1.0));
		chartCanvas.setColorProvider(plot, new ConstantColorProvider(new RGB(
				255, 0, 0), new RGB(0, 0, 255)));

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
