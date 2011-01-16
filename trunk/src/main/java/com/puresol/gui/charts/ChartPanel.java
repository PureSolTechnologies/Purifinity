package com.puresol.gui.charts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;

public class ChartPanel extends JPanel {

	private static final long serialVersionUID = 5436118855798297948L;

	private final JFreeChart chart;

	public ChartPanel(JFreeChart chart) {
		super();
		this.chart = chart;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(0, 0, this.getWidth(),
				this.getHeight());
		chart.draw(g2, rect);
	}
}
