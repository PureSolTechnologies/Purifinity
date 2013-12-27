package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.security.DigestInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;
import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.impl.source.SourceCodeImpl;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.framework.commons.utils.StringUtils;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;

public final class FileStoreImpl implements FileStore {

	@Override
	public HashId storeRawFile(InputStream rawStream) throws FileStoreException {
		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
			try (DigestInputStream digestInputStream = new DigestInputStream(
					rawStream, HashUtilities.getDefaultMessageDigest())) {
				IOUtils.copy(digestInputStream, buffer);
				byte[] hashBytes = digestInputStream.getMessageDigest()
						.digest();
				String hashString = StringUtils
						.convertByteArrayToString(hashBytes);
				HashId hashId = new HashId(
						HashUtilities.getDefaultMessageDigestAlgorithm(),
						hashString);

				Session session = CassandraConnection.getAnalysisSession();
				PreparedStatement preparedStmt = CassandraConnection
						.getPreparedStatement(
								session,
								"storeRawFile",
								"INSERT INTO "
										+ CassandraConnection.ANALYSIS_FILES_TABLE
										+ " (hashid, raw) VALUES (?,?)");
				byte[] array = buffer.toByteArray();
				ByteBuffer byteBuffer = ByteBuffer.wrap(array);
				BoundStatement boundStatement = preparedStmt.bind(hashId
						.toString());
				boundStatement.setBytes("raw", byteBuffer);
				session.execute(boundStatement);
				return hashId;
			}
		} catch (IOException e) {
			throw new FileStoreException("Could not store raw file.", e);
		}
	}

	@Override
	public InputStream readRawFile(HashId hashId) throws FileStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session.execute("SELECT raw FROM "
				+ CassandraConnection.ANALYSIS_FILES_TABLE + " WHERE hashid='"
				+ hashId.toString() + "'");
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
	public CodeAnalysis loadAnalysis(HashId hashId) throws FileStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session.execute("SELECT analysis FROM "
				+ CassandraConnection.ANALYSIS_FILES_TABLE + " WHERE hashid='"
				+ hashId.toString() + "'");
		Row result = resultSet.one();
		if (result == null) {
			throw new FileStoreException(
					"Could not load analysis for file with hash '" + hashId
							+ "'");
		}
		ByteBuffer byteBuffer = result.getBytes("analysis");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream inStream = new ObjectInputStream(
					byteArrayInputStream)) {
				return (CodeAnalysis) inStream.readObject();
			}
		} catch (ClassNotFoundException | IOException e) {
			throw new FileStoreException(
					"Could not load analysis for file with hash '" + hashId
							+ "'", e);
		}
	}

	@Override
	public final void storeAnalysis(HashId hashId, CodeAnalysis fileAnalysis)
			throws FileStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "storeAnalysis", "INSERT INTO "
						+ CassandraConnection.ANALYSIS_FILES_TABLE
						+ " (hashid, analysis) VALUES (?,?)");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
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
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session.execute("SELECT hash FROM "
				+ CassandraConnection.ANALYSIS_FILES_TABLE + " WHERE hashid='"
				+ hashId.toString() + "'");
		Row result = resultSet.one();
		return result != null;
	}

	@Override
	public final SourceCode readSourceCode(HashId hashId)
			throws FileStoreException {
		try (InputStream inputStream = readRawFile(hashId)) {
			return SourceCodeImpl.read(inputStream,
					new UnspecifiedSourceCodeLocation());
		} catch (IOException e) {
			throw new FileStoreException("Could not load file with id '"
					+ hashId.toString() + "'!", e);
		}
	}

	@Override
	public final boolean wasAnalyzed(HashId hashId) {
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session.execute("SELECT analysis FROM "
				+ CassandraConnection.ANALYSIS_FILES_TABLE + " WHERE hashid='"
				+ hashId.toString() + "'");
		Row result = resultSet.one();
		return result.getBytes("analysis") != null;
	}
}
