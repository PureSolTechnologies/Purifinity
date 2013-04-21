package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

/**
 * This renderer only renders a simple circle mark with a dot in the center.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CircleMarkRenderer implements MarkRenderer {

	private final static int CIRCE_RADIUS = 3;

	@Override
	public Rectangle render(GC gc, TransformationMatrix2D transformation,
			Plot<?, ?> plot, Object x, Object y) {
		Axis<?> xAxis = plot.getXAxis();
		Axis<?> yAxis = plot.getYAxis();
		Point2D point = new Point2D(xAxis.getPosition(x), yAxis.getPosition(y));
		point = transformation.transform(point);
		int pixelX = (int) point.getX() - CIRCE_RADIUS;
		int pixelY = (int) point.getY() - CIRCE_RADIUS;
		int pixelWidth = 2 * CIRCE_RADIUS;
		gc.drawOval(pixelX, pixelY, pixelWidth, pixelWidth);
		gc.drawPoint((int) point.getX(), (int) point.getY());
		return new Rectangle(pixelX, pixelY, pixelWidth, pixelWidth);
	}
}
