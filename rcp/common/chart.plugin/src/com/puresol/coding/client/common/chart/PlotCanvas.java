package com.puresol.coding.client.common.chart;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;
import com.puresol.coding.client.common.chart.renderer.AxisRenderer;

public class PlotCanvas extends Canvas implements PaintListener {

	private static final int PADDING = 5;

	private Chart2D chart2D = null;

	public PlotCanvas(Composite parent, int style) {
		super(parent, style);
		addPaintListener(this);
	}

	@Override
	public void paintControl(PaintEvent e) {
		if (chart2D == null) {
			return;
		}
		GC gc = e.gc;
		Rectangle clientArea = getClientArea();
		if ((clientArea.width <= 2 * PADDING || (clientArea.height <= 2 * PADDING))) {
			return;
		}

		clientArea.x += PADDING;
		clientArea.y += PADDING;
		clientArea.width -= 2 * PADDING;
		clientArea.height -= 2 * PADDING;

		// create transform matrix
		Transform transform = new Transform(gc.getDevice());
		// move origin to canvas center
		transform.translate(clientArea.x + clientArea.width / 2, clientArea.y
				+ clientArea.height / 2);
		// transform.rotate(-15f);
		gc.setTransform(transform);
		/*
		 * Due to the missing fractional pixel drawing facilities in Eclipse we
		 * need to transform everything on our own. The transformation matrix is
		 * created to have a mathematical correct drawing.
		 */
		TransformationMatrix2D transformMatrix2d = new TransformationMatrix2D();
		// let y axis point upright
		transformMatrix2d.mirror(AxisDirection.X);

		Axis<?> xAxis = chart2D.getXAxis();
		Axis<?> yAxis = chart2D.getYAxis();

		double rangeX = xAxis.getMaximum() - xAxis.getMinimum();
		double rangeY = yAxis.getMaximum() - yAxis.getMinimum();

		// scale to use the values from dataset to paint
		double scaleX = clientArea.width / rangeX * 0.95;
		double scaleY = clientArea.height / rangeY * 0.95;
		transformMatrix2d.scale(scaleX, scaleY);

		// move the center of axes to center of canvas
		double centerX = (xAxis.getMinimum() + xAxis.getMaximum()) / 2.0;
		double centerY = (yAxis.getMinimum() + yAxis.getMaximum()) / 2.0;
		transformMatrix2d.translate(-centerX, -centerY);

		AxisRenderer axisRenderer = new AxisRenderer();
		axisRenderer.render(gc, chart2D.getXAxis(), transformMatrix2d,
				10.0 / scaleY);
		axisRenderer.render(gc, chart2D.getYAxis(), transformMatrix2d,
				10.0 / scaleX);
	}

	public void setChart(Chart2D chart2D) {
		this.chart2D = chart2D;
	}

}
