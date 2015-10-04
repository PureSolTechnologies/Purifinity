package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.DigestInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import com.puresoltechnologies.purifinity.server.core.impl.analysis.AnalysisServiceConnection;
import com.puresoltechnologies.purifinity.server.database.hadoop.utils.bloob.BloobService;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;

@Singleton
public class FileStoreServiceBean implements FileStoreService, FileStoreServiceRemote {

    @Inject
    private Logger logger;

    @Inject
    private BloobService bloob;

    @Inject
    @AnalysisServiceConnection
    private Connection connection;

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
		try {
		    FileInformation fileInformation = readFileInformation(hashId);
		    return fileInformation;
		} catch (FileNotFoundException e) {
		    try (FileInputStream fileInputStream = new FileInputStream(tempFile)) {
			return storeFile(hashId, fileInputStream);
		    }
		}
	    } finally {
		if (!tempFile.delete()) {
		    logger.warn("Could not delete temporary file '" + tempFile + "'.");
		}
	    }
	} catch (IOException e) {
	    throw new FileStoreException("Could not store raw file.", e);
	}
    }

    private FileInformation storeFile(HashId hashId, InputStream buffer) throws IOException {
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
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"SELECT analysis FROM " + HBaseElementNames.ANALYSIS_ANALYSES_TABLE + " WHERE hashid=?")) {
	    preparedStatement.setString(1, hashId.toString());
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    throw new FileStoreException("Could not load analyses for file with hash '" + hashId + "'");
		}
		List<CodeAnalysis> analyses = new ArrayList<>();
		do {
		    byte[] bytes = resultSet.getBytes(1);
		    try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
			try (ObjectInputStream inStream = new ObjectInputStream(byteArrayInputStream)) {
			    Object object = inStream.readObject();
			    CodeAnalysis analysis = (CodeAnalysis) object;
			    if (!hashId.equals(analysis.getAnalysisInformation().getHashId())) {
				/*
				 * This check is necessary, because an issue
				 * occurred during Purifinity 0.3.0 development.
				 * If hash IDs do not match, evaluations could
				 * crash.
				 */
				throw new FileStoreException("Could not load analysis for file with hash id '" + hashId
					+ "', because analysis assigned to this hash id contains hash id '"
					+ analysis.getAnalysisInformation().getHashId() + "'!");
			    }
			    analyses.add(analysis);
			}
		    } catch (ClassNotFoundException | IOException e) {
			throw new FileStoreException("Could not load analysis for file with hash id '" + hashId + "'",
				e);
		    }
		} while (resultSet.next());
		return analyses;
	    }
	} catch (SQLException e) {
	    throw new FileStoreException("Could not read analyses.", e);
	}
    }

    @Override
    @Lock(LockType.READ)
    public void storeAnalysis(CodeAnalysis fileAnalysis) throws FileStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("UPSERT INTO "
		+ HBaseElementNames.ANALYSIS_ANALYSES_TABLE
		+ " (time, hashid, language, language_version, analyzer_id, analyzer_version, analyzer_message, successful, duration, analysis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
	    AnalysisInformation analysisInformation = fileAnalysis.getAnalysisInformation();
	    preparedStatement.setTime(1, new Time(analysisInformation.getStartTime().getTime()));
	    preparedStatement.setString(2, analysisInformation.getHashId().toString());
	    preparedStatement.setString(3, analysisInformation.getLanguageName());
	    preparedStatement.setString(4, analysisInformation.getLanguageVersion().toString());
	    preparedStatement.setString(5, analysisInformation.getAnalyzerId());
	    preparedStatement.setString(6, analysisInformation.getAnalyzerVersion().toString());
	    preparedStatement.setString(7, analysisInformation.getAnalyzerErrorMessage());
	    preparedStatement.setBoolean(8, analysisInformation.isSuccessful());
	    preparedStatement.setLong(9, analysisInformation.getDuration());
	    try {
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
		    try (ObjectOutputStream outStream = new ObjectOutputStream(byteArrayOutputStream)) {
			outStream.writeObject(fileAnalysis);
			preparedStatement.setBytes(10, byteArrayOutputStream.toByteArray());
		    }
		}
	    } catch (IOException e) {
		throw new FileStoreException(
			"Could not store analysis for file with hash '" + analysisInformation.getHashId() + "'", e);
	    }
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback analysis storage.", e1);
	    }
	    throw new FileStoreException("Could not store analysis.", e);
	}
    }

    @Override
    @Lock(LockType.READ)
    public boolean isAvailable(HashId hashId) {
	try {
	    return bloob.isAvailable(hashId);
	} catch (IOException e) {
	    logger.warn("Error during availability check. Returning false.", e);
	    return false;
	}
    }

    @Override
    @Lock(LockType.READ)
    public SourceCode readSourceCode(HashId hashId) throws FileStoreException {
	try (InputStream inputStream = readRawFile(hashId)) {
	    return SourceCode.read(inputStream, new UnspecifiedSourceCodeLocation());
	} catch (IOException e) {
	    throw new FileStoreException("Could not load file with id '" + hashId.toString() + "'!", e);
	}
    }

    @Override
    @Lock(LockType.READ)
    public boolean wasAnalyzed(HashId hashId) throws FileStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"SELECT successful FROM " + HBaseElementNames.ANALYSIS_ANALYSES_TABLE + " WHERE hashid=?")) {
	    preparedStatement.setString(1, hashId.toString());
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return false;
		}
		boolean analyzed = resultSet.getBoolean(1);
		if (resultSet.next()) {
		    throw new FileStoreException(
			    "Could not check for successful analysis due to multiple restuls for '" + hashId + "'.");
		}
		return analyzed;
	    }
	} catch (SQLException e) {
	    throw new FileStoreException("Could not read analysis status.", e);
	}
    }
}
