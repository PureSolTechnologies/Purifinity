package com.puresol.coding.lang.java.samples;

import java.io.IOException;
import java.io.Serializable;

public class Test implements Serializable {

	private static final long serialVersionUID = 2921265612698155191L;

	private final int a;
	private final double c = 10.1;

	public Test() {
		a = 0;
	}

	public Test(int b) {
		super();
		a = b;
	}

	public void publicMethod(int c) throws IOException {
		c = c * 2;
	}

	public int getA() {
		return a;
	}

	public double getC() {
		return c;
	}
}
