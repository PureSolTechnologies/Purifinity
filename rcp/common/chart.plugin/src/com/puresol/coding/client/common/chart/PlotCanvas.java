package com.puresol.coding.client.common.chart;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;
import com.puresol.coding.client.common.chart.renderer.AxisRenderer;
import com.puresol.coding.client.common.chart.renderer.ColorProvider;
import com.puresol.coding.client.common.chart.renderer.MarkRenderer;
import com.puresol.coding.client.common.chart.renderer.PlotRenderer;

public class PlotCanvas extends Canvas implements PaintListener {

	private static final int PADDING = 5;

	private Chart2D chart2D = null;

	private final Map<Plot<?, ?>, MarkRenderer> markRenderers = new HashMap<Plot<?, ?>, MarkRenderer>();
	private final Map<Plot<?, ?>, ColorProvider> colorProviders = new HashMap<Plot<?, ?>, ColorProvider>();

	public PlotCanvas(Composite parent, int style) {
		super(parent, style);
		addPaintListener(this);
	}

	@Override
	public void paintControl(PaintEvent e) {
		if ((chart2D == null) || (chart2D.getXAxis() == null)
				|| (chart2D.getYAxis() == null)) {
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

		// Initialize renderer objects...
		AxisRenderer xAxisRenderer = new AxisRenderer(gc, chart2D.getXAxis(),
				chart2D.getYAxis().getMinimum());
		AxisRenderer yAxisRenderer = new AxisRenderer(gc, chart2D.getYAxis(),
				chart2D.getXAxis().getMinimum());
		int yAxisWidth = yAxisRenderer.getWidth();
		clientArea.x += yAxisWidth;
		clientArea.width -= yAxisWidth;
		int xAxisWidth = xAxisRenderer.getWidth();
		clientArea.height -= xAxisWidth;

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
		double scaleX = clientArea.width / rangeX;
		double scaleY = clientArea.height / rangeY;
		transformMatrix2d.scale(scaleX, scaleY);

		// move the center of axes to center of canvas
		double centerX = (xAxis.getMinimum() + xAxis.getMaximum()) / 2.0;
		double centerY = (yAxis.getMinimum() + yAxis.getMaximum()) / 2.0;
		transformMatrix2d.translate(-centerX, -centerY);

		for (Plot<?, ?> plot : chart2D.getPlots()) {
			PlotRenderer plotRenderer = new PlotRenderer(gc, plot);
			MarkRenderer markRenderer = markRenderers.get(plot);
			if (markRenderer != null) {
				plotRenderer.setMarkRenderer(markRenderer);
			}
			ColorProvider colorProvider = colorProviders.get(plot);
			if (colorProvider != null) {
				plotRenderer.setColorProvider(colorProvider);
			}
			plotRenderer.render(transformMatrix2d);
		}

		xAxisRenderer.render(transformMatrix2d);
		yAxisRenderer.render(transformMatrix2d);
	}

	public void setChart(Chart2D chart2D) {
		this.chart2D = chart2D;
	}

	public void setMarkRenderer(Plot<?, ?> plot, MarkRenderer markRenderer) {
		markRenderers.put(plot, markRenderer);
	}

	public void setColorProvider(Plot<?, ?> plot, ColorProvider colorProvider) {
		colorProviders.put(plot, colorProvider);
	}

}
