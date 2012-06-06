package com.puresol.coding.client.charting;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.client.charting.rendering.Point2D;

/**
 * This is a base class for 2D charts.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class XYChart extends AbstractChart2D {

    private final List<Point2D> xyData = new ArrayList<Point2D>();
    private XAxis xAxis = new XAxis();
    private YAxis yAxis = new YAxis();

    public final void setData(List<Point2D> xyData) {
	this.xyData.clear();
	this.xyData.addAll(xyData);
    }

    public final void addData(Point2D xyData) {
	this.xyData.add(xyData);
    }

    public final List<Point2D> getData() {
	return xyData;
    }

    public final void setXAxis(XAxis axis) {
	xAxis = axis;
    }

    public final void setYAxis(YAxis axis) {
	yAxis = axis;
    }

    public final XAxis getXAxis() {
	return xAxis;
    }

    public final YAxis getYAxis() {
	return yAxis;
    }

}
