package com.puresol.coding.client.common.chart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;
import com.puresol.coding.client.common.chart.renderer.AxisRenderer;
import com.puresol.coding.client.common.chart.renderer.ColorProvider;
import com.puresol.coding.client.common.chart.renderer.MarkRenderer;
import com.puresol.coding.client.common.chart.renderer.ChartRenderer;

/**
 * This is the central canvas class for interactive charting.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ChartCanvas extends Canvas implements PaintListener {

	private static final int MARGIN = 5;

	private final Map<Plot<?, ?>, List<MarkPosition>> markPositions = new HashMap<Plot<?, ?>, List<MarkPosition>>();

	private final Map<Plot<?, ?>, MarkRenderer> markRenderers = new HashMap<Plot<?, ?>, MarkRenderer>();
	private final Map<Plot<?, ?>, ColorProvider> colorProviders = new HashMap<Plot<?, ?>, ColorProvider>();

	private Chart2D chart2D = null;

	public ChartCanvas(Composite parent, int style) {
		super(parent, style);

		setBackground(new Color(getDisplay(), new RGB(255, 255, 255)));

		addPaintListener(this);
		DefaultToolTip toolTip = new DefaultToolTip(this) {
			@Override
			protected String getText(Event event) {
				return getTooltipText(event.x, event.y);
			}

			@Override
			protected boolean shouldCreateToolTip(Event event) {
				return getTooltipText(event.x, event.y) != null;
			}
		};
		toolTip.setHideDelay(0);
		toolTip.setPopupDelay(0);
		toolTip.setShift(new Point(10, 10));

	}

	public Chart2D getChart2D() {
		return chart2D;
	}

	public void setChart2D(Chart2D chart2D) {
		this.chart2D = chart2D;
		refresh();
	}

	public void setMarkRenderer(Plot<?, ?> plot, MarkRenderer markRenderer) {
		markRenderers.put(plot, markRenderer);
	}

	public void setColorProvider(Plot<?, ?> plot, ColorProvider colorProvider) {
		colorProviders.put(plot, colorProvider);
	}

	public String getTooltipText(int x, int y) {
		for (Plot<?, ?> plot : markPositions.keySet()) {
			for (MarkPosition markPosition : markPositions.get(plot)) {
				Rectangle position = markPosition.getPosition();
				if ((x >= position.x) && (y >= position.y)
						&& (x <= position.x + position.width)
						&& (y <= position.y + position.height)) {
					int index = markPosition.getDataPointIndex();
					DataPoint2D<?, ?> dataPoint2D = plot.getDataPoints().get(
							index);
					String text = dataPoint2D.getX().toString() + " : "
							+ dataPoint2D.getY().toString();
					String remark = dataPoint2D.getRemark();
					if ((remark != null)
							&& (!dataPoint2D.getRemark().isEmpty())) {
						text += " (" + dataPoint2D.getRemark() + ")";
					}
					return text;
				}
			}
		}
		return null;
	}

	@Override
	public void paintControl(PaintEvent e) {
		markPositions.clear();
		if ((chart2D == null) || (chart2D.getXAxis() == null)
				|| (chart2D.getYAxis() == null)) {
			return;
		}
		GC gc = e.gc;
		Rectangle clientArea = getClientArea();
		if ((clientArea.width <= 2 * MARGIN || (clientArea.height <= 2 * MARGIN))) {
			return;
		}

		clientArea.x += MARGIN;
		clientArea.y += MARGIN;
		clientArea.width -= 2 * MARGIN;
		clientArea.height -= 2 * MARGIN;

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
			ChartRenderer plotRenderer = new ChartRenderer(gc, plot);
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
	}

	public void refresh() {
		layout();
		redraw();
		update();
	}

}