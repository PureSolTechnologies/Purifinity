package com.puresol.coding.analysis.api;

import java.util.List;

import com.puresol.utils.HashId;

/**
 * This is the interface for a module store. Modules are collections of source
 * codes which represent a meaningful set like libraries. These sets may be
 * stored in directories in file system.
 * 
 * This is an additional store to the {@link CodeStore}. The modules are named
 * with hash codes, too, to get only unique modules. For this module information
 * can be stored for later access.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ModuleStore {

    /**
     * Stores the file in the store.
     * 
     * @param analyzedFile
     * @return True is returned in case of a successful storage. False is
     *         returned in case of an already stored file. In this case the new
     *         file is not stored and the already stored file is assumed to be
     *         equal due to the same hash id.
     * @throws ModuleStoreException
     */
    public boolean createPackage(HashId hashId, List<HashId> files,
	    List<HashId> directories) throws ModuleStoreException;

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
    public List<HashId> getFiles(HashId hashId) throws ModuleStoreException;

    /**
     * This method returns a list of hashes of the modules contained in the
     * module.
     * 
     * @param hashId
     *            is the {@link HashId} of the module.
     * @return A {@link List} of {@link HashId} is returned containing the
     *         relevant file information.
     */
    public List<HashId> getDirectories(HashId hashId)
	    throws ModuleStoreException;
}
