package com.puresoltechnologies.commons.math.la;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.commons.math.la.Matrix;

public class MatrixTest {

	@Test
	public void testMatrixConstructors() {
		Matrix matrix = new Matrix(2, 5);
		assertEquals(2, matrix.getM());
		assertEquals(5, matrix.getN());
	}

	@Test
	public void testSet() {
		Matrix matrix = new Matrix(3, 2);
		matrix.set(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalSet() {
		Matrix matrix = new Matrix(2, 2);
		matrix.set(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalSet2() {
		Matrix matrix = new Matrix(3, 3);
		matrix.set(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
	}

}
