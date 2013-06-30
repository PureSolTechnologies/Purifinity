package com.puresol.purifinity.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.purifinity.client.common.chart.Axis;
import com.puresol.purifinity.client.common.chart.Plot;
import com.puresol.purifinity.client.common.chart.math.Point2D;
import com.puresol.purifinity.client.common.chart.math.TransformationMatrix2D;

/**
 * This renderer renders a bar for a bar chart. The bar stands (or hangs) on the
 * X axis. The center is the X value and the bar ends at the Y value in Y
 * direction. The width provides the width which is evenly applied on the left
 * and right side.
 * 
 * @author Rick-Rainer Ludwig
 */
public class BarMarkRenderer implements MarkRenderer {

	private final double width;

	public BarMarkRenderer(double width) {
		this.width = width;
	}

	@Override
	public Rectangle render(GC gc, TransformationMatrix2D transformation,
			Plot<?, ?> plot, Object x, Object y) {
		Axis<?> xAxis = plot.getXAxis();
		Axis<?> yAxis = plot.getYAxis();
		Point2D point = new Point2D(xAxis.getPosition(x), yAxis.getPosition(y));
		Point2D point1 = new Point2D(point.getX() - width / 2.0, 0.0);
		Point2D point2 = new Point2D(point.getX() + width / 2.0, point.getY());
		point1 = transformation.transform(point1);
		point2 = transformation.transform(point2);
		int pixelWidth = (int) Math.abs(point1.getX() - point2.getX());
		int pixelHeight = (int) Math.abs(point1.getY() - point2.getY());
		int pixelX = (int) point1.getX();
		int pixelY;
		if (point.getY() > 0) {
			pixelY = (int) point2.getY();
		} else {
			pixelY = (int) point1.getY();
		}
		gc.fillRectangle(pixelX, pixelY, pixelWidth, pixelHeight);
		gc.drawRectangle(pixelX, pixelY, pixelWidth, pixelHeight);
		return new Rectangle(pixelX, pixelY, pixelWidth, pixelHeight);
	}
}
