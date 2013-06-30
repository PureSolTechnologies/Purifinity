package com.puresol.purifinity.utils.math;

import java.util.HashMap;
import java.util.Map;

/**
 * This call represents a physical unit which is a compound unit of
 * {@link SIUnit}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CompoundSIUnit {

	private final Map<SIUnit, Integer> exponents = new HashMap<SIUnit, Integer>();

	public CompoundSIUnit() {
		for (SIUnit unit : SIUnit.values()) {
			exponents.put(unit, 0);
		}
	}

	@Override
	public String toString() {
		StringBuilder positives = new StringBuilder();
		StringBuilder negatives = new StringBuilder();
		for (SIUnit unit : SIUnit.values()) {
			Integer exponent = exponents.get(unit);
			if (exponent > 0) {
				if (positives.length() > 0) {
					positives.append(" ");
				}
				positives.append(unit.getSign());
				positives.append("^");
				positives.append(exponent);
			} else if (exponent < 0) {
				if (negatives.length() > 0) {
					negatives.append(" ");
				}
				negatives.append(unit.getSign());
				negatives.append("^");
				negatives.append(-exponent);
			}
		}
		if (negatives.length() > 0) {
			return positives.toString() + " / (" + negatives.toString() + ")";
		} else {
			return positives.toString();
		}
	}
}
