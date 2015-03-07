package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericProjectMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

/**
 * This is an abstract implementation of an evaluator store.
 * 
 * @author Rick-Rainer Ludwig
 */
@Stateless
public class EvaluatorStoreServiceBean implements EvaluatorStoreService,
	EvaluatorStoreServiceRemote {

    @Inject
    private Logger logger;

    @Inject
    @EvaluationStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements cassandraPreparedStatements;

    @Inject
    private EvaluatorServiceManager evaluatorPluginService;

    @Override
    public boolean hasFileResults(HashId hashId, CodeRangeType codeRangeType,
	    String codeRangeName, String evaluatorId, String parameterName)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT hashid FROM "
				+ CassandraElementNames.EVALUATION_FILE_METRICS_TABLE
				+ " WHERE hashid=? AND code_range_type=? AND code_range_name=? AND evaluator_id=? AND parameter_name=?");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), codeRangeType.name(), codeRangeName,
		evaluatorId, parameterName);
	ResultSet result = session.execute(boundStatement);
	if (result.one() == null) {
	    return false;
	}
	if (result.one() != null) {
	    throw new RuntimeException("More than result found for hashId="
		    + hashId.toString() + ", codeRangeType="
		    + codeRangeType.name() + ", codeRangeName='"
		    + codeRangeName + ", evaluatorId=" + evaluatorId
		    + " and parameterName=" + parameterName + ".");
	}
	return true;
    }

    @Override
    public boolean hasFileResults(HashId hashId, String evaluatorId)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "SELECT hashid FROM "
			+ CassandraElementNames.EVALUATION_FILE_METRICS_TABLE
			+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), evaluatorId);
	ResultSet result = session.execute(boundStatement);
	return result.one() != null;
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId, String evaluatorId,
	    String parameterName) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT hashid FROM "
				+ CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
				+ " WHERE hashid=? AND evaluator_id=? AND parameter_name=?");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), evaluatorId, parameterName);
	ResultSet result = session.execute(boundStatement);
	if (result.one() == null) {
	    return false;
	}
	if (result.one() != null) {
	    throw new RuntimeException("More than result found for hashId="
		    + hashId.toString() + ", evaluatorId=" + evaluatorId
		    + " and parameterName=" + parameterName + ".");
	}
	return true;
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId, String evaluatorId)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT hashid FROM "
				+ CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
				+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), evaluatorId);
	ResultSet result = session.execute(boundStatement);
	return result.one() != null;
    }

    @Override
    public boolean hasProjectResults(UUID projectUUID, UUID runUUID,
	    String evaluatorId, String parameterName)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT hashid FROM "
				+ CassandraElementNames.EVALUATION_PROJECT_METRICS_TABLE
				+ " WHERE project_id=? AND run_id=? AND evaluator_id=? AND parameter_name=?");
	BoundStatement boundStatement = preparedStatement.bind(projectUUID,
		runUUID, evaluatorId, parameterName);
	ResultSet result = session.execute(boundStatement);
	if (result.one() == null) {
	    return false;
	}
	if (result.one() != null) {
	    throw new RuntimeException(
		    "More than result found for projectUUID=" + projectUUID
			    + ", runUUID=" + runUUID + ", evaluatorId="
			    + evaluatorId + " and parameterName="
			    + parameterName + ".");
	}
	return true;
    }

    @Override
    public boolean hasProjectResults(UUID projectUUID, UUID runUUID,
	    String evaluatorId) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT hashid FROM "
				+ CassandraElementNames.EVALUATION_PROJECT_METRICS_TABLE
				+ " WHERE project_id=? AND run_id=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(projectUUID,
		runUUID, evaluatorId);
	ResultSet result = session.execute(boundStatement);
	return result.one() != null;
    }

    @Override
    public final void storeFileResults(AnalysisRun analysisRun,
	    CodeAnalysis codeAnalysis, GenericFileMetrics metrics)
	    throws EvaluationStoreException {
	storeFileMetricsAsValues(analysisRun, codeAnalysis, metrics);
	storeMetricsInBigTable(analysisRun, codeAnalysis, metrics);
    }

    private void storeFileMetricsAsValues(AnalysisRun analysisRun,
	    CodeAnalysis codeAnalysis, GenericFileMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "INSERT INTO "
			+ CassandraElementNames.EVALUATION_FILE_METRICS_TABLE
			+ " (time, " + "hashid, " + "source_code_location, "
			+ "code_range_type, " + "code_range_name, "
			+ "evaluator_id, " + "parameter_name, "
			+ "value) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	AnalysisInformation analysisInformation = codeAnalysis
		.getAnalysisInformation();
	HashId hashId = analysisInformation.getHashId();
	SourceCodeLocation sourceCodeLocation = analysisRun
		.findTreeNode(hashId).getSourceCodeLocation();
	String evaluatorId = metrics.getEvaluatorId();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter
		.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter
		.getInstance();
	String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	for (GenericCodeRangeMetrics metric : metrics.getValues()) {
	    String codeRangeName = metric.getCodeRangeName();
	    CodeRangeType codeRangeType = metric.getCodeRangeType();

	    for (MetricParameter<?> parameter : metrics.getParameters()) {
		String parameterName = parameter.getName();
		if (parameterName.equals(codeRangeNameParameterName)
			|| parameterName.equals(codeRangeTypeParameterName)) {
		    continue;
		}
		MetricValue<? extends Number> value = metric
			.getValue(parameter);
		if (value == null) {
		    // There is not value assigned for the parameter. So we can
		    // safely skip it.
		    continue;
		}
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement
			.bind(time, hashId.toString(),
				PropertiesUtils.toString(sourceCodeLocation
					.getSerialization()), codeRangeType
					.name(), codeRangeName, evaluatorId,
				parameterName, numericValue);
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public void storeMetricsInBigTable(AnalysisRun analysisRun,
	    CodeAnalysis codeAnalysis, GenericFileMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO "
				+ CassandraElementNames.EVALUATION_METRICS_TABLE
				+ " (time,"
				+ "project_id, "
				+ "run_id, "
				+ "hashid, "
				+ "internal_directory, "
				+ "file_name, "
				+ "source_code_location, "
				+ "language_name, "
				+ "language_version, "
				+ "evaluator_name, "
				+ "code_range_name, "
				+ "code_range_type, "
				+ "parameter_name, "
				+ "parameter_unit, "
				+ "parameter_type, "
				+ "value, "
				+ "level_of_measurement, "
				+ "parameter_description ) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	String projectId = analysisRun.getInformation().getProjectId();
	long runId = analysisRun.getInformation().getRunId();
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
	String languageVersion = codeAnalysis.getLanguageVersion();
	String evaluatorId = metrics.getEvaluatorId();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter
		.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter
		.getInstance();
	String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	for (GenericCodeRangeMetrics metric : metrics.getValues()) {
	    String codeRangeName = metric.getCodeRangeName();
	    CodeRangeType codeRangeType = metric.getCodeRangeType();

	    for (MetricParameter<?> parameter : metrics.getParameters()) {
		String parameterName = parameter.getName();
		if (parameterName.equals(codeRangeNameParameterName)
			|| parameterName.equals(codeRangeTypeParameterName)) {
		    continue;
		}
		String parameterUnit = parameter.getUnit();
		String parameterType = parameter.getType().getName();
		MetricValue<? extends Number> value = metric
			.getValue(parameter);
		if (value == null) {
		    // There is not value assigned for the parameter. So we can
		    // safely skip it.
		    continue;
		}
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time,
			projectId, runId, hashId.toString(), internalPath,
			fileName, sourceCodeLocation, languageName,
			languageVersion, evaluatorId, codeRangeName,
			codeRangeType.name(), parameterName, parameterUnit,
			parameterType, numericValue, parameter
				.getLevelOfMeasurement().name(), parameter
				.getDescription());
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public final void storeDirectoryResults(AnalysisRun analysisRun,
	    AnalysisFileTree directory, GenericDirectoryMetrics metrics)
	    throws EvaluationStoreException {
	storeDirectoryMetricsAsValues(analysisRun, directory, metrics);
	storeMetricsInBigTable(analysisRun, directory, metrics);
    }

    private void storeDirectoryMetricsAsValues(AnalysisRun analysisRun,
	    AnalysisFileTree analysisFileTree, GenericDirectoryMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO "
				+ CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
				+ " (time, " + "hashid, " + "evaluator_id, "
				+ "parameter_name, " + "parameter_unit, "
				+ "value) VALUES " + "(?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	HashId hashId = analysisFileTree.getHashId();
	String evaluatorId = metrics.getEvaluatorId();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter
		.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter
		.getInstance();
	String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	Map<String, MetricValue<?>> values = metrics.getValues();
	for (Parameter<?> parameter : metrics.getParameters()) {
	    String parameterName = parameter.getName();
	    MetricValue<?> value = values.get(parameter.getName());
	    if (value != null) {
		if (parameterName.equals(codeRangeNameParameterName)
			|| parameterName.equals(codeRangeTypeParameterName)) {
		    continue;
		}
		String parameterUnit = parameter.getUnit();
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time,
			hashId.toString(), evaluatorId, parameterName,
			parameterUnit, numericValue);
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public void storeMetricsInBigTable(AnalysisRun analysisRun,
	    AnalysisFileTree directory, GenericDirectoryMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "INSERT INTO "
			+ CassandraElementNames.EVALUATION_METRICS_TABLE
			+ " (time, " + "project_id, " + "run_id, " + "hashid, "
			+ "internal_directory, " + "file_name, "
			+ "evaluator_name, " + "parameter_name, "
			+ "parameter_unit, " + "parameter_type, " + "value, "
			+ "level_of_measurement, "
			+ "parameter_description) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	String projectId = analysisRun.getInformation().getProjectId();
	long runId = analysisRun.getInformation().getRunId();
	File pathFile = directory.getPathFile(false);
	String internalPath = pathFile.getParent();
	if (internalPath == null) {
	    internalPath = "";
	}
	String fileName = pathFile.getName();
	String evaluatorId = metrics.getEvaluatorId();

	Map<String, MetricValue<?>> values = metrics.getValues();
	for (Parameter<?> parameter : metrics.getParameters()) {
	    String parameterName = parameter.getName();
	    MetricValue<?> value = values.get(parameter);
	    if (value != null) {
		String parameterUnit = parameter.getUnit();
		String parameterType = parameter.getType().getName();
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time,
			projectId, runId, directory.getHashId().toString(),
			internalPath, fileName, evaluatorId, parameterName,
			parameterUnit, parameterType, numericValue, parameter
				.getLevelOfMeasurement().name(), parameter
				.getDescription());
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public final void storeProjectResults(AnalysisRun analysisRun,
	    AnalysisFileTree directory, GenericProjectMetrics metrics)
	    throws EvaluationStoreException {
	storeProjectMetricsAsValues(analysisRun, directory, metrics);
	storeMetricsInBigTable(analysisRun, directory, metrics);
    }

    private void storeProjectMetricsAsValues(AnalysisRun analysisRun,
	    AnalysisFileTree directory, GenericProjectMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO "
				+ CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
				+ " (time, " + "project_id, " + "run_id, "
				+ "evaluator_id, " + "parameter_name, "
				+ "value) VALUES " + "(?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	AnalysisRunInformation information = analysisRun.getInformation();
	String projectId = information.getProjectId();
	long runId = information.getRunId();
	String evaluatorId = metrics.getEvaluatorId();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter
		.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter
		.getInstance();
	String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	Map<String, MetricValue<?>> values = metrics.getValues();
	for (Parameter<?> parameter : metrics.getParameters()) {
	    String parameterName = parameter.getName();
	    MetricValue<?> value = values.get(parameter);
	    if (value != null) {
		if (parameterName.equals(codeRangeNameParameterName)
			|| parameterName.equals(codeRangeTypeParameterName)) {
		    continue;
		}
		String parameterUnit = parameter.getUnit();
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time,
			projectId, runId, evaluatorId, parameterName,
			parameterUnit, numericValue);
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public void storeMetricsInBigTable(AnalysisRun analysisRun,
	    AnalysisFileTree directory, GenericProjectMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "INSERT INTO "
			+ CassandraElementNames.EVALUATION_METRICS_TABLE
			+ " (time, " + "project_id, " + "run_id, " + "hashid, "
			+ "internal_directory, " + "file_name, "
			+ "evaluator_name, " + "parameter_name, "
			+ "parameter_unit, " + "parameter_type, " + "value, "
			+ "level_of_measurement, "
			+ "parameter_description) VALUES "
			+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	String projectId = analysisRun.getInformation().getProjectId();
	long runId = analysisRun.getInformation().getRunId();
	File pathFile = directory.getPathFile(false);
	String internalPath = pathFile.getParent();
	if (internalPath == null) {
	    internalPath = "";
	}
	String fileName = pathFile.getName();
	String evaluatorId = metrics.getEvaluatorId();

	Map<String, MetricValue<?>> values = metrics.getValues();
	for (Parameter<?> parameter : metrics.getParameters()) {
	    String parameterName = parameter.getName();
	    MetricValue<?> value = values.get(parameter);
	    if (value != null) {
		String parameterUnit = parameter.getUnit();
		String parameterType = parameter.getType().getName();
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time,
			projectId, runId, directory.getHashId().toString(),
			internalPath, fileName, evaluatorId, parameterName,
			parameterUnit, parameterType, numericValue, parameter
				.getLevelOfMeasurement().name(), parameter
				.getDescription());
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public final GenericFileMetrics readFileResults(HashId hashId,
	    String evaluatorId) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "SELECT " + "time, "
			+ "code_range_type, " + "code_range_name, "
			+ "parameter_name, " + "value, "
			+ "source_code_location " + "FROM "
			+ CassandraElementNames.EVALUATION_FILE_METRICS_TABLE
			+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	Set<MetricParameter<?>> parameters = evaluatorPluginService
		.getEvaluatorPluginInformation(evaluatorId).getParameters();
	Date time = null;
	SourceCodeLocation sourceCodeLocation = null;
	Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> buffer = new HashMap<>();
	while (!resultSet.isExhausted()) {
	    Row result = resultSet.one();
	    if (time == null) {
		time = result.getDate("time");
	    } else {
		if (!time.equals(result.getDate("time"))) {
		    throw new EvaluationStoreException(
			    "Times are different for evaluatorId="
				    + evaluatorId + " and hashId="
				    + hashId.toString());
		}
	    }
	    SourceCodeLocation alternateSourceCodeLocation = SourceCodeLocationCreator
		    .createFromSerialization(PropertiesUtils.fromString(result
			    .getString("source_code_location")));
	    if (sourceCodeLocation == null) {
		sourceCodeLocation = alternateSourceCodeLocation;
	    } else {
		if (!sourceCodeLocation.equals(alternateSourceCodeLocation)) {
		    throw new EvaluationStoreException(
			    "Source code locations are different for evaluatorId="
				    + evaluatorId + " and hashId="
				    + hashId.toString());
		}
	    }
	    String parameterName = result.getString("parameter_name");
	    Parameter<?> foundParameter = null;
	    for (Parameter<?> parameter : parameters) {
		if (parameter.getName().equals(parameterName)) {
		    foundParameter = parameter;
		}
	    }
	    if (foundParameter == null) {
		logger.warn("No parameter '" + parameterName
			+ "' found for evaluator with id '" + evaluatorId
			+ "'.");
		continue;
	    }
	    CodeRangeType codeRangeType = CodeRangeType.valueOf(result
		    .getString("code_range_type"));
	    String codeRangeName = result.getString("code_range_name");
	    Map<String, Map<Parameter<?>, MetricValue<?>>> codeRangeTypeBuffer;
	    Map<Parameter<?>, MetricValue<?>> parameterBuffer;
	    if (buffer.containsKey(codeRangeType)) {
		codeRangeTypeBuffer = buffer.get(codeRangeType);
		if (codeRangeTypeBuffer.containsKey(codeRangeName)) {
		    parameterBuffer = codeRangeTypeBuffer.get(codeRangeName);
		    if (parameterBuffer.containsKey(foundParameter)) {
			throw new EvaluationStoreException(
				"Multiple parameters with same name '"
					+ parameterName
					+ "' are different for evaluatorId="
					+ evaluatorId + " and hashId="
					+ hashId.toString());
		    }
		} else {
		    parameterBuffer = new HashMap<>();
		    codeRangeTypeBuffer.put(codeRangeName, parameterBuffer);
		}
	    } else {
		codeRangeTypeBuffer = new HashMap<>();
		buffer.put(codeRangeType, codeRangeTypeBuffer);
		parameterBuffer = new HashMap<>();
		codeRangeTypeBuffer.put(codeRangeName, parameterBuffer);
	    }
	    double value = result.getDouble("value");
	    if (foundParameter.getType().equals(Double.class)) {
		parameterBuffer.put(foundParameter, new MetricValue<Double>(
			value, (Parameter<Double>) foundParameter));
	    } else if (foundParameter.getType().equals(Float.class)) {
		parameterBuffer.put(foundParameter, new MetricValue<Float>(
			(float) value, (Parameter<Float>) foundParameter));
	    } else if (foundParameter.getType().equals(Byte.class)) {
		parameterBuffer.put(foundParameter, new MetricValue<Byte>(
			((Double) value).byteValue(),
			(Parameter<Byte>) foundParameter));
	    } else if (foundParameter.getType().equals(Short.class)) {
		parameterBuffer.put(foundParameter, new MetricValue<Short>(
			((Double) value).shortValue(),
			(Parameter<Short>) foundParameter));
	    } else if (foundParameter.getType().equals(Integer.class)) {
		parameterBuffer.put(foundParameter, new MetricValue<Integer>(
			((Double) value).intValue(),
			(Parameter<Integer>) foundParameter));
	    } else if (foundParameter.getType().equals(Long.class)) {
		parameterBuffer.put(foundParameter, new MetricValue<Long>(
			((Double) value).longValue(),
			(Parameter<Long>) foundParameter));
	    } else {
		parameterBuffer.put(foundParameter, new MetricValue<Double>(
			value, (Parameter<Double>) foundParameter));
	    }
	}

	GenericFileMetrics fileMetrics = new GenericFileMetrics(evaluatorId,
		hashId, sourceCodeLocation, time, parameters);
	for (Entry<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> codeRangeTypeEntry : buffer
		.entrySet()) {
	    CodeRangeType codeRangeType = codeRangeTypeEntry.getKey();
	    for (Entry<String, Map<Parameter<?>, MetricValue<?>>> codeRangeNameEntry : codeRangeTypeEntry
		    .getValue().entrySet()) {
		String codeRangeName = codeRangeNameEntry.getKey();
		Map<String, MetricValue<?>> values = new HashMap<>();
		for (Entry<Parameter<?>, MetricValue<?>> parameterEntry : codeRangeNameEntry
			.getValue().entrySet()) {
		    Parameter<?> parameter = parameterEntry.getKey();
		    MetricValue<?> value = parameterEntry.getValue();
		    values.put(parameter.getName(), value);
		}
		fileMetrics.addCodeRangeMetrics(new GenericCodeRangeMetrics(
			sourceCodeLocation, codeRangeType, codeRangeName,
			parameters, values));
	    }
	}
	return fileMetrics;
    }

    @Override
    public final GenericDirectoryMetrics readDirectoryResults(HashId hashId,
	    String evaluatorId) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT "
				+ "time, "
				+ "hashid, "
				+ "evaluator_id, "
				+ "parameter_name, "
				+ "value "
				+ "FROM "
				+ CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
				+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	Set<MetricParameter<?>> parameters = new HashSet<>();
	Date time = null;
	while (resultSet.isExhausted()) {
	    Row result = resultSet.one();

	    if (time == null) {
		time = result.getDate("time");
	    }
	}
	// FIXME: The correct values need to be added!
	Map<String, MetricValue<?>> metrics = new HashMap<>();
	GenericDirectoryMetrics directoryMetrics = new GenericDirectoryMetrics(
		evaluatorId, hashId, time, parameters, metrics);
	return directoryMetrics;
    }

    @Override
    public GenericProjectMetrics readProjectResults(UUID projectUUID,
	    UUID runUUID, String evaluatorId) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(
			session,
			"SELECT "
				+ "time, "
				+ "project_id, "
				+ "run_id, "
				+ "evaluator_id, "
				+ "parameter_name, "
				+ "value "
				+ "FROM "
				+ CassandraElementNames.EVALUATION_PROJECT_METRICS_TABLE
				+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(projectUUID,
		runUUID, evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	Set<MetricParameter<?>> parameters = new HashSet<>();
	Date time = null;
	while (resultSet.isExhausted()) {
	    Row result = resultSet.one();
	    if (time == null) {
		time = result.getDate("time");
	    }
	}
	// GenericMetrics metrics = new
	// GenericMetrics(SourceCodeLocationCreator.createFromSerialization(properties),
	// codeRangeType, codeRangeName, parameters, values)
	Map<String, MetricValue<?>> metrics = null;
	GenericProjectMetrics directoryMetrics = new GenericProjectMetrics(
		evaluatorId, projectUUID, runUUID, time, parameters, metrics);
	return directoryMetrics;
    }
}
