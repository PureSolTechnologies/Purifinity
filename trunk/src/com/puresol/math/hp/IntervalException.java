package com.puresol.math.hp;

import javax.i18n4java.Translator;

public class IntervalException extends Exception {

	private static final long serialVersionUID = -690704246943189793L;

	private static final Translator translator = Translator
			.getTranslator(IntervalException.class);

	public IntervalException(double leftEdge, double rightEdge) {
		super(translator.i18n("Interval ({0}, {1}) is not suitable!", leftEdge,
				rightEdge));
	}

	public IntervalException(double leftEdge, double leftValue,
			double rightEdge, double rightValue) {
		super(
				translator
						.i18n("Interval ({0}, {1}) is not suitable because of edge function values ({2},{3})!",
								leftEdge, rightEdge, leftValue, rightValue));
	}

	public IntervalException(double leftEdge, double leftValue,
			double rightEdge, double rightValue, double findValue) {
		super(
				translator
						.i18n("Interval ({0}, {1}) is not suitable to find value {2} because of edge function values ({3},{4})!",
								leftEdge, rightEdge, findValue, leftValue,
								rightValue));
	}

}
