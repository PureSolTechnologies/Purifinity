package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;

/**
 * This is the interface for a directory store. Directories are collections of
 * source codes which represent a meaningful set like libraries.
 * 
 * This is an additional store to the {@link CommonFileStore}. The modules are named
 * with hash codes, too, to get only unique modules. For this module information
 * can be stored for later access.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CommonDirectoryStore {

    /**
     * This method checks whether a directory is already present or not.
     * 
     * @param hashId
     *            is the id of the directory to be checked for presence.
     * @return <code>true</code> is returned in case the given directory is
     *         present. <code>false</code> is returned otherwise.
     * @throws DirectoryStoreException
     *             is thrown in case of issues within directory store.
     */
    public boolean isAvailable(HashId hashId) throws DirectoryStoreException;

    /**
     * This method returns a list of hashes of the files contained in the
     * module.
     * 
     * @param hashId
     *            is the {@link HashId} of the module.
     * @return A {@link List} of {@link HashId} is returned containing the
     *         relevant file information.
     * @throws DirectoryStoreException
     *             is thrown in case of issues within directory store.
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
     * @throws DirectoryStoreException
     *             is thrown in case of issues within directory store.
     */
    public List<HashId> getDirectories(HashId hashId) throws DirectoryStoreException;
}
