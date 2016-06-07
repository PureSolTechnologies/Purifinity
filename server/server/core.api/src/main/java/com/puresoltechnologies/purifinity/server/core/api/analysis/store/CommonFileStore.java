package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import java.io.InputStream;
import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.versioning.Version;

/**
 * This is the interface for a file store. The filess are stored by id and the
 * content is stored in a generic way. The name of the file is not stored due to
 * the possibility to change the name of the file or the position within the
 * project without changing the analysis and evaluation results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CommonFileStore {

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
     *             is thrown in cases of issues with file store.
     */
    InputStream readRawFile(HashId hashId) throws FileStoreException;

    /**
     * Checks whether a file is available or not.
     * 
     * @param hashId
     *            is the id of the file to be checked for presence.
     * @return <code>true</code> is returned in case the given file is present.
     *         <code>false</code> is returned otherwise.
     */
    public boolean isAvailable(HashId hashId);

    /**
     * This method reads the original source code of the file.
     * 
     * @param hashId
     *            is the id of the file to be read.
     * @return A {@link SourceCode} object is returned containing the original
     *         content of the file.
     * @throws FileStoreException
     *             is thrown in cases of issues with file store.
     */
    public SourceCode readSourceCode(HashId hashId) throws FileStoreException;

    /**
     * This method loads all analyzes of a file.
     * 
     * @param hashId
     *            is the id of the file for which analyzes are to be loaded.
     * @return A {@link List} of {@link CodeAnalysis} is returned containing
     *         analysis information.
     * @throws FileStoreException
     *             is thrown in cases of issues with file store.
     */
    public List<CodeAnalysis> loadAnalyzes(HashId hashId) throws FileStoreException;

    /**
     * Loads a single analysis for a given analyzer.
     * 
     * @param hashId
     *            is the hash id of the file to look a analysis up.
     * @param analyzerId
     *            is the analyzer id for which the analysis is to be found.
     * @param version
     *            is the version of the analyzer.
     * @return A {@link CodeAnalysis} object is returned.
     * @throws FileStoreException
     *             is thrown in case of file store issue.
     */
    public CodeAnalysis loadAnalysis(HashId hashId, String analyzerId, Version version) throws FileStoreException;

    /**
     * This method stores a single analysis for a file.
     * 
     * @param analysis
     *            is the {@link CodeAnalysis} to be stored.
     * @throws FileStoreException
     *             is thrown in cases of issues with file store.
     */
    void storeAnalysis(CodeAnalysis analysis) throws FileStoreException;

    /**
     * Checks whether a code with a given id was analyzed or not.
     * 
     * @param hashId
     *            is the id of the file for which the analysis availability is
     *            to be checked.
     * @return <code>true</code> is returned in case the file was analyzed
     *         already. <code>false</code> is returned otherwise.
     * @throws FileStoreException
     *             is thrown in cases of issues with file store.
     */
    public boolean wasAnalyzed(HashId hashId) throws FileStoreException;
}
