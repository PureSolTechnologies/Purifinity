package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericProjectMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericRunMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.versioning.Version;

/**
 * This is an abstract implementation of an evaluator store.
 * 
 * @author Rick-Rainer Ludwig
 */
@Stateless
public class EvaluatorStoreServiceBean implements EvaluatorStoreService, EvaluatorStoreServiceRemote {

    @Inject
    private Logger logger;

    @Inject
    @EvaluationStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements cassandraPreparedStatements;

    private MetricParameter<?> extractParameter(Row row) {
	String parameterName = row.getString("parameter_name");
	String parameterUnit = row.getString("parameter_unit");
	String parameterDescription = row.getString("parameter_description");
	LevelOfMeasurement levelOfMeasurement = LevelOfMeasurement.valueOf(row.getString("level_of_measurement"));
	String parameterType = row.getString("parameter_type");
	try {
	    Class<?> type = Class.forName(parameterType);
	    return MetricParameter.create(parameterName, parameterUnit, levelOfMeasurement, parameterDescription, type);
	} catch (ClassNotFoundException e) {
	    logger.error("Could not find type class '" + parameterType + "'.");
	    return null;
	}
    }

    private SourceCodeLocation extractSourceCodeLocation(Row row) {
	String locationString = row.getString("source_code_location");
	if (locationString == null) {
	    return new UnspecifiedSourceCodeLocation();
	}
	Properties properties = PropertiesUtils.fromString(locationString);
	return SourceCodeLocationCreator.createFromSerialization(properties);
    }

