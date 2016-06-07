package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface FileStoreRemote extends CommonFileStore {

    public static final String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/FileStoreBean!com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreRemote";

}
