package com.puresol.combatcoding;

import java.lang.reflect.Field;

import com.puresol.exceptions.StrangeSituationException;

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
			int prime = HASH_CODE_PRIME_SEED;
			int result = 1;
			for (Field field : source.getClass().getDeclaredFields()) {
				if (field.isEnumConstant()) {
					continue;
				}
				Object value = field.get(source);
				result = prime * result
						+ ((value == null) ? 0 : value.hashCode());
			}
			return result;
		} catch (IllegalArgumentException e) {
			throw new StrangeSituationException(e);
		} catch (IllegalAccessException e) {
			throw new StrangeSituationException(e);
		}
	}

}
