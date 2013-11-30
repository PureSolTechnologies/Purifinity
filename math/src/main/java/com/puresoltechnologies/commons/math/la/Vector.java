package com.puresoltechnologies.commons.math.la;

import java.util.Arrays;

public class Vector {

	private final double[] elements;

	/**
	 * This constructor constructs a new vector out of elements provided.
	 * 
	 * @param elements
	 */
	public Vector(double[] elements) {
		this.elements = Arrays.copyOf(elements, elements.length);
	}

	/**
	 * This constructor constructs a new vector with a specified length and all
	 * elements set to 0.0.
	 * 
	 * @param n
	 *            is the length of the vector (the number of rows).
	 */
	public Vector(int n) {
		elements = new double[n];
		Arrays.fill(elements, 0.0);
	}

	public Vector(Vector vector) {
		this(vector.elements);
	}

	public int getN() {
		return elements.length;
	}

	public double get(int row) {
		return elements[row];
	}

	public void set(int row, double d) {
		elements[row] = d;
	}

	public void add(int row, double d) {
		elements[row] += d;
	}

}
