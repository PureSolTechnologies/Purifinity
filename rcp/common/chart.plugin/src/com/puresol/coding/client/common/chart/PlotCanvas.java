package com.puresol.coding.client.common.chart;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

public class PlotCanvas extends Canvas implements PaintListener {

	public PlotCanvas(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void paintControl(PaintEvent e) {
		GC gc = e.gc;
		Rectangle clientArea = getClientArea();
		Transform transform = new Transform(gc.getDevice());
		transform.translate(clientArea.width / 2, clientArea.height / 2);
		// transform.rotate(-15f);
		gc.setTransform(transform);
		/*
		 * Due to the missing fractional pixel drawing facilities in Eclipse we
		 * need to transform everything on our own.
		 */
		TransformationMatrix2D transformMatrix2d = new TransformationMatrix2D();
		transformMatrix2d.mirror(AxisDirection.X);
	}

}
