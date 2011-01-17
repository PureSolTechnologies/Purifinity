package com.puresol.document.convert.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

import com.puresol.document.Chart;

public class GUIChart {

	public static JPanel convert(Chart chart) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new ChartPanel(chart.getChart()));
		panel.add(new JLabel(chart.getCaption()));
		return panel;
	}

}
