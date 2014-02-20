package com.puresoltechnologies.purifinity.client.common.chart.renderer;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Transform;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.client.common.chart.Axis;
import com.puresoltechnologies.purifinity.client.common.chart.AxisDirection;
import com.puresoltechnologies.purifinity.client.common.chart.Tick;
import com.puresoltechnologies.purifinity.client.common.chart.math.Point2D;
import com.puresoltechnologies.purifinity.client.common.chart.math.TransformationMatrix2D;

/**
 * This is a central renderer for {@link Axis} object.
 * 
 * Due to the changes made on {@link GC}, <b<this class is not thread-safe.</b>
 * This should not be a problem due to it is run on the UI thread.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AxisRenderer {

	private static final int SUB_TICK_LENGTH = 1;
	private static final int MAIN_TICK_LENGTH = 3;

	private final GC gc;
	private final Axis<?> axis;
	private final double drawingPosition;

	private final Font labelFont;

	public AxisRenderer(GC gc, Axis<?> axis, double drawingPosition) {
		this.gc = gc;
		this.axis = axis;
		this.drawingPosition = drawingPosition;
		labelFont = new Font(gc.getDevice(), "Arial", 10, SWT.BOLD);
	}

	public void dispose() {
		labelFont.dispose();
	}

	public void render(TransformationMatrix2D transformation) {
		drawAxes(transformation);
		int maxLength = drawTicks(transformation);
		drawNameAndUnit(transformation, maxLength);
	}

	private void drawNameAndUnit(TransformationMatrix2D transformation,
			int maxLength) {
		AxisDirection direction = axis.getDirection();
		switch (direction) {
		case X:
			drawXAxisNameAndUnit(transformation);
			break;
		case Y:
			drawYAxisNameAndUnit(transformation, maxLength);
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
	}

	private void drawXAxisNameAndUnit(TransformationMatrix2D transformation) {
		String text = getAxisText(axis);
		Font currentFont = gc.getFont();
		Font font = new Font(gc.getDevice(), "Arial", 10, SWT.BOLD);
		try {
			gc.setFont(font);
			FontMetrics fontMetrics = gc.getFontMetrics();
			int averageCharWidth = fontMetrics.getAverageCharWidth();
			int height = fontMetrics.getHeight();
			double xPos = (axis.getMaximum() + axis.getMinimum()) / 2.0;
			Point2D pos = new Point2D(xPos, drawingPosition);
			pos = transformation.transform(pos);
			pos = new Point2D(pos.getX() - (text.length() * averageCharWidth)
					/ 2.0, pos.getY() + height);
			gc.drawText(text, (int) pos.getX(),
					(int) (pos.getY() + 2 * MAIN_TICK_LENGTH), true);
		} finally {
			gc.setFont(currentFont);
			font.dispose();
		}
	}

	private void drawYAxisNameAndUnit(TransformationMatrix2D transformation,
			int maxLength) {
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
			TransformationMatrix2D t = TransformationMatrix2D
					.createRotationMatrixDeg2D(90).multiply(transformation);
			// Now we can paint... :-)
			String text = getAxisText(axis);
			Font currentFont = gc.getFont();
			try {
				gc.setFont(labelFont);
				FontMetrics fontMetrics = gc.getFontMetrics();
				int averageCharWidth = fontMetrics.getAverageCharWidth();
				int height = fontMetrics.getHeight();
				double yPos = (axis.getMaximum() + axis.getMinimum()) / 2.0;
				Point2D pos = new Point2D(drawingPosition, yPos);
				pos = t.transform(pos);
				pos = new Point2D(pos.getX()
						- (text.length() * averageCharWidth) / 2.0, pos.getY()
						- maxLength - MAIN_TICK_LENGTH - height);
				gc.drawText(text, (int) pos.getX(), (int) pos.getY(), true);
			} finally {
				gc.setFont(currentFont);
			}
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

	private void drawAxes(TransformationMatrix2D transformation) {
		AxisDirection direction = axis.getDirection();
		switch (direction) {
		case X:
			drawXAxis(transformation);
			break;
		case Y:
			drawYAxis(transformation);
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");

		}
	}

	private void drawXAxis(TransformationMatrix2D transformation) {
		Point2D point1 = new Point2D(axis.getMinimum(), drawingPosition);
		Point2D point2 = new Point2D(axis.getMaximum(), drawingPosition);
		RendererUtils.drawLine(gc, transformation, point1, point2);
	}

	private void drawYAxis(TransformationMatrix2D transformation) {
		Point2D point1 = new Point2D(drawingPosition, axis.getMinimum());
		Point2D point2 = new Point2D(drawingPosition, axis.getMaximum());
		RendererUtils.drawLine(gc, transformation, point1, point2);
	}

	private int drawTicks(TransformationMatrix2D transformation) {
		int maxLength = 0;
		List<?> ticks = axis.getTicks();
		for (Object tickObject : ticks) {
			Tick<?> tick = (Tick<?>) tickObject;
			double position = tick.getPosition();
			switch (tick.getType()) {
			case MAJOR:
				drawTick(transformation, position, axis.getDirection(),
						MAIN_TICK_LENGTH);
				if (Number.class.isAssignableFrom(tick.getValue().getClass())) {
					maxLength = Math.max(
							maxLength,
							drawTickLabel(transformation, tick.getLabel(),
									position, axis.getDirection()));
				} else {
					drawCategoryLabel(transformation, tick.getLabel(),
							position, axis.getDirection());
				}
				break;
			case MINOR:
				drawTick(transformation, position, axis.getDirection(),
						SUB_TICK_LENGTH);
				break;
			default:
				throw new RuntimeException("Wrong tick type '"
						+ tick.getType().name() + "'.");
			}
		}
		return maxLength;
	}

	private void drawTick(TransformationMatrix2D transformation,
			double position, AxisDirection direction, int length) {
		switch (direction) {
		case X:
			Point2D subPos = new Point2D(position, drawingPosition);
			subPos = transformation.transform(subPos);
			gc.drawLine((int) subPos.getX(), (int) subPos.getY() - length,
					(int) subPos.getX(), (int) subPos.getY() + length);
			break;
		case Y:
			subPos = new Point2D(drawingPosition, position);
			subPos = transformation.transform(subPos);
			gc.drawLine((int) subPos.getX() - length, (int) subPos.getY(),
					(int) subPos.getX() + length, (int) subPos.getY());
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
	}

	private int drawTickLabel(TransformationMatrix2D transformation,
			String label, double position, AxisDirection direction) {
		Point2D pos = new Point2D();
		FontMetrics fontMetrics = gc.getFontMetrics();
		int labelLength = fontMetrics.getAverageCharWidth() * label.length();
		switch (direction) {
		case X:
			pos = new Point2D(position, drawingPosition);
			pos = transformation.transform(pos);
			gc.drawText(label, (int) pos.getX() - labelLength / 2,
					(int) (pos.getY() + MAIN_TICK_LENGTH), true);
			break;
		case Y:
			pos = new Point2D(drawingPosition, position);
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

	private void drawCategoryLabel(TransformationMatrix2D transformation,
			String categoryName, double position, AxisDirection direction) {
		FontMetrics fontMetrics = gc.getFontMetrics();
		int averageCharWidth = fontMetrics.getAverageCharWidth();
		Point2D pos = new Point2D();
		switch (direction) {
		case X:
			pos = new Point2D(position, drawingPosition);
			pos = transformation.transform(pos);
			pos = new Point2D(pos.getX()
					- (categoryName.length() * averageCharWidth) / 2.0,
					pos.getY());
			break;
		case Y:
			pos = new Point2D(drawingPosition, position);
			pos = transformation.transform(pos);
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
		gc.drawText(categoryName, (int) pos.getX() + 3, (int) pos.getY() + 3,
				true);
	}

	public int getWidth() {
		int width = 1; // axis width
		width += MAIN_TICK_LENGTH; // space for ticks
		FontMetrics fontMetrics = gc.getFontMetrics();
		AxisDirection direction = axis.getDirection();
		switch (direction) {
		case X:
			width += fontMetrics.getHeight(); // tick label
			width += fontMetrics.getHeight(); // axis label
			break;
		case Y:
			width += fontMetrics.getHeight(); // axis label
			int averageCharWidth = fontMetrics.getAverageCharWidth();
			int labelWidth = 0;
			for (Tick<?> tick : axis.getTicks()) {
				int w = tick.getLabel().length() * averageCharWidth;
				labelWidth = Math.max(labelWidth, w);
			}
			width += labelWidth; // tick label
			break;
		default:
			throw new IllegalArgumentException("Direction '" + direction.name()
					+ "' is not supported in 2D!");
		}
		return width;
	}
}
