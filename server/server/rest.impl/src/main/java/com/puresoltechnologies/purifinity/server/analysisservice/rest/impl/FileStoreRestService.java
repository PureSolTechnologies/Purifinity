package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.FileStoreRestInterface;
import com.puresoltechnologies.purifinity.server.core.api.analysis.FileStoreService;

public class FileStoreRestService implements FileStoreRestInterface {

	@Inject
	private FileStoreService fileStore;

	@Override
	public HashId storeRawFile(InputStream rawStream) throws FileStoreException {
		return fileStore.storeRawFile(rawStream);
	}

	@Override
	public InputStream readRawFile(HashId hashId) throws FileStoreException {
		return fileStore.readRawFile(hashId);
	}

	@Override
	public boolean isAvailable(HashId hashId) {
		return fileStore.isAvailable(hashId);
	}

	@Override
	public SourceCode readSourceCode(HashId hashId) throws FileStoreException {
		return fileStore.readSourceCode(null);
	}

	@Override
	public List<CodeAnalysis> loadAnalyses(HashId hashId)
			throws FileStoreException {
		return fileStore.loadAnalyses(hashId);
	}

	@Override
	public void storeAnalysis(HashId hashId, CodeAnalysis analysis)
			throws FileStoreException {
		fileStore.storeAnalysis(hashId, analysis);
	}

	@Override
	public boolean wasAnalyzed(HashId hashId) {
		return fileStore.wasAnalyzed(hashId);
	}

}
