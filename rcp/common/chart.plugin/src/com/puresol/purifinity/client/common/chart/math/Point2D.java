package com.puresol.purifinity.client.common.chart.math;

import com.puresol.commons.math.la.Vector;

/**
 * This is a special Point2D implementation for graphical linear algebra. The
 * trick is to use a 'hidden' third dimension which is initially 1.0. The
 * graphics can be transformed than with a 3x3 transformation matrix which also
 * supports translations which would not be possible otherwise.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Point2D extends Vector {

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
		super(checkElements(elements));
	}

	public Point2D(double x, double y) {
		super(new double[] { x, y, 1.0 });
	}

	public Point2D(Vector vector) {
		super(vector);
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
}
