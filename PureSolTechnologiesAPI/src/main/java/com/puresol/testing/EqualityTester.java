package com.puresol.testing;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EqualityTester {

	private static final String[] regExpStrings = new String[] { "^com\\.puresol\\..+" };
	private static final List<Pattern> regExps = new ArrayList<Pattern>();
	static {
		for (String regExpString : regExpStrings)
			regExps.add(Pattern.compile(regExpString));
	}

	public static boolean isEqual(Object expected, Object actual) {
		try {
			return isEqual(expected, actual, true);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			return false;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isEqualButNotSame(Object expected, Object actual) {
		try {
			return isEqual(expected, actual, false);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			return false;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static boolean isEqual(Object expected, Object actual,
			boolean sameAllowed) throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		if (expected == actual) {
			if (sameAllowed) {
				return true;
			} else {
				return false;
			}
		}
		Class<?> expectedClass = expected.getClass();
		Class<?> actualClass = actual.getClass();
		if (!expectedClass.equals(actualClass)) {
			return false;
		}
		if (!isEffectedPackage(expectedClass)) {
			return expected.equals(actual);
		}
		for (Field expectedField : expectedClass.getDeclaredFields()) {
			Field actualField = actualClass.getDeclaredField(expectedField
					.getName());
			Class<?> expectedFieldType = expectedField.getType();
			Class<?> actualFieldType = actualField.getType();
			if (!expectedFieldType.equals(actualFieldType)) {
				return false;
			}
			Object expectedFieldValue = expectedField.get(expected);
			Object actualFieldValue = actualField.get(actual);
			if (!isEqual(expectedFieldValue, actualFieldValue)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isEffectedPackage(Class<?> expectedFieldType) {
		String expectedFieldTypeName = expectedFieldType.getName();
		boolean isEffectedPackage = false;
		for (Pattern regExp : regExps) {
			if (regExp.matcher(expectedFieldTypeName).matches()) {
				isEffectedPackage = true;
				break;
			}
		}
		return isEffectedPackage;
	}
}
