package com.puresol.hpmath;

import java.math.BigDecimal;

import org.junit.Test;

import com.puresol.hpmath.IntervalException;
import com.puresol.utils.MethodInvokationException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HPNewtonsMethodTest extends TestCase {

    public BigDecimal y(BigDecimal x) {
	return x;
    }

    @Test
    public void testConstructor() {
	try {
	    HPNewtonsMethod newton = new HPNewtonsMethod(this, "y");
	    Assert.assertSame(this, newton.getObject());
	    Assert.assertEquals("y", newton.getMethodName());
	} catch (MethodInvokationException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }

    @Test
    public void testFunction() {
	try {
	    HPNewtonsMethod newton = new HPNewtonsMethod(this, "y");
	    for (double x = -10.0; x <= 10.0; x += 1.0) {
		BigDecimal bx = BigDecimal.valueOf(x);
		Assert.assertTrue(y(bx).subtract(newton.function(bx))
			.abs().compareTo(BigDecimal.valueOf(1e-10)) < 0.0);
	    }
	} catch (MethodInvokationException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }

    @Test
    public void testFind() {
	try {
	    HPNewtonsMethod newton = new HPNewtonsMethod(this, "y");
	    for (double y = -10.0; y <= 10.0; y += 1.0) {
		BigDecimal by = BigDecimal.valueOf(y);
		BigDecimal x =
			newton.find(by, BigDecimal.valueOf(-12.0),
				BigDecimal.valueOf(12.0), BigDecimal
					.valueOf(1e-9));
		Assert.assertTrue(by.subtract(y(x)).abs().compareTo(
			BigDecimal.valueOf(1e-8)) < 0.0);
	    }
	} catch (MethodInvokationException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (IntervalException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }

    @Test
    public void testIntervalException() {
	try {
	    HPNewtonsMethod newton = new HPNewtonsMethod(this, "y");
	    newton.find(BigDecimal.valueOf(-5), BigDecimal.valueOf(-8.0),
		    BigDecimal.valueOf(-7), BigDecimal.valueOf(1e-9));
	    Assert.fail("An IntervalException was expected!");
	} catch (MethodInvokationException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (IntervalException e) {
	    // this exception was expected!
	}
    }

}
