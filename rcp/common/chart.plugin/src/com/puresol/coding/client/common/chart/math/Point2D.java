package com.puresol.coding.client.common.chart.math;

import com.puresol.utils.math.Vector;

public class Point2D extends Vector {

	public Point2D() {
		this(new double[] { 0.0, 0.0, 1.0 });
	}

	public Point2D(double[] elements) {
		super(3, elements);
	}

	public Point2D(double x, double y) {
		super(3, new double[] { x, y, 1.0 });
	}

	public double getX() {
		return get(0);
	}

	public double getY() {
		return get(1);
	}
}
