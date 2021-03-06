package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

import java.util.Set;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.passwordstore.core.api.PasswordStore;

/**
 * This is the common interface for local and remote AccountManagerBean
 * interface. This is a single place to document the interface.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AccountManagerCommon extends PasswordStore {

    /**
     * This method is used to create a new empty account. This method is used
     * once(!) after account activation.
     * 
     * @param email
     *            is the email address of the user which activated the account.
     * @param name
     *            is the real name of the user.
     * @param roleId
     *            is the roleId of the role which is to be assigned to the new
     *            account.
     */
    public void createAccount(EmailAddress email, String name, String roleId);

    /**
     * This method alters the user account.
     * 
     * @param email
     *            is the account to be altered.
     * @param name
     *            is the new name of the user.
     * @param roleId
     *            is the new role of the user.
     */
    public void alterAccount(EmailAddress email, String name, String roleId);

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
    public boolean isLoggedIn();

    /**
     * This method returns all roles currently provided.
     * 
     * @return A {@link Set} is returned containing {@link Role} objects.
     */
    public Set<Role> getRoles();

    /**
     * This method returns all setup users.
     * 
     * @return A {@link Set} of {@link User} is returned.
     */
    public Set<User> getUsers();

    public void setUser(EmailAddress email, User user);

    public User getUser(EmailAddress email);
}
