package com.puresol.coding.client.charting;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract implementation of a 2D chart which is the foundation of
 * all 2D charts and 3D charts.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractChart {

    private XAxis xAxis = new XAxis();
    private YAxis yAxis = new YAxis();
    private Legend legend = new Legend(BorderPosition.EAST);
    private final List<Title> titles = new ArrayList<Title>();

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

    public final void setLegend(Legend legend) {
	this.legend = legend;
    }

    public final Legend getLegend() {
	return legend;
    }

    public final void addTitle(Title title) {
	titles.add(title);
    }

    public final List<Title> getTitles() {
	return titles;
    }
}
