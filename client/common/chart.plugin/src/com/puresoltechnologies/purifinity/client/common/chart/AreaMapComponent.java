package com.puresoltechnologies.purifinity.client.common.chart;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import com.puresoltechnologies.commons.trees.Tree;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.AreaMapData;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.AreaMapRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ColorProvider;

/**
 * This component provides a 2D view on data. The data provided as a
 * {@link Tree} are shown as summed up areas.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AreaMapComponent extends Canvas implements PaintListener {

	private AreaMapData data = null;
	private String unit = null;
	private final Map<Rectangle, AreaMapData> tooltipAreas = new HashMap<Rectangle, AreaMapData>();
	private ColorProvider colorProvider;
	private final AreaMapRenderer renderer = new AreaMapRenderer();

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

	@Override
	public AreaMapData getData() {
		return data;
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
		renderer.setColorProvider(colorProvider);
	}

	@Override
	public void paintControl(PaintEvent event) {
		GC context = event.gc;
		Point size = getSize();
		tooltipAreas.clear();
		renderer.render(context, data, 0, 0, size.x - 1, size.y - 1);
		tooltipAreas.putAll(renderer.getTooltipAreas());
	}

}
