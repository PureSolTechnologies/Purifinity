package com.puresol.coding.client.common.evaluation.metrics.maintainability;

import static com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;
import static com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_CW;
import static com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_WOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

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
import com.puresol.coding.client.common.chart.VerticalColoredArea;
import com.puresol.coding.client.common.chart.renderer.CircleMarkRenderer;
import com.puresol.coding.client.common.chart.renderer.ConstantColorProvider;
import com.puresol.coding.client.common.evaluation.views.AbstractMetricViewPart;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.HashId;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.ParameterWithArbitraryUnit;
import com.puresol.utils.math.Value;

public class MaintainabilityIndexCumulativeDistributionChartView extends
		AbstractMetricViewPart {

	private final CodeRangeType codeRangeTypeSelection = CodeRangeType.FILE;
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
	public void refresh() {
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
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateEvaluation() {
		FileAnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
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
				.createInstance(MaintainabilityIndexEvaluator.class);
		final List<DataPoint2D<String, Double>> paretoValuesMI = new ArrayList<DataPoint2D<String, Double>>();
		final List<DataPoint2D<String, Double>> paretoValuesMIwoc = new ArrayList<DataPoint2D<String, Double>>();
		final List<DataPoint2D<String, Double>> paretoValuesMIcw = new ArrayList<DataPoint2D<String, Double>>();
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
				Map<String, Value<?>> valueMap = findSuitableValueMap(node,
						results, MI, codeRangeTypeSelection);
				if (valueMap != null) {
					String codeRangeName = (String) valueMap.get(
							CodeRangeNameParameter.getInstance().getName())
							.getValue();
					double value = convertToDouble(valueMap, MI);
					String name = node.getPathFile(false).getPath() + "."
							+ codeRangeName;
					paretoValuesMI.add(new DataPoint2D<String, Double>(name,
							value, codeRangeTypeSelection.getName() + " "
									+ codeRangeName));
					value = convertToDouble(valueMap, MI_WOC);
					paretoValuesMIwoc.add(new DataPoint2D<String, Double>(name,
							value, codeRangeTypeSelection.getName() + " "
									+ codeRangeName));
					value = convertToDouble(valueMap, MI_CW);
					paretoValuesMIcw.add(new DataPoint2D<String, Double>(name,
							value, codeRangeTypeSelection.getName() + " "
									+ codeRangeName));
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);

		setupChart(paretoValuesMI, paretoValuesMIwoc, paretoValuesMIcw);
	}

	private void setupChart(List<DataPoint2D<String, Double>> paretoValuesMI,
			List<DataPoint2D<String, Double>> paretoValuesMIwoc,
			List<DataPoint2D<String, Double>> paretoValuesMIcw) {
		chart.removeAllPlots();

		chart.setTitle("Maintainability");
		chart.setSubTitle("Cumulative Distribution Chart");

		Collections.sort(paretoValuesMI,
				new Comparator<DataPoint2D<String, Double>>() {
					@Override
					public int compare(DataPoint2D<String, Double> o1,
							DataPoint2D<String, Double> o2) {
						return o1.getY().compareTo(o2.getY());
					}
				});

		Collections.sort(paretoValuesMIwoc,
				new Comparator<DataPoint2D<String, Double>>() {
					@Override
					public int compare(DataPoint2D<String, Double> o1,
							DataPoint2D<String, Double> o2) {
						return o1.getY().compareTo(o2.getY());
					}
				});

		Collections.sort(paretoValuesMIcw,
				new Comparator<DataPoint2D<String, Double>>() {
					@Override
					public int compare(DataPoint2D<String, Double> o1,
							DataPoint2D<String, Double> o2) {
						return o1.getY().compareTo(o2.getY());
					}
				});

		double min = 0.0;
		double max = 0.0;
		for (DataPoint2D<String, Double> value : paretoValuesMI) {
			min = Math.min(min, value.getY());
			max = Math.max(max, value.getY());
		}
		for (DataPoint2D<String, Double> value : paretoValuesMIwoc) {
			min = Math.min(min, value.getY());
			max = Math.max(max, value.getY());
		}
		for (DataPoint2D<String, Double> value : paretoValuesMIcw) {
			min = Math.min(min, value.getY());
			max = Math.max(max, value.getY());
		}

		min = Axis.suggestMin(min);
		max = Axis.suggestMax(max);

		Axis<Double> xAxis = AxisFactory.createDoubleValueAxis(AxisDirection.X,
				MI, min, max, (max - min) / 10.0, 1, 2);
		chart.setxAxis(xAxis);

		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				new ParameterWithArbitraryUnit<Double>(
						"Cumulative Propability", "", LevelOfMeasurement.RATIO,
						"Cumulative distribution propability", Double.class),
				0.0, 1.0, 0.1, 1, 2);
		chart.setyAxis(yAxis);

		List<DataPoint2D<Double, Double>> mi = new ArrayList<DataPoint2D<Double, Double>>();
		List<DataPoint2D<Double, Double>> miWoc = new ArrayList<DataPoint2D<Double, Double>>();
		List<DataPoint2D<Double, Double>> miCw = new ArrayList<DataPoint2D<Double, Double>>();
		for (int i = 0; i < paretoValuesMI.size(); i++) {
			mi.add(new DataPoint2D<Double, Double>(
					paretoValuesMI.get(i).getY(), (double) i
							/ (double) paretoValuesMI.size()));
			miWoc.add(new DataPoint2D<Double, Double>(paretoValuesMIwoc.get(i)
					.getY(), (double) i / (double) paretoValuesMI.size()));
			miCw.add(new DataPoint2D<Double, Double>(paretoValuesMIcw.get(i)
					.getY(), (double) i / (double) paretoValuesMIcw.size()));
		}

		Plot<Double, Double> plotMI = new Plot<Double, Double>(xAxis, yAxis,
				"MI");
		plotMI.add(mi);
		plotMI.addColoredArea(new VerticalColoredArea<Double, Double>(plotMI,
				xAxis.getMinimum(), 65, new RGB(255, 210, 210)));
		plotMI.addColoredArea(new VerticalColoredArea<Double, Double>(plotMI,
				65, 85, new RGB(255, 255, 210)));
		plotMI.addColoredArea(new VerticalColoredArea<Double, Double>(plotMI,
				85, xAxis.getMaximum(), new RGB(210, 255, 210)));
		chart.addPlot(plotMI);

		Plot<Double, Double> plotMIwoc = new Plot<Double, Double>(xAxis, yAxis,
				"MIwoc");
		plotMIwoc.add(miWoc);
		chart.addPlot(plotMIwoc);

		Plot<Double, Double> plotMIcw = new Plot<Double, Double>(xAxis, yAxis,
				"MIcw");
		plotMIcw.add(miCw);
		chart.addPlot(plotMIcw);

		chartCanvas.setMarkRenderer(plotMI, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMI, new ConstantColorProvider(new RGB(
				255, 0, 0)));

		chartCanvas.setMarkRenderer(plotMIwoc, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMIwoc, new ConstantColorProvider(
				new RGB(0, 255, 0)));

		chartCanvas.setMarkRenderer(plotMIcw, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMIcw, new ConstantColorProvider(
				new RGB(0, 0, 255)));
		chartCanvas.refresh();
	}
}
