package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

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
		for (DataPoint2D dataPoint : plot.getDataPoints()) {
			Color foreground = null;
			Color background = null;
			if (colorProvider != null) {
				foreground = colorProvider.provideForegroundColor(
						gc.getDevice(), dataPoint);
				gc.setForeground(foreground);
				background = colorProvider.provideBackgroundColor(
						gc.getDevice(), dataPoint);
				gc.setBackground(background);

			}
			markRenderer.render(gc, transformation, dataPoint);
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