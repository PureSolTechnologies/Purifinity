package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
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
import com.puresoltechnologies.purifinity.client.common.chart.renderer.BarMarkRenderer;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.ParetoChartViewSettingsDialog;
import com.puresoltechnologies.purifinity.client.common.evaluation.metrics.ChartConfigProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.metrics.DefaultParetoChartConfigProvider;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ExportAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.Evaluators;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.api.HistogramChartDataProvider;
import com.puresoltechnologies.purifinity.framework.store.api.HistogramChartDataProviderFactory;

public class ParetoChartView extends AbstractMetricChartViewPart {

	private ParetoChartViewSettingsDialog settingsDialog;

	private UUID analysisProjectSelectionUUID = null;
	private UUID oldAnalysisProjectSelectionUUID = null;
	private UUID analysisRunSelectionUUID = null;
	private UUID oldAnalysisRunSelectionUUID = null;
	private EvaluatorFactory evaluatorSelection = null;
	private EvaluatorFactory oldEvaluatorSelection = null;
	private Parameter<?> parameterSelection = null;
	private Parameter<?> oldParameterSelection = null;
	private CodeRangeType codeRangeTypeSelection = CodeRangeType.FILE;
	private CodeRangeType oldCodeRangeTypeSelection = CodeRangeType.FILE;

	private Chart2D chart;

	private final Map<HashId, List<Value<?>>> values = new HashMap<>();

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
		String codeRangeTypeSelectionName = memento.getString("coderangetype");
		if (codeRangeTypeSelectionName != null) {
			codeRangeTypeSelection = CodeRangeType.valueOf(CodeRangeType.class,
					codeRangeTypeSelectionName);
		}
	}

	@Override
	public void saveState(IMemento memento) {
		memento.putString("metric.class", evaluatorSelection.getClass()
				.getName());
		memento.putString("metric", evaluatorSelection.getName());
		memento.putString("parameter", parameterSelection.getName());
		memento.putString("coderangetype", codeRangeTypeSelection.name());

		super.saveState(memento);
	}

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
		toolbarManager.add(new ExportAction(this));
		toolbarManager.add(new ShowSettingsAction(this));
		toolbarManager.add(new ViewReproductionAction(this));
		toolbarManager.add(new RefreshAction(this));
	}

	@Override
	public void showSettings() {
		if (settingsDialog == null) {
			settingsDialog = new ParetoChartViewSettingsDialog(this,
					evaluatorSelection, parameterSelection,
					codeRangeTypeSelection);
			settingsDialog.open();
		} else {
			settingsDialog.close();
			settingsDialog = null;
		}
	}

	@Override
	public void applySettings() {
		evaluatorSelection = settingsDialog.getMetric();
		parameterSelection = settingsDialog.getParameter();
		codeRangeTypeSelection = settingsDialog.getCodeRangeType();
		if ((evaluatorSelection != null) && (parameterSelection != null)
				&& (codeRangeTypeSelection != null)) {
			handleChangedAnalysisSelection();
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
	protected void handleChangedAnalysisSelection() {
		AnalysisSelection analysisSelection = getAnalysisSelection();
		if ((analysisSelection != null) && (evaluatorSelection != null)
				&& (parameterSelection != null)) {
			analysisProjectSelectionUUID = analysisSelection
					.getAnalysisProject().getInformation().getUUID();
			analysisRunSelectionUUID = analysisSelection.getAnalysisRun()
					.getInformation().getUUID();
			if (wasSelectionChanged()) {
				oldAnalysisProjectSelectionUUID = analysisProjectSelectionUUID;
				oldAnalysisRunSelectionUUID = analysisRunSelectionUUID;
				oldEvaluatorSelection = evaluatorSelection;
				oldParameterSelection = parameterSelection;
				oldCodeRangeTypeSelection = codeRangeTypeSelection;
				loadData();
			}
			showEvaluation(analysisSelection.getFileTreeNode());
		}
	}

	private boolean wasSelectionChanged() {
		if (analysisProjectSelectionUUID
				.equals(oldAnalysisProjectSelectionUUID)) {
			return true;
		}
		if (!analysisRunSelectionUUID.equals(oldAnalysisRunSelectionUUID)) {
			return true;
		}
		if ((oldEvaluatorSelection == null)
				|| (!evaluatorSelection.getClass().equals(
						oldEvaluatorSelection.getClass()))) {
			return true;
		}
		if (!oldParameterSelection.equals(parameterSelection)) {
			return true;
		}
		if (!oldCodeRangeTypeSelection.equals(codeRangeTypeSelection)) {
			return true;
		}
		return false;
	}

	private void loadData() {
		values.clear();
		HistogramChartDataProvider dataProvider = HistogramChartDataProviderFactory
				.getFactory().getInstance();
		Map<HashId, List<Value<?>>> loadedValues = dataProvider.loadValues(
				analysisProjectSelectionUUID, analysisRunSelectionUUID,
				evaluatorSelection.getName(), parameterSelection,
				codeRangeTypeSelection);
		values.putAll(loadedValues);
	}

	@Override
	public void showEvaluation(AnalysisFileTree path) {
		final EvaluatorStore store = EvaluatorStoreFactory.getFactory()
				.createInstance(evaluatorSelection.getEvaluatorClass());
		final List<Mark2D<String, Double>> paretoValues = new ArrayList<Mark2D<String, Double>>();
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
							node, results, parameterSelection,
							codeRangeTypeSelection);
					String codeRangeNameParameterName = CodeRangeNameParameter
							.getInstance().getName();
					Set<String> usedCategories = new HashSet<String>();
					for (Map<String, Value<?>> valueMap : valueMaps) {
						String codeRangeName = (String) valueMap.get(
								codeRangeNameParameterName).getValue();
						double value = convertToDouble(valueMap,
								parameterSelection);
						String category = node.getPathFile(false).getPath()
								+ "." + codeRangeName;
						paretoValues
								.add(new GenericMark2D<String, Double>(
										category, value, codeRangeTypeSelection
												.getName()
												+ " "
												+ codeRangeName, node));
						usedCategories.add(category);
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

		setupChart(paretoValues);
	}

	private void setupChart(final List<Mark2D<String, Double>> paretoValues) {
		chart.removeAllPlots();

		chart.setTitle("Pareto Chart for " + evaluatorSelection.getName());
		chart.setSubTitle(parameterSelection.getName());

		Collections.sort(paretoValues,
				new Comparator<Mark2D<String, Double>>() {
					@Override
					public int compare(Mark2D<String, Double> o1,
							Mark2D<String, Double> o2) {
						return o2.getY().compareTo(o1.getY());
					}
				});

		List<String> categories = new ArrayList<String>();
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
		Parameter<Double> yAxisParameter = (Parameter<Double>) parameterSelection;
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
			String parameterName = parameterSelection.getName();
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
