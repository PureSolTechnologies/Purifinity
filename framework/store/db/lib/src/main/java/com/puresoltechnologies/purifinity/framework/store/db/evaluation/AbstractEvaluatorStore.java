package com.puresoltechnologies.purifinity.framework.store.db.evaluation;

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

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
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
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.query.CassandraQuery;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.query.filter.And;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.query.filter.Equals;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.MetricsResultsIterator;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;

/**
 * This is an abstract implementation of an evaluator store.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractEvaluatorStore implements EvaluatorStore {

	protected abstract Class<? extends MetricFileResults> getFileResultClass();

	protected abstract Class<? extends MetricDirectoryResults> getDirectoryResultClass();

	protected abstract Class<? extends MetricDirectoryResults> getProjectResultClass();

	private final Session session;

	public AbstractEvaluatorStore() {
		super();
		session = CassandraConnection.getEvaluationSession();
	}

	@Override
	public final boolean hasFileResults(HashId hashId)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
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
		PreparedStatement preparedStatement = CassandraConnection
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
		PreparedStatement preparedStatement = CassandraConnection
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
	public final void storeFileResults(HashId hashId, Evaluator evaluator,
			CodeAnalysis codeAnalysis, MetricFileResults results)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
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

			preparedStatement = CassandraConnection
					.getPreparedStatement(
							session,
							"INSERT INTO "
									+ CassandraElementNames.EVALUATION_METRICS_TABLE
									+ " (time, duration, "
									+ "analysis_project, "
									+ "analysis_run, "
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
									+ "name, "
									+ "unit, "
									+ "metric, "
									+ "level_of_measurement, "
									+ "description ) VALUES "
									+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			Date time = evaluator.getStartTime();
			long duration = evaluator.getDuration();
			AnalysisRun analysisRun = evaluator.getAnalysisRun();
			UUID analysisProjectUUID = analysisRun.getInformation()
					.getProjectUUID();
			UUID analysisRunUUID = analysisRun.getInformation().getUUID();
			AnalysisFileTree analysisTreeNode = evaluator.getAnalysisRun()
					.findTreeNode(
							codeAnalysis.getAnalysisInformation().getHashId());
			File pathFile = analysisTreeNode.getPathFile(false);
			String internalPath = pathFile.getParent();
			String fileName = pathFile.getName();
			String sourceCodeLocation = analysisTreeNode
					.getSourceCodeLocation().getHumanReadableLocationString();
			String languageName = codeAnalysis.getLanguageName();
			String languageVersion = codeAnalysis.getLanguageVersion();
			String evaluatorName = evaluator.getInformation().getName();
			for (Map<String, Value<?>> row : results.getValues()) {
				String codeRangeName = (String) row.get(
						CodeRangeNameParameter.getInstance().getName())
						.getValue();
				CodeRangeType codeRangeType = (CodeRangeType) row.get(
						CodeRangeTypeParameter.getInstance().getName())
						.getValue();
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
					String name = parameter.getName();
					String unit = parameter.getUnit();
					Object value = row.get(name).getValue();
					if (Number.class.isAssignableFrom(value.getClass())) {
						double val = ((Number) value).doubleValue();
						boundStatement = preparedStatement.bind(time, duration,
								analysisProjectUUID, analysisRunUUID, hashId
										.toString(), internalPath, fileName,
								sourceCodeLocation, languageName,
								languageVersion, evaluatorName, codeRangeName,
								codeRangeType.name(), quality.name(),
								qualityLevel, name, unit, val, parameter
										.getLevelOfMeasurement().name(),
								parameter.getDescription());
						session.execute(boundStatement);
					}
				}
			}

		} catch (IOException e) {
			throw new EvaluationStoreException(
					"Could not store results for hashId '" + hashId
							+ "' into '" + getStoreName() + "'.", e);
		}
	}

	@Override
	public final void storeDirectoryResults(HashId hashId, Evaluator evaluator,
			AnalysisFileTree directory, MetricDirectoryResults results)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
						+ "(hashId, resultsClass, results) VALUES (?, ?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(results);
				BoundStatement boundStatement = preparedStatement.bind(
						hashId.toString(), getDirectoryResultClass().getName());
				boundStatement.setBytes("results",
						ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
				session.execute(boundStatement);
			}
		} catch (IOException e) {
			throw new EvaluationStoreException(
					"Could not store results for hashId '" + hashId
							+ "' into '" + getStoreName() + "'.", e);
		}

		preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.EVALUATION_METRICS_TABLE
								+ " (time, "
								+ "duration, "
								+ "analysis_project, "
								+ "analysis_run, "
								+ "hashid, "
								+ "internal_directory, "
								+ "file_name, "
								+ "evaluator_name, "
								+ "code_range_name, "
								+ "code_range_type, "
								+ "quality, "
								+ "quality_level, "
								+ "name, "
								+ "unit, "
								+ "metric, "
								+ "level_of_measurement, "
								+ "description) VALUES "
								+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		Date time = evaluator.getStartTime();
		long duration = evaluator.getDuration();
		AnalysisRun analysisRun = evaluator.getAnalysisRun();
		UUID analysisProjectUUID = analysisRun.getInformation()
				.getProjectUUID();
		UUID analysisRunUUID = analysisRun.getInformation().getUUID();
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
		Double qualityLevelDouble = (qualityLevelValue != null ? ((QualityLevel) qualityLevelValue
				.getValue()).getLevel() : null);
		Float qualityLevel = qualityLevelDouble != null ? qualityLevelDouble
				.floatValue() : null;
		Set<Parameter<?>> parameters = results.getParameters();
		for (Parameter<?> parameter : parameters) {
			String name = parameter.getName();
			String unit = parameter.getUnit();
			Value<?> valueObject = row.get(name);
			if (valueObject != null) {
				Object value = valueObject.getValue();
				if (Number.class.isAssignableFrom(value.getClass())) {
					double val = ((Number) value).doubleValue();
					BoundStatement boundStatement = preparedStatement.bind(
							time, duration, analysisProjectUUID,
							analysisRunUUID, hashId.toString(), internalPath,
							fileName, evaluatorName, codeRangeName,
							codeRangeType.name(), quality.name(), qualityLevel,
							name, unit, val, parameter.getLevelOfMeasurement()
									.name(), parameter.getDescription());
					session.execute(boundStatement);
				}
			}
		}
	}

	@Override
	public final void storeProjectResults(UUID analysisRunUUID,
			Evaluator evaluator, AnalysisFileTree directory,
			MetricDirectoryResults results) throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
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

		preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.EVALUATION_METRICS_TABLE
								+ " (time, duration, "
								+ "analysis_project, "
								+ "analysis_run, "
								+ "hashid, "
								+ "internal_directory, "
								+ "file_name, "
								+ "evaluator_name, "
								+ "code_range_name, "
								+ "code_range_type, "
								+ "quality, "
								+ "quality_level, "
								+ "name, "
								+ "unit, "
								+ "metric, "
								+ "level_of_measurement, "
								+ "description) VALUES "
								+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

		Date time = evaluator.getStartTime();
		long duration = evaluator.getDuration();
		AnalysisRun analysisRun = evaluator.getAnalysisRun();
		UUID analysisProjectUUID = analysisRun.getInformation()
				.getProjectUUID();
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
		Double qualityLevelDouble = (qualityLevelValue != null ? ((QualityLevel) qualityLevelValue
				.getValue()).getLevel() : null);
		Float qualityLevel = qualityLevelDouble != null ? qualityLevelDouble
				.floatValue() : null;
		Set<Parameter<?>> parameters = results.getParameters();
		for (Parameter<?> parameter : parameters) {
			String name = parameter.getName();
			String unit = parameter.getUnit();
			Value<?> valueObject = row.get(name);
			if (valueObject != null) {
				Object value = valueObject.getValue();
				if (Number.class.isAssignableFrom(value.getClass())) {
					double val = ((Number) value).doubleValue();
					BoundStatement boundStatement = preparedStatement.bind(
							time, duration, analysisProjectUUID,
							analysisRunUUID, directory.getHashId().toString(),
							internalPath, fileName, evaluatorName,
							codeRangeName, codeRangeType.name(),
							quality.name(), qualityLevel, name, unit, val,
							parameter.getLevelOfMeasurement().name(),
							parameter.getDescription());
					session.execute(boundStatement);
				}
			}
		}
	}

	@Override
	public final MetricFileResults readFileResults(HashId hashId)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
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
		PreparedStatement preparedStatement = CassandraConnection
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
		PreparedStatement preparedStatement = CassandraConnection
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

	@Override
	public MetricsResultsIterator readMetrics(UUID analysisProject,
			UUID analysisRun, String evaluatorName, String parameterName) {
		CassandraQuery query = new CassandraQuery(session);
		Equals<UUID> analysisProjectCriterion = new Equals<UUID>(
				"analysis_project", analysisProject);
		Equals<UUID> analysisRunCriterion = new Equals<UUID>("analysis_run",
				analysisRun);
		Equals<String> evaluatorNameCriterion = new Equals<String>(
				"evaluator_name", evaluatorName);
		Equals<String> parameterNameCriterion = new Equals<String>("name",
				parameterName);
		And and = new And(evaluatorNameCriterion, parameterNameCriterion);
		// And and = new And(analysisRunCriterion, parameterNameCriterion,
		// evaluatorNameCriterion, analysisProjectCriterion);
		ResultSet resultSet = query.query("metrics", and, true, "time",
				"duration", "analysis_project", "analysis_run", "hashid",
				"internal_directory", "file_name", "source_code_location",
				"language_name", "language_version", "evaluator_name",
				"code_range_name", "code_range_type", "quality",
				"quality_level", "name", "unit", "metric",
				"level_of_measurement", "description");
		return new MetricsResultsIterator(resultSet);
	}
}
