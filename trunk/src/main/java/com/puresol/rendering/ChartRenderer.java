package com.puresol.rendering;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.JFreeChart;

public class ChartRenderer implements Renderer {

	private final JFreeChart chart;

	public ChartRenderer(JFreeChart chart) {
		super();
		this.chart = chart;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1024, 768);
	}

	@Override
	public void render(Graphics graphics, int x1, int y1, int x2, int y2) {
		Rectangle2D rectangle = new Rectangle2D.Float((float) x1, (float) y1,
				(float) x2, (float) y2);
		chart.draw((Graphics2D) graphics, rectangle);

	}

}
