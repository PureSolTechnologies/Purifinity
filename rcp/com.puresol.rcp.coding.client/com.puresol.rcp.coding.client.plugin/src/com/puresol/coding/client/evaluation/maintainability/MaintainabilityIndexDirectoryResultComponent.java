package com.puresol.coding.client.evaluation.maintainability;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.IAxisSet;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.charting.BorderPosition;
import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.charting.Legend;
import com.puresol.coding.client.charting.Title;
import com.puresol.coding.client.charting.TitleType;
import com.puresol.coding.client.charting.XAxis;
import com.puresol.coding.client.charting.YAxis;
import com.puresol.coding.client.charting.rendering.Chart2DComposite;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorStore;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexFileResults;

public class MaintainabilityIndexDirectoryResultComponent extends Composite {

    public MaintainabilityIndexDirectoryResultComponent(Composite parent,
	    int style, AnalysisRun analysisRun, HashIdFileTree directory) {
	super(parent, style);

	setLayout(new FillLayout());

	MaintainabilityIndexEvaluatorStore evaluatorStore = (MaintainabilityIndexEvaluatorStore) AbstractEvaluator
		.createEvaluatorStore(MaintainabilityIndexEvaluator.class);

	Map<Double, String> maintainability = new HashMap<Double, String>();
	for (HashIdFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		MaintainabilityIndexFileResults childResults = (MaintainabilityIndexFileResults) evaluatorStore
			.readFileResults(child.getHashId());
		for (MaintainabilityIndexFileResult result : childResults) {
		    if (result.getCodeRangeType() == CodeRangeType.FILE) {
			maintainability.put(result
				.getMaintainabilityIndexResult().getMI(), child
				.getName());
		    }
		}
	    }
	}

	// http://www.swtchart.org/doc/index.html
	Chart chart = new Chart(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	chart.getTitle().setText("Paredo Chart");
	IAxisSet axisSet = chart.getAxisSet();

	double max = Double.MIN_VALUE;
	double min = Double.MAX_VALUE;
	ISeries series = chart.getSeriesSet().createSeries(SeriesType.BAR,
		"Maintainability");

	String[] categorySeries = new String[maintainability.size()];
	double[] ySeries = new double[maintainability.size()];
	int i = 0;
	Double[] keySet = maintainability.keySet().toArray(
		new Double[maintainability.size()]);
	Arrays.sort(keySet, new Comparator<Double>() {

	    @Override
	    public int compare(Double d1, Double d2) {
		return d2.compareTo(d1);
	    }
	});

	for (Double d : keySet) {
	    ySeries[i] = d;
	    categorySeries[i] = maintainability.get(d);
	    if (d > max) {
		max = d;
	    }
	    if (d < min) {
		min = d;
	    }
	    i++;
	}
	series.setYSeries(ySeries);

	IAxis xAxis = axisSet.getXAxis(0);
	xAxis.getTitle().setText("Source File");
	xAxis.enableCategory(true);
	xAxis.setCategorySeries(categorySeries);
	xAxis.adjustRange();

	IAxis yAxis = axisSet.getYAxis(0);
	yAxis.getTitle().setText("Maintainability");
	yAxis.adjustRange();

	Chart2DComposite chart2dCanvas = new Chart2DComposite(this, SWT.NONE);
	Chart2D chart2d = new Chart2D();
	chart2d.addTitle(new Title(TitleType.TITLE, "Title"));
	chart2d.addTitle(new Title(TitleType.SUB_TITLE, "SubTitle"));
	chart2d.addTitle(new Title(TitleType.CAPTION, "Caption"));
	chart2d.addTitle(new Title(TitleType.TEXT, "Text"));
	chart2d.setLegend(new Legend(BorderPosition.EAST));
	chart2d.setXAxis(new XAxis());
	chart2d.setYAxis(new YAxis());
	chart2dCanvas.setChart2D(chart2d);
    }
}
