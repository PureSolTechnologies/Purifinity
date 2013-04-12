package com.puresol.coding.client.common.chart.renderer;

import java.util.List;

import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Transform;

import com.puresol.coding.client.common.chart.Axis;
import com.puresol.coding.client.common.chart.AxisDirection;
import com.puresol.coding.client.common.chart.Tick;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;
import com.puresol.utils.math.Parameter;

public class AxisRenderer {

	private static final int SUB_TICK_LENGTH = 1;
	private static final int MAIN_TICK_LENGTH = 3;

	public void render(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation) {
		drawAxes(gc, axis, transformation);
		int maxLength = drawTicks(gc, axis, transformation);
		drawNameAndUnit(gc, axis, transformation, maxLength);
	}

	private void drawNameAndUnit(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation, int maxLength) {
		switch (axis.getDirection()) {
		case X:
			drawXAxisNameAndUnit(gc, axis, transformation);
			break;
		case Y:
			drawYAxisNameAndUnit(gc, axis, transformation, maxLength);
			break;
		}
	}

	private void drawXAxisNameAndUnit(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation) {
		String text = getAxisText(axis);
		FontMetrics fontMetrics = gc.getFontMetrics();
		int averageCharWidth = fontMetrics.getAverageCharWidth();
		int height = fontMetrics.getHeight();
		double xPos = (axis.getMaximum() + axis.getMinimum()) / 2.0;
		Point2D pos = new Point2D(xPos, 0.0);
		pos = transformation.transform(pos);
		pos = new Point2D(
				pos.getX() - (text.length() * averageCharWidth) / 2.0,
				pos.getY() + height);
		gc.drawText(text, (int) pos.getX(),
				(int) (pos.getY() + 2 * MAIN_TICK_LENGTH), true);
	}

	private void drawYAxisNameAndUnit(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation, int maxLength) {
		/*
		 * Let's save the current transformation on GC to reset it later on.
		 */
		Transform origTransform = new Transform(gc.getDevice());
		gc.getTransform(origTransform);
		try {
			/*
			 * Now we need to get the rotation right. We turn the GC to the left
			 * by 90°. This turns the label text for draw text. The problem is
			 * we need to correct the point position calculation transformation,
			 * otherwise we end up on the wrong position.
			 * 
			 * The correction is done by turning the point transformation into
			 * the opposite direction before the other transformations take
			 * place. This means, we multiply a transformation matrix from the
			 * left site to the transformation matrix.
			 * 
			 * Attention: We need to keep the original transformation matrix in
			 * original state! Otherwise, following draws are incorrect.
			 */
			Transform transform = new Transform(gc.getDevice());
			gc.getTransform(transform);
			transform.rotate(-90);
			gc.setTransform(transform);
			TransformationMatrix2D t = new TransformationMatrix2D(
					transformation);
			t.multiplyFromLeft(TransformationMatrix2D
					.createRotationMatrixDeg(90));
			// Now we can paint... :-)
			String text = getAxisText(axis);
			FontMetrics fontMetrics = gc.getFontMetrics();
			int averageCharWidth = fontMetrics.getAverageCharWidth();
			int height = fontMetrics.getHeight();
			double yPos = (axis.getMaximum() + axis.getMinimum()) / 2.0;
			Point2D pos = new Point2D(0.0, yPos);
			pos = t.transform(pos);
			pos = new Point2D(pos.getX() + (text.length() * averageCharWidth)
					/ 2.0, pos.getY() - maxLength - MAIN_TICK_LENGTH - height);
			gc.drawText(text, (int) pos.getX(), (int) pos.getY(), true);
		} finally {
			// Reset the GC transformation...
			gc.setTransform(origTransform);
		}
	}

	private String getAxisText(Axis<?> axis) {
		Parameter<?> parameter = axis.getParameter();
		String text = parameter.getName();
		if (!parameter.getUnit().isEmpty()) {
			text += "[" + parameter.getUnit() + "]";
		}
		return text;
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

	private int drawTicks(GC gc, Axis<?> axis,
			TransformationMatrix2D transformation) {
		int maxLength = 0;
		List<?> ticks = axis.getTicks();
		for (Object tickObject : ticks) {
			Tick<?> tick = (Tick<?>) tickObject;
			double position = tick.getPosition();
			switch (tick.getType()) {
			case MAJOR:
				drawTick(gc, transformation, position, axis.getDirection(),
						MAIN_TICK_LENGTH);
				if (Number.class.isAssignableFrom(tick.getValue().getClass())) {
					maxLength = Math.max(
							maxLength,
							drawTickLabel(gc, transformation, tick.getLabel(),
									position, axis.getDirection()));
				} else {
					drawCategoryLabel(gc, transformation, tick.getLabel(),
							position, axis.getDirection());
				}
				break;
			case MINOR:
				drawTick(gc, transformation, position, axis.getDirection(),
						SUB_TICK_LENGTH);
				break;
			default:
				throw new RuntimeException("Wrong tick type '"
						+ tick.getType().name() + "'.");
			}
		}
		return maxLength;
	}

	private void drawTick(GC gc, TransformationMatrix2D transformation,
			double position, AxisDirection direction, int length) {
		switch (direction) {
		case X:
			Point2D subPos = new Point2D(position, 0);
			subPos = transformation.transform(subPos);
			gc.drawLine((int) subPos.getX(), (int) subPos.getY() - length,
					(int) subPos.getX(), (int) subPos.getY() + length);
			break;
		case Y:
			subPos = new Point2D(0, position);
			subPos = transformation.transform(subPos);
			gc.drawLine((int) subPos.getX() - length, (int) subPos.getY(),
					(int) subPos.getX() + length, (int) subPos.getY());
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
	}

	private int drawTickLabel(GC gc, TransformationMatrix2D transformation,
			String label, double position, AxisDirection direction) {
		Point2D pos = new Point2D();
		FontMetrics fontMetrics = gc.getFontMetrics();
		int labelLength = fontMetrics.getAverageCharWidth() * label.length();
		switch (direction) {
		case X:
			pos = new Point2D(position, 0);
			pos = transformation.transform(pos);
			gc.drawText(label, (int) pos.getX() - labelLength / 2,
					(int) (pos.getY() + MAIN_TICK_LENGTH), true);
			break;
		case Y:
			pos = new Point2D(0.0, position);
			pos = transformation.transform(pos);
			gc.drawText(label,
					(int) (pos.getX() - labelLength - MAIN_TICK_LENGTH),
					(int) pos.getY() - fontMetrics.getHeight() / 2, true);
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
		return labelLength;
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
