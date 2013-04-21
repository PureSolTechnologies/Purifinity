package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.AxisFactory;
import com.puresol.coding.client.common.chart.Chart2D;
import com.puresol.coding.client.common.chart.ChartCanvas;
import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.renderer.CircleMarkRenderer;
import com.puresol.coding.client.common.evaluation.CorrelationChartViewSettingsDialog;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.HashId;
import com.puresol.utils.math.Parameter;

public class CorrelationChartView extends AbstractMetricViewPart {

	private CorrelationChartViewSettingsDialog settingsDialog = null;

	private EvaluatorFactory xMetricSelection = null;
	private Parameter<?> xParameterSelection = null;
	private EvaluatorFactory yMetricSelection = null;
	private Parameter<?> yParameterSelection = null;

	private Chart2D chart;
	private ChartCanvas chartCanvas;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		chartCanvas = new ChartCanvas(parent, SWT.NONE);
		chart = new Chart2D();
		chartCanvas.setChart2D(chart);

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
		if (settingsDialog == null) {
			settingsDialog = new CorrelationChartViewSettingsDialog(this,
					xMetricSelection, xParameterSelection, yMetricSelection,
					yParameterSelection);
			settingsDialog.open();
		} else {
			settingsDialog.close();
			settingsDialog = null;
		}
	}

	@Override
	public void applySettings() {
		xMetricSelection = settingsDialog.getXMetric();
		xParameterSelection = settingsDialog.getXParameter();
		yMetricSelection = settingsDialog.getYMetric();
		yParameterSelection = settingsDialog.getYParameter();
		updateEvaluation();
	}

	@Override
	public void closeSettings() {
		settingsDialog = null;
	}

	@Override
	public void refresh() {
		if (settingsDialog != null) {
			settingsDialog.refresh();
		}
	}

	@Override
	protected void updateEvaluation() {
		FileAnalysisSelection analysisSelection = getAnalysisSelection();
		if ((analysisSelection != null) && (xMetricSelection != null)
				&& (xParameterSelection != null) && (yMetricSelection != null)
				&& (yParameterSelection != null)) {
			HashIdFileTree path = analysisSelection.getHashIdFile();
			if (path.isFile()) {
				path = path.getParent();
			}
			showEvaluation(path);
		}
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		final EvaluatorStore xStore = EvaluatorStoreFactory.getFactory()
				.createInstance(xMetricSelection.getEvaluatorClass());
		final EvaluatorStore yStore = EvaluatorStoreFactory.getFactory()
				.createInstance(yMetricSelection.getEvaluatorClass());
		final List<Point2D> correlationValues = new ArrayList<Point2D>();
		TreeVisitor<HashIdFileTree> visitor = new TreeVisitor<HashIdFileTree>() {
			@Override
			public WalkingAction visit(HashIdFileTree node) {
				if (!node.isFile()) {
					return WalkingAction.PROCEED;
				}
				HashId hashId = node.getHashId();
				MetricFileResults xResults = xStore.readFileResults(hashId);
				if (xResults == null) {
					return WalkingAction.PROCEED;
				}
				MetricFileResults yResults = yStore.readFileResults(hashId);
				if (yResults == null) {
					return WalkingAction.PROCEED;
				}
				double xValue = findSuitableValue(node, xResults,
						xParameterSelection);
				double yValue = findSuitableValue(node, yResults,
						yParameterSelection);
				Point2D value = new Point2D(xValue, yValue);
				correlationValues.add(value);
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);

		setupChart(correlationValues);
	}

	private void setupChart(final List<Point2D> correlationValues) {
		chart.removeAllPlots();

		chart.setTitle("Correlation Chart for " + xMetricSelection.getName()
				+ " and " + yMetricSelection.getName());
		chart.setSubTitle(xParameterSelection.getName() + "<->"
				+ yParameterSelection.getName());

		double xMin = Double.POSITIVE_INFINITY;
		double xMax = Double.NEGATIVE_INFINITY;
		double yMin = Double.POSITIVE_INFINITY;
		double yMax = Double.NEGATIVE_INFINITY;
		for (Point2D value : correlationValues) {
			xMin = Math.min(xMin, value.getX());
			xMax = Math.max(xMax, value.getX());
			yMin = Math.min(yMin, value.getY());
			yMax = Math.max(yMax, value.getY());
		}

		xMin = Axis.suggestMin(xMin);
		xMax = Axis.suggestMax(xMax);
		yMin = Axis.suggestMin(yMin);
		yMax = Axis.suggestMax(yMax);

		@SuppressWarnings("unchecked")
		Parameter<Double> xAxisParameter = (Parameter<Double>) xParameterSelection;
		Axis<Double> xAxis = AxisFactory.createDoubleValueAxis(AxisDirection.X,
				xAxisParameter, xMin, xMax, (xMax - xMin) / 10.0, 1);
		chart.setxAxis(xAxis);

		@SuppressWarnings("unchecked")
		Parameter<Double> yAxisParameter = (Parameter<Double>) yParameterSelection;
		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				yAxisParameter, yMin, yMax, (yMax - yMin) / 10.0, 1);
		chart.setyAxis(yAxis);

		Plot<Double, Double> plot = new Plot<Double, Double>(xAxis, yAxis,
				"Correlation Plot");
		for (Point2D value : correlationValues) {
			plot.add(new DataPoint2D<Double, Double>(value.getX(), value.getY()));
		}
		chart.addPlot(plot);
		chartCanvas.setMarkRenderer(plot, new CircleMarkRenderer());

		// ChartConfigProvider configProvider = getConfigProvider();
		// chartCanvas.setColorProvider(plot,
		// configProvider.getColorProvider());
		chartCanvas.refresh();
	}
}
