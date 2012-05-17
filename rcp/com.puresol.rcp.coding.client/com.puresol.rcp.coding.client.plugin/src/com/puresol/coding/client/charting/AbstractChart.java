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

    private final List<XAxis> xAxes = new ArrayList<XAxis>();
    private final List<YAxis> yAxes = new ArrayList<YAxis>();
    private final List<Legend> legends = new ArrayList<Legend>();

    public final void addXAxis(XAxis axis) {
	xAxes.add(axis);
    }

    public final void addYAxis(YAxis axis) {
	yAxes.add(axis);
    }

    public final List<XAxis> getXAxes() {
	return xAxes;
    }

    public final List<YAxis> getYAxes() {
	return yAxes;
    }

    public final void addLegend(Legend legend) {
	legends.add(legend);
    }

    public final List<Legend> getLegends() {
	return legends;
    }

}
