package com.puresol.coding.richclient.application.evaluation.maintainability;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;

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

		drawXYChart();
		drawCategoryChart();
	}

	private void drawXYChart() {
		Chart2DComposite chart2dCanvas = new Chart2DComposite(this, SWT.NONE);
		XYChart xyChart = new XYChart();
		xyChart.addTitle(new Title(TitleType.TITLE, "Title"));
		xyChart.addTitle(new Title(TitleType.SUB_TITLE, "SubTitle"));
		xyChart.addTitle(new Title(TitleType.CAPTION, "Caption"));
		xyChart.addTitle(new Title(TitleType.TEXT, "Text"));
		xyChart.setLegend(new Legend(BorderPosition.EAST));
		xyChart.setXAxis(new XAxis(-10, 10, 11, 1));
		xyChart.setYAxis(new YAxis(-3.0, 3.0, 7, 1));
		chart2dCanvas.setChart2D(xyChart);
	}

	private void drawCategoryChart() {
		Chart2DComposite chart2dCanvas = new Chart2DComposite(this, SWT.NONE);
		CategoryChart categoryChart = new CategoryChart();
		categoryChart.addTitle(new Title(TitleType.TITLE, "Title"));
		categoryChart.addTitle(new Title(TitleType.SUB_TITLE, "SubTitle"));
		categoryChart.addTitle(new Title(TitleType.CAPTION, "Caption"));
		categoryChart.addTitle(new Title(TitleType.TEXT, "Text"));
		categoryChart.setLegend(new Legend(BorderPosition.EAST));
		XAxis axis = new XAxis();
		axis.addCategory("Cat1");
		axis.addCategory("Cat2");
		axis.addCategory("Cat3");
		axis.addCategory("Cat4");
		axis.addCategory("Cat5");
		axis.addCategory("Cat6");
		axis.addCategory("Cat7");
		axis.addCategory("Cat8");
		axis.addCategory("Cat9");
		axis.addCategory("Cat10");
		categoryChart.setXAxis(axis);
		categoryChart.setYAxis(new YAxis(-3.0, 3.0, 7, 1));
		categoryChart.addData(new CategoryValuePair("Cat1", 2.0));
		categoryChart.addData(new CategoryValuePair("Cat2", 1.5));
		categoryChart.addData(new CategoryValuePair("Cat3", 0.5));
		categoryChart.addData(new CategoryValuePair("Cat4", 2.0));
		categoryChart.addData(new CategoryValuePair("Cat5", 1.5));
		categoryChart.addData(new CategoryValuePair("Cat6", 0.5));
		categoryChart.addData(new CategoryValuePair("Cat7", 2.0));
		categoryChart.addData(new CategoryValuePair("Cat8", 1.5));
		categoryChart.addData(new CategoryValuePair("Cat9", 0.5));
		chart2dCanvas.setChart2D(categoryChart);
	}
}
