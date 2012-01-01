package com.puresol.math.hp;

import java.math.BigDecimal;

import com.puresol.utils.MethodInvokationException;

public class HPMath {

    public static BigDecimal sqr(BigDecimal d) {
	return d.multiply(d);
    }

    public static BigDecimal sqrt(BigDecimal x) {
	try {
	    HPNewtonsMethod newton = new HPNewtonsMethod(new HPMath(), "sqr");
	    return newton.find(x, BigDecimal.valueOf(0.0), x,
		    BigDecimal.valueOf(1e-9));
	} catch (MethodInvokationException e) {
	    throw new RuntimeException(e);
	} catch (IntervalException e) {
	    throw new RuntimeException(e);
	}
    }

}
