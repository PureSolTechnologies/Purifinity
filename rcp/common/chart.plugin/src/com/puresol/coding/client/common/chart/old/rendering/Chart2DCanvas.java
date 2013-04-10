package com.puresol.coding.client.common.chart.old.rendering;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.client.common.chart.old.AxisDirection;
import com.puresol.coding.client.common.chart.old.CategoryChart;
import com.puresol.coding.client.common.chart.old.Chart2D;
import com.puresol.coding.client.common.chart.old.XAxis;
import com.puresol.coding.client.common.chart.old.XYChart;
import com.puresol.coding.client.common.chart.old.YAxis;

/**
 * This is a simple {@link XYChart} renderer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart2DCanvas extends Canvas implements PaintListener {

	private Chart2D chart2D = null;

	public Chart2DCanvas(Composite parent, int style) {
		super(parent, style);
		addPaintListener(this);
	}

	public Chart2DCanvas(Composite parent, int style, XYChart chart2D) {
		super(parent, style);
		this.chart2D = chart2D;
		addPaintListener(this);
	}

	public Chart2D getChart2D() {
		return chart2D;
	}

	public void setChart2D(Chart2D chart2d) {
		chart2D = chart2d;
	}

	@Override
	public void paintControl(PaintEvent e) {
		GC gc = e.gc;
		Rectangle clientArea = getClientArea();
		Transform transform = new Transform(gc.getDevice());
		transform.translate(clientArea.width / 2, clientArea.height / 2);
		// transform.rotate(-15f);
		gc.setTransform(transform);
		/*
		 * Due to the missing fractional pixel drawing facilities in Eclipse we
		 * need to transform everything on our own.
		 */
		TransformationMatrix2D transformMatrix2d = new TransformationMatrix2D();
		transformMatrix2d.mirror(AxisDirection.X);
		if (chart2D instanceof XYChart) {
			renderXYChart(gc, clientArea, transformMatrix2d, (XYChart) chart2D);
		} else if (chart2D instanceof CategoryChart) {
			renderCategoryChart(gc, clientArea, transformMatrix2d,
					(CategoryChart) chart2D);
		} else {
			throw new IllegalStateException("Rendering for chart type '"
					+ chart2D.getClass().getName() + "' not implemented!");
		}
	}

	private void renderXYChart(GC gc, Rectangle clientArea,
			TransformationMatrix2D transformMatrix2d, XYChart xyChart) {
		XAxis xAxis = xyChart.getXAxis();
		YAxis yAxis = xyChart.getYAxis();

		double rangeX = xAxis.getMaximum() - xAxis.getMinimum();
		double rangeY = yAxis.getMaximum() - yAxis.getMinimum();

		double centerX = (xAxis.getMinimum() + xAxis.getMaximum()) / 2.0;
		double centerY = (yAxis.getMinimum() + yAxis.getMaximum()) / 2.0;

		double scaleX = clientArea.width / rangeX * 0.95;
		double scaleY = clientArea.height / rangeY * 0.95;
		transformMatrix2d.scale(scaleX, scaleY);
		transformMatrix2d.translate(-centerX, -centerY);

		if (xyChart != null) {
			new XYChartRenderer().render(gc, xyChart, transformMatrix2d);
		}
	}

	private void renderCategoryChart(GC gc, Rectangle clientArea,
			TransformationMatrix2D transformMatrix2d,
			CategoryChart categoryChart) {
		XAxis xAxis = categoryChart.getXAxis();
		YAxis yAxis = categoryChart.getYAxis();

		double rangeX = xAxis.getMaximum() - xAxis.getMinimum();
		double rangeY = yAxis.getMaximum() - yAxis.getMinimum();

		double centerX = (xAxis.getMinimum() + xAxis.getMaximum()) / 2.0;
		double centerY = (yAxis.getMinimum() + yAxis.getMaximum()) / 2.0;

		double scaleX = clientArea.width / rangeX * 0.95;
		double scaleY = clientArea.height / rangeY * 0.95;
		transformMatrix2d.scale(scaleX, scaleY);
		transformMatrix2d.translate(-centerX, -centerY);

		if (categoryChart != null) {
			new CategoryChartRenderer().render(gc, categoryChart,
					transformMatrix2d);
		}
	}
}
