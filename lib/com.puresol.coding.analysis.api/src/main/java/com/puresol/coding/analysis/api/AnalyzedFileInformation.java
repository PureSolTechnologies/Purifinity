package com.puresol.coding.analysis.api;

import java.io.File;
import java.io.Serializable;

import com.puresol.utils.HashId;

/**
 * This interface represents the basic information about a single analyzed file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalyzedFileInformation implements Serializable {

    private static final long serialVersionUID = -483895870519484439L;

    private final HashId hashId;
    private final File file;

    /**
     * The initial value constructor.
     * 
     * @param hashId
     * @param file
     */
    public AnalyzedFileInformation(HashId hashId, File file) {
	super();
	this.hashId = hashId;
	this.file = file;
    }

    /**
     * This method returns the hashID of the file. The hash id is a hash code of
     * the whole file which is to be assumed to be unique for the files. It is
     * the same approach as GIT used to identify files.
     * 
     * @return A String is returned containing the hash code which is used to
     *         identify the file.
     */
    public final HashId getHashId() {
	return hashId;
    }

    /**
     * This method returns the relative path of the analyzed file within the
     * source code directory.
     * 
     * @return A {@link File} is returned.
     */
    public final File getFile() {
	return file;
    }

    @Override
    public final int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((file == null) ? 0 : file.hashCode());
	result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
	return result;
    }

    @Override
    public final boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AnalyzedFileInformation other = (AnalyzedFileInformation) obj;
	if (file == null) {
	    if (other.file != null)
		return false;
	} else if (!file.equals(other.file))
	    return false;
	if (hashId == null) {
	    if (other.hashId != null)
		return false;
	} else if (!hashId.equals(other.hashId))
	    return false;
	return true;
    }

}
