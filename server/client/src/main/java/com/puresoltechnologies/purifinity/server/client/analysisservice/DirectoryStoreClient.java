package com.puresoltechnologies.purifinity.server.client.analysisservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.DirectoryStoreRestInterface;
import com.puresoltechnologies.purifinity.server.common.rest.AbstractRestClient;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;

public class DirectoryStoreClient extends
		AbstractRestClient<DirectoryStoreRestInterface> {

	private static final DirectoryStoreClient INSTANCE;
	static {
		try {
			INSTANCE = new DirectoryStoreClient();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Unknown URI for Analysis Service.", e);
		}
	}

	public static DirectoryStoreClient getInstance() {
		return INSTANCE;
	}

	private DirectoryStoreClient() throws URISyntaxException {
		super(new URI("http://localhost:8080/purifinityserver/rest"),
				DirectoryStoreRestInterface.class);
	}

	public boolean isAvailable(HashId hashId) throws DirectoryStoreException {
		return getProxy().isAvailable(hashId);
	}

	public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException {
		return getProxy().getFiles(hashId);
	}

	public List<HashId> getDirectories(HashId hashId)
			throws DirectoryStoreException {
		return getProxy().getDirectories(hashId);
	}
}
