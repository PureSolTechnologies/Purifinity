package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface DirectoryStoreServiceRemote extends DirectoryStore {

    public static final String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/DirectoryStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreServiceRemote";

}
