package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import java.io.InputStream;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;

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
	 * @param maxFileSize
	 *            is the maximum size of the file to be allowed to be stored. If
	 *            the file is not stored, a <code>null</code> {@link HashId} is
	 *            returned.
	 * @return A {@link HashId} is returned which can be used for later access
	 *         to the file. If the files was not stored and there was no error
	 *         (size filtering), <code>null</code> is returned.
	 * @throws FileStoreException
	 *             is thrown in cases of issues.
	 */
	FileInformation storeRawFile(InputStream rawStream) throws FileStoreException;

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
	 * This method loads all analyzes of a file.
	 * 
	 * @param hashId
	 * @return
	 * @throws FileStoreException
	 */
	public List<CodeAnalysis> loadAnalyses(HashId hashId) throws FileStoreException;

	/**
	 * This method stores a single analysis for a file.
	 * 
	 * @param analysis
	 * @throws FileStoreException
	 */
	void storeAnalysis(CodeAnalysis analysis) throws FileStoreException;

	/**
	 * Checks whether a code with a given id was analyzed or not.
	 * 
	 * @param hashId
	 * @return
	 */
	public boolean wasAnalyzed(HashId hashId) throws FileStoreException;
}
