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

import com.puresol.coding.client.common.chart.renderer.ColorProvider;
import com.puresol.trees.Tree;
import com.puresol.utils.math.MathUtils;

/**
 * This component provides a 2D view on data. The data provided as a
 * {@link Tree} are shown as summed up areas.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AreaMapComponent extends Canvas implements PaintListener {

	private static final RGB DEFAULT_FRAME_COLOR = new RGB(0, 0, 0);
	private static final int PADDING = 2;

	private AreaMapData data = null;
	private String unit = null;
	private final Map<Rectangle, AreaMapData> tooltipAreas = new HashMap<Rectangle, AreaMapData>();
	private ColorProvider colorProvider;

	public AreaMapComponent(Composite parent, int style) {
		super(parent, style);
		Color color = new Color(getDisplay(), 255, 255, 255);
		try {
			setBackground(color);
		} finally {
			color.dispose();
		}
		addPaintListener(this);
		DefaultToolTip toolTip = new DefaultToolTip(this) {
			@Override
			protected String getText(Event event) {
				int x = event.x;
				int y = event.y;
				AreaMapData data = null;
				int size = Integer.MAX_VALUE;
				for (Rectangle r : tooltipAreas.keySet()) {
					if (r.contains(x, y)) {
						int newSize = r.height * r.width;
						if (size > newSize) {
							data = tooltipAreas.get(r);
							size = newSize;
						}
					}
				}
				if (data == null) {
					return super.getText(event);
				} else {
					if (unit != null) {
						return data.getName() + " (" + data.getValue() + unit
								+ ")";
					} else {
						return data.getName() + " (" + data.getValue() + ")";
					}
				}
			}
		};
		toolTip.setHideDelay(0);
		toolTip.setPopupDelay(0);
		toolTip.setShift(new Point(10, 10));
	}

	public void setData(AreaMapData data) {
		setData(data, "");
	}

	public void setData(AreaMapData data, String unit) {
		this.data = data;
		this.unit = unit;
		redraw();
		update();
	}

	public ColorProvider getColorProvider() {
		return colorProvider;
	}

	public void setColorProvider(ColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}

	@Override
	public void paintControl(PaintEvent event) {
		GC context = event.gc;
		Point size = getSize();
		tooltipAreas.clear();
		drawAreas(context, data, 0, 0, size.x - 1, size.y - 1);
	}

	private void drawAreas(GC context, AreaMapData area, int left, int top,
			int right, int bottom) {
		if (area == null) {
			return;
		}

		drawArea(context, left, top, right, bottom, area.getSecondaryValue());

		registerToolTipArea(area, left, top, right, bottom);

		List<AreaMapData> areaChildren = area.getChildren();
		if (areaChildren.size() == 0) {
			return;
		}

		/*
		 * Adjust internal frame...
		 */

		int width = right - left;
		int height = bottom - top;

		if ((width < 1) || (height < 1)) {
			// The area is too small to show something meaningful...
			return;
		}

		if (width > height) {
			// horizontal
			drawHorizontalSplit(context, left, top, bottom, width, areaChildren);
		} else {
			// vertical
			drawVerticalSplit(context, left, top, right, height, areaChildren);
		}
	}

	private void registerToolTipArea(AreaMapData area, int left, int top,
			int right, int bottom) {
		Rectangle rectangle = new Rectangle(left, top, right - left + 1, bottom
				- top + 1);
		tooltipAreas.put(rectangle, area);
	}

	private double[] areaRatios(List<AreaMapData> areaChildren) {
		double values[] = new double[areaChildren.size()];
		for (int i = 0; i < areaChildren.size(); i++) {
			AreaMapData child = areaChildren.get(i);
			values[i] = child.getValue();
		}
		return values;
	}

	private void drawArea(GC context, int left, int top, int right, int bottom,
			Object secondaryValue) {
		int width = right - left + 1;
		int height = bottom - top + 1;

		if ((width < 0) || (height < 0)) {
			// The area is too small to show something meaningful...
			return;
		}

		RGB frameRGB = DEFAULT_FRAME_COLOR;
		if (colorProvider != null) {
			RGB fillRGB = colorProvider.getForegroundColor(secondaryValue);
			if (fillRGB != null) {
				Color fillColor = new Color(getDisplay(), fillRGB);
				try {
					context.setBackground(fillColor);
					context.fillRectangle(left, top, width, height);
				} finally {
					fillColor.dispose();
				}
			}
			RGB rgb = colorProvider.getBackgroundColor(secondaryValue);
			if (rgb != null) {
				frameRGB = rgb;
			}
		}
		Color frameColor = new Color(context.getDevice(), frameRGB);
		try {
			context.setForeground(frameColor);
			context.drawRectangle(left, top, width, height);
		} finally {
			frameColor.dispose();
		}
	}

	private void drawAreasDouble(GC context, AreaMapData area, double left,
			double top, double right, double bottom, Object secondaryValue) {
		drawAreas(context, area, (int) Math.round(left), (int) Math.round(top),
				(int) Math.round(right), (int) Math.round(bottom));
	}

	private void drawHorizontalSplit(GC context, int left, int top, int bottom,
			int width, List<AreaMapData> areaChildren) {
		double[] values = areaRatios(areaChildren);
		long[] sizes = MathUtils.allocate(width, values);
		int position = left;
		for (int i = 0; i < values.length; i++) {
			int size = (int) sizes[i];
			AreaMapData child = areaChildren.get(i);
			drawAreasDouble(context, child, //
					position + PADDING,//
					top + PADDING, //
					position + size - PADDING,//
					bottom - PADDING,//
					child.getSecondaryValue());
			position += size;
		}
	}

	private void drawVerticalSplit(GC context, int left, int top, int right,
			int height, List<AreaMapData> areaChildren) {
		double[] values = areaRatios(areaChildren);
		long[] sizes = MathUtils.allocate(height, values);
		int position = top;
		for (int i = 0; i < values.length; i++) {
			AreaMapData child = areaChildren.get(i);
			int size = (int) sizes[i];
			drawAreasDouble(context, child, //
					left + PADDING, //
					position + PADDING, //
					right - PADDING, //
					position + size - PADDING, //
					child.getSecondaryValue()//
			);
			position += size;
		}
	}

}
