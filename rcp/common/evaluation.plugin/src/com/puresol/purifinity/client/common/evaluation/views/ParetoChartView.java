package com.puresol.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.commons.trees.TreeVisitor;
import com.puresol.commons.trees.TreeWalker;
import com.puresol.commons.trees.WalkingAction;
import com.puresol.commons.utils.HashId;
import com.puresol.commons.utils.math.LevelOfMeasurement;
import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.ParameterWithArbitraryUnit;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.chart.Axis;
import com.puresol.purifinity.client.common.chart.AxisDirection;
import com.puresol.purifinity.client.common.chart.AxisFactory;
import com.puresol.purifinity.client.common.chart.Chart2D;
import com.puresol.purifinity.client.common.chart.ChartCanvas;
import com.puresol.purifinity.client.common.chart.DataPoint2D;
import com.puresol.purifinity.client.common.chart.Plot;
import com.puresol.purifinity.client.common.chart.renderer.BarMarkRenderer;
import com.puresol.purifinity.client.common.evaluation.Activator;
import com.puresol.purifinity.client.common.evaluation.ParetoChartViewSettingsDialog;
import com.puresol.purifinity.client.common.evaluation.metrics.ChartConfigProvider;
import com.puresol.purifinity.client.common.evaluation.metrics.DefaultParetoChartConfigProvider;
import com.puresol.purifinity.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.purifinity.client.common.ui.actions.ExportAction;
import com.puresol.purifinity.client.common.ui.actions.RefreshAction;
import com.puresol.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresol.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluators;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;

public class ParetoChartView extends AbstractMetricChartViewPart implements
		EvaluationsTarget {

	private ParetoChartViewSettingsDialog settingsDialog;

	private EvaluatorFactory metricSelection = null;
	private Parameter<?> parameterSelection = null;
	private CodeRangeType codeRangeTypeSelection = CodeRangeType.FILE;

	private Chart2D chart;
	private ChartCanvas chartCanvas;

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
				metricSelection = metric;
				if (parameterSelectionName != null) {
					for (Parameter<?> parameter : metricSelection
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
		memento.putString("metric.class", metricSelection.getClass().getName());
		memento.putString("metric", metricSelection.getName());
		memento.putString("parameter", parameterSelection.getName());
		memento.putString("coderangetype", codeRangeTypeSelection.name());

		super.saveState(memento);
	}

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
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	public void showSettings() {
		if (settingsDialog == null) {
			settingsDialog = new ParetoChartViewSettingsDialog(this,
					metricSelection, parameterSelection, codeRangeTypeSelection);
			settingsDialog.open();
		} else {
			settingsDialog.close();
			settingsDialog = null;
		}
	}

	@Override
	public void applySettings() {
		metricSelection = settingsDialog.getMetric();
		parameterSelection = settingsDialog.getParameter();
		codeRangeTypeSelection = settingsDialog.getCodeRangeType();
		updateEvaluation();
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
	protected void updateEvaluation() {
		FileAnalysisSelection analysisSelection = getAnalysisSelection();
		if ((analysisSelection != null) && (metricSelection != null)
				&& (parameterSelection != null)) {
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
				.createInstance(metricSelection.getEvaluatorClass());
		final List<DataPoint2D<String, Double>> paretoValues = new ArrayList<DataPoint2D<String, Double>>();
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
						node, results, parameterSelection,
						codeRangeTypeSelection);
				String codeRangeNameParameterName = CodeRangeNameParameter
						.getInstance().getName();
				Set<String> usedCategories = new HashSet<String>();
				for (Map<String, Value<?>> valueMap : valueMaps) {
					String codeRangeName = (String) valueMap.get(
							codeRangeNameParameterName).getValue();
					double value = convertToDouble(valueMap, parameterSelection);
					String category = node.getPathFile(false).getPath() + "."
							+ codeRangeName;
					paretoValues.add(new DataPoint2D<String, Double>(category,
							value, codeRangeTypeSelection.getName() + " "
									+ codeRangeName));
					usedCategories.add(category);
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);

		setupChart(paretoValues);
	}

	private void setupChart(final List<DataPoint2D<String, Double>> paretoValues) {
		chart.removeAllPlots();

		chart.setTitle("Pareto Chart for " + metricSelection.getName());
		chart.setSubTitle(parameterSelection.getName());

		Collections.sort(paretoValues,
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
		for (DataPoint2D<String, Double> value : paretoValues) {
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

	@Override
	protected ChartCanvas getChartCanvas() {
		return chartCanvas;
	}
}
