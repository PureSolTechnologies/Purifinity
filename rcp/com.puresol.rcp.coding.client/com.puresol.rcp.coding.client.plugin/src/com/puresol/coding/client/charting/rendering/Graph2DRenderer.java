package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.charting.BorderPosition;
import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.charting.XAxis;
import com.puresol.coding.client.charting.YAxis;

public class Graph2DRenderer {

    private final AxisRenderer renderer = new AxisRenderer();

    private GC gc;
    private Chart2D chart;
    private TransformationMatrix2D transformation;

    public void render(GC gc, Chart2D chart,
	    TransformationMatrix2D transformation) {
	this.gc = gc;
	this.chart = chart;
	this.transformation = transformation;
	drawSingleAxes();
	for (double x = chart.getXAxis().getMinimum(); x <= chart.getXAxis()
		.getMaximum(); x += 0.001) {
	    double y = Math.sin(x);
	    Point2D point = new Point2D(x, y);
	    point = transformation.transform(point);
	    gc.drawPoint((int) point.getX(), (int) point.getY());
	}
    }

    private void drawSingleAxes() {
	XAxis xAxis = chart.getXAxis();
	YAxis yAxis = chart.getYAxis();
	BorderPositionCounter counter = new BorderPositionCounter();
	counter.add(BorderPosition.SOUTH);
	counter.add(BorderPosition.WEST);
	renderer.render(gc, xAxis, transformation);
	renderer.render(gc, yAxis, transformation);
    }
}
