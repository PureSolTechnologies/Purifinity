package com.puresol.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This primitive class provides a list of supported locales for the systems.
 * Configuration utilities and tests can use it to provide selections for the
 * configuration and to test the complete localization of the system for this
 * supported locales.
 * 
 * <b>This list of locales need to be put into database later on! Currently this
 * is a workaround for development! This class needs to stay, but the hard coded
 * list needs to be read from DB!</b>
 * 
 * @author "Rick-Rainer Ludwig"
 * 
 */
public class SupportedLocales {

	private static final List<Locale> locales = new ArrayList<Locale>();
	static {
		locales.add(new Locale("en", "US")); // United States of America
		locales.add(new Locale("de", "DE")); // Germany
		locales.add(new Locale("vi", "VN")); // Viet Nam
	}

	/**
	 * Private constructor to avoid instantiation.
	 */
	private SupportedLocales() {
	}

	/**
	 * This method returns a list of all locales currently supported and
	 * provided by the system.
	 * 
	 * @return An array is returned containing all supported locales. It is a
	 *         copy to avoid accidently changes.
	 */
	public static Locale[] getLocales() {
		return locales.toArray(new Locale[locales.size()]);
	}

	/**
	 * This method returns a suitable supported locale for a needed, provided
	 * locale.
	 * 
	 * The strategy is as follows:
	 * <ol>
	 * <li>If an exact match is found in {@link #locales} list (
	 * {@value #locales}), that exact match is returned.</li>
	 * <li>If the above strategy did not find a match, it is look whether a
	 * variant is provided or not. If a veriant is present, the variant is
	 * negelected and {@link #locales} is searched again for the
	 * language-country-combination.</li>
	 * <li>If there is still not match, we just look for the language without
	 * any country. It is the best remaining fit.</li>
	 * <li>If nothing helps, {@link Locale#US} is returned as default locale.</li>
	 * </ol>
	 * 
	 * @param neededLocale
	 * @return A locale is returned. The return value must never be null!
	 */
	public static Locale findSuitableSupportedLocale(Locale neededLocale) {
		/* Try to find exact match... */
		if (locales.contains(neededLocale)) {
			return neededLocale;
		}
		/* Try to find exact match without variant... */
		if ((neededLocale.getVariant() != null)
				&& (!neededLocale.getVariant().isEmpty())) {
			neededLocale = new Locale(neededLocale.getLanguage(),
					neededLocale.getCountry());
		}
		if (locales.contains(neededLocale)) {
			return neededLocale;
		}
		/* Try to find same language in other country... */
		for (Locale locale : locales) {
			if (locale.getLanguage().equals(neededLocale.getLanguage())) {
				return locale;
			}
		}
		/* If we do not find any fitting locale, we return Locale.US */
		return Locale.US;
	}

	/**
	 * This method looks for a suitable locale out of the provided list.
	 * 
	 * {@link #findSuitableSupportedLocale(Locale)} is used to find a locale.
	 * The first locale out of the array which provides a suitable locale is
	 * returned. If nothing suitable is found, here again {@link Locale#US} is
	 * returned.
	 * 
	 * @param neededLocaleArray
	 * @return A locale is returned. The return value must never be null!
	 */
	public static Locale findSuitableSupportedLocale(Locale[] neededLocaleArray) {
		for (Locale neededLocale : neededLocaleArray) {
			if (neededLocale.equals(Locale.US)) {
				return neededLocale;
			}
			Locale locale = findSuitableSupportedLocale(neededLocale);
			if (!locale.equals(Locale.US)) {
				return locale;
			}
		}
		return Locale.US;
	}

}
