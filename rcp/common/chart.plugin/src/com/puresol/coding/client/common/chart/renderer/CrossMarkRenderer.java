package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

/**
 * This renderer only renders a simple cross mark.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CrossMarkRenderer implements MarkRenderer {

	private final GC gc;

	public CrossMarkRenderer(GC gc) {
		this.gc = gc;
	}

	@Override
	public void render(TransformationMatrix2D transformation, Point2D point) {
		point = transformation.transform(point);
		gc.drawLine((int) point.getX() - 3, (int) point.getY() - 3,
				(int) point.getX() + 3, (int) point.getY() + 3);
		gc.drawLine((int) point.getX() - 3, (int) point.getY() + 3,
				(int) point.getX() + 3, (int) point.getY() - 3);
	}

}
