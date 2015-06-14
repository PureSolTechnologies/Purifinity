package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public class StoredFile {

	private final SourceCodeLocation sourceCodeLocation;
	private final HashId hashId;

	public StoredFile(SourceCodeLocation sourceCodeLocation, HashId hashId) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.hashId = hashId;
	}

	public SourceCodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public HashId getHashId() {
		return hashId;
	}

}
