package com.puresoltechnologies.purifinity.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.puresoltechnologies.purifinity.client.common.chart.Axis;
import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.math.Point2D;
import com.puresoltechnologies.purifinity.client.common.chart.math.TransformationMatrix2D;

/**
 * This renderer only renders a simple cross mark.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CrossMarkRenderer implements MarkRenderer {

	private static final int SIZE = 3;

	@Override
	public Rectangle render(GC gc, TransformationMatrix2D transformation,
			Plot<?, ?> plot, Object x, Object y) {
		Axis<?> xAxis = plot.getXAxis();
		Axis<?> yAxis = plot.getYAxis();
		Point2D point = new Point2D(xAxis.getPosition(x), yAxis.getPosition(y));
		point = transformation.transform(point);
		gc.drawLine((int) point.getX() - SIZE, (int) point.getY() - SIZE,
				(int) point.getX() + SIZE, (int) point.getY() + SIZE);
		gc.drawLine((int) point.getX() - SIZE, (int) point.getY() + SIZE,
				(int) point.getX() + SIZE, (int) point.getY() - SIZE);
		return new Rectangle((int) point.getX() - SIZE, (int) point.getX()
				- SIZE, 2 * SIZE, 2 * SIZE);
	}

}
