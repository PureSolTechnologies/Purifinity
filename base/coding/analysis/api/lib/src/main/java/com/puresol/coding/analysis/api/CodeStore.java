package com.puresol.coding.analysis.api;

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
	 * Stores the file in the store.
	 * 
	 * @param hashId
	 *            is the unique id of the code to be stored.
	 * @param content
	 *            is the content to be stored.
	 * @return True is returned in case of a successful storage. False is
	 *         returned in case of an already stored file. In this case the new
	 *         file is not stored and the already stored file is assumed to be
	 *         equal due to the same hash id.
	 * @throws CodeStoreException
	 */
	boolean storeCode(SourceCode sourceCode) throws CodeStoreException;

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
	public SourceCode loadContent(HashId hashId) throws CodeStoreException;

	/**
	 * Checks whether a code with a given id was analyzed or not.
	 * 
	 * @param hashId
	 * @return
	 */
	public boolean wasAnalyzed(HashId hashId);
}
