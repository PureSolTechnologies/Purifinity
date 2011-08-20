package com.puresol.document.convert.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import com.puresol.document.Chart;

public class GUIChart {

	public static JPanel convert(Chart chart) {
		ChartPanel panel = new ChartPanel(chart.getChartRenderer().getChart());
		panel.setDisplayToolTips(true);
		panel.setDomainZoomable(true);
		panel.setMouseWheelEnabled(true);
		panel.setMouseZoomable(true);
		panel.setRangeZoomable(true);
		panel.setRefreshBuffer(true);
		panel.setVerticalAxisTrace(true);
		panel.setZoomAroundAnchor(true);
		panel.setZoomInFactor(2.0);
		panel.setMinimumSize(new Dimension(640, 480));
		panel.setPreferredSize(new Dimension(1024, 768));
		// panel.setSize(new Dimension(1024, 768));
		return panel;
	}
}
