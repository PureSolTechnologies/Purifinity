package com.puresol.coding.client.charting.rendering;

import java.util.List;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.charting.BorderPosition;
import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.charting.XAxis;
import com.puresol.coding.client.charting.YAxis;

public class Graph2DRenderer {

    private final AxisRenderer axisRenderer = new AxisRenderer();

    private GC gc;
    private Chart2D chart;

    public void render(GC gc, Chart2D chart, Rectangle area) {
	this.gc = gc;
	this.chart = chart;
	Rectangle axesCenterArea = drawSingleAxes(area);
	RendererUtils.drawCrossedBox(gc, axesCenterArea);

    }

    private Rectangle drawSingleAxes(Rectangle legendCenterArea) {
	List<XAxis> xAxes = chart.getXAxes();
	List<YAxis> yAxes = chart.getYAxes();
	int xAxisLayers = xAxes.size() / 2 + xAxes.size() % 2;
	int yAxisLayers = yAxes.size() / 2 + yAxes.size() % 2;
	int totalAxisLayers = Math.max(xAxisLayers, yAxisLayers);
	Rectangle currentArea = legendCenterArea;
	AxisRenderer renderer = new AxisRenderer();
	for (int layer = 0; layer < totalAxisLayers; layer++) {
	    int lowerXAxisIndex = layer * 2;
	    int upperXAxisIndex = layer * 2 + 1;
	    int leftYAxisIndex = layer * 2;
	    int rightYAxisIndex = layer * 2 + 1;
	    BorderPositionCounter counter = new BorderPositionCounter();
	    if (xAxes.size() > lowerXAxisIndex) {
		counter.add(BorderPosition.SOUTH);
	    }
	    if (xAxes.size() > upperXAxisIndex) {
		counter.add(BorderPosition.NORTH);
	    }
	    if (yAxes.size() > leftYAxisIndex) {
		counter.add(BorderPosition.WEST);
	    }
	    if (yAxes.size() > rightYAxisIndex) {
		counter.add(BorderPosition.EAST);
	    }
	    BorderPositionCalculator calculator = new BorderPositionCalculator(
		    currentArea, counter, 0.05);
	    if (counter.hasPosition(BorderPosition.NORTH)) {
		XAxis xAxis = xAxes.get(upperXAxisIndex);
		renderer.render(gc, xAxis,
			calculator.getPosition(BorderPosition.NORTH, 0, 1));
	    }
	    if (counter.hasPosition(BorderPosition.SOUTH)) {
		XAxis xAxis = xAxes.get(lowerXAxisIndex);
		renderer.render(gc, xAxis,
			calculator.getPosition(BorderPosition.SOUTH, 0, 1));
	    }
	    if (counter.hasPosition(BorderPosition.WEST)) {
		YAxis yAxis = yAxes.get(leftYAxisIndex);
		renderer.render(gc, yAxis,
			calculator.getPosition(BorderPosition.WEST, 0, 1));
	    }
	    if (counter.hasPosition(BorderPosition.EAST)) {
		YAxis yAxis = yAxes.get(rightYAxisIndex);
		renderer.render(gc, yAxis,
			calculator.getPosition(BorderPosition.EAST, 0, 1));
	    }
	    currentArea = calculator.getCenterArea();
	}
	return currentArea;
    }
}
