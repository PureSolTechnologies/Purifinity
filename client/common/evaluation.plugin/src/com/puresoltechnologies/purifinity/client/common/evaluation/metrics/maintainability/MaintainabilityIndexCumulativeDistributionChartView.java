package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.puresoltechnologies.commons.trees.TreeVisitor;
import com.puresoltechnologies.commons.trees.TreeWalker;
import com.puresoltechnologies.commons.trees.WalkingAction;
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
import com.puresoltechnologies.purifinity.client.common.chart.Mark2D;
import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.VerticalColoredArea;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.CircleMarkRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ConstantColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AbstractMetricChartViewPart;
import com.puresoltechnologies.purifinity.client.common.server.connectors.MaintainabilityIndexEvaluatorConnector;
import com.puresoltechnologies.purifinity.client.common.ui.SWTColor;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.client.socket.ParetoChartDataProviderClient;
import com.puresoltechnologies.purifinity.server.domain.ParetoChartData;

public class MaintainabilityIndexCumulativeDistributionChartView extends
		AbstractMetricChartViewPart {

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

		setSelection(AnalysisSelections.getInstance().getAnalysisSelection());
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
		Job job = new Job("Maintainability Index Cumulative Distribution Chart") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Load data", 7);
				try (ParetoChartDataProviderClient client = new ParetoChartDataProviderClient()) {
					client.connect();
					monitor.worked(1);
					final AnalysisSelection analysisSelection = getAnalysisSelection();
					monitor.worked(1);
					UUID analysisProjectUUID = analysisSelection
							.getAnalysisProject().getInformation().getUUID();
					monitor.worked(1);
					UUID analysisRunUUID = analysisSelection.getAnalysisRun()
							.getInformation().getUUID();
					monitor.worked(1);
					mi = client.loadParetoChartData(analysisProjectUUID,
							analysisRunUUID,
							MaintainabilityIndexEvaluatorConnector.NAME,
							MaintainabilityIndexEvaluatorParameter.MI,
							codeRangeTypeSelection);
					monitor.worked(1);
					miWoc = client.loadParetoChartData(analysisProjectUUID,
							analysisRunUUID,
							MaintainabilityIndexEvaluatorConnector.NAME,
							MaintainabilityIndexEvaluatorParameter.MI_WOC,
							codeRangeTypeSelection);
					monitor.worked(1);
					miCw = client.loadParetoChartData(analysisProjectUUID,
							analysisRunUUID,
							MaintainabilityIndexEvaluatorConnector.NAME,
							MaintainabilityIndexEvaluatorParameter.MI_CW,
							codeRangeTypeSelection);
					monitor.worked(1);
					monitor.done();
					new UIJob(
							"Draw Maintainability Index Cumulative Distribution Chart") {
						@Override
						public IStatus runInUIThread(IProgressMonitor monitor) {
							showEvaluation(analysisSelection.getFileTreeNode());
							return Status.OK_STATUS;
						}
					}.schedule();
					return Status.OK_STATUS;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
		job.schedule();

	}

	@Override
	public void showEvaluation(AnalysisFileTree path) {
		final List<Mark2D<String, Double>> paretoValuesMI = new ArrayList<>();
		final List<Mark2D<String, Double>> paretoValuesMIwoc = new ArrayList<>();
		final List<Mark2D<String, Double>> paretoValuesMIcw = new ArrayList<>();
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

					extract(paretoValuesMI, node, miList);
					extract(paretoValuesMIwoc, node, miWocList);
					extract(paretoValuesMIcw, node, miCwList);

					return WalkingAction.PROCEED;
				}

			};
			TreeWalker.walk(visitor, path);
		}
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
				MaintainabilityIndexEvaluatorParameter.MI, min, max,
				(max - min) / 10.0, 1, 2);
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
