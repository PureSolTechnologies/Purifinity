package com.puresol.hpmath;

import org.junit.Test;

import com.puresol.hpmath.ErrorFunction;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ErrorFunctionTest extends TestCase {

    @Test
    public void testErf() {
	Assert.assertEquals(-1.0, ErrorFunction.erf(-10.0), 1e-7);
	Assert.assertEquals(-0.5204999, ErrorFunction.erf(-0.5), 1e-7);
	Assert.assertEquals(0.0, ErrorFunction.erf(0.0), 1e-7);
	Assert.assertEquals(0.5204999, ErrorFunction.erf(0.5), 1e-7);
	Assert.assertEquals(1.0, ErrorFunction.erf(10.0), 1e-7);
    }

    @Test
    public void testErfc() {
	Assert.assertEquals(-5.0, ErrorFunction.erfc(-1.0), 1e-7);
	Assert.assertEquals(-0.5, ErrorFunction.erfc(-0.5204999), 1e-7);
	Assert.assertEquals(0.0, ErrorFunction.erfc(0.0), 1e-7);
	Assert.assertEquals(0.5, ErrorFunction.erfc(0.5204999), 1e-7);
	Assert.assertEquals(5.0, ErrorFunction.erfc(1.0), 1e-7);
    }

}
