package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.Tuple;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

public class PlotRenderer {

	private final GC gc;
	private final Plot<?, ?> plot;

	public PlotRenderer(GC gc, Plot<?, ?> plot) {
		this.gc = gc;
		this.plot = plot;
	}

	public void render(TransformationMatrix2D transformation) {
		for (Tuple tuple : plot.getTuples()) {
			Point2D point = new Point2D(tuple.getX(), tuple.getY());
			point = transformation.transform(point);
			gc.drawLine((int) point.getX() - 3, (int) point.getY() - 3,
					(int) point.getX() + 3, (int) point.getY() + 3);
			gc.drawLine((int) point.getX() - 3, (int) point.getY() + 3,
					(int) point.getX() + 3, (int) point.getY() - 3);
		}
	}

}
