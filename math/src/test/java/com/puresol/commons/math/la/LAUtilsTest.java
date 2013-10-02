package com.puresol.commons.math.la;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LAUtilsTest {

	@Test
	public void testCreateIdentity() {
		Matrix matrix = LAUtils.createIdentity(3);
		assertEquals(3, matrix.getM());
		assertEquals(3, matrix.getN());
		assertEquals(1.0, matrix.get(0, 0), 1e-10);
		assertEquals(0.0, matrix.get(0, 1), 1e-10);
		assertEquals(0.0, matrix.get(0, 2), 1e-10);
		assertEquals(0.0, matrix.get(1, 0), 1e-10);
		assertEquals(1.0, matrix.get(1, 1), 1e-10);
		assertEquals(0.0, matrix.get(1, 2), 1e-10);
		assertEquals(0.0, matrix.get(2, 0), 1e-10);
		assertEquals(0.0, matrix.get(2, 1), 1e-10);
		assertEquals(1.0, matrix.get(2, 2), 1e-10);
	}

	@Test
	public void testMultiply() {
		Matrix a = new Matrix(3, 2, new double[][] { { 1, 2 }, { 3, 4 },
				{ 5, 6 } });
		Matrix b = new Matrix(2, 3, new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
		Matrix c = LAUtils.multiply(a, b);
		assertEquals(3, c.getM());
		assertEquals(3, c.getN());
		assertEquals(9.0, c.get(0, 0), 1e-10);
		assertEquals(12.0, c.get(0, 1), 1e-10);
		assertEquals(15.0, c.get(0, 2), 1e-10);
		assertEquals(19.0, c.get(1, 0), 1e-10);
		assertEquals(26.0, c.get(1, 1), 1e-10);
		assertEquals(33.0, c.get(1, 2), 1e-10);
		assertEquals(29.0, c.get(2, 0), 1e-10);
		assertEquals(40.0, c.get(2, 1), 1e-10);
		assertEquals(51.0, c.get(2, 2), 1e-10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalMultiply() {
		Matrix a = new Matrix(3, 2, new double[][] { { 1, 2 }, { 3, 4 },
				{ 5, 6 } });
		Matrix b = new Matrix(3, 2, new double[][] { { 1, 2 }, { 3, 4 },
				{ 5, 6 } });
		LAUtils.multiply(a, b);
	}

	@Test
	public void testMultiplyWithVector() {
		Matrix a = new Matrix(3, 2, new double[][] { { 1, 2 }, { 3, 4 },
				{ 5, 6 } });
		Vector b = new Vector(new double[] { 1, 2 });
		Vector c = LAUtils.multiply(a, b);
		assertEquals(3, c.getN());
		assertEquals(5.0, c.get(0), 1e-10);
		// assertEquals(11.0, c.get(1), 1e-10);
		assertEquals(17.0, c.get(2), 1e-10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalMultiplyWithVector() {
		Matrix a = new Matrix(3, 2, new double[][] { { 1, 2 }, { 3, 4 },
				{ 5, 6 } });
		Vector b = new Vector(new double[] { 1, 2, 3 });
		LAUtils.multiply(a, b);
	}

}
