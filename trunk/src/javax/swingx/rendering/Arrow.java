package javax.swingx.rendering;

import java.awt.Graphics;
import java.awt.Polygon;

public class Arrow {

	public enum ArrowType {
		SIMPLE, EMPTY_TRIANGLE, FILLED_TRIANGLE, FANCY;
	}

	private final Graphics graphics;

	private ArrowType type = ArrowType.SIMPLE;
	private double tipAngle = 45;
	private double tipLength = 16;
	private double tipShorting = 0.5;

	public Arrow(Graphics graphics) {
		super();
		this.graphics = graphics;
	}

	public double getTipAngle() {
		return tipAngle;
	}

	public void setTipAngle(double arrowTipAngle) {
		this.tipAngle = arrowTipAngle;
	}

	public double getTipLength() {
		return tipLength;
	}

	public void setTipLength(double arrowTipLength) {
		this.tipLength = arrowTipLength;
	}

	public ArrowType getType() {
		return type;
	}

	public void setType(ArrowType arrowType) {
		this.type = arrowType;
	}

	public double getTipShorting() {
		return tipShorting;
	}

	public void setTipShorting(double tipShorting) {
		this.tipShorting = tipShorting;
	}

	public void draw(int x1, int y1, int x2, int y2) {
		graphics.drawLine(x1, y1, x2, y2);
		double length = Math
				.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		double angle = 0.0;
		if (Math.abs(x2 - x1) >= Math.abs(y1 - y2)) {
			if (x2 - x1 > 0) {
				angle = Math.asin((y1 - y2) / length);
			} else {
				angle = Math.PI - Math.asin((y1 - y2) / length);
			}
		} else {
			if (y1 - y2 > 0) {
				angle = Math.acos((x2 - x1) / length);
			} else {
				angle = -Math.acos((x2 - x1) / length);
			}
		}
		angle /= 2 * Math.PI;
		angle *= 360;

		double leftSlope = angle + 180.0 + tipAngle / 2.0;
		double rightSlope = angle + 180.0 - tipAngle / 2.0;

		int leftPointX = x2
				+ (int) (tipLength * Math.cos(leftSlope / 180 * Math.PI));
		int leftPointY = y2
				- (int) (tipLength * Math.sin(leftSlope / 180.0 * Math.PI));
		int rightPointX = x2
				+ (int) (tipLength * Math.cos(rightSlope / 180.0 * Math.PI));
		int rightPointY = y2
				- (int) (tipLength * Math.sin(rightSlope / 180.0 * Math.PI));

		if (type == ArrowType.SIMPLE) {
			graphics.drawLine(x2, y2, leftPointX, leftPointY);
			graphics.drawLine(x2, y2, rightPointX, rightPointY);
		} else if (type == ArrowType.EMPTY_TRIANGLE) {
			graphics.drawLine(x2, y2, leftPointX, leftPointY);
			graphics.drawLine(x2, y2, rightPointX, rightPointY);
			graphics.drawLine(leftPointX, leftPointY, rightPointX, rightPointY);
		} else if (type == ArrowType.FILLED_TRIANGLE) {
			Polygon polygon = new Polygon();
			polygon.addPoint(x2, y2);
			polygon.addPoint(leftPointX, leftPointY);
			polygon.addPoint(rightPointX, rightPointY);
			polygon.addPoint(x2, y2);
			graphics.fillPolygon(polygon);
		} else if (type == ArrowType.FANCY) {
			int centerPointX = x2
					+ (int) (tipLength * tipShorting * Math.cos((angle - 180)
							/ 180.0 * Math.PI));
			int centerPointY = y2
					- (int) (tipLength * tipShorting * Math.sin((angle - 180)
							/ 180.0 * Math.PI));

			Polygon polygon = new Polygon();
			polygon.addPoint(x2, y2);
			polygon.addPoint(leftPointX, leftPointY);
			polygon.addPoint(centerPointX, centerPointY);
			polygon.addPoint(rightPointX, rightPointY);
			polygon.addPoint(x2, y2);
			graphics.fillPolygon(polygon);
		}
	}
}
