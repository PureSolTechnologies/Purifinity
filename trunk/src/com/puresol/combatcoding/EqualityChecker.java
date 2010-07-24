package com.puresol.combatcoding;

import java.lang.reflect.Field;

import javassist.Modifier;

import com.puresol.exceptions.StrangeSituationException;

/**
 * This class provides a mechanism to check for equality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EqualityChecker {

	private final Object left;
	private final Object right;

	public EqualityChecker(Object left, Object right) {
		super();
		this.left = left;
		this.right = right;
	}

	public boolean isEqual() {
		try {
			if (left == right)
				return true;
			if (right == null)
				return false;
			if (left == null)
				return false;
			if (left.getClass() != right.getClass())
				return false;
			for (Field field : left.getClass().getDeclaredFields()) {
				if (field.isEnumConstant()) {
					// enum constants are constant
					continue;
				}
				int fieldModifiers = field.getModifiers();
				if (Modifier.isStatic(fieldModifiers)) {
					// statics do not need to be compared
					continue;
				}
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				Object leftValue = field.get(left);
				Object rightValue = field.get(right);
				if (leftValue == null) {
					if (rightValue != null)
						return false;
				} else if (!leftValue.equals(rightValue)) {
					return false;
				}
			}
			return true;
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		}
	}
}
