package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

public interface AccountManager extends AccountManagerCommon {

    /**
     * This method returns the user email which was used to log in.
     * 
     * @return
     */
    public String getEmail();

}
