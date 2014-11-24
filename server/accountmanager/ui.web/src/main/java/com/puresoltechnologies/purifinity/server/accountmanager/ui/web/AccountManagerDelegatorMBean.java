package com.puresoltechnologies.purifinity.server.accountmanager.ui.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerRemote;

@ManagedBean(name = "accountManagerDelegatorMBean")
@SessionScoped
public class AccountManagerDelegatorMBean implements Serializable {

    private static final long serialVersionUID = 4724997649418867363L;

    /**
     * Remote EJB lookup.
     * 
     * @see https 
     *      ://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote
     *      +client+using+JNDI
     */
    @EJB(lookup = "ejb:accountmanager.app/accountmanager.core.impl//AccountManagerBean!com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerRemote?stateful")
    private AccountManagerRemote accountManager;

    public Object getName() {
	return accountManager.getName();
    }

    public boolean isLoggedIn() {
	return accountManager.isLoggedIn();
    }

    public void createAccount(String email) {
	accountManager.createAccount(new EmailAddress(email));
    }
}
