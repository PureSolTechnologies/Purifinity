package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.client.charting.Chart2D;

/**
 * This is a simple {@link Chart2D} renderer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart2DCanvas extends Canvas implements PaintListener {

    private Chart2D chart2D = null;

    public Chart2DCanvas(Composite parent, int style) {
	super(parent, style);
	addPaintListener(this);
    }

    public Chart2DCanvas(Composite parent, int style, Chart2D chart2D) {
	super(parent, style);
	this.chart2D = chart2D;
	addPaintListener(this);
    }

    public Chart2D getChart2D() {
	return chart2D;
    }

    public void setChart2D(Chart2D chart2d) {
	chart2D = chart2d;
    }

    @Override
    public void paintControl(PaintEvent e) {
	GC gc = e.gc;
	Rectangle clientArea = getClientArea();
	Transform transform = createNormalizedGraphTransform(clientArea);
	gc.setTransform(transform);

	gc.setLineAttributes(new LineAttributes((float) 1.0
		/ Math.min(clientArea.width, clientArea.height) * 2));

	if (chart2D != null) {
	    new Graph2DRenderer().render(gc, chart2D, clientArea);
	}
    }

    private Transform createNormalizedGraphTransform(Rectangle clientArea) {
	Transform transform = new Transform(getDisplay(), (float) 1.0,
		(float) 0.0, (float) 0.0, (float) -1.0, (float) 1.0,
		(float) 1.0);
	transform.translate(clientArea.width / 2, -clientArea.height / 2);
	transform.multiply(new Transform(getDisplay(), clientArea.width
		/ (float) 2.0, (float) 0.0, (float) 0.0, clientArea.height
		/ (float) 2.0, (float) 1.0, (float) 1.0));
	return transform;
    }
}
