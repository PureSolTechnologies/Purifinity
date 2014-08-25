package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import javax.ejb.Remote;

@Remote
public interface DirectoryStoreServiceRemote extends DirectoryStore {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/DirectoryStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreServiceRemote";

}
