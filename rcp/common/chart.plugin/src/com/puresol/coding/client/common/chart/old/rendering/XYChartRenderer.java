package com.puresol.coding.client.common.chart.old.rendering;

import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;
import com.puresol.coding.client.common.chart.old.XAxis;
import com.puresol.coding.client.common.chart.old.XYChart;
import com.puresol.coding.client.common.chart.old.YAxis;

public class XYChartRenderer {

	private final AxisRenderer renderer = new AxisRenderer();

	private GC gc;
	private XYChart chart;
	private TransformationMatrix2D transformation;

	public void render(GC gc, XYChart chart,
			TransformationMatrix2D transformation) {
		this.gc = gc;
		this.chart = chart;
		this.transformation = transformation;
		drawSingleAxes();
		for (double x = chart.getXAxis().getMinimum(); x <= chart.getXAxis()
				.getMaximum(); x += 0.001) {
			double y = Math.sin(x);
			Point2D point = new Point2D(x, y);
			point = transformation.transform(point);
			gc.drawPoint((int) point.getX(), (int) point.getY());
		}
	}

	private void drawSingleAxes() {
		XAxis xAxis = chart.getXAxis();
		YAxis yAxis = chart.getYAxis();
		renderer.render(gc, xAxis, transformation);
		renderer.render(gc, yAxis, transformation);
	}
}
