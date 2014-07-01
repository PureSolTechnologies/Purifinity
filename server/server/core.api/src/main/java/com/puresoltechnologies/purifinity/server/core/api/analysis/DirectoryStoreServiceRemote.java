package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;

@Remote
public interface DirectoryStoreServiceRemote extends DirectoryStore {

    public static final String JNDI_NAME = "java:global/server.app/server.core.impl/DirectoryStoreServiceImpl!com.puresoltechnologies.purifinity.server.core.api.analysis.DirectoryStoreServiceRemote";

}
