package com.puresol.utils.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.utils.math.Matrix;

public class MatrixTest {

    @Test
    public void testMatrixConstructors() {
	Matrix matrix = new Matrix(2, 5);
	assertEquals(2, matrix.getM());
	assertEquals(5, matrix.getN());
    }

    @Test
    public void testCreateIdentity() {
	Matrix matrix = Matrix.createIdentity(3);
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
    public void testSet() {
	Matrix matrix = new Matrix(3, 2);
	matrix.set(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSet() {
	Matrix matrix = new Matrix(2, 2);
	matrix.set(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
	assertEquals(1.0, matrix.get(0, 0), 1e-10);
	assertEquals(2.0, matrix.get(0, 1), 1e-10);
	assertEquals(3.0, matrix.get(1, 0), 1e-10);
	assertEquals(4.0, matrix.get(1, 1), 1e-10);
	assertEquals(5.0, matrix.get(2, 0), 1e-10);
	assertEquals(6.0, matrix.get(2, 1), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSet2() {
	Matrix matrix = new Matrix(3, 3);
	matrix.set(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
    }

    @Test
    public void testMultiply() {
	Matrix a = new Matrix(3, 2, new double[][] { { 1, 2 }, { 3, 4 },
		{ 5, 6 } });
	Matrix b = new Matrix(2, 3, new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
	Matrix c = Matrix.multiply(a, b);
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
	Matrix a = new Matrix(2, 3, new double[][] { { 1, 2 }, { 3, 4 },
		{ 5, 6 } });
	Matrix b = new Matrix(2, 2, new double[][] { { 1, 2 }, { 4, 5 } });
	Matrix.multiply(a, b);
    }
}
