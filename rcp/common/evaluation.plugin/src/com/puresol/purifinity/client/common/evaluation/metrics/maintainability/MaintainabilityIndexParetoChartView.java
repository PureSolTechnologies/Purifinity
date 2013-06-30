package com.puresol.purifinity.client.common.evaluation.metrics.maintainability;

import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_CW;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_WOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.chart.Axis;
import com.puresol.purifinity.client.common.chart.AxisDirection;
import com.puresol.purifinity.client.common.chart.AxisFactory;
import com.puresol.purifinity.client.common.chart.Chart2D;
import com.puresol.purifinity.client.common.chart.ChartCanvas;
import com.puresol.purifinity.client.common.chart.DataPoint2D;
import com.puresol.purifinity.client.common.chart.HorizontalColoredArea;
import com.puresol.purifinity.client.common.chart.Plot;
import com.puresol.purifinity.client.common.chart.renderer.CircleMarkRenderer;
import com.puresol.purifinity.client.common.chart.renderer.ConstantColorProvider;
import com.puresol.purifinity.client.common.evaluation.views.AbstractMetricChartViewPart;
import com.puresol.purifinity.client.common.ui.actions.ExportAction;
import com.puresol.purifinity.client.common.ui.actions.RefreshAction;
import com.puresol.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresol.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresol.purifinity.trees.TreeVisitor;
import com.puresol.purifinity.trees.TreeWalker;
import com.puresol.purifinity.trees.WalkingAction;
import com.puresol.purifinity.utils.HashId;
import com.puresol.purifinity.utils.math.LevelOfMeasurement;
import com.puresol.purifinity.utils.math.ParameterWithArbitraryUnit;
import com.puresol.purifinity.utils.math.Value;

public class MaintainabilityIndexParetoChartView extends
		AbstractMetricChartViewPart {

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
		toolbarManager.add(new ExportAction(this));
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
		final Map<String, DataPoint2D<String, Double>> paretoValuesMIwoc = new HashMap<String, DataPoint2D<String, Double>>();
		final Map<String, DataPoint2D<String, Double>> paretoValuesMIcw = new HashMap<String, DataPoint2D<String, Double>>();
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
				List<Map<String, Value<?>>> valueMaps = findSuitableValueMaps(
						node, results, MI, codeRangeTypeSelection);
				for (Map<String, Value<?>> valueMap : valueMaps) {
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
					paretoValuesMIwoc.put(name,
							new DataPoint2D<String, Double>(name, value,
									codeRangeTypeSelection.getName() + " "
											+ codeRangeName));
					value = convertToDouble(valueMap, MI_CW);
					paretoValuesMIcw.put(name, new DataPoint2D<String, Double>(
							name, value, codeRangeTypeSelection.getName() + " "
									+ codeRangeName));
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);

		setupChart(paretoValuesMI, paretoValuesMIwoc, paretoValuesMIcw);
	}

	private void setupChart(List<DataPoint2D<String, Double>> paretoValuesMI,
			Map<String, DataPoint2D<String, Double>> paretoValuesMIwoc,
			Map<String, DataPoint2D<String, Double>> paretoValuesMIcw) {
		chart.removeAllPlots();

		chart.setTitle("Maintainability");
		chart.setSubTitle("Pareto Chart");

		Collections.sort(paretoValuesMI,
				new Comparator<DataPoint2D<String, Double>>() {
					@Override
					public int compare(DataPoint2D<String, Double> o1,
							DataPoint2D<String, Double> o2) {
						return o2.getY().compareTo(o1.getY());
					}
				});

		List<String> categories = new ArrayList<String>();
		double min = 0.0;
		double max = 0.0;
		for (DataPoint2D<String, Double> value : paretoValuesMI) {
			categories.add(value.getX());
			min = Math.min(min, value.getY());
			max = Math.max(max, value.getY());
		}

		max = Axis.suggestMax(max);

		Axis<String> xAxis = AxisFactory.createCategoryAxis(AxisDirection.X,
				new ParameterWithArbitraryUnit<String>("File", "",
						LevelOfMeasurement.NOMINAL, "", String.class),
				categories.toArray(new String[categories.size()]));
		chart.setxAxis(xAxis);

		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				MI, min, max, (max - min) / 10.0, 1, 2);
		chart.setyAxis(yAxis);

		Plot<String, Double> plotMI = new Plot<String, Double>(xAxis, yAxis,
				"MI");
		plotMI.add(paretoValuesMI);
		chart.addPlot(plotMI);

		List<DataPoint2D<String, Double>> miWoc = new ArrayList<DataPoint2D<String, Double>>();
		List<DataPoint2D<String, Double>> miCw = new ArrayList<DataPoint2D<String, Double>>();
		for (DataPoint2D<String, Double> m : paretoValuesMI) {
			miWoc.add(paretoValuesMIwoc.get(m.getX()));
			miCw.add(paretoValuesMIcw.get(m.getX()));
		}

		Plot<String, Double> plotMIwoc = new Plot<String, Double>(xAxis, yAxis,
				"MIwoc");
		plotMIwoc.add(miWoc);
		chart.addPlot(plotMIwoc);

		Plot<String, Double> plotMIcw = new Plot<String, Double>(xAxis, yAxis,
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

		chartCanvas.addColoredArea(new HorizontalColoredArea<String, Double>(
				plotMI, yAxis.getMinimum(), 65, new RGB(255, 210, 210)));
		chartCanvas.addColoredArea(new HorizontalColoredArea<String, Double>(
				plotMI, 65, 85, new RGB(255, 255, 210)));
		chartCanvas.addColoredArea(new HorizontalColoredArea<String, Double>(
				plotMI, 85, yAxis.getMaximum(), new RGB(210, 255, 210)));

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