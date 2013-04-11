package com.puresol.coding.client.common.chart.renderer;

import java.util.List;

import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;

import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.Tick;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

public class AxisRenderer {

	public void render(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation, double thickLength) {
		drawAxes(gc, axis, transformation);
		drawTicks(gc, axis, transformation, thickLength);
	}

	private void drawAxes(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation) {
		switch (axis.getDirection()) {
		case X:
			drawXAxis(gc, axis, transformation);
			break;
		case Y:
			drawYAxis(gc, axis, transformation);
			break;
		}
	}

	private void drawXAxis(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation) {
		Point2D point1 = new Point2D(axis.getMinimum(), 0.0);
		Point2D point2 = new Point2D(axis.getMaximum(), 0.0);
		RendererUtils.drawLine(gc, transformation, point1, point2);
	}

	private void drawYAxis(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation) {
		Point2D point1 = new Point2D(0.0, axis.getMinimum());
		Point2D point2 = new Point2D(0.0, axis.getMaximum());
		RendererUtils.drawLine(gc, transformation, point1, point2);
	}

	private void drawTicks(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation, double thickLength) {
		List<?> ticks = axis.getTicks();
		for (Object tickObject : ticks) {
			Tick<?> tick = (Tick<?>) tickObject;
			double position = tick.getPosition();
			switch (tick.getType()) {
			case MAJOR:
				drawTick(gc, transformation, position, axis.getDirection(),
						thickLength);
				if (Number.class.isAssignableFrom(tick.getValue().getClass())) {
					drawTickLabel(gc, transformation, tick.getLabel(),
							position, axis.getDirection());
				} else {
					drawCategoryLabel(gc, transformation, tick.getLabel(),
							position, axis.getDirection());
				}
				break;
			case MINOR:
				drawTick(gc, transformation, position, axis.getDirection(),
						thickLength / 2.0);
				break;
			default:
				throw new RuntimeException("Wrong tick type '"
						+ tick.getType().name() + "'.");
			}
		}
	}

	private void drawTick(GC gc, TransformationMatrix2D transformation,
			double position, AxisDirection direction, double length) {
		Point2D subPos1 = new Point2D();
		Point2D subPos2 = new Point2D();
		switch (direction) {
		case X:
			subPos1 = new Point2D(position, -length);
			subPos2 = new Point2D(position, length);
			break;
		case Y:
			subPos1 = new Point2D(-length, position);
			subPos2 = new Point2D(length, position);
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
		RendererUtils.drawLine(gc, transformation, subPos1, subPos2);
	}

	private void drawTickLabel(GC gc, TransformationMatrix2D transformation,
			String label, double position, AxisDirection direction) {
		Point2D pos = new Point2D();
		switch (direction) {
		case X:
			pos = new Point2D(position, 0);
			break;
		case Y:
			pos = new Point2D(0.0, position);
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
		pos = transformation.transform(pos);
		gc.drawText(label, (int) pos.getX() + 3, (int) pos.getY() + 3, true);
	}

	private void drawCategoryLabel(GC gc,
			TransformationMatrix2D transformation, String categoryName,
			double position, AxisDirection direction) {
		FontMetrics fontMetrics = gc.getFontMetrics();
		int averageCharWidth = fontMetrics.getAverageCharWidth();
		Point2D pos = new Point2D();
		switch (direction) {
		case X:
			pos = new Point2D(position, 0);
			pos = transformation.transform(pos);
			pos = new Point2D(pos.getX()
					- (categoryName.length() * averageCharWidth) / 2.0,
					pos.getY());
			break;
		case Y:
			pos = new Point2D(0.0, position);
			pos = transformation.transform(pos);
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
		gc.drawText(categoryName, (int) pos.getX() + 3, (int) pos.getY() + 3,
				true);
	}

}
