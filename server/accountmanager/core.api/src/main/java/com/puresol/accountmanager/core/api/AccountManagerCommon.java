package com.puresol.accountmanager.core.api;

import java.util.Locale;

/**
 * This is the common interface for local and remote AccountManagerBean
 * interface. This is a single place to document the interface.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AccountManagerCommon {

	/**
	 * This method returns the user email which was used to log in.
	 * 
	 * @return
	 */
	public String getEmail();

	/**
	 * This method returns the user name which can be used to express the user
	 * explicitly with its real name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * This method returns all available locales for the user. If the user is
	 * not logged in, than this method shall return a list of all(!) supported
	 * languages of the platform.
	 * 
	 * If the user is logged in, the local list is limited to the languages
	 * defined in the user account.
	 * 
	 * @return
	 */
	public Locale[] getAvailableLocales();

	/**
	 * This method returns the default local for this account. If the user is
	 * not logged in, yet, the default locale is English. If the user is logged
	 * in, the default locale is set to the default setting in the account
	 * configuration.
	 * 
	 * @return
	 */
	public Locale getDefaultLocale();

	/**
	 * This method returns whether the user was already logged in
	 * (authenticated) or not.
	 * 
	 * @return True is returned if the user was already authenticated.
	 */
	boolean isLoggedIn();

	/**
	 * This method is used to create a new empty account. This method is used
	 * once(!) after account activation.
	 * 
	 * @param userId
	 *            is the user id for the account to be created.
	 * @param email
	 *            is the email address of the user which activated the account.
	 * @param locale
	 *            is the locale to be used to create the new account.
	 */
	void createAccount(long userId, String email, Locale locale);

}
