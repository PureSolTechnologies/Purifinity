package com.puresol.purifinity.coding.analysis.api;

import java.util.List;

import com.puresol.purifinity.utils.HashId;

/**
 * This is the interface for a directory store. Directories are collections of
 * source codes which represent a meaningful set like libraries.
 * 
 * This is an additional store to the {@link FileStore}. The modules are named
 * with hash codes, too, to get only unique modules. For this module information
 * can be stored for later access.
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
    public boolean createPackage(HashId hashId, List<HashId> files,
	    List<HashId> directories) throws DirectoryStoreException;

    /**
     * This method checks whether a directory is already present or not.
     * 
     * @param hashId
     * @return
     */
    public boolean isAvailable(HashId hashId);

    /**
     * This method returns a list of hashes of the files contained in the
     * module.
     * 
     * @param hashId
     *            is the {@link HashId} of the module.
     * @return A {@link List} of {@link HashId} is returned containing the
     *         relevant file information.
     */
    public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException;

    /**
     * This method returns a list of hashes of the modules contained in the
     * module.
     * 
     * @param hashId
     *            is the {@link HashId} of the directory.
     * @return A {@link List} of {@link HashId} is returned containing the
     *         relevant file information.
     */
    public List<HashId> getDirectories(HashId hashId)
	    throws DirectoryStoreException;
}
