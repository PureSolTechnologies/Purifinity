package com.puresol.math.hp;

import javax.i18n4java.Translator;

import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.MethodInvokationException;

/**
 * This class calculates the so called Error Function y=erf(x). The information
 * was taken from: http://en.wikipedia.org/wiki/Error_function
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ErrorFunction {

	private static final Translator translator = Translator
			.getTranslator(ErrorFunction.class);

	public static double erf(double x) {
		if (x <= -5.0) {
			return -1.0;
		}
		if (x >= +5.0) {
			return 1.0;
		}
		return calculate(x);
	}

	private static double calculate(double x) {
		return 2.0 / Math.sqrt(Math.PI) * sum(x);
	}

	private static double sum(double x) {
		double result = 0.0;
		for (int n = 0; n <= 10000; n++) {
			double summand = x / (2.0 * (double) n + 1.0) * product(x, n);
			result += summand;
			if (Math.abs(summand / result) < 1e-9) {
				break;
			}
		}
		return result;
	}

	private static double product(double x, int n) {
		double result = 1.0;
		for (int k = 1; k <= n; k++) {
			result *= -1.0 * x * x / (double) k;
		}
		return result;
	}

	public static double erfc(double y) {
		try {
			if ((y > 1.0) || (y < -1.0)) {
				throw new IllegalArgumentException(translator.i18n(
						"Argument {0} is not suitable for erfc!", y));
			}
			if ((y < 1.0) && (y > -1.0)) {
				NewtonsMethod newton = new NewtonsMethod(new ErrorFunction(),
						"erf");
				return newton.find(y, -5.0, 5.0, 1e-9);
			}
			return Math.signum(y) * 5.0;
		} catch (MethodInvokationException e) {
			throw new StrangeSituationException(e);
		} catch (IntervalException e) {
			throw new StrangeSituationException(e);
		}
	}
}
