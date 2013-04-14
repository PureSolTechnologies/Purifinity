package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.CodeRangeType;
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
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.renderer.BarMarkRenderer;
import com.puresol.coding.client.common.chart.renderer.ConstantColorProvider;
import com.puresol.coding.client.common.evaluation.ParetoChartViewSettingsDialog;
import com.puresol.coding.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.coding.client.common.ui.actions.PartSettingsCapability;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.actions.Reproducable;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.utils.HashId;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;
import com.puresol.utils.math.Value;

public class ParetoChartView extends ViewPart implements Refreshable,
		Reproducable, ISelectionListener, PartSettingsCapability,
		EvaluationsTarget {

	private ISelectionService selectionService;

	private ParetoChartViewSettingsDialog settingsDialog;

	private FileAnalysisSelection analysisSelection;
	private EvaluatorFactory metricSelection = null;
	private Parameter<?> valueSelection = null;

	private Chart2D chart;
	private ChartCanvas chartCanvas;

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
					metricSelection, valueSelection);
			settingsDialog.open();
		} else {
			settingsDialog.close();
			settingsDialog = null;
		}
	}

	@Override
	public void applySettings() {
		metricSelection = settingsDialog.getMetric();
		valueSelection = settingsDialog.getParameter();
		updateEvaluation();
	}

	@Override
	public void closeSettings() {
		settingsDialog = null;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof FileAnalysisSelection) {
			analysisSelection = (FileAnalysisSelection) selection;
			updateEvaluation();
		}
	}

	@Override
	public void refresh() {
		if (settingsDialog != null) {
			settingsDialog.refresh();
		}
	}

	private void updateEvaluation() {
		if ((analysisSelection != null) && (metricSelection != null)
				&& (valueSelection != null)) {
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
		final String coreRangeTypeParameterName = CodeRangeTypeParameter
				.getInstance().getName();
		TreeVisitor<HashIdFileTree> visitor = new TreeVisitor<HashIdFileTree>() {
			@Override
			public WalkingAction visit(HashIdFileTree node) {
				if (!node.isFile()) {
					return WalkingAction.PROCEED;
				}
				HashId hashId = node.getHashId();
				MetricResults results = store.readFileResults(hashId);
				if (results == null) {
					return WalkingAction.PROCEED;
				}
				List<Map<String, Value<?>>> values = results.getValues();
				for (Map<String, Value<?>> valueMap : values) {
					if (CodeRangeType.FILE.equals(valueMap.get(
							coreRangeTypeParameterName).getValue())) {
						double value = convertToDouble(node, valueMap,
								valueSelection);
						paretoValues.add(new ParetoValue<String, Double>(node
								.getPathFile(false).getPath(), value));
					}
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);

		chart.removeAllPlots();

		chart.setTitle("Pareto Chart for " + metricSelection.getName());
		chart.setSubTitle(valueSelection.getName());

		Collections.sort(paretoValues);
		Collections.reverse(paretoValues);

		List<String> categories = new ArrayList<String>();
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
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

		if (min > 0.0) {
			min = 0.0;
		}
		if (max < 0.0) {
			max = 0.0;
		}

		Axis<Double> yAxis = AxisFactory.createDoubleValueAxis(AxisDirection.Y,
				MaintainabilityIndexEvaluatorParameter.MI, min, max,
				(max - min) / 10.0, 1);
		chart.setyAxis(yAxis);

		Plot<String, Double> plot = new Plot<String, Double>(xAxis, yAxis,
				"Pareto Plot");
		for (int i = 0; i < paretoValues.size(); i++) {
			plot.add(new DataPoint2D(new Point2D(i, paretoValues.get(i)
					.getValue())));
		}
		chart.addPlot(plot);
		chartCanvas.setMarkRenderer(plot, new BarMarkRenderer(1.0));
		chartCanvas.setColorProvider(plot, new ConstantColorProvider(new RGB(
				255, 0, 0), new RGB(0, 0, 255)));
		chartCanvas.refresh();
	}

	private double convertToDouble(HashIdFileTree path,
			Map<String, Value<?>> valueMap, Parameter<?> parameter) {
		double sum = 0.0;
		Value<?> value = valueMap.get(parameter.getName());
		if ((value != null)
				&& (Number.class.isAssignableFrom(parameter.getType()))) {
			Number number = (Number) value.getValue();
			sum = number.doubleValue();
		} else {
			throw new RuntimeException("Value '" + value
					+ "' is not a number for '" + path.getPathFile(false)
					+ "'!");
		}
		return sum;
	}

}
