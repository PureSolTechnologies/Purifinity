package com.puresol.combatcoding;

import java.lang.reflect.Field;

/**
 * This class provides the mechanism to calculate hash codes for collections and
 * hashes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HashCodeGenerator {

	private static final int HASH_CODE_PRIME_SEED = 11;
	private final Object source;

	public HashCodeGenerator(Object source) {
		super();
		this.source = source;
	}

	public int getHashCode() {
		try {
			int result = 1;
			Class<?> clazz = source.getClass();
			do {
				result = getHashCode(result, clazz);
				clazz = clazz.getSuperclass();
			} while (clazz != null);
			return result;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private int getHashCode(int result, Class<?> clazz)
			throws IllegalArgumentException, IllegalAccessException {
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getAnnotation(NoHashCodeAndEquals.class) != null) {
				continue;
			}
			boolean accessable = field.isAccessible();
			field.setAccessible(true);
			if (field.isEnumConstant()) {
				continue;
			}
			Object value = field.get(source);
			result = HASH_CODE_PRIME_SEED * result
					+ ((value == null) ? 0 : value.hashCode());
			field.setAccessible(accessable);
		}
		return result;
	}

}
