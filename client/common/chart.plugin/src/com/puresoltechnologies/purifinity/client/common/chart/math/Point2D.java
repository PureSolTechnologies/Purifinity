package com.puresoltechnologies.purifinity.client.common.chart.math;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/**
 * This is a special Point2D implementation for graphical linear algebra. The
 * trick is to use a 'hidden' third dimension which is initially 1.0. The
 * graphics can be transformed than with a 3x3 transformation matrix which also
 * supports translations which would not be possible otherwise.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Point2D implements Vector<TransformationSpace2D> {

	private static final long serialVersionUID = -927837905899745362L;

	private final double[] elements;

	/**
	 * This internal method checks the elements before they are used to create a
	 * new vector.
	 * 
	 * @param elements
	 *            is an array of double values which represent the new
	 *            {@link Point2D} object content.
	 * @return Returns the elements specified.
	 * @throws IllegalArgumentException
	 *             is thrown in case the array length of elements is not 3.
	 */
	private static double[] checkElements(double[] elements) {
		if (elements.length != 3) {
			throw new IllegalArgumentException(
					"The number of elements must be 3.");
		}
		return elements;
	}

	public Point2D() {
		this(new double[] { 0.0, 0.0, 1.0 });
	}

	/**
	 * 
	 * @param elements
	 *            is an array of double values which represent the new
	 *            {@link Point2D} object content.
	 */
	public Point2D(double[] elements) {
		this.elements = checkElements(elements);
	}

	public Point2D(double x, double y) {
		elements = new double[] { x, y, 1.0 };
	}

	public double get(int dimension) {
		return elements[dimension];
	}

	public double getX() {
		return get(0);
	}

	public double getY() {
		return get(1);
	}

	public double getSize() {
		return get(2);
	}

	public double[] getData() {
		return elements;
	}

	@Override
	public Space getSpace() {
		return TransformationSpace2D.getInstance();
	}

	@Override
	public Vector<TransformationSpace2D> getZero() {
		return new Point2D();
	}

	@Override
	public double getNorm1() {
		double norm = 0.0;
		for (double element : elements) {
			norm += FastMath.abs(element);
		}
		return norm;
	}

	@Override
	public double getNorm() {
		return FastMath.sqrt(getNormSq());
	}

	@Override
	public double getNormSq() {
		double norm = 0.0;
		for (double element : elements) {
			norm += element * element;
		}
		return norm;
	}

	@Override
	public double getNormInf() {
		double norm = 0.0;
		for (double element : elements) {
			norm = FastMath.max(norm, FastMath.abs(element));
		}
		return norm;
	}

	@Override
	public Vector<TransformationSpace2D> add(Vector<TransformationSpace2D> v) {
		Point2D point2D = (Point2D) v;
		double[] newElements = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = elements[i] + point2D.elements[i];
		}
		return new Point2D(newElements);
	}

	@Override
	public Vector<TransformationSpace2D> add(double factor,
			Vector<TransformationSpace2D> v) {
		Point2D point2D = (Point2D) v;
		double[] newElements = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = MathArrays.linearCombination(1, elements[i],
					factor, point2D.elements[i]);
		}
		return new Point2D(newElements);
	}

	@Override
	public Vector<TransformationSpace2D> subtract(
			Vector<TransformationSpace2D> v) {
		Point2D point2D = (Point2D) v;
		double[] newElements = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = elements[i] - point2D.elements[i];
		}
		return new Point2D(newElements);
	}

	@Override
	public Vector<TransformationSpace2D> subtract(double factor,
			Vector<TransformationSpace2D> v) {
		return add(-factor, v);
	}

	@Override
	public Vector<TransformationSpace2D> negate() {
		double[] newElements = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = -elements[i];
		}
		return new Point2D(newElements);
	}

	@Override
	public Vector<TransformationSpace2D> normalize()
			throws MathArithmeticException {
		double norm = getNorm();
		if (norm == 0) {
			throw new MathArithmeticException(
					LocalizedFormats.CANNOT_NORMALIZE_A_ZERO_NORM_VECTOR);
		}
		return scalarMultiply(1 / norm);
	}

	@Override
	public Vector<TransformationSpace2D> scalarMultiply(double a) {
		double[] newElements = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = a * elements[i];
		}
		return new Point2D(newElements);
	}

	@Override
	public boolean isNaN() {
		for (double element : elements) {
			if (Double.isNaN(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isInfinite() {
		if (isNaN()) {
			return false;
		}
		for (double element : elements) {
			if (Double.isInfinite(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double distance1(Vector<TransformationSpace2D> v) {
		Point2D point2D = (Point2D) v;
		double distance = 0.0;
		for (int i = 0; i < elements.length; i++) {
			distance += FastMath.abs(elements[i] - point2D.elements[i]);
		}
		return distance;
	}

	@Override
	public double distance(Vector<TransformationSpace2D> v) {
		return FastMath.sqrt(distanceSq(v));
	}

	@Override
	public double distanceInf(Vector<TransformationSpace2D> v) {
		Point2D point2D = (Point2D) v;
		double distance = 0.0;
		for (int i = 0; i < elements.length; i++) {
			distance = FastMath.max(distance,
					FastMath.abs(elements[i] - point2D.elements[i]));
		}
		return distance;
	}

	@Override
	public double distanceSq(Vector<TransformationSpace2D> v) {
		Point2D point2D = (Point2D) v;
		double distance = 0.0;
		for (int i = 0; i < elements.length; i++) {
			double diff = elements[i] - point2D.elements[i];
			distance += diff * diff;
		}
		return distance;
	}

	@Override
	public double dotProduct(Vector<TransformationSpace2D> v) {
		Point2D point2D = (Point2D) v;
		double product = 0.0;
		for (int i = 0; i < elements.length; i++) {
			product += elements[i] * point2D.elements[i];
		}
		return product;
	}

	@Override
	public String toString() {
		return toString(NumberFormat.getInstance(Locale.getDefault()));
	}

	@Override
	public String toString(NumberFormat format) {
		StringBuilder builder = new StringBuilder();
		int m = getSpace().getDimension();
		for (int row = 0; row < m; row++) {
			if (row == 0) {
				if (m == 0) {
					builder.append("(");
				} else {
					builder.append("/");
				}
			} else if (row == m - 1) {
				builder.append("\\");
			} else {
				builder.append("|");
			}
			builder.append(" " + format.format(get(row)));
			if (row == 0) {
				if (m == 0) {
					builder.append(")");
				} else {
					builder.append("\\");
				}
			} else if (row == m - 1) {
				builder.append("/");
			} else {
				builder.append("|");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
