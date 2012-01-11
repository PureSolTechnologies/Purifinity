package com.puresol.math.hp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.math.hp.ErrorFunction;

public class ErrorFunctionTest {

	@Test
	public void testErf() {
		assertEquals(-1.0, ErrorFunction.erf(-10.0), 1e-7);
		assertEquals(-0.5204999, ErrorFunction.erf(-0.5), 1e-7);
		assertEquals(0.0, ErrorFunction.erf(0.0), 1e-7);
		assertEquals(0.5204999, ErrorFunction.erf(0.5), 1e-7);
		assertEquals(1.0, ErrorFunction.erf(10.0), 1e-7);
	}

	@Test
	public void testErfc() {
		assertEquals(-5.0, ErrorFunction.erfc(-1.0), 1e-7);
		assertEquals(-0.5, ErrorFunction.erfc(-0.5204999), 1e-7);
		assertEquals(0.0, ErrorFunction.erfc(0.0), 1e-7);
		assertEquals(0.5, ErrorFunction.erfc(0.5204999), 1e-7);
		assertEquals(5.0, ErrorFunction.erfc(1.0), 1e-7);
	}

}