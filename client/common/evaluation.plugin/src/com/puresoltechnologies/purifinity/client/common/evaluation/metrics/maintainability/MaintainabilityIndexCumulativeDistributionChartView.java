package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_CW;
import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_WOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.chart.Axis;
import com.puresoltechnologies.purifinity.client.common.chart.AxisDirection;
import com.puresoltechnologies.purifinity.client.common.chart.AxisFactory;
import com.puresoltechnologies.purifinity.client.common.chart.Chart2D;
import com.puresoltechnologies.purifinity.client.common.chart.ChartCanvas;
import com.puresoltechnologies.purifinity.client.common.chart.GenericMark2D;
import com.puresoltechnologies.purifinity.client.common.chart.Mark2D;
import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.VerticalColoredArea;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.CircleMarkRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ConstantColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AbstractMetricChartViewPart;
import com.puresoltechnologies.purifinity.client.common.ui.SWTColor;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

public class MaintainabilityIndexCumulativeDistributionChartView extends
		AbstractMetricChartViewPart {

	private final CodeRangeType codeRangeTypeSelection = CodeRangeType.FILE;
	private Chart2D chart;

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
	protected void updateEvaluation() {
		AnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			AnalysisFileTree path = analysisSelection.getFileTreeNode();
			if (path.isFile()) {
				path = path.getParent();
			}
			showEvaluation(path);
		}
	}

	@Override
	public void showEvaluation(AnalysisFileTree path) {
		final EvaluatorStore store = EvaluatorStoreFactory.getFactory()
				.createInstance(MaintainabilityIndexEvaluator.class);
		final List<Mark2D<String, Double>> paretoValuesMI = new ArrayList<>();
		final List<Mark2D<String, Double>> paretoValuesMIwoc = new ArrayList<>();
		final List<Mark2D<String, Double>> paretoValuesMIcw = new ArrayList<>();
		TreeVisitor<AnalysisFileTree> visitor = new TreeVisitor<AnalysisFileTree>() {
			@Override
			public WalkingAction visit(AnalysisFileTree node) {
				try {
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
					String path = node.getPathFile(false).getPath();
					for (Map<String, Value<?>> valueMap : valueMaps) {
						String codeRangeName = (String) valueMap.get(
								CodeRangeNameParameter.getInstance().getName())
								.getValue();
						double value = convertToDouble(valueMap, MI);
						String name = path + "." + codeRangeName;
						paretoValuesMI.add(new GenericMark2D<String, Double>(
								name, value, name, node));
						value = convertToDouble(valueMap, MI_WOC);
						paretoValuesMIwoc
								.add(new GenericMark2D<String, Double>(name,
										value, name, node));
						value = convertToDouble(valueMap, MI_CW);
						paretoValuesMIcw.add(new GenericMark2D<String, Double>(
								name, value, name, node));
					}
					return WalkingAction.PROCEED;
				} catch (EvaluationStoreException e) {
					Activator activator = Activator.getDefault();
					activator.getLog().log(
							new Status(Status.ERROR, activator.getBundle()
									.getSymbolicName(),
									"Could not handle new selection.", e));
					return WalkingAction.ABORT;
				}
			}
		};
		TreeWalker.walk(visitor, path);

		setupChart(paretoValuesMI, paretoValuesMIwoc, paretoValuesMIcw);
	}

	private void setupChart(List<Mark2D<String, Double>> paretoValuesMI,
			List<Mark2D<String, Double>> paretoValuesMIwoc,
			List<Mark2D<String, Double>> paretoValuesMIcw) {
		chart.removeAllPlots();

		chart.setTitle("Maintainability");
		chart.setSubTitle("Cumulative Distribution Chart");

		Collections.sort(paretoValuesMI,
				new Comparator<Mark2D<String, Double>>() {
					@Override
					public int compare(Mark2D<String, Double> o1,
							Mark2D<String, Double> o2) {
						return o1.getY().compareTo(o2.getY());
					}
				});

		Collections.sort(paretoValuesMIwoc,
				new Comparator<Mark2D<String, Double>>() {
					@Override
					public int compare(Mark2D<String, Double> o1,
							Mark2D<String, Double> o2) {
						return o1.getY().compareTo(o2.getY());
					}
				});

		Collections.sort(paretoValuesMIcw,
				new Comparator<Mark2D<String, Double>>() {
					@Override
					public int compare(Mark2D<String, Double> o1,
							Mark2D<String, Double> o2) {
						return o1.getY().compareTo(o2.getY());
					}
				});

		double min = 0.0;
		double max = 0.0;
		for (Mark2D<String, Double> value : paretoValuesMI) {
			min = Math.min(min, value.getY());
			max = Math.max(max, value.getY());
		}
		for (Mark2D<String, Double> value : paretoValuesMIwoc) {
			min = Math.min(min, value.getY());
			max = Math.max(max, value.getY());
		}
		for (Mark2D<String, Double> value : paretoValuesMIcw) {
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

		List<Mark2D<Double, Double>> mi = new ArrayList<>();
		List<Mark2D<Double, Double>> miWoc = new ArrayList<>();
		List<Mark2D<Double, Double>> miCw = new ArrayList<>();
		for (int i = 0; i < paretoValuesMI.size(); i++) {
			Mark2D<String, Double> miValue = paretoValuesMI.get(i);
			mi.add(new GenericMark2D<>(miValue.getY(), (double) i
					/ (double) paretoValuesMI.size(), miValue.getRemark(),
					miValue.getReference()));

			Mark2D<String, Double> miWocValue = paretoValuesMIwoc.get(i);
			miWoc.add(new GenericMark2D<>(miWocValue.getY(), (double) i
					/ (double) paretoValuesMIwoc.size(),
					miWocValue.getRemark(), miWocValue.getReference()));

			Mark2D<String, Double> miCwValue = paretoValuesMIcw.get(i);
			miCw.add(new GenericMark2D<>(miCwValue.getY(), (double) i
					/ (double) paretoValuesMIcw.size(), miCwValue.getRemark(),
					miCwValue.getReference()));
		}

		Plot<Double, Double> plotMI = new Plot<Double, Double>(xAxis, yAxis,
				"MI");
		plotMI.add(mi);
		chart.addPlot(plotMI);

		Plot<Double, Double> plotMIwoc = new Plot<Double, Double>(xAxis, yAxis,
				"MIwoc");
		plotMIwoc.add(miWoc);
		chart.addPlot(plotMIwoc);

		Plot<Double, Double> plotMIcw = new Plot<Double, Double>(xAxis, yAxis,
				"MIcw");
		plotMIcw.add(miCw);
		chart.addPlot(plotMIcw);

		ChartCanvas chartCanvas = getChartCanvas();
		chartCanvas.setMarkRenderer(plotMI, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMI, new ConstantColorProvider(
				SWTColor.RED));

		chartCanvas.setMarkRenderer(plotMIwoc, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMIwoc, new ConstantColorProvider(
				SWTColor.GREEN));

		chartCanvas.setMarkRenderer(plotMIcw, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMIcw, new ConstantColorProvider(
				SWTColor.BLUE));

		chartCanvas.addColoredArea(new VerticalColoredArea<Double, Double>(
				plotMI, xAxis.getMinimum(), 65, SWTColor.PALE_RED));
		chartCanvas.addColoredArea(new VerticalColoredArea<Double, Double>(
				plotMI, 65, 85, SWTColor.PALE_YELLOW));
		chartCanvas.addColoredArea(new VerticalColoredArea<Double, Double>(
				plotMI, 85, xAxis.getMaximum(), SWTColor.PALE_GREEN));

		chartCanvas.refresh();
	}

	@Override
	public void export() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}
}
