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
    private final long size;

    public FileInformation(HashId hashId, long size) {
	super();
	this.hashId = hashId;
	this.size = size;
    }

    public HashId getHashId() {
	return hashId;
    }

    public long getSize() {
	return size;
    }

    @Override
    public String toString() {
	return hashId + "(size: " + size + " bytes)";
    }
}
