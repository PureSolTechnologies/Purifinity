package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreService;
import com.puresoltechnologies.purifinity.server.rest.api.FileStoreRestInterface;

public class FileStoreRestService implements FileStoreRestInterface {

	@Inject
	private FileStoreService fileStore;

	@Override
	public FileInformation storeRawFile(InputStream rawStream)
			throws FileStoreException {
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
		return fileStore.readSourceCode(hashId);
	}

	@Override
	public List<CodeAnalysis> loadAnalyses(HashId hashId)
			throws FileStoreException {
		return fileStore.loadAnalyses(hashId);
	}

	@Override
	public void storeAnalysis(HashId hashId, CodeAnalysis analysis)
			throws FileStoreException {
		if (!hashId.equals(analysis.getAnalysisInformation().getHashId())) {
			throw new IllegalArgumentException("Hash id in URL '" + hashId
					+ "' does not match hash id in analysis '"
					+ analysis.getAnalysisInformation().getHashId() + "'!");
		}
		fileStore.storeAnalysis(analysis);
	}

	@Override
	public boolean wasAnalyzed(HashId hashId) {
		return fileStore.wasAnalyzed(hashId);
	}

}
