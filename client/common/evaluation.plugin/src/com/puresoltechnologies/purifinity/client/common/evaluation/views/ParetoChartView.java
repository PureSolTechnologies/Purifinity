package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
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
import com.puresoltechnologies.purifinity.client.common.chart.renderer.BarMarkRenderer;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.HistogramChartViewSettingsDialog;
import com.puresoltechnologies.purifinity.client.common.evaluation.ParetoChartViewSettingsDialog;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.MetricParameterSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.metrics.ChartConfigProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.metrics.DefaultParetoChartConfigProvider;
import com.puresoltechnologies.purifinity.client.common.server.PurifinityServerClientFactory;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ExportAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.ParetoChartData;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerClient;

public class ParetoChartView extends AbstractMetricChartViewPart {

	private ParetoChartViewSettingsDialog settingsDialog;

	private MetricParameterSelection metricParameterSelection = new MetricParameterSelection(
			null, null, null);
	private MetricParameterSelection oldMetricParameterSelection = new MetricParameterSelection(
			null, null, null);

	private Chart2D chart;

	private ParetoChartData values = new ParetoChartData();

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site, memento);
		metricParameterSelection = HistogramChartViewSettingsDialog
				.init(memento);
	}

	@Override
	public void saveState(IMemento memento) {
		HistogramChartViewSettingsDialog.saveState(memento,
				metricParameterSelection);
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
		toolbarManager.add(new ExportAction(this));
		toolbarManager.add(new ShowSettingsAction(this));
		toolbarManager.add(new ViewReproductionAction(this));
		toolbarManager.add(new RefreshAction(this));
	}

	@Override
	public void showSettings() {
		if (settingsDialog == null) {
			settingsDialog = new ParetoChartViewSettingsDialog(this,
					metricParameterSelection);
			settingsDialog.open();
		} else {
			settingsDialog.close();
			settingsDialog = null;
		}
	}

	@Override
	public void applySettings() {
		metricParameterSelection = settingsDialog.getMetricParameterSelection();
		if (metricParameterSelection.isComplete()) {
			updateView();
		}
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
		oldMetricParameterSelection = metricParameterSelection;
		loadData();
	}

	@Override
	protected boolean hasFullViewSettings() {
		return metricParameterSelection.isComplete();
	}

	@Override
	protected boolean hasChangedViewSettings() {
		if (!oldMetricParameterSelection.equals(metricParameterSelection)) {
			return true;
		}
		return false;
	}

	private void loadData() {
		Job job = new Job("Pareto Chart") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Load data", 5);
				PurifinityServerClient client = PurifinityServerClientFactory
						.getInstance();
				monitor.worked(1);
				final AnalysisSelection analysisSelection = getAnalysisSelection();
				monitor.worked(1);
				UUID analysisProjectUUID = analysisSelection
						.getAnalysisProject().getInformation().getUUID();
				monitor.worked(1);
				UUID analysisRunUUID = analysisSelection.getAnalysisRun()
						.getInformation().getUUID();
				monitor.worked(1);
				try {
					values = client.loadParetoChartData(analysisProjectUUID,
							analysisRunUUID, metricParameterSelection
									.getEvaluatorFactory().getName(),
							metricParameterSelection.getParameter(),
							metricParameterSelection.getCodeRangeType());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				monitor.worked(1);
				monitor.done();
				new UIJob("Draw Pareto Chart") {

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
		final List<Mark2D<String, Double>> paretoValues = new ArrayList<>();
		if (path != null) {
			TreeVisitor<AnalysisFileTree> visitor = new TreeVisitor<AnalysisFileTree>() {
				@Override
				public WalkingAction visit(AnalysisFileTree node) {
					if (!node.isFile()) {
						return WalkingAction.PROCEED;
					}
					HashId hashId = node.getHashId();

					Map<String, Value<? extends Number>> valueList = values
							.getValues(hashId);
					if (valueList == null) {
						return WalkingAction.PROCEED;
					}

					for (Entry<String, Value<? extends Number>> entry : valueList
							.entrySet()) {
						String codeRangeName = entry.getKey();
						Number value = entry.getValue().getValue();
						String category = node.getPathFile(false).getPath()
								+ "." + codeRangeName;
						paretoValues
								.add(new GenericMark2D<String, Double>(
										category, value.doubleValue(),
										metricParameterSelection
												.getCodeRangeType().getName()
												+ " " + codeRangeName, node));
					}
					return WalkingAction.PROCEED;
				}
			};
			TreeWalker.walk(visitor, path);
		}
		setupChart(paretoValues);
	}

	private void setupChart(final List<Mark2D<String, Double>> paretoValues) {
		chart.removeAllPlots();

		if (!metricParameterSelection.isComplete()) {
			return;
		}

		chart.setTitle("Pareto Chart for "
				+ metricParameterSelection.getEvaluatorFactory().getName());
		chart.setSubTitle(metricParameterSelection.getParameter().getName());

		Collections.sort(paretoValues,
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
		for (Mark2D<String, Double> value : paretoValues) {
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

		@SuppressWarnings("unchecked")
		Parameter<Double> yAxisParameter = (Parameter<Double>) metricParameterSelection
				.getParameter();
		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				yAxisParameter, min, max, (max - min) / 10.0, 1, 2);
		chart.setyAxis(yAxis);

		Plot<String, Double> plot = new Plot<String, Double>(xAxis, yAxis,
				"Pareto Plot");
		plot.add(paretoValues);
		chart.addPlot(plot);
		ChartCanvas chartCanvas = getChartCanvas();
		chartCanvas.setMarkRenderer(plot, new BarMarkRenderer(1.0));

		ChartConfigProvider configProvider = getConfigProvider();

		chartCanvas.setColorProvider(plot, configProvider.getColorProvider());
		chartCanvas.refresh();
	}

	private ChartConfigProvider getConfigProvider() {
		try {
			BundleContext bundleContext = Activator.getDefault().getBundle()
					.getBundleContext();
			String parameterName = metricParameterSelection.getParameter()
					.getName();
			parameterName = parameterName.replaceAll("\\(", "\\\\(")
					.replaceAll("\\)", "\\\\)");
			String filter = "(parameterName=" + parameterName + ")";
			Collection<ServiceReference<ChartConfigProvider>> serviceReferences = bundleContext
					.getServiceReferences(ChartConfigProvider.class, filter);
			if (!serviceReferences.isEmpty()) {
				ServiceReference<ChartConfigProvider> serviceReference = serviceReferences
						.iterator().next();
				try {
					return bundleContext.getService(serviceReference);
				} finally {
					bundleContext.ungetService(serviceReference);
				}
			} else {
				return new DefaultParetoChartConfigProvider();
			}
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void export() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}

}
