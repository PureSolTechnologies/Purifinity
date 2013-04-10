package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
import org.eclipse.birt.chart.model.attribute.LegendItemType;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.NumberDataSet;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.TextDataSet;
import org.eclipse.birt.chart.model.data.impl.NumberDataSetImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.data.impl.TextDataSetImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.layout.Plot;
import org.eclipse.birt.chart.model.type.BarSeries;
import org.eclipse.birt.chart.model.type.impl.BarSeriesImpl;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.client.common.ui.actions.PartSettingsCapability;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.actions.Reproducable;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.client.common.ui.components.ChartCanvas;

public class ParetoChartView extends ViewPart implements Refreshable,
		Reproducable, ISelectionListener, PartSettingsCapability {

	private ChartCanvas chartCanvas;
	private ChartWithAxes chart;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		chartCanvas = new ChartCanvas(parent);
		chart = ChartWithAxesImpl.create();
		chartCanvas.setChart(chart);

		chart.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
		Plot plot = chart.getPlot();
		plot.setBackground(ColorDefinitionImpl.CREAM());
		plot.getClientArea().setBackground(ColorDefinitionImpl.WHITE());
		chart.getLegend().setItemType(LegendItemType.CATEGORIES_LITERAL);
		chart.getLegend().setVisible(true);
		chart.getTitle().getLabel().getCaption().setValue("Pareto Chart");
		chart.getTitle().getLabel().getCaption().getFont().setSize(14);
		chart.getTitle().getLabel().getCaption().getFont().setName("Arial");

		Axis xAxis = chart.getPrimaryBaseAxes()[0];
		xAxis.getTitle().setVisible(true);
		xAxis.getTitle().getCaption().setValue("X-Axis");
		xAxis.setGapWidth(0);

		Axis yAxis = chart.getPrimaryOrthogonalAxis(xAxis);
		yAxis.getTitle().setVisible(true);
		yAxis.getTitle().getCaption().setValue("Y-Axis");
		yAxis.getScale().setStep(1000.0);

		List<String> files = new ArrayList<String>();
		List<Double> values = new ArrayList<Double>();
		for (int i = 0; i < 100; i++) {
			files.add("File" + i);
			values.add(Math.pow(100.0 - i, 2.0));
		}

		TextDataSet categoryValues = TextDataSetImpl.create(files
				.toArray(new String[files.size()]));

		Series seCategory = SeriesImpl.create();
		seCategory.setDataSet(categoryValues);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();

		xAxis.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		NumberDataSet orthoValuesDataSet1 = NumberDataSetImpl.create(values
				.toArray(new Double[values.size()]));

		BarSeries bs1 = (BarSeries) BarSeriesImpl.create();
		bs1.setDataSet(orthoValuesDataSet1);

		SeriesDefinition sdY = SeriesDefinitionImpl.create();
		yAxis.getSeriesDefinitions().add(sdY);
		sdY.getSeries().add(bs1);

		// TODO Auto-generated method stub
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
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

}
