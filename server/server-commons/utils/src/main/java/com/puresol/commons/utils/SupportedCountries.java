package com.puresol.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This primitive class provides a list of supported countries for the systems.
 * Configuration utilities and tests can use it to provide selections for the
 * configuration and to test the completeness of the system for this supported
 * countries.
 * 
 * <b>This list of countries need to be put into database later on! Currently
 * this is a workaround for development! This class needs to stay, but the hard
 * coded list needs to be read from DB!</b>
 * 
 * @author "Rick-Rainer Ludwig"
 * 
 */
public class SupportedCountries {

	private static final List<String> countryCodes = new ArrayList<String>();
	static {
		countryCodes.add("DE");
	}

	private static final Map<String, String> countryNames = new HashMap<String, String>();
	static {
		countryNames.put("DE", "Deutschland");
	}

	/**
	 * Private constructor to avoid instantiation.
	 */
	private SupportedCountries() {
	}

	/**
	 * This method returns a list of all countries currently supported and
	 * provided by the system.
	 * 
	 * @return An array is returned containing all supported country codes. It
	 *         is a copy to avoid accidently changes.
	 */
	public static String[] getCountryCodes() {
		return countryCodes.toArray(new String[countryCodes.size()]);
	}
}
