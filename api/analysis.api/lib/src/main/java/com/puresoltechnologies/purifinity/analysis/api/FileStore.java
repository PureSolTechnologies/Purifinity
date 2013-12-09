package com.puresoltechnologies.purifinity.analysis.api;

import java.io.InputStream;

import com.puresoltechnologies.commons.HashId;
import com.puresoltechnologies.parsers.api.source.SourceCode;

/**
 * This is the interface for a file store. The filess are stored by id and the
 * content is stored in a generic way. The name of the file is not stored due to
 * the possibility to change the name of the file or the position within the
 * project without changing the analysis and evaluation results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface FileStore {

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
	 * @throws FileStoreException
	 *             is thrown in cases of issues.
	 */
	HashId storeRawFile(InputStream rawStream) throws FileStoreException;

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
	 * @throws FileStoreException
	 *             is thrown in cases of issues.
	 */
	InputStream readRawFile(HashId hashId) throws FileStoreException;

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
	public SourceCode readSourceCode(HashId hashId) throws FileStoreException;

	/**
	 * This method loads a single analysis.
	 * 
	 * @param hashId
	 * @return
	 * @throws FileStoreException
	 */
	public CodeAnalysis loadAnalysis(HashId hashId) throws FileStoreException;

	/**
	 * This method stores a single analysis for a file.
	 * 
	 * @param hashId
	 * @param analysis
	 * @throws FileStoreException
	 */
	void storeAnalysis(HashId hashId, CodeAnalysis analysis)
			throws FileStoreException;

	/**
	 * Checks whether a code with a given id was analyzed or not.
	 * 
	 * @param hashId
	 * @return
	 */
	public boolean wasAnalyzed(HashId hashId);
}
