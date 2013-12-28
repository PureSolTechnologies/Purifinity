package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.List;

import com.puresoltechnologies.commons.misc.HashId;

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
	 * This method checks whether a directory is already present or not.
	 * 
	 * @param hashId
	 * @return
	 * @throws DirectoryStoreException
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
