package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.security.DigestInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.hash.HashUtilities;
import com.puresoltechnologies.commons.types.StringUtils;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChange;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChangeEvent;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.database.hadoop.bloob.BloobService;

@Singleton
public class FileStoreServiceBean implements FileStoreService, FileStoreServiceRemote {

    @Inject
    private Logger logger;

    @Inject
    private BloobService bloob;

    @Inject
    @AnalysisStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements cassandraPreparedStatements;

    @Inject
    private PreferencesStore preferencesStore;

    private long maxFileSize = PurifinityConfiguration.MAX_FILE_SIZE.getDefaultValue();

    @PostConstruct
    public void initialize() {
	maxFileSize = preferencesStore.getSystemPreference(PurifinityConfiguration.MAX_FILE_SIZE).getValue();
	logger.info("File store was initialized with max file size of " + maxFileSize + " bytes.");
    }

    @Override
    public void onSystemPreferenceChange(@Observes @SystemPreferenceChange SystemPreferenceChangeEvent event) {
	if (event.getConfigurationParameter().equals(PurifinityConfiguration.MAX_FILE_SIZE)) {
	    maxFileSize = (Long) event.getValue();
	    logger.info("File store was reconfigured with max file size of " + maxFileSize + " bytes.");
	}
    }

    @Override
    @Lock(LockType.WRITE)
    public FileInformation storeRawFile(InputStream rawStream) throws FileStoreException {
	try (DigestInputStream digestInputStream = new DigestInputStream(rawStream,
		AnalysisStoreServiceBean.DEFAULT_HASH)) {
	    File tempFile = File.createTempFile("sourceRawFile", ".tmp");
	    try {
		tempFile.deleteOnExit();
		try (FileOutputStream tempFileOutputStream = new FileOutputStream(tempFile)) {
		    IOUtils.copy(digestInputStream, tempFileOutputStream);
		    if (tempFile.length() > maxFileSize) {
			logger.debug("Temporary file '" + tempFile + "' is larger than " + maxFileSize + " bytes.");
			return null;
		    }
		}
		byte[] hashBytes = digestInputStream.getMessageDigest().digest();
		String hashString = StringUtils.convertByteArrayToString(hashBytes);
		HashId hashId = new HashId(HashUtilities.getDefaultMessageDigestAlgorithm(), hashString);
		FileInformation fileInformation = readFileInformation(hashId);
		if (fileInformation == null) {
		    try (FileInputStream fileInputStream = new FileInputStream(tempFile)) {
			try (ByteArrayOutputStream buffer = new ByteArrayOutputStream((int) tempFile.length())) {
			    IOUtils.copy(fileInputStream, buffer);
			    fileInformation = storeFile(hashId, buffer);
			}
		    }
		}
		return fileInformation;
	    } finally {
		if (!tempFile.delete()) {
		    logger.warn("Could not delete temporary file '" + tempFile + "'.");
		}
	    }
	} catch (IOException e) {
	    throw new FileStoreException("Could not store raw file.", e);
	}
    }

    private FileInformation storeFile(HashId hashId, ByteArrayOutputStream buffer) throws IOException {
	bloob.storeRawFile(hashId, buffer);
	return new FileInformation(hashId, bloob.getFileSize(hashId));
    }

    private FileInformation readFileInformation(HashId hashId) throws FileStoreException, IOException {
	return new FileInformation(hashId, bloob.getFileSize(hashId));
    }

    @Override
    @Lock(LockType.READ)
    public InputStream readRawFile(HashId hashId) throws FileStoreException {
	try {
	    return bloob.readRawFile(hashId);
	} catch (IOException e) {
	    throw new FileStoreException(e);
	}
    }

