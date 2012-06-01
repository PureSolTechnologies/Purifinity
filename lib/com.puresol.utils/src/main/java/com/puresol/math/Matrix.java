package com.puresol.math;

/**
 * This is an implementation of a mathematical matrix for matrix calculations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Matrix {

    /**
     * Performs a matrix multiplication A x B and returns a new {@link Matrix}
     * instance with the result.
     * 
     * @param a
     *            is the matrix A.
     * @param b
     *            is the matrix B.
     * @return The result matrix is returned.
     */
    public static Matrix multiply(Matrix a, Matrix b) {
	if (a.n != b.m) {
	    throw new IllegalArgumentException(
		    "The dimenstions A.m and B.n are not equal! Matrix multiplication is not possible!");
	}
	Matrix matrix = new Matrix(a.m, b.n);
	for (int i = 0; i < a.m; i++) {
	    for (int k = 0; k < b.n; k++) {
		for (int j = 0; j < a.n; j++) {
		    matrix.elements[i][k] += a.elements[i][j]
			    * b.elements[j][k];
		}
	    }
	}
	return matrix;
    }

    /**
     * Creates an identity matrix.
     * 
     * @param mAndN
     *            is m and n due to the requirement to create a quadratic matrix
     *            (m == n).
     * @return An identity is returned.
     */
    public static Matrix createIdentity(int mAndN) {
	Matrix identity = new Matrix(mAndN, mAndN);
	for (int i = 0; i < mAndN; i++) {
	    identity.elements[i][i] = 1.0;
	}
	return identity;
    }

    private final int m;
    private final int n;

    private final double[][] elements;

    /**
     * Creates a Matrix with dimension m x n.
     * 
     * @param m
     *            is the number of rows.
     * @param n
     *            is the number of cols.
     */
    public Matrix(int m, int n) {
	super();
	this.m = m;
	this.n = n;
	elements = new double[m][n];
	for (int row = 0; row < m; row++) {
	    for (int col = 0; col < n; col++) {
		elements[row][col] = 0.0;
	    }
	}
    }

    /**
     * Creates a Matrix with dimension m x n.
     * 
     * @param m
     *            is the number of rows.
     * @param n
     *            is the number of cols.
     * @param elements
     *            are the default values to be set.
     */
    public Matrix(int m, int n, double[][] elements) {
	this(m, n);
	set(elements);
    }

    public int getM() {
	return m;
    }

    public int getN() {
	return n;
    }

    public double get(int row, int col) {
	return elements[row][col];
    }

    /**
     * This method set the elements of the matrix to be an identity.
     */
    public void setIdentity() {
	if (m != n) {
	    throw new IllegalStateException("Matrix is not quadratic (m == n)!");
	}
	for (int row = 0; row < m; row++) {
	    for (int col = 0; col < n; col++) {
		if (row == col) {
		    elements[row][col] = 1.0;
		} else {
		    elements[row][col] = 0.0;
		}
	    }
	}
    }

    /**
     * This method sets new values to the elements of the matrix.
     * 
     * @param elements
     */
    public void set(double[][] elements) {
	if (this.elements.length != elements.length) {
	    throw new IllegalArgumentException("Number of rows does not fit!");
	}
	for (int row = 0; row < elements.length; row++) {
	    if (this.elements[row].length != elements[row].length) {
		throw new IllegalArgumentException(
			"Number of columns does not fit for row " + row + "!");
	    }
	    for (int col = 0; col < elements[row].length; col++) {
		this.elements[row][col] = elements[row][col];
	    }
	}
    }

    public void set(int row, int col, double value) {
	elements[row][col] = value;
    }

}
