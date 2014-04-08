package com.puresol.passwordstore.core.api;

import com.puresol.passwordstore.domain.AccountActivationException;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.domain.PasswordChangeException;
import com.puresol.passwordstore.domain.PasswordResetException;

/**
 * This is the interface for the PasswordStore.
 * 
 * @author "Rick-Rainer Ludwig"
 * 
 */
public interface PasswordStore {

	/**
	 * This method creates a new account.
	 * 
	 * @param email
	 *            is the email of the user which is used as login and to verify
	 *            and contact the user.
	 * @param password
	 *            is the password to be used for authentication.
	 * @return An String is returned containing the activation key to activate
	 *         the account after verifying the email address by sending the
	 *         activation key to the user.
	 * @throws AccountCreationException
	 *             if there is any issue during account creation, this checked
	 *             exception is thrown.
	 */
	public String createAccount(String email, String password)
			throws AccountCreationException;

	/**
	 * This method activates a new account. Needed is the information from the
	 * {@link ActivationInformation} object returned from
	 * {@link #createAccount(String, String)}.
	 * 
	 * @param email
	 *            is the email of the created account.
	 * @param activationKey
	 *            is the activation key returned from
	 *            {@link #createAccount(String, String)}.
	 * @return The userId is returned which was assigned to the user.
	 * @throws AccountActivationException
	 *             is thrown if the account could not be activated.
	 */
	public long activateAccount(String email, String activationKey)
			throws AccountActivationException;

	/**
	 * This method performs the authentication for a given email and a provided
	 * password.
	 * 
	 * @param email
	 *            is the email of the user to be authenticated.
	 * @param password
	 *            is the password to check for validity.
	 * @return True is returned, if the user was authenticated. False is
	 *         returned otherwise. In case of a false, the application using
	 *         this authentication should only tell that email and password do
	 *         not match, but it is not told whether the password is wrong or
	 *         the email.
	 */
	public boolean authenticate(String email, String password);

	/**
	 * This method changes a password of an already existing account. To make
	 * sure the correct user is on the machine, the old password needs to be
	 * typed in again.
	 * 
	 * @param email
	 *            is the email of the user to be authenticated.
	 * @param oldPassword
	 *            is the old password to check for validity.
	 * @param newPassword
	 *            is the new password to be set.
	 * @return True is returned if the change was successful.
	 * @throws PasswordChangeException
	 *             is thrown in cases of issues.
	 */
	public boolean changePassword(String email, String oldPassword,
			String newPassword) throws PasswordChangeException;

	/**
	 * This method resets the password of a user account in case of a lost or
	 * forgotten password.
	 * 
	 * @param email
	 *            is the email of the user account to be reset.
	 * @return A new password is returned.
	 * @throws PasswordResetException
	 *             is thrown in a case that a reset was not possible.
	 */
	public String resetPassword(String email) throws PasswordResetException;
}
