package com.puresol.coding.client.grammar;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import org.eclipse.swt.graphics.GC;
import org.jfree.chart.JFreeChart;

public class ChartRenderer implements Renderer {

    private final JFreeChart chart;

    public ChartRenderer(JFreeChart chart) {
	super();
	this.chart = chart;
    }

    public JFreeChart getChart() {
	return chart;
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(1024, 768);
    }

    @Override
    public void render(GC graphics, int x1, int y1, int x2, int y2) {
	Rectangle2D rectangle = new Rectangle2D.Float(x1, y1, x2, y2);
	chart.draw(graphics, rectangle);
    }

}
