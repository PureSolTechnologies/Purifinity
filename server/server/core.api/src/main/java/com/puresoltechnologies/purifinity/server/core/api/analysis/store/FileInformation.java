package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import com.puresoltechnologies.commons.misc.hash.HashId;

/**
 * This class contains the meta-information about files stored in
 * {@link FileStore}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class FileInformation {

	private final HashId hashId;
	private final int size;

	public FileInformation(HashId hashId, int size) {
		super();
		this.hashId = hashId;
		this.size = size;
	}

	public HashId getHashId() {
		return hashId;
	}

	public int getSize() {
		return size;
	}
}
