package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

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
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AbstractMetricChartViewPart;
import com.puresoltechnologies.purifinity.client.common.ui.SWTColor;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresoltechnologies.purifinity.framework.store.api.ParetoChartData;
import com.puresoltechnologies.purifinity.framework.store.api.ParetoChartDataProvider;
import com.puresoltechnologies.purifinity.framework.store.api.ParetoChartDataProviderFactory;

public class MaintainabilityIndexCumulativeDistributionChartView extends
		AbstractMetricChartViewPart {

	private UUID analysisProjectSelectionUUID = null;
	private UUID oldAnalysisProjectSelectionUUID = null;
	private UUID analysisRunSelectionUUID = null;
	private UUID oldAnalysisRunSelectionUUID = null;

	private ParetoChartData miWoc = new ParetoChartData();
	private ParetoChartData miCw = new ParetoChartData();
	private ParetoChartData mi = new ParetoChartData();

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
	protected void handleChangedAnalysisSelection() {
		AnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			analysisProjectSelectionUUID = analysisSelection
					.getAnalysisProject().getInformation().getUUID();
			analysisRunSelectionUUID = analysisSelection.getAnalysisRun()
					.getInformation().getUUID();
			if (wasSelectionChanged()) {
				oldAnalysisProjectSelectionUUID = analysisProjectSelectionUUID;
				oldAnalysisRunSelectionUUID = analysisRunSelectionUUID;
				loadData();
			}
			showEvaluation(analysisSelection.getFileTreeNode());
		}
	}

	private boolean wasSelectionChanged() {
		if (!analysisProjectSelectionUUID
				.equals(oldAnalysisProjectSelectionUUID)) {
			return true;
		}
		if (!analysisRunSelectionUUID.equals(oldAnalysisRunSelectionUUID)) {
			return true;
		}
		return false;
	}

	private void loadData() {
		ParetoChartDataProvider dataProvider = ParetoChartDataProviderFactory
				.getFactory().getInstance();
		mi = dataProvider.loadValues(analysisProjectSelectionUUID,
				analysisRunSelectionUUID, MaintainabilityIndexEvaluator.NAME,
				MaintainabilityIndexEvaluatorParameter.MI,
				codeRangeTypeSelection);
		miWoc = dataProvider.loadValues(analysisProjectSelectionUUID,
				analysisRunSelectionUUID, MaintainabilityIndexEvaluator.NAME,
				MaintainabilityIndexEvaluatorParameter.MI_WOC,
				codeRangeTypeSelection);
		miCw = dataProvider.loadValues(analysisProjectSelectionUUID,
				analysisRunSelectionUUID, MaintainabilityIndexEvaluator.NAME,
				MaintainabilityIndexEvaluatorParameter.MI_CW,
				codeRangeTypeSelection);
	}

	@Override
	public void showEvaluation(AnalysisFileTree path) {
		final List<Mark2D<String, Double>> paretoValuesMI = new ArrayList<>();
		final List<Mark2D<String, Double>> paretoValuesMIwoc = new ArrayList<>();
		final List<Mark2D<String, Double>> paretoValuesMIcw = new ArrayList<>();
		TreeVisitor<AnalysisFileTree> visitor = new TreeVisitor<AnalysisFileTree>() {
			@Override
			public WalkingAction visit(AnalysisFileTree node) {
				if (!node.isFile()) {
					return WalkingAction.PROCEED;
				}
				HashId hashId = node.getHashId();

				Map<String, Value<? extends Number>> miList = mi
						.getValues(hashId);
				if (miList == null) {
					return WalkingAction.PROCEED;
				}

				Map<String, Value<? extends Number>> miWocList = miWoc
						.getValues(hashId);
				if (miWocList == null) {
					return WalkingAction.PROCEED;
				}

				Map<String, Value<? extends Number>> miCwList = miCw
						.getValues(hashId);
				if (miCwList == null) {
					return WalkingAction.PROCEED;
				}

				extract(paretoValuesMI, node, miList);
				extract(paretoValuesMIwoc, node, miWocList);
				extract(paretoValuesMIcw, node, miCwList);

				return WalkingAction.PROCEED;
			}

		};
		TreeWalker.walk(visitor, path);

		setupChart(paretoValuesMI, paretoValuesMIwoc, paretoValuesMIcw);
	}

	private void extract(final List<Mark2D<String, Double>> paretoValuesMI,
			AnalysisFileTree node, Map<String, Value<? extends Number>> miList) {
		for (Entry<String, Value<? extends Number>> entry : miList.entrySet()) {
			String codeRangeName = entry.getKey();
			Number value = entry.getValue().getValue();
			String category = node.getPathFile(false).getPath() + "."
					+ codeRangeName;
			paretoValuesMI.add(new GenericMark2D<String, Double>(category,
					value.doubleValue(), codeRangeTypeSelection.getName() + " "
							+ codeRangeName, node));
		}
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
