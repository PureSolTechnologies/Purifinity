package com.puresol.coding.client.common.ui.components;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresol.trees.Tree;
import com.puresol.utils.math.MathUtils;

/**
 * This component provides a 2D view on data. The data provided as a
 * {@link Tree} are shown as summed up areas.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AreaMapComponent extends Canvas implements PaintListener {

	private static final RGB FRAME_COLOR = new RGB(0, 0, 0);
	private static final int PADDING = 2;

	private AreaMapData data = null;
	private final List<Rectangle> tooltipAreas = new ArrayList<Rectangle>();

	public AreaMapComponent(Composite parent, int style) {
		super(parent, style);
		Color color = new Color(getDisplay(), 255, 255, 255);
		try {
			setBackground(color);
		} finally {
			color.dispose();
		}
		addPaintListener(this);
	}

	public void setData(AreaMapData data) {
		this.data = data;
		redraw();
		update();
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
		tooltipAreas.add(new Rectangle(left, top, right - left + 1, bottom
				- top + 1));
		Color color = new Color(context.getDevice(), FRAME_COLOR);
		try {
			context.setForeground(color);
			context.drawLine(left, top, right, top);
			context.drawLine(right, top, right, bottom);
			context.drawLine(left, bottom, right, bottom);
			context.drawLine(left, top, left, bottom);

			left += PADDING;
			top += PADDING;
			right -= PADDING;
			bottom -= PADDING;

			int width = right - left + 1;
			int height = bottom - top + 1;

			if (Math.min(width, height) < 5) {
				// The area is too small to show something meaningful...
				return;
			}

			List<AreaMapData> areaChildren = area.getChildren();
			if (areaChildren.size() == 0) {
				return;
			}
			double values[] = new double[areaChildren.size()];
			for (int i = 0; i < areaChildren.size(); i++) {
				AreaMapData child = areaChildren.get(i);
				values[i] = child.getValue();
			}

			if (width > height) {
				// horizontal
				long[] sizes = MathUtils.allocate(width, values);
				int position = left;
				for (int i = 0; i < values.length; i++) {
					int size = (int) sizes[i];
					AreaMapData child = areaChildren.get(i);
					drawAreasDouble(context, child, //
							position + PADDING,//
							top + PADDING, //
							position + size - 1 - PADDING,//
							bottom - PADDING//
					);
					position += size;
				}
			} else {
				// vertical
				long[] sizes = MathUtils.allocate(height, values);
				int position = top;
				for (int i = 0; i < values.length; i++) {
					AreaMapData child = areaChildren.get(i);
					int size = (int) sizes[i];
					drawAreasDouble(context, child, //
							left + PADDING, //
							position + PADDING, //
							right - PADDING, //
							position + size - 1 - PADDING//
					);
					position += size;
				}
			}
		} finally {
			color.dispose();
		}
	}

	private void drawAreasDouble(GC context, AreaMapData area, double left,
			double top, double right, double bottom) {
		drawAreas(context, area, (int) Math.round(left), (int) Math.round(top),
				(int) Math.round(right), (int) Math.round(bottom));
	}
}
