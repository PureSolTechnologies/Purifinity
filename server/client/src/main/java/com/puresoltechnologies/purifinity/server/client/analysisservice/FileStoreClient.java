package com.puresoltechnologies.purifinity.server.client.analysisservice;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.FileStoreRestInterface;
import com.puresoltechnologies.purifinity.server.common.rest.AbstractRestClient;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;

public class FileStoreClient extends AbstractRestClient<FileStoreRestInterface> {

	private static final FileStoreClient INSTANCE;
	static {
		try {
			INSTANCE = new FileStoreClient();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Unknown URI for Analysis Service.", e);
		}
	}

	public static FileStoreClient getInstance() {
		return INSTANCE;
	}

	private FileStoreClient() throws URISyntaxException {
		super(new URI("http://localhost:8080/purifinityserver/rest"),
				FileStoreRestInterface.class);
	}

	public InputStream readRawFile(HashId hashId) throws FileStoreException {
		return getProxy().readRawFile(hashId);
	}

	public boolean isAvailable(HashId hashId) {
		return getProxy().isAvailable(hashId);
	}

	public SourceCode readSourceCode(HashId hashId) throws FileStoreException {
		return getProxy().readSourceCode(hashId);
	}

	public List<CodeAnalysis> loadAnalysis(HashId hashId,
			ClassLoader classLoader) throws FileStoreException {
		return getProxy().loadAnalyses(hashId);
	}

	public boolean wasAnalyzed(HashId hashId) {
		return getProxy().wasAnalyzed(hashId);
	}
}
