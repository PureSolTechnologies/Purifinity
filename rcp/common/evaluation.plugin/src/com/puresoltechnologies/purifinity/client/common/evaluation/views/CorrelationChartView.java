package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.trees.TreeVisitor;
import com.puresoltechnologies.commons.trees.TreeWalker;
import com.puresoltechnologies.commons.trees.WalkingAction;
import com.puresoltechnologies.commons.utils.HashId;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.chart.Axis;
import com.puresoltechnologies.purifinity.client.common.chart.AxisDirection;
import com.puresoltechnologies.purifinity.client.common.chart.AxisFactory;
import com.puresoltechnologies.purifinity.client.common.chart.Chart2D;
import com.puresoltechnologies.purifinity.client.common.chart.ChartCanvas;
import com.puresoltechnologies.purifinity.client.common.chart.GenericMark2D;
import com.puresoltechnologies.purifinity.client.common.chart.Mark2D;
import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.CircleMarkRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ConstantColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.CorrelationChartViewSettingsDialog;
import com.puresoltechnologies.purifinity.client.common.ui.SWTColor;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.Evaluators;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricFileResults;

public class CorrelationChartView extends AbstractMetricChartViewPart {

	private CorrelationChartViewSettingsDialog settingsDialog = null;

	private EvaluatorFactory xMetricSelection = null;
	private Parameter<?> xParameterSelection = null;
	private EvaluatorFactory yMetricSelection = null;
	private Parameter<?> yParameterSelection = null;

	private Chart2D chart;

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site, memento);
		if (memento == null) {
			return;
		}
		// touch old classes to get the plugins activated... :-(
		String xMetricClass = memento.getString("x.metric.class");
		if (xMetricClass != null) {
			try {
				Class.forName(xMetricClass);
			} catch (ClassNotFoundException e) {
			}
		}
		String yMetricClass = memento.getString("y.metric.class");
		if (yMetricClass != null) {
			try {
				Class.forName(yMetricClass);
			} catch (ClassNotFoundException e) {
			}
		}

		List<EvaluatorFactory> allMetrics = Evaluators.createInstance()
				.getAllMetrics();
		String xMetricSelectionName = memento.getString("x.metric");
		String xParameterSelectionName = memento.getString("x.parameter");
		for (EvaluatorFactory metric : allMetrics) {
			if (metric.getName().equals(xMetricSelectionName)) {
				xMetricSelection = metric;
				if (xParameterSelectionName != null) {
					for (Parameter<?> parameter : xMetricSelection
							.getParameters()) {
						if (parameter.getName().equals(xParameterSelectionName)) {
							xParameterSelection = parameter;
							break;
						}
					}
				}
				break;
			}
		}

		String yMetricSelectionName = memento.getString("y.metric");
		String yParameterSelectionName = memento.getString("y.parameter");
		for (EvaluatorFactory metric : allMetrics) {
			if (metric.getName().equals(yMetricSelectionName)) {
				yMetricSelection = metric;
				if (yParameterSelectionName != null) {
					for (Parameter<?> parameter : yMetricSelection
							.getParameters()) {
						if (parameter.getName().equals(yParameterSelectionName)) {
							yParameterSelection = parameter;
							break;
						}
					}
				}
				break;
			}
		}
	}

	@Override
	public void saveState(IMemento memento) {
		memento.putString("x.metric.class", xMetricSelection.getClass()
				.getName());
		memento.putString("x.metric", xMetricSelection.getName());
		memento.putString("x.parameter", xParameterSelection.getName());

		memento.putString("y.metric.class", yMetricSelection.getClass()
				.getName());
		memento.putString("y.metric", yMetricSelection.getName());
		memento.putString("y.parameter", yParameterSelection.getName());

		super.saveState(memento);
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		chart = new Chart2D();
		getChartCanvas().setChart2D(chart);

		initializeToolBar();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getToolBarManager();
		toolbarManager.add(new ShowSettingsAction(this));
		toolbarManager.add(new ViewReproductionAction(this));
		toolbarManager.add(new RefreshAction(this));
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
		AnalysisSelection analysisSelection = getAnalysisSelection();
		if ((analysisSelection != null) && (xMetricSelection != null)
				&& (xParameterSelection != null) && (yMetricSelection != null)
				&& (yParameterSelection != null)) {
			HashIdFileTree path = analysisSelection.getFileTreeNode();
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
		final List<Mark2D<Double, Double>> correlationValues = new ArrayList<Mark2D<Double, Double>>();
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
				Double xValue = findSuitableValue(node, xResults,
						xParameterSelection, CodeRangeType.FILE);
				Double yValue = findSuitableValue(node, yResults,
						yParameterSelection, CodeRangeType.FILE);
				if ((xValue != null) && (yValue != null)) {
					Mark2D<Double, Double> value = new GenericMark2D<Double, Double>(
							xValue, yValue, node.getPathFile(false).toString(),
							node);
					correlationValues.add(value);
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);

		setupChart(correlationValues);
	}

	private void setupChart(final List<Mark2D<Double, Double>> correlationValues) {
		chart.removeAllPlots();

		chart.setTitle("Correlation Chart for " + xMetricSelection.getName()
				+ " and " + yMetricSelection.getName());
		chart.setSubTitle(xParameterSelection.getName() + "<->"
				+ yParameterSelection.getName());

		double xMin = Double.POSITIVE_INFINITY;
		double xMax = Double.NEGATIVE_INFINITY;
		double yMin = Double.POSITIVE_INFINITY;
		double yMax = Double.NEGATIVE_INFINITY;
		for (Mark2D<Double, Double> value : correlationValues) {
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
				xAxisParameter, xMin, xMax, (xMax - xMin) / 10.0, 1, 2);
		chart.setxAxis(xAxis);

		@SuppressWarnings("unchecked")
		Parameter<Double> yAxisParameter = (Parameter<Double>) yParameterSelection;
		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				yAxisParameter, yMin, yMax, (yMax - yMin) / 10.0, 1, 2);
		chart.setyAxis(yAxis);

		Plot<Double, Double> plot = new Plot<Double, Double>(xAxis, yAxis,
				"Correlation Plot");
		plot.add(correlationValues);
		chart.addPlot(plot);
		CircleMarkRenderer markRenderer = new CircleMarkRenderer();
		ChartCanvas chartCanvas = getChartCanvas();
		chartCanvas.setMarkRenderer(plot, markRenderer);
		chartCanvas.setColorProvider(plot, new ConstantColorProvider(
				SWTColor.BLACK, SWTColor.DARK_RED));

		// ChartConfigProvider configProvider = getConfigProvider();
		// chartCanvas.setColorProvider(plot,
		// configProvider.getColorProvider());
		chartCanvas.refresh();
	}

	@Override
	public void export() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}
}
