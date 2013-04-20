package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

/**
 * This renderer only renders a simple cross mark.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CrossMarkRenderer implements MarkRenderer {

	@Override
	public void render(GC gc, TransformationMatrix2D transformation,
			Plot<?, ?> plot, Object x, Object y) {
		Axis<?> xAxis = plot.getXAxis();
		Axis<?> yAxis = plot.getYAxis();
		Point2D point = new Point2D(xAxis.getPosition(x), yAxis.getPosition(y));
		point = transformation.transform(point);
		gc.drawLine((int) point.getX() - 3, (int) point.getY() - 3,
				(int) point.getX() + 3, (int) point.getY() + 3);
		gc.drawLine((int) point.getX() - 3, (int) point.getY() + 3,
				(int) point.getX() + 3, (int) point.getY() - 3);
	}

}
