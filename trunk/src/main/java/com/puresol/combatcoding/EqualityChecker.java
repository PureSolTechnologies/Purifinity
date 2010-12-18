package com.puresol.combatcoding;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
			Class<?> clazz = left.getClass();
			do {
				if (!isEqual(clazz)) {
					return false;
				}
				clazz = clazz.getSuperclass();
			} while (clazz != null);
			return true;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isEqual(Class<?> clazz) throws IllegalArgumentException,
			IllegalAccessException {
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getAnnotation(NoHashCodeAndEquals.class) != null) {
				continue;
			}
			if (field.isEnumConstant()) {
				// enum constants are constant
				continue;
			}
			int fieldModifiers = field.getModifiers();
			if (Modifier.isStatic(fieldModifiers)) {
				// statics do not need to be compared
				continue;
			}
			boolean accessable = field.isAccessible();
			field.setAccessible(true);
			Object leftValue = field.get(left);
			Object rightValue = field.get(right);
			field.setAccessible(accessable);
			if (leftValue == null) {
				if (rightValue != null)
					return false;
			} else if (!leftValue.equals(rightValue)) {
				return false;
			}
		}
		return true;
	}
}
