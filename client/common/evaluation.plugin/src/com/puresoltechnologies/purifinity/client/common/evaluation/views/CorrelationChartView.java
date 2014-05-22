package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.progress.UIJob;

import com.puresoltechnologies.commons.math.Parameter;
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
import com.puresoltechnologies.purifinity.client.common.chart.renderer.CircleMarkRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ConstantColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.CorrelationChartViewSettingsDialog;
import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;
import com.puresoltechnologies.purifinity.client.common.server.Evaluators;
import com.puresoltechnologies.purifinity.client.common.ui.SWTColor;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.server.client.socket.ParetoChartDataProviderClient;
import com.puresoltechnologies.purifinity.server.domain.ParetoChartData;

public class CorrelationChartView extends AbstractMetricChartViewPart {

	private EvaluatorFactory xMetricSelection = null;
	private EvaluatorFactory oldXMetricSelection = null;
	private Parameter<?> xParameterSelection = null;
	private Parameter<?> oldXParameterSelection = null;
	private EvaluatorFactory yMetricSelection = null;
	private EvaluatorFactory oldYMetricSelection = null;
	private Parameter<?> yParameterSelection = null;
	private Parameter<?> oldYParameterSelection = null;

	private CorrelationChartViewSettingsDialog settingsDialog = null;

	private Chart2D chart;

	private ParetoChartData xValues = new ParetoChartData();
	private ParetoChartData yValues = new ParetoChartData();

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
				throw new RuntimeException("Could not find class '"
						+ xMetricClass + "'.", e);
			}
		}
		String yMetricClass = memento.getString("y.metric.class");
		if (yMetricClass != null) {
			try {
				Class.forName(yMetricClass);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Could not find class '"
						+ yMetricClass + "'.", e);
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
		if (xMetricSelection != null) {
			memento.putString("x.metric.class", xMetricSelection.getClass()
					.getName());
			memento.putString("x.metric", xMetricSelection.getName());
		}
		if (xParameterSelection != null) {
			memento.putString("x.parameter", xParameterSelection.getName());
		}
		if (yMetricSelection != null) {
			memento.putString("y.metric.class", yMetricSelection.getClass()
					.getName());
			memento.putString("y.metric", yMetricSelection.getName());
		}
		if (yParameterSelection != null) {
			memento.putString("y.parameter", yParameterSelection.getName());
		}
		super.saveState(memento);
	}

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
		updateView();
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
	protected void clear() {
		showEvaluation(null);
	}

	@Override
	protected void updateView() {
		oldXMetricSelection = xMetricSelection;
		oldXParameterSelection = xParameterSelection;
		oldYMetricSelection = yMetricSelection;
		oldYParameterSelection = yParameterSelection;
		loadData();
	}

	@Override
	protected boolean hasFullViewSettings() {
		return (xMetricSelection != null) && (xParameterSelection != null)
				&& (yMetricSelection != null) && (yParameterSelection != null);
	}

	@Override
	protected boolean hasChangedViewSettings() {
		if ((oldXMetricSelection == null)
				|| (!xMetricSelection.getClass().equals(
						oldXMetricSelection.getClass()))) {
			return true;
		}
		if (!oldXParameterSelection.equals(xParameterSelection)) {
			return true;
		}
		if ((oldYMetricSelection == null)
				|| (!yMetricSelection.getClass().equals(
						oldYMetricSelection.getClass()))) {
			return true;
		}
		if (!oldYParameterSelection.equals(yParameterSelection)) {
			return true;
		}
		return false;
	}

	private void loadData() {
		Job job = new Job("Correlation Chart") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Load data", 6);
				try (ParetoChartDataProviderClient client = new ParetoChartDataProviderClient()) {
					client.connect();
					monitor.worked(1);
					final AnalysisSelection analysisSelection = getAnalysisSelection();
					monitor.worked(1);
					UUID analysisProjectUUID = analysisSelection
							.getAnalysisProject().getInformation().getUUID();
					monitor.worked(1);
					UUID analysisRunUUID = analysisSelection.getAnalysisRun()
							.getInformation().getRunUUID();
					monitor.worked(1);
					xValues = client.loadParetoChartData(analysisProjectUUID,
							analysisRunUUID, xMetricSelection.getName(),
							xParameterSelection, CodeRangeType.FILE);
					monitor.worked(1);
					yValues = client.loadParetoChartData(analysisProjectUUID,
							analysisRunUUID, yMetricSelection.getName(),
							yParameterSelection, CodeRangeType.FILE);
					monitor.worked(1);
					monitor.done();
					new UIJob("Draw Correlation Chart") {
						@Override
						public IStatus runInUIThread(IProgressMonitor monitor) {
							AnalysisFileTree path = analysisSelection
									.getFileTreeNode();
							if (path.isFile()) {
								path = path.getParent();
							}
							showEvaluation(path);
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
		final List<Mark2D<Double, Double>> correlationValues = new ArrayList<Mark2D<Double, Double>>();
		if (path != null) {
			TreeVisitor<AnalysisFileTree> visitor = new TreeVisitor<AnalysisFileTree>() {
				@Override
				public WalkingAction visit(AnalysisFileTree node) {
					if (!node.isFile()) {
						return WalkingAction.PROCEED;
					}
					HashId hashId = node.getHashId();

					Map<String, Value<? extends Number>> xValueList = xValues
							.getValues(hashId);
					if (xValueList == null) {
						return WalkingAction.PROCEED;
					}
					Map<String, Value<? extends Number>> yValueList = yValues
							.getValues(hashId);
					if (yValueList == null) {
						return WalkingAction.PROCEED;
					}

					for (String codeRangeName : xValueList.keySet()) {

						Double xValue = xValueList.get(codeRangeName)
								.getValue().doubleValue();
						Double yValue = yValueList.get(codeRangeName)
								.getValue().doubleValue();
						if ((xValue != null) && (yValue != null)) {
							Mark2D<Double, Double> value = new GenericMark2D<Double, Double>(
									xValue, yValue, node.getPathFile(false)
											.toString(), node);
							correlationValues.add(value);
						}
					}
					return WalkingAction.PROCEED;
				}
			};
			TreeWalker.walk(visitor, path);
		}
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
