package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.AxisFactory;
import com.puresol.coding.client.common.chart.Chart2D;
import com.puresol.coding.client.common.chart.ChartCanvas;
import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.ParetoValue;
import com.puresol.coding.client.common.chart.renderer.BarMarkRenderer;
import com.puresol.coding.client.common.evaluation.Activator;
import com.puresol.coding.client.common.evaluation.ParetoChartViewSettingsDialog;
import com.puresol.coding.client.common.evaluation.metrics.ChartConfigProvider;
import com.puresol.coding.client.common.evaluation.metrics.DefaultParetoChartConfigProvider;
import com.puresol.coding.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.Evaluators;
import com.puresol.coding.evaluation.api.MetricFileResults;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.HashId;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;

public class ParetoChartView extends AbstractMetricViewPart implements
		EvaluationsTarget {

	private ISelectionService selectionService;

	private ParetoChartViewSettingsDialog settingsDialog;

	private EvaluatorFactory metricSelection = null;
	private Parameter<?> parameterSelection = null;

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
	}

	@Override
	public void saveState(IMemento memento) {
		memento.putString("metric.class", metricSelection.getClass().getName());
		memento.putString("metric", metricSelection.getName());
		memento.putString("parameter", parameterSelection.getName());

		super.saveState(memento);
	}

	@Override
	public void dispose() {
		selectionService.removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		chartCanvas = new ChartCanvas(parent, SWT.NONE);
		chart = new Chart2D();

		chartCanvas.setChart2D(chart);

		IWorkbenchPartSite site = getSite();
		IWorkbenchWindow workbenchWindow = site.getWorkbenchWindow();
		selectionService = workbenchWindow.getSelectionService();
		selectionService.addSelectionListener(this);

		initializeToolBar();
		super.createPartControl(parent);
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
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
					metricSelection, parameterSelection);
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
		final List<ParetoValue<String, Double>> paretoValues = new ArrayList<ParetoValue<String, Double>>();
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
				double value = findSuitableValue(node, results,
						parameterSelection);
				paretoValues.add(new ParetoValue<String, Double>(node
						.getPathFile(false).getPath(), value));
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);

		setupChart(paretoValues);
	}

	private void setupChart(final List<ParetoValue<String, Double>> paretoValues) {
		chart.removeAllPlots();

		chart.setTitle("Pareto Chart for " + metricSelection.getName());
		chart.setSubTitle(parameterSelection.getName());

		Collections.sort(paretoValues);
		Collections.reverse(paretoValues);

		List<String> categories = new ArrayList<String>();
		double min = 0.0;
		double max = 0.0;
		for (ParetoValue<String, Double> value : paretoValues) {
			categories.add(value.getCategory());
			min = Math.min(min, value.getValue());
			max = Math.max(max, value.getValue());
		}

		Axis<String> xAxis = AxisFactory.createCategoryAxis(AxisDirection.X,
				new ParameterWithArbitraryUnit<String>("File", "",
						LevelOfMeasurement.NOMINAL, "", String.class),
				categories.toArray(new String[categories.size()]));
		chart.setxAxis(xAxis);

		@SuppressWarnings("unchecked")
		Parameter<Double> yAxisParameter = (Parameter<Double>) parameterSelection;
		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				yAxisParameter, min, max, (max - min) / 10.0, 1);
		chart.setyAxis(yAxis);

		Plot<String, Double> plot = new Plot<String, Double>(xAxis, yAxis,
				"Pareto Plot");
		for (ParetoValue<String, Double> paretoValue : paretoValues) {
			plot.add(new DataPoint2D<String, Double>(paretoValue.getCategory(),
					paretoValue.getValue()));
		}
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

}
