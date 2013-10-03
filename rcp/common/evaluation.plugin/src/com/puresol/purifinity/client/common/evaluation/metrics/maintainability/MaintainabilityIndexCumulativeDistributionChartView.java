package com.puresol.purifinity.client.common.evaluation.metrics.maintainability;

import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_CW;
import static com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_WOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.commons.math.LevelOfMeasurement;
import com.puresol.commons.math.ParameterWithArbitraryUnit;
import com.puresol.commons.math.Value;
import com.puresol.commons.trees.TreeVisitor;
import com.puresol.commons.trees.TreeWalker;
import com.puresol.commons.trees.WalkingAction;
import com.puresol.commons.utils.HashId;
import com.puresol.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresol.purifinity.client.common.chart.Axis;
import com.puresol.purifinity.client.common.chart.AxisDirection;
import com.puresol.purifinity.client.common.chart.AxisFactory;
import com.puresol.purifinity.client.common.chart.Chart2D;
import com.puresol.purifinity.client.common.chart.ChartCanvas;
import com.puresol.purifinity.client.common.chart.GenericMark2D;
import com.puresol.purifinity.client.common.chart.Mark2D;
import com.puresol.purifinity.client.common.chart.Plot;
import com.puresol.purifinity.client.common.chart.VerticalColoredArea;
import com.puresol.purifinity.client.common.chart.renderer.CircleMarkRenderer;
import com.puresol.purifinity.client.common.chart.renderer.ConstantColorProvider;
import com.puresol.purifinity.client.common.evaluation.views.AbstractMetricChartViewPart;
import com.puresol.purifinity.client.common.ui.actions.RefreshAction;
import com.puresol.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresol.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresol.purifinity.coding.analysis.api.AnalysisProject;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluator;

public class MaintainabilityIndexCumulativeDistributionChartView extends
		AbstractMetricChartViewPart implements MouseListener,
		ISelectionProvider {

	private final List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();
	private AnalysisSelection analysisSelection;

	private final CodeRangeType codeRangeTypeSelection = CodeRangeType.FILE;
	private Chart2D chart;
	private ChartCanvas chartCanvas;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		chartCanvas = new ChartCanvas(parent, SWT.NONE);
		chartCanvas.addMouseListener(this);
		chart = new Chart2D();

		chartCanvas.setChart2D(chart);

		initializeToolBar();

		getSite().setSelectionProvider(this);

		super.createPartControl(parent);
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
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateEvaluation() {
		AnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			HashIdFileTree path = analysisSelection.getFileTreeNode();
			if (path.isFile()) {
				path = path.getParent();
			}
			showEvaluation(path);
		}
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		final EvaluatorStore store = EvaluatorStoreFactory.getFactory()
				.createInstance(MaintainabilityIndexEvaluator.class);
		final List<Mark2D<String, Double>> paretoValuesMI = new ArrayList<>();
		final List<Mark2D<String, Double>> paretoValuesMIwoc = new ArrayList<>();
		final List<Mark2D<String, Double>> paretoValuesMIcw = new ArrayList<>();
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
						node, results, MI, codeRangeTypeSelection);
				String path = node.getPathFile(false).getPath();
				for (Map<String, Value<?>> valueMap : valueMaps) {
					String codeRangeName = (String) valueMap.get(
							CodeRangeNameParameter.getInstance().getName())
							.getValue();
					double value = convertToDouble(valueMap, MI);
					String name = path + "." + codeRangeName;
					paretoValuesMI.add(new GenericMark2D<String, Double>(name,
							value, name, node));
					value = convertToDouble(valueMap, MI_WOC);
					paretoValuesMIwoc.add(new GenericMark2D<String, Double>(
							name, value, name, node));
					value = convertToDouble(valueMap, MI_CW);
					paretoValuesMIcw.add(new GenericMark2D<String, Double>(
							name, value, name, node));
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, path);

		setupChart(paretoValuesMI, paretoValuesMIwoc, paretoValuesMIcw);
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

		chartCanvas.setMarkRenderer(plotMI, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMI, new ConstantColorProvider(new RGB(
				255, 0, 0)));

		chartCanvas.setMarkRenderer(plotMIwoc, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMIwoc, new ConstantColorProvider(
				new RGB(0, 255, 0)));

		chartCanvas.setMarkRenderer(plotMIcw, new CircleMarkRenderer());
		chartCanvas.setColorProvider(plotMIcw, new ConstantColorProvider(
				new RGB(0, 0, 255)));

		chartCanvas.addColoredArea(new VerticalColoredArea<Double, Double>(
				plotMI, xAxis.getMinimum(), 65, new RGB(255, 210, 210)));
		chartCanvas.addColoredArea(new VerticalColoredArea<Double, Double>(
				plotMI, 65, 85, new RGB(255, 255, 210)));
		chartCanvas.addColoredArea(new VerticalColoredArea<Double, Double>(
				plotMI, 85, xAxis.getMaximum(), new RGB(210, 255, 210)));

		chartCanvas.refresh();
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

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// intentionally left blank
	}

	@Override
	public void mouseDown(MouseEvent e) {
		if (e.getSource() == chartCanvas) {
			int x = e.x;
			int y = e.y;
			Object reference = chartCanvas.getReference(x, y);
			AnalysisSelection selection = getAnalysisSelection();
			if ((reference != null) && (selection != null)) {
				AnalysisProject analysis = selection.getAnalysis();
				AnalysisRun analysisRun = selection.getAnalysisRun();
				if (reference instanceof HashIdFileTree) {
					HashIdFileTree node = (HashIdFileTree) reference;
					setSelection(new AnalysisSelection(analysis, analysisRun,
							node));
				}
			}
		}
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// intentionally left blank
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListener.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListener.remove(listener);
	}

	@Override
	public ISelection getSelection() {
		return analysisSelection;
	}

	@Override
	public void setSelection(ISelection selection) {
		analysisSelection = (AnalysisSelection) selection;
		for (ISelectionChangedListener listener : selectionChangedListener) {
			listener.selectionChanged(new SelectionChangedEvent(this,
					analysisSelection));
		}
	}

}
