package com.puresol.commons.math.la;

public class LAUtils {

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
		if (a.getN() != b.getM()) {
			throw new IllegalArgumentException(
					"The dimenstions A.m and B.n are not equal! Matrix multiplication is not possible!");
		}
		Matrix matrix = new Matrix(a.getM(), b.getN());
		for (int i = 0; i < a.getM(); i++) {
			for (int k = 0; k < b.getN(); k++) {
				for (int j = 0; j < a.getN(); j++) {
					matrix.add(i, k, a.get(i, j) * b.get(j, k));
				}
			}
		}
		return matrix;
	}

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
	public static Vector multiply(Matrix a, Vector b) {
		if (a.getN() != b.getN()) {
			throw new IllegalArgumentException(
					"The dimenstions A.b and B.n are not equal! Matrix multiplication is not possible!");
		}
		Vector vector = new Vector(a.getM());
		for (int i = 0; i < a.getM(); i++) {
			for (int j = 0; j < a.getN(); j++) {
				vector.add(i, a.get(i, j) * b.get(j));
			}
		}
		return vector;
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
			identity.set(i, i, 1.0);
		}
		return identity;
	}
}
