package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.security.DigestInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

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
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

@Stateless
public class FileStoreServiceBean implements FileStoreService,
		FileStoreServiceRemote {

	@Inject
	@AnalysisStoreKeyspace
	private Session session;

	@Inject
	private CassandraPreparedStatements cassandraPreparedStatements;

	@Override
	public HashId storeRawFile(InputStream rawStream) throws FileStoreException {
		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
			try (DigestInputStream digestInputStream = new DigestInputStream(
					rawStream, AnalysisStoreServiceBean.DEFAULT_HASH)) {
				IOUtils.copy(digestInputStream, buffer);
				byte[] hashBytes = digestInputStream.getMessageDigest()
						.digest();
				String hashString = StringUtils
						.convertByteArrayToString(hashBytes);
				HashId hashId = new HashId(
						HashUtilities.getDefaultMessageDigestAlgorithm(),
						hashString);

				PreparedStatement preparedStatement = cassandraPreparedStatements
						.getPreparedStatement(
								session,
								"INSERT INTO "
										+ CassandraElementNames.ANALYSIS_FILES_TABLE
										+ " (time, hashid, raw, size) VALUES (?, ?, ?, ?)");
				byte[] array = buffer.toByteArray();
				ByteBuffer byteBuffer = ByteBuffer.wrap(array);
				BoundStatement boundStatement = preparedStatement.bind(
						new Date(), hashId.toString());
				boundStatement.setBytes("raw", byteBuffer);
				boundStatement.setInt("size", buffer.size());
				session.execute(boundStatement);
				return hashId;
			}
		} catch (IOException e) {
			throw new FileStoreException("Could not store raw file.", e);
		}
	}

	@Override
	public InputStream readRawFile(HashId hashId) throws FileStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT raw FROM "
						+ CassandraElementNames.ANALYSIS_FILES_TABLE
						+ " WHERE hashid=?");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			throw new FileStoreException("Could not find file with hash id '"
					+ hashId + "'.");

		}
		if (resultSet.one() != null) {
			throw new FileStoreException("Multiple files for hashid '" + hashId
					+ "' found!");
		}
		ByteBuffer byteBuffer = result.getBytes("raw");
		return new ByteArrayInputStream(byteBuffer.array(),
				byteBuffer.position(), byteBuffer.limit());
	}

	@Override
	public List<CodeAnalysis> loadAnalyses(HashId hashId)
			throws FileStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT analysis FROM "
						+ CassandraElementNames.ANALYSIS_ANALYZES_TABLE
						+ " WHERE hashid=?");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			throw new FileStoreException(
					"Could not load analyses for file with hash '" + hashId
							+ "'");
		}
		List<CodeAnalysis> analyses = new ArrayList<>();
		while (result != null) {
			ByteBuffer byteBuffer = result.getBytes("analysis");
			try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
					byteBuffer.array(), byteBuffer.position(),
					byteBuffer.limit())) {
				try (ObjectInputStream inStream = new ObjectInputStream(
						byteArrayInputStream)) {
					Object object = inStream.readObject();
					analyses.add((CodeAnalysis) object);
				}
			} catch (ClassNotFoundException | IOException e) {
				throw new FileStoreException(
						"Could not load analysis for file with hash '" + hashId
								+ "'", e);
			}
			result = resultSet.one();
		}
		return analyses;
	}

	@Override
	public final void storeAnalysis(HashId hashId, CodeAnalysis fileAnalysis)
			throws FileStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.ANALYSIS_ANALYZES_TABLE
								+ " (time, hashid, language, language_version, plugin_version, analyzer_message, successful, duration, analysis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		AnalysisInformation analysisInformation = fileAnalysis
				.getAnalysisInformation();
		BoundStatement boundStatement = preparedStatement.bind(
				analysisInformation.getStartTime(), hashId.toString(),
				analysisInformation.getLanguageName(), analysisInformation
						.getLanguageVersion().toString(), analysisInformation
						.getPluginVersion().toString(), analysisInformation
						.getAnalyzerErrorMessage(), analysisInformation
						.isSuccessful(), analysisInformation.getDuration());
		try {
			try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
				try (ObjectOutputStream outStream = new ObjectOutputStream(
						byteArrayOutputStream)) {
					outStream.writeObject(fileAnalysis);
					boundStatement.setBytes("analysis", ByteBuffer
							.wrap(byteArrayOutputStream.toByteArray()));
				}
			}
		} catch (IOException e) {
			throw new FileStoreException(
					"Could not store analysis for file with hash '" + hashId
							+ "'", e);
		}
		session.execute(boundStatement);
	}

	@Override
	public final boolean isAvailable(HashId hashId) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT hashid FROM "
						+ CassandraElementNames.ANALYSIS_FILES_TABLE
						+ " WHERE hashid=?");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		ResultSet resultSet = session.execute(boundStatement);
		return !resultSet.isExhausted();
	}

	@Override
	public final SourceCode readSourceCode(HashId hashId)
			throws FileStoreException {
		try (InputStream inputStream = readRawFile(hashId)) {
			return SourceCode.read(inputStream,
					new UnspecifiedSourceCodeLocation());
		} catch (IOException e) {
			throw new FileStoreException("Could not load file with id '"
					+ hashId.toString() + "'!", e);
		}
	}

	@Override
	public final boolean wasAnalyzed(HashId hashId) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT analysis FROM "
						+ CassandraElementNames.ANALYSIS_ANALYZES_TABLE
						+ " WHERE hashid=?");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		return (result != null) && (result.getBytes("analysis") != null);
	}
}
