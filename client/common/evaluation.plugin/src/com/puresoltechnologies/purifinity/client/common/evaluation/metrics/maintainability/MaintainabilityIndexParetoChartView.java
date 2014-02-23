package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import static com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.progress.UIJob;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.client.common.analysis.AnalysisSelections;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.chart.Axis;
import com.puresoltechnologies.purifinity.client.common.chart.AxisDirection;
import com.puresoltechnologies.purifinity.client.common.chart.AxisFactory;
import com.puresoltechnologies.purifinity.client.common.chart.Chart2D;
import com.puresoltechnologies.purifinity.client.common.chart.ChartCanvas;
import com.puresoltechnologies.purifinity.client.common.chart.GenericMark2D;
import com.puresoltechnologies.purifinity.client.common.chart.HorizontalColoredArea;
import com.puresoltechnologies.purifinity.client.common.chart.Mark2D;
import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.CircleMarkRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ConstantColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AbstractMetricChartViewPart;
import com.puresoltechnologies.purifinity.client.common.ui.SWTColor;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ExportAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresoltechnologies.purifinity.framework.store.api.ParetoChartData;
import com.puresoltechnologies.purifinity.framework.store.api.ParetoChartDataProvider;
import com.puresoltechnologies.purifinity.framework.store.api.ParetoChartDataProviderFactory;

