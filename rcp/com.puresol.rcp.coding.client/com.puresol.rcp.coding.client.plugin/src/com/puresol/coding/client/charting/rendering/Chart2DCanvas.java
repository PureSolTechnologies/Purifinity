package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.client.charting.AxisDirection;
import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.charting.XAxis;
import com.puresol.coding.client.charting.YAxis;

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
	/*
	 * Due to the missing fractional pixel drawing facilities in Eclipse we
	 * need to transform everything on our own.
	 */
	TransformationMatrix2D transformMatrix2d = new TransformationMatrix2D();
	transformMatrix2d.mirror(AxisDirection.X);
	transformMatrix2d.translate(clientArea.width / 2,
		-clientArea.height / 2);

	XAxis xAxis = chart2D.getXAxis();
	YAxis yAxis = chart2D.getYAxis();

	double rangeX = xAxis.getMaximum() - xAxis.getMinimum();
	double rangeY = yAxis.getMaximum() - yAxis.getMinimum();

	double scaleX = clientArea.width / rangeX * 0.95;
	double scaleY = clientArea.height / rangeY * 0.95;
	transformMatrix2d.scale(scaleX, scaleY);

	if (chart2D != null) {
	    new Graph2DRenderer().render(gc, chart2D, clientArea,
		    transformMatrix2d);
	}
    }
}
