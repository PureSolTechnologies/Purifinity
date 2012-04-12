package com.puresol.coding.analysis.api;

import java.io.InputStream;

import com.puresol.utils.HashId;

/**
 * This is the interface for a file store. The files are stored by id and the
 * content is stored in a generic way. The name of the file is not stored due to
 * the possibility to change the name of the file or the position within the
 * project without changing the analysis and evaluation results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface FileStore {

    public AnalyzedFile loadFile(HashId hashId);

    /**
     * Stores the file in the store.
     * 
     * @param analyzedFile
     * @return True is returned in case of a successful storage. False is
     *         returned in case of an already stored file. In this case the new
     *         file is not stored and the already stored file is assumed to be
     *         equal due to the same hash id.
     * @throws FileStoreException
     */
    boolean storeFile(HashId analyzedFile, InputStream conent)
	    throws FileStoreException;

    /**
     * This method loads a single analysis.
     * 
     * @param hashId
     * @return
     * @throws FileStoreException
     */
    public FileAnalysis loadAnalysis(HashId hashId) throws FileStoreException;

    /**
     * This method stores a single analysis for a file.
     * 
     * @param hashId
     * @param fileAnalysis
     * @throws FileStoreException
     */
    void storeAnalysis(HashId hashId, FileAnalysis fileAnalysis)
	    throws FileStoreException;

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
     * @throws FileStoreException
     */
    public InputStream loadContent(HashId hashId) throws FileStoreException;

    public boolean wasAnalyzed(HashId hashId);
}
