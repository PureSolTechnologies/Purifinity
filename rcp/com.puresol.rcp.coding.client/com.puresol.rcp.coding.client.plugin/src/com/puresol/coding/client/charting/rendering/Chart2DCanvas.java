package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.client.charting.Chart2D;

/**
 * This class provides a chart canvas for charts with all interactive features
 * available.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart2DCanvas extends Canvas implements PaintListener {

    private Chart2D chart2D;
    private final Chart2DRenderer renderer = new Chart2DRenderer();

    public Chart2DCanvas(Composite parent, int style) {
	super(parent, style);
	addPaintListener(this);
    }

    public Chart2DCanvas(Composite parent, int style, Chart2D chart2D) {
	this(parent, style);
	this.chart2D = chart2D;
    }

    public Chart2D getChart2D() {
	return chart2D;
    }

    public void setChart2D(Chart2D chart2d) {
	chart2D = chart2d;
    }

    @Override
    public void paintControl(PaintEvent e) {
	renderer.render(e.gc, getClientArea(), getDisplay(), chart2D);
    }

}
