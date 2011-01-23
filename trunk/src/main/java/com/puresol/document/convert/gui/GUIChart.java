package com.puresol.document.convert.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.puresol.document.Chart;
import com.puresol.rendering.RendererPanel;

public class GUIChart {

	public static JPanel convert(Chart chart) {
		JPanel panel = new RendererPanel(chart.getChartRenderer());
		panel.setMinimumSize(new Dimension(640, 480));
		panel.setPreferredSize(new Dimension(1024, 768));
		panel.setSize(new Dimension(1024, 768));
		return panel;
	}
}
