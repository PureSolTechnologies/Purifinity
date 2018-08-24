package com.puresoltechnologies.purifinity.server.passwordstore.core.api;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

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
     * @throws PasswordCreationException
     *             if there is any issue during account creation, this checked
     *             exception is thrown.
     */
    public String createPassword(EmailAddress email, Password password) throws PasswordCreationException;

    /**
     * This method activates a new account. Needed is the activation key from
     * {@link #createPassword(EmailAddress, Password)}.
     * <p>
     * <b>Important(!):</b> The activation is only be applied as soon as the
     * email address was checked for validity. The best way to do this it to
     * send the activation key and a link to an activation page via email to the
     * email address provided.
     * 
     * @param email
     *            is the email of the created account.
     * @param activationKey
     *            is the activation key returned from
     *            {@link #createPassword(EmailAddress, Password)}.
     * @return The {@link EmailAddress} is returned which was assigned to the
     *         user.
     * @throws PasswordActivationException
     *             is thrown if the account could not be activated.
     */
    public EmailAddress activatePassword(EmailAddress email, String activationKey) throws PasswordActivationException;

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
    public boolean authenticate(EmailAddress email, Password password);

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
    public boolean changePassword(EmailAddress email, Password oldPassword, Password newPassword)
	    throws PasswordChangeException;

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
    public Password resetPassword(EmailAddress email) throws PasswordResetException;

    /**
     * Removes a password from the store.
     * 
     * @param email
     *            is the email address of the user whose password is to be
     *            removed.
     */
    public void deletePassword(EmailAddress email);

}
