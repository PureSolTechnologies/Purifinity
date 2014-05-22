package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.DirectoryStoreRestInterface;
import com.puresoltechnologies.purifinity.server.core.api.analysis.DirectoryStoreService;

public class DirectoryStoreRestService implements DirectoryStoreRestInterface {

	@Inject
	private DirectoryStoreService directoryStore;

	@Override
	public boolean isAvailable(HashId hashId) throws DirectoryStoreException {
		return directoryStore.isAvailable(hashId);
	}

	@Override
	public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException {
		return directoryStore.getFiles(null);
	}

	@Override
	public List<HashId> getDirectories(HashId hashId)
			throws DirectoryStoreException {
		return directoryStore.getDirectories(hashId);
	}
}
