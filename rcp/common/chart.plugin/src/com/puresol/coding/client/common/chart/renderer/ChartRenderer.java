package com.puresol.coding.client.common.chart.renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.Chart2D;
import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.MarkPosition;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

public class ChartRenderer {

	private final Map<Plot<?, ?>, List<MarkPosition>> markPositions = new HashMap<Plot<?, ?>, List<MarkPosition>>();

	private final Map<Plot<?, ?>, MarkRenderer> markRenderers = new HashMap<Plot<?, ?>, MarkRenderer>();
	private final Map<Plot<?, ?>, ColorProvider> colorProviders = new HashMap<Plot<?, ?>, ColorProvider>();

	private Chart2D chart2D;

	public void setChart2D(Chart2D chart2d) {
		chart2D = chart2d;
	}

	public Chart2D getChart2D() {
		return chart2D;
	}

	public void setMarkRenderer(Plot<?, ?> plot, MarkRenderer markRenderer) {
		markRenderers.put(plot, markRenderer);
	}

	public void setColorProvider(Plot<?, ?> plot, ColorProvider colorProvider) {
		colorProviders.put(plot, colorProvider);
	}

	public Map<Plot<?, ?>, List<MarkPosition>> render(GC gc,
			Rectangle clientArea) {
		markPositions.clear();
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

		/*
		 * Due to the missing fractional pixel drawing facilities in Eclipse we
		 * need to transform everything on our own. The transformation matrix is
		 * created to have a mathematical correct drawing.
		 */
		TransformationMatrix2D transformMatrix2d = new TransformationMatrix2D();
		// move origin to canvas center
		transformMatrix2d.translate(clientArea.x + clientArea.width / 2,
				clientArea.y + clientArea.height / 2);
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
			List<MarkPosition> positions = plotRenderer
					.render(transformMatrix2d);
			markPositions.put(plot, positions);
		}

		xAxisRenderer.render(transformMatrix2d);
		yAxisRenderer.render(transformMatrix2d);
		return markPositions;
	}

	public DataPoint2D<?, ?> getDataPointAt(int x, int y) {
		for (Plot<?, ?> plot : markPositions.keySet()) {
			for (MarkPosition markPosition : markPositions.get(plot)) {
				Rectangle position = markPosition.getPosition();
				if ((x >= position.x) && (y >= position.y)
						&& (x <= position.x + position.width)
						&& (y <= position.y + position.height)) {
					int index = markPosition.getDataPointIndex();
					return plot.getDataPoints().get(index);
				}
			}
		}
		return null;
	}
}
