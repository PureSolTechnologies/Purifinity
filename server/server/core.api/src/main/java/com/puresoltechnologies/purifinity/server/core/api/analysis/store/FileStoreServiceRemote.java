package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import javax.ejb.Remote;

@Remote
public interface FileStoreServiceRemote extends FileStore {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/FileStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.analysis.FileStoreServiceRemote";

}
