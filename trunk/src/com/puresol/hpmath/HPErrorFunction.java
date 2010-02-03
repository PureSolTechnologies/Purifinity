package com.puresol.hpmath;

import java.math.BigDecimal;

import javax.i18n4j.Translator;

import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.MethodInvokationException;

/**
 * This class calculates the so called Error Function y=erf(x). The
 * information was taken from: http://en.wikipedia.org/wiki/Error_function
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HPErrorFunction {

    private static final Translator translator =
	    Translator.getTranslator(ErrorFunction.class);

    public static BigDecimal erf(BigDecimal x) {
	if (x.doubleValue() <= -5.0) {
	    return BigDecimal.valueOf(-1.0);
	}
	if (x.doubleValue() >= +5.0) {
	    return BigDecimal.valueOf(1.0);
	}
	return calculate(x);
    }

    private static BigDecimal calculate(BigDecimal x) {
	return BigDecimal.valueOf(2.0).divide(
		HPMath.sqrt(BigDecimal.valueOf(Math.PI))).multiply(sum(x));
    }

    private static BigDecimal sum(BigDecimal x) {
	BigDecimal result = BigDecimal.valueOf(0.0);
	for (int n = 0; n <= 100; n++) {
	    result.add(x.divide(
		    BigDecimal.valueOf(2.0)
			    .multiply(BigDecimal.valueOf(n)).add(
				    BigDecimal.valueOf(1.0))).multiply(
		    product(x, n)));
	}
	return result;
    }

    private static BigDecimal product(BigDecimal x, int n) {
	BigDecimal result = BigDecimal.valueOf(1.0);
	for (int k = 1; k <= n; k++) {
	    result.multiply(BigDecimal.valueOf(-1.0).multiply(
		    HPMath.sqr(x).divide(BigDecimal.valueOf(k))));
	}
	return result;
    }

    public static BigDecimal erfc(BigDecimal y) {
	try {
	    if ((y.doubleValue() > 1.0) || (y.doubleValue() < -1.0)) {
		throw new IllegalArgumentException(translator.i18n(
			"Argument {0} is not suitable for erfc!", y));
	    }
	    if ((y.doubleValue() < 1.0) && (y.doubleValue() > -1.0)) {
		HPNewtonsMethod newton =
			new HPNewtonsMethod(new ErrorFunction(), "erf");
		return newton.find(y, BigDecimal.valueOf(-5.0), BigDecimal
			.valueOf(5.0), BigDecimal.valueOf(1e-9));
	    }
	    return BigDecimal.valueOf(5.0).multiply(
		    BigDecimal.valueOf(y.signum()));
	} catch (MethodInvokationException e) {
	    throw new StrangeSituationException(e);
	} catch (IntervalException e) {
	    throw new StrangeSituationException(e);
	}
    }
}
