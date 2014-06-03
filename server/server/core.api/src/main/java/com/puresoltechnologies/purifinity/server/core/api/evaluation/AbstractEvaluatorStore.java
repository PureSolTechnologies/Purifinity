package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevelParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQualityParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

/**
 * This is an abstract implementation of an evaluator store.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractEvaluatorStore implements EvaluatorStore {

	@Inject
	@EvaluationStoreKeyspace
	private Session session;

	@Inject
	private CassandraPreparedStatements cassandraPreparedStatements;

	protected abstract Class<? extends MetricFileResults> getFileResultClass();

	protected abstract Class<? extends MetricDirectoryResults> getDirectoryResultClass();

	protected abstract Class<? extends MetricDirectoryResults> getProjectResultClass();

	@Override
	public final boolean hasFileResults(HashId hashId)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT hashid FROM "
						+ CassandraElementNames.EVALUATION_FILES_TABLE
						+ " WHERE hashid=? AND resultsClass=?");
		BoundStatement boundStatement = preparedStatement.bind(
				hashId.toString(), getFileResultClass().getName());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (resultSet.one() != null) {
			throw new EvaluationStoreException("Multiple files for hashid '"
					+ hashId + "' found. Database is inkonsistent!");
		}
		return result != null;
	}

	@Override
	public final boolean hasDirectoryResults(HashId hashId)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT hashid FROM "
						+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
						+ " WHERE hashid=? AND resultsClass=?");
		BoundStatement boundStatement = preparedStatement.bind(
				hashId.toString(), getDirectoryResultClass().getName());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (resultSet.one() != null) {
			throw new EvaluationStoreException("Multiple files for hashid '"
					+ hashId + "' found. Database is inkonsistent!");
		}
		return result != null;
	}

	@Override
	public final boolean hasProjectResults(UUID analysisRunUUID)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT uuid FROM "
						+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
						+ " WHERE uuid=? AND resultsClass=?");
		BoundStatement boundStatement = preparedStatement.bind(analysisRunUUID,
				getProjectResultClass().getName());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (resultSet.one() != null) {
			throw new EvaluationStoreException("Multiple files for run uuid '"
					+ analysisRunUUID + "' found. Database is inkonsistent!");
		}
		return result != null;
	}

	@Override
	public final void storeFileResults(CodeAnalysis codeAnalysis,
			Evaluator evaluator, MetricFileResults results)
			throws EvaluationStoreException {
		HashId hashId = codeAnalysis.getAnalysisInformation().getHashId();
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.EVALUATION_FILES_TABLE
						+ "(hashId, resultsClass, results) VALUES (?, ?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(
						byteArrayOutputStream)) {
			objectOutputStream.writeObject(results);
			BoundStatement boundStatement = preparedStatement.bind(
					hashId.toString(), getFileResultClass().getName());
			boundStatement.setBytes("results",
					ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
			session.execute(boundStatement);
		} catch (IOException e) {
			throw new EvaluationStoreException(
					"Could not store results for hashId '" + hashId
							+ "' into '" + getStoreName() + "'.", e);
		}
		storeMetricsInBigTable(codeAnalysis, evaluator, results);
	}

	@Override
	public void storeMetricsInBigTable(CodeAnalysis codeAnalysis,
			Evaluator evaluator, MetricFileResults results) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.EVALUATION_METRICS_TABLE
								+ " (time,"
								+ " duration, "
								+ "project_uuid, "
								+ "run_uuid, "
								+ "hashid, "
								+ "internal_directory, "
								+ "file_name, "
								+ "source_code_location, "
								+ "language_name, "
								+ "language_version, "
								+ "evaluator_name, "
								+ "code_range_name, "
								+ "code_range_type, "
								+ "quality, "
								+ "quality_level, "
								+ "parameter_name, "
								+ "parameter_unit, "
								+ "numeric, "
								+ "parameter_type, "
								+ "numeric_value, "
								+ "string_value, "
								+ "level_of_measurement, "
								+ "parameter_description ) VALUES "
								+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		Date time = evaluator.getStartTime();
		long duration = evaluator.getDuration();
		AnalysisRun analysisRun = evaluator.getAnalysisRun();
		UUID analysisProjectUUID = analysisRun.getInformation()
				.getProjectUUID();
		UUID analysisRunUUID = analysisRun.getInformation().getRunUUID();
		AnalysisInformation analysisInformation = codeAnalysis
				.getAnalysisInformation();
		HashId hashId = analysisInformation.getHashId();
		AnalysisFileTree analysisTreeNode = analysisRun.findTreeNode(hashId);
		File pathFile = analysisTreeNode.getPathFile(false);
		String internalPath = pathFile.getParent();
		String fileName = pathFile.getName();
		String sourceCodeLocation = analysisTreeNode.getSourceCodeLocation()
				.getHumanReadableLocationString();
		String languageName = codeAnalysis.getLanguageName();
		Version languageVersion = codeAnalysis.getLanguageVersion();
		String evaluatorName = evaluator.getInformation().getName();
		CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter
				.getInstance();
		String codeRangeNameParameterName = codeRangeNameParameter.getName();
		CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter
				.getInstance();
		String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
		for (Map<String, Value<?>> row : results.getValues()) {
			String codeRangeName = (String) row.get(codeRangeNameParameterName)
					.getValue();
			CodeRangeType codeRangeType = (CodeRangeType) row.get(
					codeRangeTypeParameterName).getValue();

			Value<?> sourceCodeQualityValue = row
					.get(SourceCodeQualityParameter.getInstance().getName());
			SourceCodeQuality quality = (SourceCodeQuality) (sourceCodeQualityValue != null ? sourceCodeQualityValue
					.getValue() : SourceCodeQuality.UNSPECIFIED);
			Value<?> qualityLevelValue = row.get(QualityLevelParameter
					.getInstance().getName());
			Double qualityLevelDouble = (qualityLevelValue != null ? ((QualityLevel) qualityLevelValue
					.getValue()).getLevel() : null);
			Float qualityLevel = qualityLevelDouble != null ? qualityLevelDouble
					.floatValue() : null;
			Set<Parameter<?>> parameters = results.getParameters();
			for (Parameter<?> parameter : parameters) {
				String parameterName = parameter.getName();
				if (parameterName.equals(codeRangeNameParameterName)
						|| parameterName.equals(codeRangeTypeParameterName)) {
					continue;
				}
				String parameterUnit = parameter.getUnit();
				boolean numeric = parameter.isNumeric();
				String parameterType = parameter.getType().getName();
				Value<?> value = row.get(parameterName);
				Object valueObject = value.getValue();
				Double numericValue = null;
				String stringValue = null;
				if (numeric) {
					numericValue = ((Number) valueObject).doubleValue();
				} else {
					stringValue = ValueSerializer.toString(value);
				}
				BoundStatement boundStatement = preparedStatement.bind(time,
						duration, analysisProjectUUID, analysisRunUUID,
						hashId.toString(), internalPath, fileName,
						sourceCodeLocation, languageName, languageVersion,
						evaluatorName, codeRangeName, codeRangeType.name(),
						quality.name(), qualityLevel, parameterName,
						parameterUnit, numeric, parameterType, numericValue,
						stringValue, parameter.getLevelOfMeasurement().name(),
						parameter.getDescription());
				session.execute(boundStatement);
			}
		}
	}

	@Override
	public final void storeDirectoryResults(AnalysisFileTree directory,
			Evaluator evaluator, MetricDirectoryResults results)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
						+ "(hashId, resultsClass, results) VALUES (?, ?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(results);
				BoundStatement boundStatement = preparedStatement.bind(
						directory.getHashId().toString(),
						getDirectoryResultClass().getName());
				boundStatement.setBytes("results",
						ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
				session.execute(boundStatement);
			}
		} catch (IOException e) {
			throw new EvaluationStoreException(
					"Could not store results for hashId '"
							+ directory.getHashId() + "' into '"
							+ getStoreName() + "'.", e);
		}

		storeMetricsInBigTable(directory, evaluator, results);
	}

	@Override
	public void storeMetricsInBigTable(AnalysisFileTree directory,
			Evaluator evaluator, MetricDirectoryResults results) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.EVALUATION_METRICS_TABLE
								+ " (time, "
								+ "duration, "
								+ "project_uuid, "
								+ "run_uuid, "
								+ "hashid, "
								+ "internal_directory, "
								+ "file_name, "
								+ "evaluator_name, "
								+ "code_range_name, "
								+ "code_range_type, "
								+ "quality, "
								+ "quality_level, "
								+ "parameter_name, "
								+ "parameter_unit, "
								+ "numeric, "
								+ "parameter_type, "
								+ "numeric_value, "
								+ "string_value, "
								+ "level_of_measurement, "
								+ "parameter_description) VALUES "
								+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		Date time = evaluator.getStartTime();
		long duration = evaluator.getDuration();
		AnalysisRun analysisRun = evaluator.getAnalysisRun();
		UUID analysisProjectUUID = analysisRun.getInformation()
				.getProjectUUID();
		UUID analysisRunUUID = analysisRun.getInformation().getRunUUID();
		File pathFile = directory.getPathFile(false);
		String internalPath = pathFile.getParent();
		if (internalPath == null) {
			internalPath = "";
		}
		String fileName = pathFile.getName();
		String evaluatorName = evaluator.getInformation().getName();

		Map<String, Value<?>> row = results.getValues();
		String codeRangeName = (String) row.get(
				CodeRangeNameParameter.getInstance().getName()).getValue();
		CodeRangeType codeRangeType = (CodeRangeType) row.get(
				CodeRangeTypeParameter.getInstance().getName()).getValue();
		Value<?> sourceCodeQualityValue = row.get(SourceCodeQualityParameter
				.getInstance().getName());
		SourceCodeQuality quality = (SourceCodeQuality) (sourceCodeQualityValue != null ? sourceCodeQualityValue
				.getValue() : SourceCodeQuality.UNSPECIFIED);
		Value<?> qualityLevelValue = row.get(QualityLevelParameter
				.getInstance().getName());
		Double qualityLevelDouble = null;
		if (qualityLevelValue != null) {
			QualityLevel l = (QualityLevel) qualityLevelValue.getValue();
			if (l != null) {
				qualityLevelDouble = l.getLevel();
			}
		}
		Float qualityLevel = qualityLevelDouble != null ? qualityLevelDouble
				.floatValue() : null;
		Set<Parameter<?>> parameters = results.getParameters();
		for (Parameter<?> parameter : parameters) {
			String parameterName = parameter.getName();
			Value<?> value = row.get(parameterName);
			if (value != null) {
				String parameterUnit = parameter.getUnit();
				boolean numeric = parameter.isNumeric();
				String parameterType = parameter.getType().getName();
				Double numericValue = null;
				String stringValue = null;
				if (numeric) {
					Object valueObject = value.getValue();
					numericValue = ((Number) valueObject).doubleValue();
				} else {
					stringValue = ValueSerializer.toString(value);
				}
				BoundStatement boundStatement = preparedStatement.bind(time,
						duration, analysisProjectUUID, analysisRunUUID,
						directory.getHashId().toString(), internalPath,
						fileName, evaluatorName, codeRangeName, codeRangeType
								.name(), quality.name(), qualityLevel,
						parameterName, parameterUnit, numeric, parameterType,
						numericValue, stringValue, parameter
								.getLevelOfMeasurement().name(), parameter
								.getDescription());
				session.execute(boundStatement);
			}
		}
	}

	@Override
	public final void storeProjectResults(UUID analysisRunUUID,
			Evaluator evaluator, AnalysisFileTree directory,
			MetricDirectoryResults results) throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
						+ "(uuid, resultsClass, results) VALUES (?, ?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(results);
				BoundStatement boundStatement = preparedStatement.bind(
						analysisRunUUID, getProjectResultClass().getName());
				boundStatement.setBytes("results",
						ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
				session.execute(boundStatement);
			}
		} catch (IOException e) {
			throw new EvaluationStoreException(
					"Could not store results for analysis run UUID '"
							+ analysisRunUUID + "' into '" + getStoreName()
							+ "'.", e);
		}
		storeMetricsInBigTable(directory, evaluator, results);
	}

	@Override
	public final MetricFileResults readFileResults(HashId hashId)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT results FROM "
						+ CassandraElementNames.EVALUATION_FILES_TABLE
						+ " WHERE hashid=? AND resultsClass=?");
		BoundStatement boundStatement = preparedStatement.bind(
				hashId.toString(), getFileResultClass().getName());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		if (resultSet.one() != null) {
			throw new EvaluationStoreException(
					"Found multiple elements for hashId '" + hashId
							+ "'. Database is inconsistent!");
		}
		ByteBuffer byteBuffer = result.getBytes("results");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream)) {
				MetricFileResults object = (MetricFileResults) objectInputStream
						.readObject();
				return object;
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new EvaluationStoreException(
					"Could not read object with hashId '" + hashId + "'.", e);
		}
	}

	@Override
	public final MetricDirectoryResults readDirectoryResults(HashId hashId)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT results FROM "
						+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
						+ " WHERE hashid=? AND resultsClass=?");
		BoundStatement boundStatement = preparedStatement.bind(
				hashId.toString(), getDirectoryResultClass().getName());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		if (resultSet.one() != null) {
			throw new EvaluationStoreException(
					"Found multiple elements for hashId '" + hashId
							+ "'. Database is inconsistent!");
		}
		ByteBuffer byteBuffer = result.getBytes("results");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream)) {
				MetricDirectoryResults object = (MetricDirectoryResults) objectInputStream
						.readObject();
				return object;
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new EvaluationStoreException(
					"Could not read object with hashId '" + hashId + "'.", e);
		}
	}

	@Override
	public final MetricDirectoryResults readProjectResults(UUID analysisRunUUID)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "SELECT results FROM "
						+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
						+ " WHERE uuid=? AND resultsClass=?");
		BoundStatement boundStatement = preparedStatement.bind(analysisRunUUID,
				getProjectResultClass().getName());
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		if (resultSet.one() != null) {
			throw new EvaluationStoreException(
					"Found multiple elements for analysis run UUID '"
							+ analysisRunUUID + "'. Database is inconsistent!");
		}
		ByteBuffer byteBuffer = result.getBytes("results");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream)) {
				MetricDirectoryResults object = (MetricDirectoryResults) objectInputStream
						.readObject();
				return object;
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new EvaluationStoreException(
					"Could not read object with analysis run UUID '"
							+ analysisRunUUID + "'.", e);
		}
	}
}
