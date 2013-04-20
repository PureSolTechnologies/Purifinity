package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

public class PlotRenderer {

	private final GC gc;
	private final Plot<?, ?> plot;
	private MarkRenderer markRenderer = new CrossMarkRenderer();
	private ColorProvider colorProvider;

	public PlotRenderer(GC gc, Plot<?, ?> plot) {
		this.gc = gc;
		this.plot = plot;
	}

	public MarkRenderer getMarkRenderer() {
		return markRenderer;
	}

	public void setMarkRenderer(MarkRenderer markRenderer) {
		this.markRenderer = markRenderer;
	}

	public ColorProvider getColorProvider() {
		return colorProvider;
	}

	public void setColorProvider(ColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}

	public void render(TransformationMatrix2D transformation) {
		Color currentForeground = gc.getForeground();
		Color currentBackground = gc.getBackground();
		for (DataPoint2D<?, ?> dataPoint : plot.getDataPoints()) {
			Color foreground = null;
			Color background = null;
			if (colorProvider != null) {
				Object y = dataPoint.getY();
				RGB foregroundRGB = colorProvider.getForegroundColor(y);
				if (foregroundRGB != null) {
					foreground = new Color(gc.getDevice(), foregroundRGB);
					gc.setForeground(foreground);
				}
				RGB backgroundRGB = colorProvider.getBackgroundColor(y);
				if (backgroundRGB != null) {
					background = new Color(gc.getDevice(), backgroundRGB);
					gc.setBackground(background);
				}

			}
			markRenderer.render(gc, transformation, plot, dataPoint.getX(),
					dataPoint.getY());
			if (foreground != null) {
				foreground.dispose();
			}
			if (background != null) {
				background.dispose();
			}
		}
		gc.setForeground(currentForeground);
		gc.setBackground(currentBackground);
	}
}
