package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.framework.store.api.FileStore;

@Remote
public interface FileStoreServiceRemote extends FileStore {

    public static final String JNDI_NAME = "java:global/server.app/server.core.impl/FileStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.analysis.FileStoreServiceRemote";

}
