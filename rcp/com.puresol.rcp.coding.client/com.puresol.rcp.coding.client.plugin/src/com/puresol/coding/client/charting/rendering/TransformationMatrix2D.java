package com.puresol.coding.client.charting.rendering;

import com.puresol.coding.client.charting.AxisDirection;
import com.puresol.math.Matrix;

/**
 * This is a 3x3 matrix for 2D graphics transformations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TransformationMatrix2D extends Matrix {

    private static final int DIMENSIONS = 3;

    /**
     * This is the default constructor. This constructor initializes a new
     * instance with an identity.
     */
    public TransformationMatrix2D() {
	super(DIMENSIONS, DIMENSIONS);
	setIdentity();
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
	    set(i, 0, get(i, 0) * scaleX);
	    set(i, 1, get(i, 1) * scaleY);
	}
    }

    public Point2D transform(Point2D point) {
	Matrix matrix = multiply(this, point);
	Point2D transformedPoint = new Point2D();
	for (int i = 0; i < DIMENSIONS; i++) {
	    transformedPoint.set(i, matrix.get(i, 0));
	}
	return transformedPoint;
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

    public void translate(int x, int y) {
	for (int i = 0; i < DIMENSIONS; i++) {
	    set(i, 2, get(i, 0) * x + get(i, 1) * y + get(i, 2));
	}
    }
}
