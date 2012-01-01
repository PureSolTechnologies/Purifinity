package com.puresol.gui.charts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Test;

import com.puresol.gui.Application;
import com.puresol.gui.PureSolDialog;

public class JFreeChartTest {

    @Test
    public void test() {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	for (Double d = 0.0; d <= 10.0; d += 1.0) {
	    dataset.addValue(Double.valueOf(d * d), "TEST", d);
	}
	JFreeChart chart = ChartFactory.createLineChart("Title",
		"Category-Axis", "Value-Axis", dataset,
		PlotOrientation.VERTICAL, true, true, true);
	assertNotNull(chart);
	assertEquals("Title", chart.getTitle().getText());
	assertEquals(1, chart.getSubtitleCount());

	chart.setAntiAlias(false);
	assertEquals(false, chart.getAntiAlias());

	PureSolDialog dialog = new PureSolDialog(Application.getInstance(),
		"Test", true);
	dialog.add(new ChartPanel(chart));
	dialog.pack();
	// dialog.setVisible(true);
    }

}
