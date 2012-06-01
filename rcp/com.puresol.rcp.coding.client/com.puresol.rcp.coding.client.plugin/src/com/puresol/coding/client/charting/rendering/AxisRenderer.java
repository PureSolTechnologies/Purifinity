package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.charting.Axis;

public class AxisRenderer {

    public void render(GC gc, Axis axis, TransformationMatrix2D transformation) {
	Point2D point1 = new Point2D();
	Point2D point2 = new Point2D();
	switch (axis.getDirection()) {
	case X:
	    point1 = new Point2D(new double[] { axis.getMinimum(), 0.0, 1.0 });
	    point2 = new Point2D(new double[] { axis.getMaximum(), 0.0, 1.0 });
	    break;
	case Y:
	    point1 = new Point2D(new double[] { 0.0, axis.getMinimum(), 1.0 });
	    point2 = new Point2D(new double[] { 0.0, axis.getMaximum(), 1.0 });
	    break;
	}
	drawLine(gc, transformation, point1, point2);
    }

    private void drawLine(GC gc, TransformationMatrix2D transformation,
	    Point2D point1, Point2D point2) {
	point1 = transformation.transform(point1);
	point2 = transformation.transform(point2);
	gc.drawLine((int) point1.get(0), (int) point1.get(1),
		(int) point2.get(0), (int) point2.get(1));
    }
}
