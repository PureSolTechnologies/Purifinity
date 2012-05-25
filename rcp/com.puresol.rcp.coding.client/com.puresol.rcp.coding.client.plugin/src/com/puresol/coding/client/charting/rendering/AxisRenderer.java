package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.charting.Axis;
import com.puresol.coding.client.grammar.Arrow;
import com.puresol.coding.client.grammar.Arrow.ArrowType;

public class AxisRenderer {

    public void render(GC gc, Axis axis, Rectangle position) {
	switch (axis.getDirection()) {
	case X:
	    gc.drawLine((int) Math.round(axis.getMinimum()), 0,
		    (int) Math.round(axis.getMaximum()), 0);
	    Arrow arrow = new Arrow(gc);
	    // arrow.setTipAngle(45);
	    arrow.setTipLength(0.1);
	    arrow.setType(ArrowType.FANCY);
	    arrow.draw(0, 0, 1, 0, new Color(gc.getDevice(), new RGB(0, 0, 0)));
	    break;
	case Y:
	    gc.drawLine(0, (int) Math.round(axis.getMinimum()), 0,
		    (int) Math.round(axis.getMaximum()));
	    break;
	}
	// TODO Auto-generated method stub
	// AxisDirection direction = axis.getDirection();
	// RendererUtils.drawCrossedBox(gc, position);
    }
}
