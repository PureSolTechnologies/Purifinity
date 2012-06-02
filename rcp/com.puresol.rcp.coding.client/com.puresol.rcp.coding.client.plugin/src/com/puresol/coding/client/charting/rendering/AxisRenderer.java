package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.charting.Axis;

public class AxisRenderer {

    public void render(GC gc, Axis axis, TransformationMatrix2D transformation) {
	drawAxes(gc, axis, transformation);
	drawTicks(gc, axis, transformation);
    }

    private void drawAxes(GC gc, Axis axis,
	    TransformationMatrix2D transformation) {
	switch (axis.getDirection()) {
	case X:
	    drawXAxis(gc, axis, transformation);
	    break;
	case Y:
	    drawYAxis(gc, axis, transformation);
	    break;
	}
    }

    private void drawXAxis(GC gc, Axis axis,
	    TransformationMatrix2D transformation) {
	Point2D point1 = new Point2D(axis.getMinimum(), 0.0);
	Point2D point2 = new Point2D(axis.getMaximum(), 0.0);
	RendererUtils.drawLine(gc, transformation, point1, point2);
    }

    private void drawYAxis(GC gc, Axis axis,
	    TransformationMatrix2D transformation) {
	Point2D point1 = new Point2D(0.0, axis.getMinimum());
	Point2D point2 = new Point2D(0.0, axis.getMaximum());
	RendererUtils.drawLine(gc, transformation, point1, point2);
    }

    private void drawTicks(GC gc, Axis axis,
	    TransformationMatrix2D transformation) {
	switch (axis.getDirection()) {
	case X:
	    drawTicksX(gc, axis, transformation);
	    break;
	case Y:
	    drawTicksY(gc, axis, transformation);
	    break;
	}
    }

    private void drawTicksX(GC gc, Axis axis,
	    TransformationMatrix2D transformation) {
	double min = axis.getMinimum();
	double max = axis.getMaximum();
	double range = max - min;
	int mainTicks = axis.getMainTicks();
	int subTicks = axis.getSubTicks();
	double subRange = range / (mainTicks - 1.0);
	for (int mainTick = 0; mainTick < mainTicks; mainTick++) {
	    double x = min + subRange * mainTick;
	    Point2D pos = new Point2D(x, 0.0);
	    pos = transformation.transform(pos);
	    gc.drawLine((int) pos.getX(), (int) pos.getY() - 10,
		    (int) pos.getX(), (int) pos.getY() + 10);
	    gc.drawText(String.valueOf(x), (int) pos.getX() + 3,
		    (int) pos.getY() + 3, true);
	    if (mainTick < mainTicks - 1) {
		for (int subTick = 0; subTick < subTicks; subTick++) {
		    double subX = x + subRange / (subTicks + 1) * (subTick + 1);
		    Point2D subPos = new Point2D(subX, 0.0);
		    subPos = transformation.transform(subPos);
		    gc.drawLine((int) subPos.getX(), (int) subPos.getY() - 2,
			    (int) subPos.getX(), (int) subPos.getY() + 2);
		}
	    }
	}
    }

    private void drawTicksY(GC gc, Axis axis,
	    TransformationMatrix2D transformation) {
	double min = axis.getMinimum();
	double max = axis.getMaximum();
	double range = max - min;
	int mainTicks = axis.getMainTicks();
	int subTicks = axis.getSubTicks();
	double subRange = range / (mainTicks - 1.0);
	for (int mainTick = 0; mainTick < mainTicks; mainTick++) {
	    double y = min + range / (mainTicks - 1.0) * mainTick;
	    Point2D pos = new Point2D(0.0, y);
	    pos = transformation.transform(pos);
	    gc.drawLine((int) pos.getX() - 10, (int) pos.getY(),
		    (int) pos.getX() + 10, (int) pos.getY());
	    gc.drawText(String.valueOf(y), (int) pos.getX() + 3,
		    (int) pos.getY() + 3, true);
	    if (mainTick < mainTicks - 1) {
		for (int subTick = 0; subTick < subTicks; subTick++) {
		    double subY = y + subRange / (subTicks + 1) * (subTick + 1);
		    Point2D subPos = new Point2D(0.0, subY);
		    subPos = transformation.transform(subPos);
		    gc.drawLine((int) subPos.getX() - 2, (int) subPos.getY(),
			    (int) subPos.getX() + 2, (int) subPos.getY());
		}
	    }
	}
    }

}
