package com.puresol.coding.analysis.api;

import java.io.File;

import com.puresol.utils.HashId;

/**
 * This interface represents the basic information about a single analyzed file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface AnalyzedFileInformation {

    /**
     * This method returns the hashID of the file. The hash id is a hash code of
     * the whole file which is to be assumed to be unique for the files. It is
     * the same approach as GIT used to identify files.
     * 
     * @return A String is returned containing the hash code which is used to
     *         identify the file.
     */
    public HashId getHashId();

    /**
     * This method returns the relative path of the analyzed file within the
     * source code directory.
     * 
     * @return A {@link File} is returned.
     */
    public File getFile();

}
