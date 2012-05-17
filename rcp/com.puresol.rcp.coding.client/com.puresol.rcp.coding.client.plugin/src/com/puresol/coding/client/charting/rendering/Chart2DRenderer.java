package com.puresol.coding.client.charting.rendering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import com.puresol.coding.client.charting.AxisDirection;
import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.charting.Legend;
import com.puresol.coding.client.charting.LegendPosition;
import com.puresol.coding.client.charting.XAxis;

/**
 * This is a simple {@link Chart2D} renderer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart2DRenderer {

    private final AxisRenderer axisRenderer = new AxisRenderer();

    private final Map<LegendPosition, Integer> legendCount = new HashMap<LegendPosition, Integer>();
    private final Map<AxisDirection, Map<AxisPosition, Integer>> axisCount = new HashMap<AxisDirection, Map<AxisPosition, Integer>>();

    private final AxisType axisType = AxisType.SINGLE;

    private GC gc;
    private Rectangle clientArea;
    private Display display;
    private Chart2D chart2D;

    public void render(GC gc, Rectangle clientArea, Display display,
	    Chart2D chart2D) {
	this.gc = gc;
	this.clientArea = clientArea;
	this.display = display;
	this.chart2D = chart2D;

	drawLegends();

	drawCross(gc, clientArea);

	if (axisType == AxisType.SINGLE) {
	    List<XAxis> xAxes = chart2D.getXAxes();

	}
    }

    private void drawLegends() {
	// init fields and variables...
	legendCount.clear();
	Map<LegendPosition, Integer> nums = new HashMap<LegendPosition, Integer>();
	for (LegendPosition position : LegendPosition.values()) {
	    legendCount.put(position, 0);
	    nums.put(position, 0);
	}
	// render
	List<Legend> legends = chart2D.getLegends();
	for (Legend legend : legends) {
	    LegendPosition position = legend.getPosition();
	    Integer count = legendCount.get(position);
	    legendCount.put(position, count + 1);
	}
	for (Legend legend : legends) {
	    Integer num = nums.get(legend.getPosition());
	    Rectangle legendArea = LegendPositionCalculator.getPosition(
		    legend.getPosition(), num, legendCount, clientArea);
	    RendererUtils.drawCrossedBox(gc, legendArea);
	    nums.put(legend.getPosition(), num + 1);
	}
    }

    /**
     * For debugging purpose only to see the position of the graph!
     * 
     * @param gc
     * @param clientArea
     */
    private void drawCross(GC gc, Rectangle clientArea) {
	RendererUtils.drawCrossedBox(gc, clientArea);
    }
}
