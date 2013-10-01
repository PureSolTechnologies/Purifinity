package com.puresol.commons.math;

public class Vector extends Matrix {

    public Vector(int rows, double[] elements) {
	super(rows, 1);
	double[][] matrixElements = new double[elements.length][1];
	for (int i = 0; i < elements.length; i++) {
	    matrixElements[i][0] = elements[i];
	}
	set(matrixElements);
    }

    public Vector(int m) {
	super(m, 1);
    }

    public double get(int row) {
	return get(row, 0);
    }

    public void set(int row, double d) {
	set(row, 0, d);
    }

}