    @Override
    @Lock(LockType.READ)
    public List<CodeAnalysis> loadAnalyses(HashId hashId) throws FileStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT analysis FROM " + CassandraElementNames.ANALYSIS_ANALYSES_TABLE + " WHERE hashid=?");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString());
	ResultSet resultSet = session.execute(boundStatement);
	Row result = resultSet.one();
	if (result == null) {
	    throw new FileStoreException("Could not load analyses for file with hash '" + hashId + "'");
	}
	List<CodeAnalysis> analyses = new ArrayList<>();
	while (result != null) {
	    ByteBuffer byteBuffer = result.getBytes("analysis");
	    try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array(),
		    byteBuffer.position(), byteBuffer.limit())) {
		try (ObjectInputStream inStream = new ObjectInputStream(byteArrayInputStream)) {
		    Object object = inStream.readObject();
		    CodeAnalysis analysis = (CodeAnalysis) object;
		    if (!hashId.equals(analysis.getAnalysisInformation().getHashId())) {
			/*
			 * This check is necessary, because an issue occurred
			 * during Purifinity 0.3.0 development. If hash IDs do
			 * not match, evaluations could crash.
			 */
			throw new FileStoreException("Could not load analysis for file with hash id '" + hashId
				+ "', because analysis assigned to this hash id contains hash id '"
				+ analysis.getAnalysisInformation().getHashId() + "'!");
		    }
		    analyses.add(analysis);
		}
	    } catch (ClassNotFoundException | IOException e) {
		throw new FileStoreException("Could not load analysis for file with hash id '" + hashId + "'", e);
	    }
	    result = resultSet.one();
	}
	return analyses;
    }

    @Override
    @Lock(LockType.READ)
    public final void storeAnalysis(CodeAnalysis fileAnalysis) throws FileStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session, "INSERT INTO "
		+ CassandraElementNames.ANALYSIS_ANALYSES_TABLE
		+ " (time, hashid, language, language_version, analyzer_id, analyzer_version, analyzer_message, successful, duration, analysis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	AnalysisInformation analysisInformation = fileAnalysis.getAnalysisInformation();
	BoundStatement boundStatement = preparedStatement.bind(analysisInformation.getStartTime(),
		analysisInformation.getHashId().toString(), analysisInformation.getLanguageName(),
		analysisInformation.getLanguageVersion().toString(), analysisInformation.getAnalyzerId(),
		analysisInformation.getAnalyzerVersion().toString(), analysisInformation.getAnalyzerErrorMessage(),
		analysisInformation.isSuccessful(), analysisInformation.getDuration());
	try {
	    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
		try (ObjectOutputStream outStream = new ObjectOutputStream(byteArrayOutputStream)) {
		    outStream.writeObject(fileAnalysis);
		    boundStatement.setBytes("analysis", ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
		}
	    }
	} catch (IOException e) {
	    throw new FileStoreException(
		    "Could not store analysis for file with hash '" + analysisInformation.getHashId() + "'", e);
	}
	session.execute(boundStatement);
    }

    @Override
    @Lock(LockType.READ)
    public final boolean isAvailable(HashId hashId) {
	try {
	    return bloob.isAvailable(hashId);
	} catch (IOException e) {
	    logger.warn("Error during availability check. Returning false.", e);
	    return false;
	}
    }

    @Override
    @Lock(LockType.READ)
    public final SourceCode readSourceCode(HashId hashId) throws FileStoreException {
	try (InputStream inputStream = readRawFile(hashId)) {
	    return SourceCode.read(inputStream, new UnspecifiedSourceCodeLocation());
	} catch (IOException e) {
	    throw new FileStoreException("Could not load file with id '" + hashId.toString() + "'!", e);
	}
    }

    @Override
    @Lock(LockType.READ)
    public final boolean wasAnalyzed(HashId hashId) throws FileStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT successful FROM " + CassandraElementNames.ANALYSIS_ANALYSES_TABLE + " WHERE hashid=?");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString());
	ResultSet resultSet = session.execute(boundStatement);
	Row result = resultSet.one();
	if (result == null) {
	    return false;
	}
	if (resultSet.one() != null) {
	    throw new FileStoreException(
		    "Could not check for successful analysis due to multiple restuls for '" + hashId + "'.");
	}
	return result.getBool(0);
    }
}
