package com.puresol.coding.analysis.api;

import java.io.InputStream;

import com.puresol.uhura.source.SourceCode;
import com.puresol.utils.HashId;

/**
 * This is the interface for a code store. The codes are stored by id and the
 * content is stored in a generic way. The name of the code is not stored due to
 * the possibility to change the name of the code or the position within the
 * project without changing the analysis and evaluation results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeStore {

    /**
     * Stores the file in the store in raw format. This means we just copy it
     * from the original directory into the store. There is not manipulation on
     * the file. So we have a byte equal copy.
     * 
     * Additionally, this method generates the {@link HashId} of the file for
     * later use.
     * 
     * @param rawStream
     *            is the {@link InputStream} to be stored.
     * @return A {@link HashId} is returned which can be used for later access
     *         to the file.
     * @throws CodeStoreException
     *             is thrown in cases of issues.
     */
    HashId storeRawFile(InputStream rawStream) throws CodeStoreException;

    /**
     * Stores the file in the store in raw format. This means we just copy it
     * from the original directory into the store. There is not manipulation on
     * the file. So we have a byte equal copy.
     * 
     * Additionally, this method generates the {@link HashId} of the file for
     * later use.
     * 
     * @param hashId
     *            is the unique id of the file to be read.
     * @return An {@link InputStream} is returned for access of the file.
     * @throws CodeStoreException
     *             is thrown in cases of issues.
     */
    InputStream readRawFile(HashId hashId) throws CodeStoreException;

    /**
     * Checks whether a file is available or not.
     * 
     * @param hashId
     * @return
     */
    public boolean isAvailable(HashId hashId);

    /**
     * This method
     * 
     * @param hashId
     * @return
     * @throws CodeStoreException
     */
    public SourceCode readSourceCode(HashId hashId) throws CodeStoreException;

    /**
     * This method loads a single analysis.
     * 
     * @param hashId
     * @return
     * @throws CodeStoreException
     */
    public CodeAnalysis loadAnalysis(HashId hashId) throws CodeStoreException;

    /**
     * This method stores a single analysis for a file.
     * 
     * @param hashId
     * @param analysis
     * @throws CodeStoreException
     */
    void storeAnalysis(HashId hashId, CodeAnalysis analysis)
	    throws CodeStoreException;

    /**
     * Checks whether a code with a given id was analyzed or not.
     * 
     * @param hashId
     * @return
     */
    public boolean wasAnalyzed(HashId hashId);
}
