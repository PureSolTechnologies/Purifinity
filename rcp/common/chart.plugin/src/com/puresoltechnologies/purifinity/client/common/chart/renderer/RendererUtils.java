package com.puresoltechnologies.purifinity.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;

import com.puresoltechnologies.purifinity.client.common.chart.math.Point2D;
import com.puresoltechnologies.purifinity.client.common.chart.math.TransformationMatrix2D;

public class RendererUtils {

	/**
	 * Private default constructor to avoid instantiation.
	 */
	private RendererUtils() {
	}

	public static void drawLine(GC gc, TransformationMatrix2D transformation,
			Point2D point1, Point2D point2) {
		point1 = transformation.transform(point1);
		point2 = transformation.transform(point2);
		gc.drawLine((int) point1.get(0), (int) point1.get(1),
				(int) point2.get(0), (int) point2.get(1));
	}

}