public class MaintainabilityIndexParetoChartView extends
		AbstractMetricChartViewPart {

	private final CodeRangeType codeRangeTypeSelection = CodeRangeType.FILE;
	private Chart2D chart;
	private ParetoChartData miWoc = new ParetoChartData();
	private ParetoChartData miCw = new ParetoChartData();
	private ParetoChartData mi = new ParetoChartData();

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		chart = new Chart2D();
		getChartCanvas().setChart2D(chart);

		initializeToolBar();

		setSelection(AnalysisSelections.getInstance().getAnalysisSelection());
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getToolBarManager();
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
	protected void clear() {
		showEvaluation(null);
	}

	@Override
	protected void updateView() {
		loadData();
	}

	@Override
	protected boolean hasFullViewSettings() {
		return true;
	}

	@Override
	protected boolean hasChangedViewSettings() {
		return false;
	}

	private void loadData() {
		Job job = new Job("Load maintainability index pareto chart data...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Load data", 7);
				ParetoChartDataProvider dataProvider = ParetoChartDataProviderFactory
						.getFactory().getInstance();
				monitor.worked(1);
				final AnalysisSelection analysisSelection = getAnalysisSelection();
				monitor.worked(1);
				UUID analysisProjectUUID = analysisSelection
						.getAnalysisProject().getInformation().getUUID();
				monitor.worked(1);
				UUID analysisRunUUID = analysisSelection.getAnalysisRun()
						.getInformation().getUUID();
				monitor.worked(1);
				mi = dataProvider.loadValues(analysisProjectUUID,
						analysisRunUUID, MaintainabilityIndexEvaluator.NAME,
						MaintainabilityIndexEvaluatorParameter.MI,
						codeRangeTypeSelection);
				monitor.worked(1);
				miWoc = dataProvider.loadValues(analysisProjectUUID,
						analysisRunUUID, MaintainabilityIndexEvaluator.NAME,
						MaintainabilityIndexEvaluatorParameter.MI_WOC,
						codeRangeTypeSelection);
				monitor.worked(1);
				miCw = dataProvider.loadValues(analysisProjectUUID,
						analysisRunUUID, MaintainabilityIndexEvaluator.NAME,
						MaintainabilityIndexEvaluatorParameter.MI_CW,
						codeRangeTypeSelection);
				monitor.worked(1);
				monitor.done();
				new UIJob("Draw Maintainability Index Pareto Chart") {
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						showEvaluation(analysisSelection.getFileTreeNode());
						return Status.OK_STATUS;
					}
				}.schedule();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	@Override
	public void showEvaluation(AnalysisFileTree path) {
		final List<Mark2D<String, Double>> paretoValuesMI = new ArrayList<>();
		final Map<String, Mark2D<String, Double>> paretoValuesMIwoc = new HashMap<>();
		final Map<String, Mark2D<String, Double>> paretoValuesMIcw = new HashMap<>();
		if (path != null) {
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

					extractValues(paretoValuesMI, node, miList);
					extractValues(paretoValuesMIcw, node, miCwList);
					extractValues(paretoValuesMIwoc, node, miWocList);

					return WalkingAction.PROCEED;
				}

			};
			TreeWalker.walk(visitor, path);
		}
		setupChart(paretoValuesMI, paretoValuesMIwoc, paretoValuesMIcw);
	}

	private void extractValues(
			final List<Mark2D<String, Double>> paretoValuesMI,
			AnalysisFileTree node, Map<String, Value<? extends Number>> miList) {
		Set<String> usedCategories = new HashSet<String>();
		for (Entry<String, Value<? extends Number>> entry : miList.entrySet()) {
			String codeRangeName = entry.getKey();
			Number value = entry.getValue().getValue();
			String category = node.getPathFile(false).getPath() + "."
					+ codeRangeName;
			paretoValuesMI.add(new GenericMark2D<String, Double>(category,
					value.doubleValue(), codeRangeTypeSelection.getName() + " "
							+ codeRangeName, node));
			usedCategories.add(category);
		}
	}

	private void extractValues(
			final Map<String, Mark2D<String, Double>> paretoValuesMI,
			AnalysisFileTree node, Map<String, Value<? extends Number>> miList) {
		Set<String> usedCategories = new HashSet<String>();
		for (Entry<String, Value<? extends Number>> entry : miList.entrySet()) {
			String codeRangeName = entry.getKey();
			Number value = entry.getValue().getValue();
			String category = node.getPathFile(false).getPath() + "."
					+ codeRangeName;
			paretoValuesMI.put(
					category,
					new GenericMark2D<String, Double>(category, value
							.doubleValue(), codeRangeTypeSelection.getName()
							+ " " + codeRangeName, node));
			usedCategories.add(category);
		}
	}

	private void setupChart(List<Mark2D<String, Double>> paretoValuesMI,
			Map<String, Mark2D<String, Double>> paretoValuesMIwoc,
			Map<String, Mark2D<String, Double>> paretoValuesMIcw) {
		chart.removeAllPlots();

		chart.setTitle("Maintainability");
		chart.setSubTitle("Pareto Chart");

		Collections.sort(paretoValuesMI,
				new Comparator<Mark2D<String, Double>>() {
					@Override
					public int compare(Mark2D<String, Double> o1,
							Mark2D<String, Double> o2) {
						return o2.getY().compareTo(o1.getY());
					}
				});

		List<String> categories = new ArrayList<>();
		double min = 0.0;
		double max = 0.0;
		for (Mark2D<String, Double> value : paretoValuesMI) {
			categories.add(value.getX());
			min = Math.min(min, value.getY());
			max = Math.max(max, value.getY());
		}

		max = Axis.suggestMax(max);

		Axis<String> xAxis = AxisFactory.createCategoryAxis(AxisDirection.X,
				new ParameterWithArbitraryUnit<>("File", "",
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

		List<Mark2D<String, Double>> miWoc = new ArrayList<Mark2D<String, Double>>();
		List<Mark2D<String, Double>> miCw = new ArrayList<Mark2D<String, Double>>();
		for (Mark2D<String, Double> m : paretoValuesMI) {
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

		chartCanvas.addColoredArea(new HorizontalColoredArea<String, Double>(
				plotMI, yAxis.getMinimum(), 65, SWTColor.PALE_RED));
		chartCanvas.addColoredArea(new HorizontalColoredArea<String, Double>(
				plotMI, 65, 85, SWTColor.PALE_YELLOW));
		chartCanvas.addColoredArea(new HorizontalColoredArea<String, Double>(
				plotMI, 85, yAxis.getMaximum(), SWTColor.PALE_GREEN));

		chartCanvas.refresh();
	}

	@Override
	public void export() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}
}
