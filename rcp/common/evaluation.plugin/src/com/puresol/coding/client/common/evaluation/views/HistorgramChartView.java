package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.puresol.coding.client.common.chart.renderer.BarMarkRenderer;
import com.puresol.coding.client.common.evaluation.HistogramChartViewSettingsDialog;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.HashId;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;
import com.puresol.utils.math.Value;

public class HistorgramChartView extends AbstractMetricViewPart {

	private HistogramChartViewSettingsDialog settingsDialog;

	private EvaluatorFactory metricSelection = null;
	private Parameter<?> parameterSelection = null;

	private ChartCanvas chartCanvas;
	private Chart2D chart;

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
				Map<String, Value<?>> value = findSuitableValueMap(node,
						results, parameterSelection);
				histogramValues.add(value.get(parameterSelection.getName()));
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);
		setupChart(histogramValues);
	}

	private void setupChart(List<Value<?>> histogramValues) {
		chart.removeAllPlots();

		chart.setTitle("Pareto Chart for " + metricSelection.getName());
		chart.setSubTitle(parameterSelection.getName());

		if (Number.class.isAssignableFrom(parameterSelection.getType())) {
			createNumericalHistogram(histogramValues);
		} else {
			createCategoryHistogram(histogramValues);
		}
	}

	private void createNumericalHistogram(List<Value<?>> histogramValues) {
		Collections.sort(histogramValues, new Comparator<Value<?>>() {
			@Override
			public int compare(Value<?> o1, Value<?> o2) {
				Number n1 = (Number) o1;
				Number n2 = (Number) o2;
				return Double.valueOf(n2.doubleValue()).compareTo(
						n1.doubleValue());
			}
		});

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

		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				MaintainabilityIndexEvaluatorParameter.MI, 0, max, max / 10.0,
				1);
		chart.setyAxis(yAxis);

		Plot<String, Double> plot = new Plot<String, Double>(xAxis, yAxis,
				"Pareto Plot");
		for (int i = 0; i < sums.size(); i++) {
			String category = categoryArray[i];
			plot.add(new DataPoint2D(new Point2D(i, sums.get(category))));
		}
		chart.addPlot(plot);
		chartCanvas.setMarkRenderer(plot, new BarMarkRenderer(1.0));

		chartCanvas.refresh();
	}

}
