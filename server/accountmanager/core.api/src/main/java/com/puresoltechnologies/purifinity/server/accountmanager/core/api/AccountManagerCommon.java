package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;

/**
 * This is the common interface for local and remote AccountManagerBean
 * interface. This is a single place to document the interface.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AccountManagerCommon extends PasswordStore {

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
     * @param email
     *            is the email address of the user which activated the account.
     */
    void createAccount(String email);

}
