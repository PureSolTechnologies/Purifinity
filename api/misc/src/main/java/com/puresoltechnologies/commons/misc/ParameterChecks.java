package com.puresoltechnologies.commons.misc;

/**
 * This class contains functionality to check for input parameters.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ParameterChecks {

	/**
	 * Checks whether a parameter is not <code>null</code>.
	 * 
	 * @param parameterName
	 *            is the name to be displayed in the error message.
	 * @param parameter
	 *            is the parameter to be checked.
	 * @throws IllegalArgumentException
	 *             is thrown in case the parameter is <code>null</code>.
	 */
	public static <T> T checkNotNull(String parameterName, T parameter) {
		if (parameter == null) {
			throw new IllegalArgumentException(parameterName
					+ " must not be null!");
		}
		return parameter;
	}

	/**
	 * Checks whether a String parameter is not <code>null</code> and not empty
	 * what is checked with {@link String#isEmpty()}.
	 * 
	 * @param parameterName
	 *            is the name to be displayed in the error message.
	 * @param parameter
	 *            is the parameter to be checked.
	 * @throws IllegalArgumentException
	 *             is thrown in case the parameter is <code>null</code> or
	 *             empty.
	 */
	public static void checkNotNullOrEmpty(String parameterName,
			String parameter) {
		checkNotNull(parameterName, parameter);
		if (parameter.isEmpty()) {
			throw new IllegalArgumentException(parameterName
					+ " must not be empty!");
		}
	}

}
