package com.puresoltechnologies.purifinity.server.rest.impl;

import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStore;
import com.puresoltechnologies.purifinity.server.rest.api.DirectoryStoreRestInterface;

public class DirectoryStoreRestService implements DirectoryStoreRestInterface {

	@Inject
	private DirectoryStore directoryStore;

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
