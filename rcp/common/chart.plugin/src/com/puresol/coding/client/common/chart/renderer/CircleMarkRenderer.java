package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

/**
 * This renderer only renders a simple circle mark with a dot in the center.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CircleMarkRenderer implements MarkRenderer {

	@Override
	public void render(GC gc, TransformationMatrix2D transformation,
			DataPoint2D dataPoint) {
		Point2D point = transformation.transform(dataPoint.getPoint());
		gc.drawOval((int) point.getX() - 3, (int) point.getY() - 3, 6, 6);
		gc.drawPoint((int) point.getX(), (int) point.getY());
	}

}