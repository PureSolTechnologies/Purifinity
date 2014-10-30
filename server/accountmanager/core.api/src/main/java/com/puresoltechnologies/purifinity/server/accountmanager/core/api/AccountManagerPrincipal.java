package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

import java.io.Serializable;
import java.security.Principal;

public interface AccountManagerPrincipal extends Principal, Serializable {

    @Override
    public String getName();

    public String getRealName();

}
