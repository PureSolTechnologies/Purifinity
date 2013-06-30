package com.puresol.purifinity.coding.lang.java.samples;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Serializable;

import org.junit.Test;

public class TestClass implements Serializable {

	private static final long serialVersionUID = 2921265612698155191L;

	private final int a;
	private final double c = 10.1;

	public TestClass() {
		super();
		a = 0;
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

	@Test
	public void test() {
		assertNotNull(new TestClass());
	}
}
