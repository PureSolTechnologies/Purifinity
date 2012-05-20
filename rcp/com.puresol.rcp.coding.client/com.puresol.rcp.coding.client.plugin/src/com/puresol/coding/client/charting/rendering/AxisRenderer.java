package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.charting.Axis;
import com.puresol.coding.client.charting.AxisDirection;

public class AxisRenderer {

    public void render(GC gc, Axis axis, Rectangle position) {
	// TODO Auto-generated method stub
	AxisDirection direction = axis.getDirection();
	RendererUtils.drawCrossedBox(gc, position);
    }

}
