package com.puresol.coding.client.charting.rendering;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import com.puresol.coding.client.charting.AxisDirection;
import com.puresol.coding.client.charting.Chart2D;

/**
 * This is a simple {@link Chart2D} renderer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart2DRenderer {

    private final BorderPositionCounter titleCount = new BorderPositionCounter();
    private final BorderPositionCounter legendCount = new BorderPositionCounter();
    private final Map<AxisDirection, Map<AxisPosition, Integer>> axisCount = new HashMap<AxisDirection, Map<AxisPosition, Integer>>();

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

	new Graph2DRenderer().render(gc, chart2D, clientArea);
    }

}
