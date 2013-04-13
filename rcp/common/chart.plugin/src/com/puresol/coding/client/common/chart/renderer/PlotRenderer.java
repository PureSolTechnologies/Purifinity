package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.DataPoint;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

public class PlotRenderer {

	private final GC gc;
	private final Plot<?, ?> plot;
	private MarkRenderer markRenderer;

	public PlotRenderer(GC gc, Plot<?, ?> plot) {
		this.gc = gc;
		this.plot = plot;
		markRenderer = new CrossMarkRenderer(gc);
	}

	public MarkRenderer getMarkRenderer() {
		return markRenderer;
	}

	public void setMarkRenderer(MarkRenderer markRenderer) {
		this.markRenderer = markRenderer;
	}

	public void render(TransformationMatrix2D transformation) {
		for (DataPoint tuple : plot.getTuples()) {
			Point2D point = new Point2D(tuple.getX(), tuple.getY());
			markRenderer.render(transformation, point);
		}
	}

}
