package com.puresol.coding.analysis.api;

import com.puresol.utils.HashId;

/**
 * This is the interface for a directory store. This is an additional store to
 * the file store. The directories are named with hash codes, too, to get only
 * unique directories. For this directories information can be stored for later
 * access.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface DirectoryStore {

    /**
     * Stores the file in the store.
     * 
     * @param analyzedFile
     * @return True is returned in case of a successful storage. False is
     *         returned in case of an already stored file. In this case the new
     *         file is not stored and the already stored file is assumed to be
     *         equal due to the same hash id.
     * @throws DirectoryStoreException
     */
    public boolean createDirectory(HashId hashId)
	    throws DirectoryStoreException;

    /**
     * This method checks whether a directory is already present or not.
     * 
     * @param hashId
     * @return
     */
    public boolean isAvailable(HashId hashId);
}
