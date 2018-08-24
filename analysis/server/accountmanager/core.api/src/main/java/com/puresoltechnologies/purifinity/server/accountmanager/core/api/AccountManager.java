package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

import com.puresoltechnologies.commons.types.EmailAddress;

public interface AccountManager extends AccountManagerCommon {

    /**
     * This method returns the user email which was used to log in.
     * 
     * @return
     */
    public EmailAddress getEmail();

}
