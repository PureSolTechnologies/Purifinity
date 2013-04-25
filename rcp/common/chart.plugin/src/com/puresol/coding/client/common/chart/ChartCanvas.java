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

import com.puresol.coding.client.common.chart.renderer.ChartRenderer;
import com.puresol.coding.client.common.chart.renderer.ColorProvider;
import com.puresol.coding.client.common.chart.renderer.MarkRenderer;

/**
 * This is the central canvas class for interactive charting.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ChartCanvas extends Canvas implements PaintListener {

	private static final int MARGIN = 5;

	private final Map<Plot<?, ?>, List<MarkPosition>> markPositions = new HashMap<Plot<?, ?>, List<MarkPosition>>();
	private final ChartRenderer chartRenderer = new ChartRenderer();

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
		return chartRenderer.getChart2D();
	}

	public void setChart2D(Chart2D chart2D) {
		chartRenderer.setChart2D(chart2D);
		refresh();
	}

	public void setMarkRenderer(Plot<?, ?> plot, MarkRenderer markRenderer) {
		chartRenderer.setMarkRenderer(plot, markRenderer);
	}

	public void setColorProvider(Plot<?, ?> plot, ColorProvider colorProvider) {
		chartRenderer.setColorProvider(plot, colorProvider);
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
		if ((getChart2D() == null) || (getChart2D().getXAxis() == null)
				|| (getChart2D().getYAxis() == null)) {
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

		chartRenderer.render(gc, clientArea);
	}

	public void refresh() {
		layout();
		redraw();
		update();
	}

}