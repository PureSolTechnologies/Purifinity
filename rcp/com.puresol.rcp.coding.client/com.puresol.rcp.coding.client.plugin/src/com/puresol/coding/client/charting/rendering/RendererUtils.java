package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public class RendererUtils {

    /**
     * Private default constructor to avoid instantiation.
     */
    private RendererUtils() {
    }

    public static void drawCrossedBox(GC gc, Rectangle clientArea) {
	gc.drawLine(clientArea.x, clientArea.y, clientArea.x + clientArea.width
		- 1, clientArea.y);
	gc.drawLine(clientArea.x, clientArea.y + clientArea.height - 1,
		clientArea.x + clientArea.width - 1, clientArea.y
			+ clientArea.height - 1);
	gc.drawLine(clientArea.x, clientArea.y, clientArea.x, clientArea.y
		+ clientArea.height - 1);
	gc.drawLine(clientArea.x + clientArea.width - 1, clientArea.y,
		clientArea.x + clientArea.width - 1, clientArea.y
			+ clientArea.height - 1);

	gc.drawLine(clientArea.x, clientArea.y, clientArea.x + clientArea.width
		- 1, clientArea.y + clientArea.height - 1);
	gc.drawLine(clientArea.x + clientArea.width - 1, clientArea.y,
		clientArea.x, clientArea.y + clientArea.height - 1);
    }

    public static void drawLine(GC gc, TransformationMatrix2D transformation,
	    Point2D point1, Point2D point2) {
	point1 = transformation.transform(point1);
	point2 = transformation.transform(point2);
	gc.drawLine((int) point1.get(0), (int) point1.get(1),
		(int) point2.get(0), (int) point2.get(1));
    }

}
