package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.AxisFactory;
import com.puresol.coding.client.common.chart.Chart2D;
import com.puresol.coding.client.common.chart.ChartCanvas;
import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.renderer.BarMarkRenderer;
import com.puresol.coding.client.common.chart.renderer.ConstantColorProvider;
import com.puresol.coding.client.common.evaluation.HistogramChartViewSettingsDialog;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.Evaluators;
import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.HashId;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;
import com.puresol.utils.math.Value;

public class HistogramChartView extends AbstractMetricChartViewPart {

	private static final int NUMBER_OF_NUMERICAL_CATEGORIES = 10;

	private HistogramChartViewSettingsDialog settingsDialog;

	private EvaluatorFactory metricSelection = null;
	private Parameter<?> parameterSelection = null;

	private ChartCanvas chartCanvas;
	private Chart2D chart;

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site, memento);
		if (memento == null) {
			return;
		}
		// touch old classes to get the plugins activated... :-(
		String mapMeticClass = memento.getString("metric.class");
		if (mapMeticClass != null) {
			try {
				Class.forName(mapMeticClass);
			} catch (ClassNotFoundException e) {
			}
		}
		List<EvaluatorFactory> allMetrics = Evaluators.createInstance()
				.getAllMetrics();
		String metricSelectionName = memento.getString("metric");
		String parameterSelectionName = memento.getString("parameter");
		for (EvaluatorFactory metric : allMetrics) {
			if (metric.getName().equals(metricSelectionName)) {
				metricSelection = metric;
				if (parameterSelectionName != null) {
					for (Parameter<?> parameter : metricSelection
							.getParameters()) {
						if (parameter.getName().equals(parameterSelectionName)) {
							parameterSelection = parameter;
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
		memento.putString("metric.class", metricSelection.getClass().getName());
		memento.putString("metric", metricSelection.getName());
		memento.putString("parameter", parameterSelection.getName());

		super.saveState(memento);
	}

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
			settingsDialog = new HistogramChartViewSettingsDialog(this,
					metricSelection, parameterSelection);
			settingsDialog.open();
		} else {
			settingsDialog.close();
			settingsDialog = null;
		}
	}

	@Override
	public void closeSettings() {
		settingsDialog = null;
	}

	@Override
	public void applySettings() {
		metricSelection = settingsDialog.getMetric();
		parameterSelection = settingsDialog.getParameter();
		updateEvaluation();
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
		if ((analysisSelection != null) && (metricSelection != null)
				&& (parameterSelection != null)) {
			HashIdFileTree path = analysisSelection.getHashIdFile();
			if (path.isFile()) {
				path = path.getParent();
			}
			showEvaluation(path);
		}
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		final EvaluatorStore store = EvaluatorStoreFactory.getFactory()
				.createInstance(metricSelection.getEvaluatorClass());
		final List<Value<?>> histogramValues = new ArrayList<Value<?>>();
		TreeVisitor<HashIdFileTree> visitor = new TreeVisitor<HashIdFileTree>() {
			@Override
			public WalkingAction visit(HashIdFileTree node) {
				if (!node.isFile()) {
					return WalkingAction.PROCEED;
				}
				HashId hashId = node.getHashId();
				MetricFileResults results = store.readFileResults(hashId);
				if (results == null) {
					return WalkingAction.PROCEED;
				}
				List<Map<String, Value<?>>> values = findSuitableValueMaps(
						node, results, parameterSelection, CodeRangeType.FILE);
				for (Map<String, Value<?>> value : values) {
					histogramValues
							.add(value.get(parameterSelection.getName()));
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);
		setupChart(histogramValues);
	}

	private void setupChart(List<Value<?>> histogramValues) {
		chart.removeAllPlots();

		chart.setTitle("Histogram Chart for " + metricSelection.getName());
		chart.setSubTitle(parameterSelection.getName());

		if (parameterSelection.isNumeric()) {
			createNumericalHistogram(histogramValues);
		} else {
			createCategoryHistogram(histogramValues);
		}
	}

	private void createNumericalHistogram(List<Value<?>> histogramValues) {
		if (histogramValues.size() == 0) {
			chart.removeAllPlots();
			chartCanvas.refresh();
			return;
		}
		List<Double> values = new ArrayList<Double>();
		for (Value<?> value : histogramValues) {
			double d = ((Number) value.getValue()).doubleValue();
			values.add(d);
		}
		Collections.sort(values);
		double minValue = values.get(0);
		double maxValue = values.get(values.size() - 1);
		double maxDifference = maxValue - minValue;
		int sums[] = new int[NUMBER_OF_NUMERICAL_CATEGORIES];
		Arrays.fill(sums, 0);
		int max = 0;
		for (double value : values) {
			double difference = value - minValue;
			double ratio = difference / maxDifference;
			int cat = (int) (ratio * NUMBER_OF_NUMERICAL_CATEGORIES);
			if (cat == NUMBER_OF_NUMERICAL_CATEGORIES) {
				cat--;
			}
			sums[cat]++;
			max = Math.max(max, sums[cat]);
		}
		String[] categoryArray = new String[NUMBER_OF_NUMERICAL_CATEGORIES + 1];
		for (int i = 0; i <= NUMBER_OF_NUMERICAL_CATEGORIES; i++) {
			double from = minValue + maxDifference
					/ NUMBER_OF_NUMERICAL_CATEGORIES * i;
			double to = minValue + maxDifference
					/ NUMBER_OF_NUMERICAL_CATEGORIES * (i + 1);
			categoryArray[i] = String.valueOf(from) + "-" + String.valueOf(to);
		}

		Axis<String> xAxis = AxisFactory.createCategoryAxis(AxisDirection.X,
				new ParameterWithArbitraryUnit<String>("Intervals", "",
						LevelOfMeasurement.INTERVAL, "", String.class),
				categoryArray);
		chart.setxAxis(xAxis);

		Axis<Integer> yAxis = AxisFactory.createIntegerValueAxis(
				AxisDirection.Y, new ParameterWithArbitraryUnit<Integer>(
						"Count", "", LevelOfMeasurement.RATIO, "",
						Integer.class), 0, max, Math.max(max / 10, 1), 1);
		chart.setyAxis(yAxis);

		Plot<String, Integer> plot = new Plot<String, Integer>(xAxis, yAxis,
				"Histogram Plot");
		for (int i = 0; i < sums.length; i++) {
			plot.add(new DataPoint2D<String, Integer>(categoryArray[i], sums[i]));
		}
		chart.addPlot(plot);
		chartCanvas.setMarkRenderer(plot, new BarMarkRenderer(1.0));
		chartCanvas.setColorProvider(plot, new ConstantColorProvider(new RGB(0,
				0, 0), new RGB(192, 0, 0)));

		chartCanvas.refresh();
	}

	private void createCategoryHistogram(List<Value<?>> histogramValues) {
		Set<String> categories = new HashSet<String>();
		Map<String, Integer> sums = new HashMap<String, Integer>();
		int max = 0;
		for (Value<?> value : histogramValues) {
			String category = value.getValue().toString();
			categories.add(category);
			int sum;
			if (sums.get(category) == null) {
				sum = 1;
			} else {
				sum = sums.get(category) + 1;
			}
			max = Math.max(max, sum);
			sums.put(category, sum);
		}

		String[] categoryArray = categories.toArray(new String[categories
				.size()]);
		Axis<String> xAxis = AxisFactory.createCategoryAxis(AxisDirection.X,
				new ParameterWithArbitraryUnit<String>("File", "",
						LevelOfMeasurement.NOMINAL, "", String.class),
				categoryArray);
		chart.setxAxis(xAxis);

		Axis<Integer> yAxis = AxisFactory.createIntegerValueAxis(
				AxisDirection.Y, new ParameterWithArbitraryUnit<Integer>(
						"Count", "", LevelOfMeasurement.RATIO, "",
						Integer.class), 0, max, Math.max(1, max / 10), 1);
		chart.setyAxis(yAxis);

		Plot<String, Integer> plot = new Plot<String, Integer>(xAxis, yAxis,
				"Histogram Plot");
		for (int i = 0; i < sums.size(); i++) {
			String category = categoryArray[i];
			plot.add(new DataPoint2D<String, Integer>(category, sums
					.get(category)));
		}
		chart.addPlot(plot);
		chartCanvas.setMarkRenderer(plot, new BarMarkRenderer(1.0));
		chartCanvas.setColorProvider(plot, new ConstantColorProvider(new RGB(0,
				0, 0), new RGB(192, 0, 0)));

		chartCanvas.refresh();
	}

	@Override
	public void export() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}

	@Override
	protected ChartCanvas getChartCanvas() {
		return chartCanvas;
	}
}