    @Override
    public boolean hasFileResults(HashId hashId, CodeRangeType codeRangeType, String codeRangeName, String evaluatorId,
	    String parameterName) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT hashid FROM " + CassandraElementNames.EVALUATION_FILE_METRICS_TABLE
			+ " WHERE hashid=? AND code_range_type=? AND code_range_name=? AND evaluator_id=? AND parameter_name=?");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), codeRangeType.name(), codeRangeName,
		evaluatorId, parameterName);
	ResultSet resultSet = session.execute(boundStatement);
	if (resultSet.one() == null) {
	    return false;
	}
	if (resultSet.one() != null) {
	    throw new RuntimeException("More than result found for hashId=" + hashId.toString() + ", codeRangeType="
		    + codeRangeType.name() + ", codeRangeName='" + codeRangeName + ", evaluatorId=" + evaluatorId
		    + " and parameterName=" + parameterName + ".");
	}
	return true;
    }

    @Override
    public boolean hasFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT hashid FROM " + CassandraElementNames.EVALUATION_FILE_METRICS_TABLE
			+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	return resultSet.one() != null;
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId, String evaluatorId, String parameterName)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT hashid FROM " + CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
			+ " WHERE hashid=? AND evaluator_id=? AND parameter_name=?");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), evaluatorId, parameterName);
	ResultSet resultSet = session.execute(boundStatement);
	if (resultSet.one() == null) {
	    return false;
	}
	if (resultSet.one() != null) {
	    throw new RuntimeException("More than result found for hashId=" + hashId.toString() + ", evaluatorId="
		    + evaluatorId + " and parameterName=" + parameterName + ".");
	}
	return true;
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT hashid FROM " + CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
			+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	return resultSet.one() != null;
    }

    @Override
    public boolean hasProjectResults(String projectId, long runId, String evaluatorId, String parameterName)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT hashid FROM " + CassandraElementNames.EVALUATION_PROJECT_METRICS_TABLE
			+ " WHERE project_id=? AND run_id=? AND evaluator_id=? AND parameter_name=?");
	BoundStatement boundStatement = preparedStatement.bind(projectId, runId, evaluatorId, parameterName);
	ResultSet resultSet = session.execute(boundStatement);
	if (resultSet.one() == null) {
	    return false;
	}
	if (resultSet.one() != null) {
	    throw new RuntimeException("More than result found for projectUUID=" + projectId + ", runUUID=" + runId
		    + ", evaluatorId=" + evaluatorId + " and parameterName=" + parameterName + ".");
	}
	return true;
    }

    @Override
    public boolean hasProjectResults(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT hashid FROM " + CassandraElementNames.EVALUATION_PROJECT_METRICS_TABLE
			+ " WHERE project_id=? AND run_id=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(projectId, runId, evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	return resultSet.one() != null;
    }

    @Override
    public void storeFileResults(AnalysisRun analysisRun, CodeAnalysis codeAnalysis, GenericFileMetrics metrics)
	    throws EvaluationStoreException {
	storeFileMetricsAsValues(analysisRun, codeAnalysis, metrics);
	storeMetricsInBigTable(analysisRun, codeAnalysis, metrics);
    }

    private void storeFileMetricsAsValues(AnalysisRun analysisRun, CodeAnalysis codeAnalysis,
	    GenericFileMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"INSERT INTO " + CassandraElementNames.EVALUATION_FILE_METRICS_TABLE + " (time, " + "hashid, "
			+ "source_code_location, " + "code_range_type, " + "code_range_name, " + "evaluator_id, "
			+ "evaluator_version, " + "parameter_name, " + "parameter_unit, " + "parameter_type, "
			+ "parameter_description, " + "level_of_measurement, " + "value) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	AnalysisInformation analysisInformation = codeAnalysis.getAnalysisInformation();
	HashId hashId = analysisInformation.getHashId();
	SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();
	String evaluatorId = metrics.getEvaluatorId();
	Version evaluatorVersion = metrics.getEvaluatorVersion();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter.getInstance();
	String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	for (GenericCodeRangeMetrics metric : metrics.getCodeRangeMetrics()) {
	    String codeRangeName = metric.getCodeRangeName();
	    CodeRangeType codeRangeType = metric.getCodeRangeType();

	    for (MetricParameter<?> parameter : metrics.getParameters()) {
		String parameterName = parameter.getName();
		String unit = parameter.getUnit();
		Class<?> type = parameter.getType();
		String description = parameter.getDescription();
		LevelOfMeasurement levelOfMeasurement = parameter.getLevelOfMeasurement();
		if (parameterName.equals(codeRangeNameParameterName)
			|| parameterName.equals(codeRangeTypeParameterName)) {
		    continue;
		}
		MetricValue<? extends Number> value = metric.getValue(parameter);
		if (value == null) {
		    // There is not value assigned for the parameter. So we can
		    // safely skip it.
		    continue;
		}
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time, hashId.toString(),
			PropertiesUtils.toString(sourceCodeLocation.getSerialization()), codeRangeType.name(),
			codeRangeName, evaluatorId, evaluatorVersion.toString(), parameterName, unit, type.getName(),
			description, levelOfMeasurement.name(), numericValue);
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public void storeMetricsInBigTable(AnalysisRun analysisRun, CodeAnalysis codeAnalysis, GenericFileMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"INSERT INTO " + CassandraElementNames.EVALUATION_METRICS_TABLE + " (time," + "project_id, "
			+ "run_id, " + "hashid, " + "internal_directory, " + "file_name, " + "source_code_location, "
			+ "language_name, " + "language_version, " + "evaluator_id, " + "evaluator_version, "
			+ "code_range_name, " + "code_range_type, " + "parameter_name, " + "parameter_unit, "
			+ "parameter_type, " + "value, " + "level_of_measurement, " + "parameter_description ) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	String projectId = analysisRun.getInformation().getProjectId();
	long runId = analysisRun.getInformation().getRunId();
	AnalysisInformation analysisInformation = codeAnalysis.getAnalysisInformation();
	HashId hashId = analysisInformation.getHashId();
	AnalysisFileTree analysisTreeNode = analysisRun.findTreeNode(hashId);
	File pathFile = analysisTreeNode.getPathFile(false);
	String internalPath = pathFile.getParent();
	String fileName = pathFile.getName();
	String sourceCodeLocation = PropertiesUtils
		.toString(analysisTreeNode.getSourceCodeLocation().getSerialization());
	String languageName = analysisInformation.getLanguageName();
	String languageVersion = analysisInformation.getLanguageVersion();
	String evaluatorId = metrics.getEvaluatorId();
	Version evaluatorVersion = metrics.getEvaluatorVersion();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter.getInstance();
	String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	for (GenericCodeRangeMetrics metric : metrics.getCodeRangeMetrics()) {
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
		MetricValue<? extends Number> value = metric.getValue(parameter);
		if (value == null) {
		    // There is not value assigned for the parameter. So we can
		    // safely skip it.
		    continue;
		}
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time, projectId, runId, hashId.toString(),
			internalPath, fileName, sourceCodeLocation, languageName, languageVersion, evaluatorId,
			evaluatorVersion.toString(), codeRangeName, codeRangeType.name(), parameterName, parameterUnit,
			parameterType, numericValue, parameter.getLevelOfMeasurement().name(),
			parameter.getDescription());
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryMetrics metrics) throws EvaluationStoreException {
	storeDirectoryMetricsAsValues(analysisRun, directory, metrics);
	storeMetricsInBigTable(analysisRun, directory, metrics);
    }

    private void storeDirectoryMetricsAsValues(AnalysisRun analysisRun, AnalysisFileTree analysisFileTree,
	    GenericDirectoryMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"INSERT INTO " + CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE + " (time, " + "hashid, "
			+ "evaluator_id, " + "evaluator_version, " + "parameter_name, " + "parameter_unit, "
			+ "parameter_type, " + "parameter_description, " + "level_of_measurement, " + "value) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	HashId hashId = analysisFileTree.getHashId();
	String evaluatorId = metrics.getEvaluatorId();
	Version evaluatorVersion = metrics.getEvaluatorVersion();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter.getInstance();
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
		Class<?> parameterType = parameter.getType();
		String parameterDescription = parameter.getDescription();
		LevelOfMeasurement levelOfMeasurement = parameter.getLevelOfMeasurement();
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time, hashId.toString(), evaluatorId,
			evaluatorVersion.toString(), parameterName, parameterUnit, parameterType.getName(),
			parameterDescription, levelOfMeasurement.name(), numericValue);
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public void storeMetricsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"INSERT INTO " + CassandraElementNames.EVALUATION_METRICS_TABLE + " (time, " + "project_id, "
			+ "run_id, " + "hashid, " + "internal_directory, " + "file_name, " + "code_range_type,"
			+ "code_range_name," + "evaluator_id, " + "evaluator_version, " + "parameter_name, "
			+ "parameter_unit, " + "parameter_type, " + "value, " + "level_of_measurement, "
			+ "parameter_description) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

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
	Version evaluatorVersion = metrics.getEvaluatorVersion();

	Map<String, MetricValue<?>> values = metrics.getValues();
	for (Parameter<?> parameter : metrics.getParameters()) {
	    String parameterName = parameter.getName();
	    MetricValue<?> value = values.get(parameter.getName());
	    if (value != null) {
		String parameterUnit = parameter.getUnit();
		String parameterType = parameter.getType().getName();
		double numericValue = value.getValue().doubleValue();
		BoundStatement boundStatement = preparedStatement.bind(time, projectId, runId,
			directory.getHashId().toString(), internalPath, fileName, CodeRangeType.DIRECTORY.name(),
			directory.getName(), evaluatorId, evaluatorVersion.toString(), parameterName, parameterUnit,
			parameterType, numericValue, parameter.getLevelOfMeasurement().name(),
			parameter.getDescription());
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directory, GenericProjectMetrics metrics)
	    throws EvaluationStoreException {
	storeProjectMetricsAsValues(analysisRun, directory, metrics);
	storeMetricsInBigTable(analysisRun, directory, metrics);
    }

    private void storeProjectMetricsAsValues(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericProjectMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"INSERT INTO " + CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE + " (time, " + "project_id, "
			+ "run_id, " + "evaluator_id, " + "evaluator_version, " + "parameter_name, " + "value) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?);");

	Date time = metrics.getTime();
	AnalysisRunInformation information = analysisRun.getInformation();
	String projectId = information.getProjectId();
	long runId = information.getRunId();
	String evaluatorId = metrics.getEvaluatorId();
	Version evaluatorVersion = metrics.getEvaluatorVersion();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter.getInstance();
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
		BoundStatement boundStatement = preparedStatement.bind(time, projectId, runId, evaluatorId,
			evaluatorVersion, parameterName, parameterUnit, numericValue);
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public void storeMetricsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericProjectMetrics metrics) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"INSERT INTO " + CassandraElementNames.EVALUATION_METRICS_TABLE + " (time, " + "project_id, "
			+ "run_id, " + "hashid, " + "internal_directory, " + "file_name, " + "code_range_type"
			+ "evaluator_id, " + "evaluator_version, " + "parameter_name, " + "parameter_unit, "
			+ "parameter_type, " + "value, " + "level_of_measurement, " + "parameter_description) VALUES "
			+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

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
		BoundStatement boundStatement = preparedStatement.bind(time, projectId, runId,
			directory.getHashId().toString(), internalPath, fileName, CodeRangeType.DIRECTORY.name(),
			evaluatorId, parameterName, parameterUnit, parameterType, numericValue,
			parameter.getLevelOfMeasurement().name(), parameter.getDescription());
		session.execute(boundStatement);
	    }
	}
    }

    @Override
    public GenericFileMetrics readFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT " + "time, " + "code_range_type, " + "code_range_name, " + "evaluator_version, "
			+ "parameter_name, " + "parameter_unit, " + "parameter_description, " + "parameter_type, "
			+ "level_of_measurement, " + "value, " + "source_code_location " + "FROM "
			+ CassandraElementNames.EVALUATION_FILE_METRICS_TABLE + " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	Set<MetricParameter<?>> parameters = new HashSet<>();
	Date time = null;
	SourceCodeLocation sourceCodeLocation = null;
	Version evaluatorVersion = null;
	Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> buffer = new HashMap<>();
	while (!resultSet.isExhausted()) {
	    Row result = resultSet.one();
	    if (time == null) {
		time = result.getDate("time");
		// XXX Do we need checks here?
		// } else {
		// if (!time.equals(result.getDate("time"))) {
		// throw new EvaluationStoreException(
		// "Times are different for evaluatorId="
		// + evaluatorId + " and hashId="
		// + hashId.toString());
		// }
	    }
	    SourceCodeLocation alternateSourceCodeLocation = extractSourceCodeLocation(result);
	    if (sourceCodeLocation == null) {
		sourceCodeLocation = alternateSourceCodeLocation;
	    } else {
		if (!sourceCodeLocation.equals(alternateSourceCodeLocation)) {
		    throw new EvaluationStoreException("Source code locations are different for evaluatorId="
			    + evaluatorId + " and hashId=" + hashId.toString());
		}
	    }
	    evaluatorVersion = getEvaluatorVersionAndCheckConsistency(result, hashId, evaluatorId, evaluatorVersion);
	    String parameterName = result.getString("parameter_name");
	    MetricParameter<?> metricsParameter = extractParameter(result);
	    if (metricsParameter == null) {
		continue;
	    }
	    parameters.add(metricsParameter);
	    CodeRangeType codeRangeType = CodeRangeType.valueOf(result.getString("code_range_type"));
	    String codeRangeName = result.getString("code_range_name");
	    Map<Parameter<?>, MetricValue<?>> parameterBuffer;
	    if (buffer.containsKey(codeRangeType)) {
		Map<String, Map<Parameter<?>, MetricValue<?>>> codeRangeTypeBuffer = buffer.get(codeRangeType);
		if (codeRangeTypeBuffer.containsKey(codeRangeName)) {
		    parameterBuffer = codeRangeTypeBuffer.get(codeRangeName);
		    if (parameterBuffer.containsKey(metricsParameter)) {
			throw new EvaluationStoreException("Multiple parameters with same name '" + parameterName
				+ "' are different for evaluatorId=" + evaluatorId + " and hashId="
				+ hashId.toString());
		    }
		} else {
		    parameterBuffer = new HashMap<>();
		    codeRangeTypeBuffer.put(codeRangeName, parameterBuffer);
		}
	    } else {
		Map<String, Map<Parameter<?>, MetricValue<?>>> codeRangeTypeBuffer = new HashMap<>();
		parameterBuffer = new HashMap<>();
		codeRangeTypeBuffer.put(codeRangeName, parameterBuffer);
		buffer.put(codeRangeType, codeRangeTypeBuffer);
	    }
	    double value = result.getDouble("value");
	    MetricValue<?> metricValue = MetricValue.create(metricsParameter, value);
	    parameterBuffer.put(metricsParameter, metricValue);
	}

	GenericFileMetrics fileMetrics = new GenericFileMetrics(evaluatorId, evaluatorVersion, hashId,
		sourceCodeLocation, time, parameters);
	for (Entry<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> codeRangeTypeEntry : buffer
		.entrySet()) {
	    CodeRangeType codeRangeType = codeRangeTypeEntry.getKey();
	    for (Entry<String, Map<Parameter<?>, MetricValue<?>>> codeRangeNameEntry : codeRangeTypeEntry.getValue()
		    .entrySet()) {
		String codeRangeName = codeRangeNameEntry.getKey();
		Map<String, MetricValue<?>> values = new HashMap<>();
		for (Entry<Parameter<?>, MetricValue<?>> parameterEntry : codeRangeNameEntry.getValue().entrySet()) {
		    Parameter<?> parameter = parameterEntry.getKey();
		    MetricValue<?> value = parameterEntry.getValue();
		    values.put(parameter.getName(), value);
		}
		fileMetrics.addCodeRangeMetrics(new GenericCodeRangeMetrics(sourceCodeLocation, codeRangeType,
			codeRangeName, parameters, values));
	    }
	}
	return fileMetrics;
    }

    @Override
    public GenericDirectoryMetrics readDirectoryResults(HashId hashId, String evaluatorId)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT " + "time, " + "hashid, " + "evaluator_version, " + "parameter_name, " + "parameter_unit, "
			+ "parameter_description, " + "parameter_type, " + "level_of_measurement, " + "value " + "FROM "
			+ CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
			+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	Set<MetricParameter<?>> parameters = new HashSet<>();
	Date time = null;
	Version evaluatorVersion = null;
	Map<Parameter<?>, MetricValue<?>> buffer = new HashMap<>();
	while (!resultSet.isExhausted()) {
	    Row result = resultSet.one();
	    if (time == null) {
		time = result.getDate("time");
	    } else {
		if (!time.equals(result.getDate("time"))) {
		    throw new EvaluationStoreException(
			    "Times are different for evaluatorId=" + evaluatorId + " and hashId=" + hashId.toString());
		}
	    }
	    evaluatorVersion = getEvaluatorVersionAndCheckConsistency(result, hashId, evaluatorId, evaluatorVersion);
	    String parameterName = result.getString("parameter_name");
	    MetricParameter<?> metricsParameter = extractParameter(result);
	    if (metricsParameter == null) {
		continue;
	    }
	    parameters.add(metricsParameter);
	    if (buffer.containsKey(metricsParameter)) {
		throw new EvaluationStoreException("Multiple parameters with same name '" + parameterName
			+ "' are different for evaluatorId=" + evaluatorId + " and hashId=" + hashId.toString());
	    }
	    double value = result.getDouble("value");
	    MetricValue<?> metricValue = MetricValue.create(metricsParameter, value);
	    buffer.put(metricsParameter, metricValue);
	}

	Map<String, MetricValue<?>> values = new HashMap<>();
	for (Entry<Parameter<?>, MetricValue<?>> parameterEntry : buffer.entrySet()) {
	    Parameter<?> parameter = parameterEntry.getKey();
	    MetricValue<?> value = parameterEntry.getValue();
	    values.put(parameter.getName(), value);
	}
	GenericDirectoryMetrics directoryMetrics = new GenericDirectoryMetrics(evaluatorId, evaluatorVersion, hashId,
		time, parameters, values);
	return directoryMetrics;
    }

    @Override
    public GenericProjectMetrics readProjectResults(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT " + "time, " + "project_id, " + "run_id, " + "evaluator_version, " + "parameter_name, "
			+ "value " + "FROM " + CassandraElementNames.EVALUATION_PROJECT_METRICS_TABLE
			+ " WHERE hashid=? AND evaluator_id=?");
	BoundStatement boundStatement = preparedStatement.bind(projectId, runId, evaluatorId);
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
	Version evaluatorVersion = new Version(1, 0, 0); // FIXME
	GenericProjectMetrics directoryMetrics = new GenericProjectMetrics(evaluatorId, evaluatorVersion, projectId,
		runId, time, parameters, metrics);
	return directoryMetrics;
    }

    @Override
    public GenericRunMetrics readRunMetrics(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"SELECT " + "time, " + "hashid, " + "evaluator_version, " + "code_range_name, "
			+ "source_code_location, " + "code_range_type, " + "parameter_name, " + "parameter_unit, "
			+ "parameter_type, " + "value, " + "level_of_measurement, " + "parameter_description FROM "
			+ CassandraElementNames.EVALUATION_METRICS_TABLE + " WHERE "
			+ "project_id=? AND run_id=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(projectId, runId, evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);

	Map<HashId, Set<MetricParameter<?>>> parametersBuffer = new HashMap<>();
	Map<HashId, CodeRangeType> hashIdTypes = new HashMap<>();
	Date minTime = null;
	Map<HashId, Date> timeBuffer = new HashMap<HashId, Date>();
	Version evaluatorVersion = null;
	Map<HashId, SourceCodeLocation> sourceCodeLocationBuffer = new HashMap<>();
	Map<HashId, Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>>> buffer = new HashMap<>();
	while (!resultSet.isExhausted()) {
	    Row result = resultSet.one();
	    HashId hashId = HashId.valueOf(result.getString("hashid"));
	    Date time = getTimeAndCheckConsistency(evaluatorId, timeBuffer, result, hashId);
	    minTime = minTime == null ? time : (minTime.getTime() <= time.getTime() ? minTime : time);
	    getSourceCodeLocationAndCheckConsistency(result, hashId, evaluatorId, sourceCodeLocationBuffer);
	    evaluatorVersion = getEvaluatorVersionAndCheckConsistency(result, hashId, evaluatorId, evaluatorVersion);
	    MetricParameter<?> metricsParameter = extractParameter(result);
	    if (metricsParameter == null) {
		continue;
	    }
	    Set<MetricParameter<?>> parameters = parametersBuffer.get(hashId);
	    if (parameters == null) {
		parameters = new HashSet<MetricParameter<?>>();
		parametersBuffer.put(hashId, parameters);
	    }
	    parameters.add(metricsParameter);
	    CodeRangeType codeRangeType = CodeRangeType.valueOf(result.getString("code_range_type"));
	    if (!hashIdTypes.containsKey(hashId)) {
		if (codeRangeType == CodeRangeType.DIRECTORY) {
		    hashIdTypes.put(hashId, CodeRangeType.DIRECTORY);
		} else {
		    hashIdTypes.put(hashId, CodeRangeType.FILE);

		}
	    }
	    String codeRangeName = result.getString("code_range_name");
	    Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> hashIdBuffer = buffer.get(hashId);
	    if (hashIdBuffer == null) {
		hashIdBuffer = new HashMap<>();
		buffer.put(hashId, hashIdBuffer);
	    }
	    Map<Parameter<?>, MetricValue<?>> parameterBuffer;
	    Map<String, Map<Parameter<?>, MetricValue<?>>> codeRangeTypeBuffer = hashIdBuffer.get(codeRangeType);
	    if (codeRangeTypeBuffer != null) {
		parameterBuffer = codeRangeTypeBuffer.get(codeRangeName);
		if (parameterBuffer != null) {
		    if (parameterBuffer.containsKey(metricsParameter)) {
			throw new EvaluationStoreException("Multiple parameters with same name '"
				+ metricsParameter.getName() + "' are different for evaluatorId=" + evaluatorId
				+ " and hashId=" + hashId.toString());
		    }
		} else {
		    parameterBuffer = new HashMap<>();
		    codeRangeTypeBuffer.put(codeRangeName, parameterBuffer);
		}
	    } else {
		codeRangeTypeBuffer = new HashMap<>();
		hashIdBuffer.put(codeRangeType, codeRangeTypeBuffer);
		parameterBuffer = new HashMap<>();
		codeRangeTypeBuffer.put(codeRangeName, parameterBuffer);
	    }
	    double value = result.getDouble("value");
	    MetricValue<?> metricValue = MetricValue.create(metricsParameter, value);
	    parameterBuffer.put(metricsParameter, metricValue);
	}

	Set<MetricParameter<?>> allParameters = new HashSet<>();
	for (Set<MetricParameter<?>> parameters : parametersBuffer.values()) {
	    allParameters.addAll(parameters);
	}
	GenericRunMetrics metrics = new GenericRunMetrics(evaluatorId, evaluatorVersion, minTime, allParameters);
	for (HashId hashId : buffer.keySet()) {
	    Set<MetricParameter<?>> parameters = parametersBuffer.get(hashId);
	    Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> hashIdBuffer = buffer.get(hashId);
	    if (hashIdTypes.get(hashId) == CodeRangeType.FILE) {
		SourceCodeLocation sourceCodeLocation = sourceCodeLocationBuffer.get(hashId);
		GenericFileMetrics fileMetrics = new GenericFileMetrics(evaluatorId, evaluatorVersion, hashId,
			sourceCodeLocation, timeBuffer.get(hashId), parameters);
		for (Entry<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> codeRangeTypeEntry : hashIdBuffer
			.entrySet()) {
		    CodeRangeType codeRangeType = codeRangeTypeEntry.getKey();
		    for (Entry<String, Map<Parameter<?>, MetricValue<?>>> codeRangeNameEntry : codeRangeTypeEntry
			    .getValue().entrySet()) {
			String codeRangeName = codeRangeNameEntry.getKey();
			Map<String, MetricValue<?>> metricValues = new HashMap<>();
			for (Entry<Parameter<?>, MetricValue<?>> parameterEntry : codeRangeNameEntry.getValue()
				.entrySet()) {
			    Parameter<?> parameter = parameterEntry.getKey();
			    MetricValue<?> value = parameterEntry.getValue();
			    metricValues.put(parameter.getName(), value);
			}
			fileMetrics.addCodeRangeMetrics(new GenericCodeRangeMetrics(sourceCodeLocation, codeRangeType,
				codeRangeName, parameters, metricValues));
		    }
		}
		metrics.add(fileMetrics);
	    } else {
		Map<String, MetricValue<?>> metricValues = new HashMap<>();
		Map<String, Map<Parameter<?>, MetricValue<?>>> codeRangeTypeEntry = hashIdBuffer
			.get(CodeRangeType.DIRECTORY);
		for (Entry<String, Map<Parameter<?>, MetricValue<?>>> codeRangeNameEntry : codeRangeTypeEntry
			.entrySet()) {
		    for (Entry<Parameter<?>, MetricValue<?>> parameterEntry : codeRangeNameEntry.getValue()
			    .entrySet()) {
			Parameter<?> parameter = parameterEntry.getKey();
			MetricValue<?> value = parameterEntry.getValue();
			metricValues.put(parameter.getName(), value);
		    }
		}
		GenericDirectoryMetrics directoryMetrics = new GenericDirectoryMetrics(evaluatorId, evaluatorVersion,
			hashId, timeBuffer.get(hashId), parameters, metricValues);
		metrics.add(directoryMetrics);
	    }
	}

	return metrics;
    }

    private Date getTimeAndCheckConsistency(String evaluatorId, Map<HashId, Date> timeBuffer, Row result, HashId hashId)
	    throws EvaluationStoreException {
	// Get time, check consistency and get min time
	Date time = timeBuffer.get(hashId);
	if (time == null) {
	    time = result.getDate("time");
	    timeBuffer.put(hashId, time);
	    // XXX Need to think about times here!
	    // } else {
	    // if (!time.equals(result.getDate("time"))) {
	    // throw new EvaluationStoreException(
	    // "Times are different for evaluatorId=" + evaluatorId
	    // + " and hashId=" + hashId.toString());
	    // }
	}
	return time;
    }

    private void getSourceCodeLocationAndCheckConsistency(Row result, HashId hashId, String evaluatorId,
	    Map<HashId, SourceCodeLocation> sourceCodeLocationBuffer) throws EvaluationStoreException {
	// Get source code location and check for consistency
	SourceCodeLocation alternateSourceCodeLocation = extractSourceCodeLocation(result);
	SourceCodeLocation sourceCodeLocation = sourceCodeLocationBuffer.get(hashId);
	if (sourceCodeLocation == null) {
	    sourceCodeLocation = alternateSourceCodeLocation;
	    sourceCodeLocationBuffer.put(hashId, sourceCodeLocation);
	} else {
	    if (!sourceCodeLocation.equals(alternateSourceCodeLocation)) {
		throw new EvaluationStoreException("Source code locations are different for evaluatorId=" + evaluatorId
			+ " and hashId=" + hashId.toString());
	    }
	}
    }

    private Version getEvaluatorVersionAndCheckConsistency(Row result, HashId hashId, String evaluatorId,
	    Version evaluatorVersion) throws EvaluationStoreException {
	// Get evaluator version and check consistency
	Version alternateEvaluatorVersion = Version.valueOf(result.getString("evaluator_version"));
	if (evaluatorVersion == null) {
	    evaluatorVersion = alternateEvaluatorVersion;
	} else {
	    if (!evaluatorVersion.equals(alternateEvaluatorVersion)) {
		throw new EvaluationStoreException("Evaluator versions are different for evaluatorId=" + evaluatorId
			+ " and hashId=" + hashId.toString());
	    }
	}
	return evaluatorVersion;
    }
}
