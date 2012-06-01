package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.charting.BorderPosition;
import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.charting.XAxis;
import com.puresol.coding.client.charting.YAxis;

public class Graph2DRenderer {

    private final AxisRenderer renderer = new AxisRenderer();

    private GC gc;
    private Chart2D chart;
    private TransformationMatrix2D transformation;

    public void render(GC gc, Chart2D chart, Rectangle area,
	    TransformationMatrix2D transformation) {
	this.gc = gc;
	this.chart = chart;
	this.transformation = transformation;
	drawSingleAxes(area);
    }

    private void drawSingleAxes(Rectangle legendCenterArea) {
	XAxis xAxis = chart.getXAxis();
	YAxis yAxis = chart.getYAxis();
	BorderPositionCounter counter = new BorderPositionCounter();
	counter.add(BorderPosition.SOUTH);
	counter.add(BorderPosition.WEST);
	renderer.render(gc, xAxis, transformation);
	renderer.render(gc, yAxis, transformation);
    }
}
