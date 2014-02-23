package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
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
import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.BarMarkRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ConstantColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.HistogramChartViewSettingsDialog;
import com.puresoltechnologies.purifinity.client.common.ui.SWTColor;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.Evaluators;
import com.puresoltechnologies.purifinity.framework.store.api.HistogramChartData;
import com.puresoltechnologies.purifinity.framework.store.api.HistogramChartDataProvider;
import com.puresoltechnologies.purifinity.framework.store.api.HistogramChartDataProviderFactory;

public class HistogramChartView extends AbstractMetricChartViewPart {

	private static final int NUMBER_OF_NUMERICAL_CATEGORIES = 10;

	private HistogramChartViewSettingsDialog settingsDialog;

	private EvaluatorFactory evaluatorSelection = null;
	private EvaluatorFactory oldEvaluatorSelection = null;
	private Parameter<?> parameterSelection = null;
	private Parameter<?> oldParameterSelection = null;

	private Chart2D chart;

	private HistogramChartData values = new HistogramChartData();

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
				evaluatorSelection = metric;
				if (parameterSelectionName != null) {
					for (Parameter<?> parameter : evaluatorSelection
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
		if (evaluatorSelection != null) {
			memento.putString("metric.class", evaluatorSelection.getClass()
					.getName());
			memento.putString("metric", evaluatorSelection.getName());
		}
		if (parameterSelection != null) {
			memento.putString("parameter", parameterSelection.getName());
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
			settingsDialog = new HistogramChartViewSettingsDialog(this,
					evaluatorSelection, parameterSelection);
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
		evaluatorSelection = settingsDialog.getMetric();
		parameterSelection = settingsDialog.getParameter();
		if ((evaluatorSelection != null) && (parameterSelection != null)) {
			updateView();
		}
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
		oldEvaluatorSelection = evaluatorSelection;
		oldParameterSelection = parameterSelection;
		loadData();
	}

	@Override
	protected boolean hasFullViewSettings() {
		return (evaluatorSelection != null) && (parameterSelection != null);
	}

	@Override
	protected boolean hasChangedViewSettings() {
		if ((oldEvaluatorSelection == null)
				|| (!evaluatorSelection.getClass().equals(
						oldEvaluatorSelection.getClass()))) {
			return true;
		}
		if (!oldParameterSelection.equals(parameterSelection)) {
			return true;
		}
		return false;
	}

	private void loadData() {
		Job job = new Job("Histogram Chart") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Load data", 6);
				HistogramChartDataProvider dataProvider = HistogramChartDataProviderFactory
						.getFactory().getInstance();
				monitor.worked(1);
				final AnalysisSelection analysisSelection = getAnalysisSelection();
				monitor.worked(1);
				AnalysisProject analysisProject = analysisSelection
						.getAnalysisProject();
				monitor.worked(1);
				UUID analysisProjectUUID = analysisProject.getInformation()
						.getUUID();
				monitor.worked(1);
				UUID analysisRunUUID = analysisSelection.getAnalysisRun()
						.getInformation().getUUID();
				monitor.worked(1);
				values = dataProvider.loadValues(analysisProjectUUID,
						analysisRunUUID, evaluatorSelection.getName(),
						parameterSelection, CodeRangeType.FILE);
				monitor.worked(1);
				monitor.done();
				new UIJob("Draw Histogram Chart") {

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
		final List<Value<?>> histogramValues = new ArrayList<Value<?>>();
		if (path != null) {
			TreeVisitor<AnalysisFileTree> visitor = new TreeVisitor<AnalysisFileTree>() {
				@Override
				public WalkingAction visit(AnalysisFileTree node) {
					if (!node.isFile()) {
						return WalkingAction.PROCEED;
					}
					HashId hashId = node.getHashId();
					List<Value<?>> valueList = values.getValues(hashId);
					if (valueList != null) {
						for (Value<?> value : valueList) {
							histogramValues.add(value);
						}
					}
					return WalkingAction.PROCEED;
				}
			};
			TreeWalker.walk(visitor, path);
		}
		setupChart(histogramValues);
	}

	private void setupChart(List<Value<?>> histogramValues) {
		chart.removeAllPlots();

		chart.setTitle("Histogram Chart for " + evaluatorSelection.getName());
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
			getChartCanvas().refresh();
			return;
		}
		List<Double> values = new ArrayList<Double>();
		for (Value<?> value : histogramValues) {
			Object valueObject = value.getValue();
			if (valueObject != null) {
				double d = ((Number) valueObject).doubleValue();
				values.add(d);
			}
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
			plot.add(new GenericMark2D<String, Integer>(categoryArray[i],
					sums[i]));
		}
		chart.addPlot(plot);
		ChartCanvas chartCanvas = getChartCanvas();
		chartCanvas.setMarkRenderer(plot, new BarMarkRenderer(1.0));
		chartCanvas.setColorProvider(plot, new ConstantColorProvider(
				SWTColor.BLACK, SWTColor.DARK_RED));

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
			plot.add(new GenericMark2D<String, Integer>(category, sums
					.get(category)));
		}
		chart.addPlot(plot);
		ChartCanvas chartCanvas = getChartCanvas();
		chartCanvas.setMarkRenderer(plot, new BarMarkRenderer(1.0));
		chartCanvas.setColorProvider(plot, new ConstantColorProvider(
				SWTColor.BLACK, SWTColor.DARK_RED));

		chartCanvas.refresh();
	}

	@Override
	public void export() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}
}
