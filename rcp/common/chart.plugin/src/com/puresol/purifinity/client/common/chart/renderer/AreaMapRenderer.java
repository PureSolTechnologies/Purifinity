package com.puresol.purifinity.client.common.chart.renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.commons.utils.math.MathUtils;
import com.puresol.purifinity.client.common.chart.AreaMapComponent;

/**
 * This class renders area maps and is used in {@link AreaMapComponent}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AreaMapRenderer {

	private static final RGB DEFAULT_FRAME_COLOR = new RGB(0, 0, 0);
	private static final int MARGIN = 2;

	private final Map<Rectangle, AreaMapData> tooltipAreas = new HashMap<Rectangle, AreaMapData>();

	private ColorProvider colorProvider = null;

	public void render(GC context, AreaMapData area, int left, int top,
			int right, int bottom) {
		if (area == null) {
			return;
		}

		renderArea(context, left, top, right, bottom, area.getSecondaryValue());

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

	private void renderArea(GC context, int left, int top, int right,
			int bottom, Object secondaryValue) {
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
				Color fillColor = new Color(context.getDevice(), fillRGB);
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

	private void drawHorizontalSplit(GC context, int left, int top, int bottom,
			int width, List<AreaMapData> areaChildren) {
		double[] values = areaRatios(areaChildren);
		long[] sizes = MathUtils.allocate(width, values);
		int position = left;
		for (int i = 0; i < values.length; i++) {
			int size = (int) sizes[i];
			AreaMapData child = areaChildren.get(i);
			drawAreasDouble(context, child, //
					position + MARGIN,//
					top + MARGIN, //
					position + size - MARGIN,//
					bottom - MARGIN,//
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
					left + MARGIN, //
					position + MARGIN, //
					right - MARGIN, //
					position + size - MARGIN, //
					child.getSecondaryValue()//
			);
			position += size;
		}
	}

	private double[] areaRatios(List<AreaMapData> areaChildren) {
		double values[] = new double[areaChildren.size()];
		for (int i = 0; i < areaChildren.size(); i++) {
			AreaMapData child = areaChildren.get(i);
			values[i] = child.getValue();
		}
		return values;
	}

	private void drawAreasDouble(GC context, AreaMapData area, double left,
			double top, double right, double bottom, Object secondaryValue) {
		render(context, area, (int) Math.round(left), (int) Math.round(top),
				(int) Math.round(right), (int) Math.round(bottom));
	}

	public Map<Rectangle, AreaMapData> getTooltipAreas() {
		return tooltipAreas;
	}

	public void setColorProvider(ColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}

}
