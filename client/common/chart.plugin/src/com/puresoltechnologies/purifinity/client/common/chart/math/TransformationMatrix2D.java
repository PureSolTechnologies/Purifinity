package com.puresoltechnologies.purifinity.client.common.chart.math;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.linear.BlockRealMatrix;

import com.puresoltechnologies.purifinity.client.common.chart.AxisDirection;

/**
 * This is a 3x3 matrix for 2D graphics transformations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TransformationMatrix2D {

	public static TransformationMatrix2D createRotationMatrixRad2D(double rad) {
		TransformationMatrix2D rotationMatrix = new TransformationMatrix2D(
				new double[][] { { Math.cos(rad), -Math.sin(rad), 0.0 },
						{ Math.sin(rad), Math.cos(rad), 0.0 },
						{ 0.0, 0.0, 1.0 } });
		return rotationMatrix;
	}

	public static TransformationMatrix2D createRotationMatrixDeg2D(double deg) {
		return createRotationMatrixRad2D(deg / 180.0 * Math.PI);
	}

	private static final int DIMENSIONS = 3;

	private final BlockRealMatrix matrix;

	/**
	 * This constructor creates a new transformation matrix with the given data.
	 * This constructor is to be used internally and is set to private.
	 * 
	 * @param rawData
	 *            is the data to be used for the new transformation matrix.
	 * @throws DimensionMismatchException
	 * @throws NotStrictlyPositiveException
	 */
	private TransformationMatrix2D(double[][] rawData)
			throws DimensionMismatchException, NotStrictlyPositiveException {
		matrix = new BlockRealMatrix(rawData);
	}

	/**
	 * This is the default constructor. This constructor initializes a new
	 * instance with an identity.
	 */
	public TransformationMatrix2D() {
		this(new double[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } });
	}

	/**
	 * Scales the current transformation additionally by the given scaling
	 * factors.
	 * 
	 * @param scaleX
	 * @param scaleY
	 */
	public void scale(double scaleX, double scaleY) {
		for (int i = 0; i < DIMENSIONS; i++) {
			matrix.setEntry(i, 0, matrix.getEntry(i, 0) * scaleX);
			matrix.setEntry(i, 1, matrix.getEntry(i, 1) * scaleY);
		}
	}

	public Point2D transform(Point2D point) {
		return new Point2D(matrix.operate(point.getData()));
	}

	public void mirror(AxisDirection axis) {
		switch (axis) {
		case X:
			scale(1, -1.0);
			break;
		case Y:
			scale(-1, 1.0);
			break;
		default:
			throw new IllegalArgumentException("Direction " + axis.name()
					+ " is not valid for 2D!");
		}
	}

	public void translate(double x, double y) {
		for (int i = 0; i < DIMENSIONS; i++) {
			matrix.setEntry(i, 2,
					matrix.getEntry(i, 0) * x + matrix.getEntry(i, 1) * y
							+ matrix.getEntry(i, 2));
		}
	}

	public void rotateDeg(double deg) {
		rotateRad(deg / 180.0 * Math.PI);
	}

	public void rotateRad(double rad) {
		TransformationMatrix2D rotationMatrix = createRotationMatrixRad2D(rad);
		BlockRealMatrix newMatrix = matrix.multiply(rotationMatrix.matrix);
		matrix.setSubMatrix(newMatrix.getData(), 0, 0);
	}

	public TransformationMatrix2D multiply(
			TransformationMatrix2D createRotationMatrixDeg2D) {
		BlockRealMatrix multipliedMatrix = matrix
				.multiply(createRotationMatrixDeg2D.matrix);
		return new TransformationMatrix2D(multipliedMatrix.getData());
	}
}
