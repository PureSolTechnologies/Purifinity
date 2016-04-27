package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.EvaluatorMetricsStoreService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.EvaluatorMetricsStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.EvaluatorStoreConnection;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;
import com.puresoltechnologies.versioning.Version;

/**
 * This is an abstract implementation of an evaluator store.
 * 
 * @author Rick-Rainer Ludwig
 */
@Stateless
public class EvaluatorMetricsStoreServiceBean
	implements EvaluatorMetricsStoreService, EvaluatorMetricsStoreServiceRemote {

    @Inject
    private Logger logger;

    @Inject
    @EvaluatorStoreConnection
    private Connection connection;

    private MetricParameter<?> extractParameter(ResultSet resultSet) throws SQLException {
	String parameterName = resultSet.getString("parameter_name");
	String parameterUnit = resultSet.getString("parameter_unit");
	String parameterDescription = resultSet.getString("parameter_description");
	LevelOfMeasurement levelOfMeasurement = LevelOfMeasurement.valueOf(resultSet.getString("level_of_measurement"));
	String parameterType = resultSet.getString("parameter_type");
	try {
	    Class<?> type = Class.forName(parameterType);
	    return MetricParameter.create(parameterName, parameterUnit == null ? "" : parameterUnit, levelOfMeasurement,
		    parameterDescription, type);
	} catch (ClassNotFoundException e) {
	    logger.error("Could not find type class '" + parameterType + "'.");
	    return null;
	}
    }

    private SourceCodeLocation extractSourceCodeLocation(ResultSet resultSet) throws SQLException {
	String locationString = resultSet.getString("source_code_location");
	if (locationString == null) {
	    return new UnspecifiedSourceCodeLocation();
	}
	Properties properties = PropertiesUtils.fromString(locationString);
	return SourceCodeLocationCreator.createFromSerialization(properties);
    }

    @Override
    public boolean hasFileResults(HashId hashId, CodeRangeType codeRangeType, String codeRangeName, String evaluatorId,
	    String parameterName) throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT hashid FROM "
		+ HBaseElementNames.EVALUATION_FILE_METRICS_TABLE
		+ " WHERE hashid=? AND code_range_type=? AND code_range_name=? AND evaluator_id=? AND parameter_name=?")) {
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, codeRangeType.name());
	    preparedStatement.setString(3, codeRangeName);
	    preparedStatement.setString(4, evaluatorId);
	    preparedStatement.setString(5, parameterName);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return false;
		}
		if (resultSet.next()) {
		    throw new RuntimeException("More than result found for hashId=" + hashId.toString()
			    + ", codeRangeType=" + codeRangeType.name() + ", codeRangeName='" + codeRangeName
			    + ", evaluatorId=" + evaluatorId + " and parameterName=" + parameterName + ".");
		}
		return true;
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for file results.", e);
	}
    }

    @Override
    public boolean hasFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT hashid FROM "
		+ HBaseElementNames.EVALUATION_FILE_METRICS_TABLE + " WHERE hashid=? AND evaluator_id=?")) {
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, evaluatorId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		return resultSet.next();
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for file results.", e);
	}
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId, String evaluatorId, String parameterName)
	    throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT hashid FROM " + HBaseElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
			+ " WHERE hashid=? AND evaluator_id=? AND parameter_name=?")) {
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, evaluatorId);
	    preparedStatement.setString(3, parameterName);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return false;
		}
		if (resultSet.next()) {
		    throw new RuntimeException("More than result found for hashId=" + hashId.toString()
			    + ", evaluatorId=" + evaluatorId + " and parameterName=" + parameterName + ".");
		}
		return true;
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for directory results.", e);
	}
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT hashid FROM "
		+ HBaseElementNames.EVALUATION_DIRECTORY_METRICS_TABLE + " WHERE hashid=? AND evaluator_id=?")) {
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, evaluatorId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		return resultSet.next();
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for directory results.", e);
	}
    }

    @Override
    public boolean hasProjectResults(String projectId, long runId, String evaluatorId, String parameterName)
	    throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT hashid FROM " + HBaseElementNames.EVALUATION_PROJECT_METRICS_TABLE
			+ " WHERE project_id=? AND run_id=? AND evaluator_id=? AND parameter_name=?")) {
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.setString(3, evaluatorId);
	    preparedStatement.setString(4, parameterName);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return false;
		}
		if (resultSet.next()) {
		    throw new RuntimeException("More than result found for projectUUID=" + projectId + ", runUUID="
			    + runId + ", evaluatorId=" + evaluatorId + " and parameterName=" + parameterName + ".");
		}
		return true;
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for project results.", e);
	}
    }

    @Override
    public boolean hasProjectResults(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT hashid FROM " + HBaseElementNames.EVALUATION_PROJECT_METRICS_TABLE
			+ " WHERE project_id=? AND run_id=? AND evaluator_id=?")) {
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.setString(3, evaluatorId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		return resultSet.next();
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for project results.", e);
	}
    }

    @Override
    public void storeFileResults(AnalysisRun analysisRun, CodeAnalysis codeAnalysis, GenericFileMetrics metrics)
	    throws EvaluationStoreException {
	try {
	    storeFileMetricsAsValues(analysisRun, codeAnalysis, metrics);
	    storeFileMetricsInBigTable(analysisRun, codeAnalysis, metrics);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback file result storage.", e1);
	    }
	    throw new EvaluationStoreException("Could not store file results.", e);
	}
    }

    private void storeFileMetricsAsValues(AnalysisRun analysisRun, CodeAnalysis codeAnalysis,
	    GenericFileMetrics metrics) throws SQLException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("UPSERT INTO "
		+ HBaseElementNames.EVALUATION_FILE_METRICS_TABLE + " (time, " + "hashid, " + "source_code_location, "
		+ "code_range_type, " + "code_range_name, " + "evaluator_id, " + "evaluator_version, "
		+ "parameter_name, " + "parameter_unit, " + "parameter_type, " + "parameter_description, "
		+ "level_of_measurement, " + "metric) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
			// There is not value assigned for the parameter. So we
			// can
			// safely skip it.
			continue;
		    }
		    double numericValue = value.getValue().doubleValue();
		    preparedStatement.setTime(1, new Time(time.getTime()));
		    preparedStatement.setString(2, hashId.toString());
		    preparedStatement.setString(3, PropertiesUtils.toString(sourceCodeLocation.getSerialization()));
		    preparedStatement.setString(4, codeRangeType.name());
		    preparedStatement.setString(5, codeRangeName);
		    preparedStatement.setString(6, evaluatorId);
		    preparedStatement.setString(7, evaluatorVersion.toString());
		    preparedStatement.setString(8, parameterName);
		    preparedStatement.setString(9, unit);
		    preparedStatement.setString(10, type.getName());
		    preparedStatement.setString(11, description);
		    preparedStatement.setString(12, levelOfMeasurement.name());
		    preparedStatement.setDouble(13, numericValue);
		    preparedStatement.execute();
		}
	    }
	}
    }

    @Override
    public void storeFileMetricsInBigTable(AnalysisRun analysisRun, CodeAnalysis codeAnalysis,
	    GenericFileMetrics metrics) throws EvaluationStoreException {
	try {
	    storeMetricsInBigTableWithoutCommit(analysisRun, codeAnalysis, metrics);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback metrics storage.", e1);
	    }
	    throw new EvaluationStoreException("Could not store metrics in big table.", e);
	}
    }

    private void storeMetricsInBigTableWithoutCommit(AnalysisRun analysisRun, CodeAnalysis codeAnalysis,
	    GenericFileMetrics metrics) throws SQLException {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"UPSERT INTO " + HBaseElementNames.EVALUATION_METRICS_TABLE + " (time," + "project_id, " + "run_id, "
			+ "hashid, " + "internal_directory, " + "file_name, " + "source_code_location, "
			+ "language_name, " + "language_version, " + "evaluator_id, " + "evaluator_version, "
			+ "code_range_name, " + "code_range_type, " + "parameter_name, " + "parameter_unit, "
			+ "parameter_type, " + "metric, " + "level_of_measurement, " + "parameter_description ) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
			// There is not value assigned for the parameter. So we
			// can
			// safely skip it.
			continue;
		    }
		    double numericValue = value.getValue().doubleValue();
		    preparedStatement.setTime(1, new Time(time.getTime()));
		    preparedStatement.setString(2, projectId);
		    preparedStatement.setLong(3, runId);
		    preparedStatement.setString(4, hashId.toString());
		    preparedStatement.setString(5, internalPath);
		    preparedStatement.setString(6, fileName);
		    preparedStatement.setString(7, sourceCodeLocation);
		    preparedStatement.setString(8, languageName);
		    preparedStatement.setString(9, languageVersion.toString());
		    preparedStatement.setString(10, evaluatorId);
		    preparedStatement.setString(11, evaluatorVersion.toString());
		    preparedStatement.setString(12, codeRangeName);
		    preparedStatement.setString(13, codeRangeType.name());
		    preparedStatement.setString(14, parameterName);
		    preparedStatement.setString(15, parameterUnit);
		    preparedStatement.setString(16, parameterType);
		    preparedStatement.setDouble(17, numericValue);
		    preparedStatement.setString(18, parameter.getLevelOfMeasurement().name());
		    preparedStatement.setString(19, parameter.getDescription());
		    preparedStatement.execute();
		}
	    }
	}
    }

    @Override
    public void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryMetrics metrics) throws EvaluationStoreException {
	try {
	    storeDirectoryMetricsAsValues(analysisRun, directory, metrics);
	    storeDirectoryMetricsInBigTable(analysisRun, directory, metrics);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback directory result storage.", e1);
	    }
	    throw new EvaluationStoreException("Could not store directory results.", e);
	}
    }

    private void storeDirectoryMetricsAsValues(AnalysisRun analysisRun, AnalysisFileTree analysisFileTree,
	    GenericDirectoryMetrics metrics) throws SQLException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + HBaseElementNames.EVALUATION_DIRECTORY_METRICS_TABLE + " (time, "
			+ "hashid, " + "evaluator_id, " + "evaluator_version, " + "parameter_name, "
			+ "parameter_unit, " + "parameter_type, " + "parameter_description, " + "level_of_measurement, "
			+ "metric) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
		    preparedStatement.setTime(1, new Time(time.getTime()));
		    preparedStatement.setString(2, hashId.toString());
		    preparedStatement.setString(3, evaluatorId);
		    preparedStatement.setString(4, evaluatorVersion.toString());
		    preparedStatement.setString(5, parameterName);
		    preparedStatement.setString(6, parameterUnit);
		    preparedStatement.setString(7, parameterType.getName());
		    preparedStatement.setString(8, parameterDescription);
		    preparedStatement.setString(9, levelOfMeasurement.name());
		    preparedStatement.setDouble(10, numericValue);
		    preparedStatement.execute();
		}
	    }
	}
    }

    @Override
    public void storeDirectoryMetricsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryMetrics metrics) throws EvaluationStoreException {
	try {
	    storeMetricsInBigTableWithoutCommit(analysisRun, directory, metrics);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback metrics storage.", e1);
	    }
	    throw new EvaluationStoreException("Could not store metrics in big table.", e);
	}
    }

    private void storeMetricsInBigTableWithoutCommit(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryMetrics metrics) throws SQLException {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"UPSERT INTO " + HBaseElementNames.EVALUATION_METRICS_TABLE + " (time, " + "project_id, " + "run_id, "
			+ "hashid, " + "internal_directory, " + "file_name, " + "code_range_type," + "code_range_name,"
			+ "evaluator_id, " + "evaluator_version, " + "parameter_name, " + "parameter_unit, "
			+ "parameter_type, " + "metric, " + "level_of_measurement, " + "parameter_description) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
		    preparedStatement.setTime(1, new Time(time.getTime()));
		    preparedStatement.setString(2, projectId);
		    preparedStatement.setLong(3, runId);
		    preparedStatement.setString(4, directory.getHashId().toString());
		    preparedStatement.setString(5, internalPath);
		    preparedStatement.setString(6, fileName);
		    preparedStatement.setString(7, CodeRangeType.DIRECTORY.name());
		    preparedStatement.setString(8, directory.getName());
		    preparedStatement.setString(9, evaluatorId);
		    preparedStatement.setString(10, evaluatorVersion.toString());
		    preparedStatement.setString(11, parameterName);
		    preparedStatement.setString(12, parameterUnit);
		    preparedStatement.setString(13, parameterType);
		    preparedStatement.setDouble(14, numericValue);
		    preparedStatement.setString(15, parameter.getLevelOfMeasurement().name());
		    preparedStatement.setString(16, parameter.getDescription());
		    preparedStatement.execute();
		}
	    }
	}
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directory, GenericProjectMetrics metrics)
	    throws EvaluationStoreException {
	try {
	    storeProjectMetricsAsValues(analysisRun, directory, metrics);
	    storeProjectMetricsInBigTable(analysisRun, directory, metrics);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback project result storage.", e1);
	    }
	    throw new EvaluationStoreException("Could not store directory results.", e);
	}
    }

    private void storeProjectMetricsAsValues(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericProjectMetrics metrics) throws SQLException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + HBaseElementNames.EVALUATION_DIRECTORY_METRICS_TABLE + " (time, "
			+ "project_id, " + "run_id, " + "evaluator_id, " + "evaluator_version, " + "parameter_name, "
			+ "parameter_unit, " + "metric) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)")) {
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
		    preparedStatement.setTime(1, new Time(time.getTime()));
		    preparedStatement.setString(2, projectId);
		    preparedStatement.setLong(3, runId);
		    preparedStatement.setString(4, evaluatorId);
		    preparedStatement.setString(5, evaluatorVersion.toString());
		    preparedStatement.setString(6, parameterName);
		    preparedStatement.setString(7, parameterUnit);
		    preparedStatement.setDouble(8, numericValue);
		    preparedStatement.execute();
		}
	    }
	}
    }

    @Override
    public void storeProjectMetricsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericProjectMetrics metrics) throws EvaluationStoreException {
	try {
	    storeMetricsInBigTableWithoutCommit(analysisRun, directory, metrics);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback metrics storage.", e1);
	    }
	    throw new EvaluationStoreException("Could not store metrics in big table.", e);
	}
    }

    private void storeMetricsInBigTableWithoutCommit(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericProjectMetrics metrics) throws SQLException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("UPSERT INTO "
		+ HBaseElementNames.EVALUATION_METRICS_TABLE + " (time, " + "project_id, " + "run_id, " + "hashid, "
		+ "internal_directory, " + "file_name, " + "code_range_type" + "evaluator_id, " + "evaluator_version, "
		+ "parameter_name, " + "parameter_unit, " + "parameter_type, " + "metric, " + "level_of_measurement, "
		+ "parameter_description) VALUES " + "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
		    preparedStatement.setTime(1, new Time(time.getTime()));
		    preparedStatement.setString(2, projectId);
		    preparedStatement.setLong(3, runId);
		    preparedStatement.setString(4, directory.getHashId().toString());
		    preparedStatement.setString(5, internalPath);
		    preparedStatement.setString(6, fileName);
		    preparedStatement.setString(7, CodeRangeType.DIRECTORY.name());
		    preparedStatement.setString(8, evaluatorId);
		    preparedStatement.setString(9, parameterName);
		    preparedStatement.setString(10, parameterUnit);
		    preparedStatement.setString(11, parameterType);
		    preparedStatement.setDouble(12, numericValue);
		    preparedStatement.setString(13, parameter.getLevelOfMeasurement().name());
		    preparedStatement.setString(14, parameter.getDescription());
		    preparedStatement.execute();
		}
	    }
	}
    }

    @Override
    public GenericFileMetrics readFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT " + "time, " + "code_range_type, " + "code_range_name, "
			+ "evaluator_version, " + "parameter_name, " + "parameter_unit, " + "parameter_description, "
			+ "parameter_type, " + "level_of_measurement, " + "metric, " + "source_code_location " + "FROM "
			+ HBaseElementNames.EVALUATION_FILE_METRICS_TABLE + " WHERE hashid=? AND evaluator_id=?")) {
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, evaluatorId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		Date time = null;
		SourceCodeLocation sourceCodeLocation = null;
		Version evaluatorVersion = null;
		Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> buffer = new HashMap<>();
		MetricParameter<?>[] parameters = new MetricParameter<?>[] {};
		while (resultSet.next()) {
		    if (time == null) {
			time = resultSet.getDate("time");
			// XXX Do we need checks here?
			// } else {
			// if (!time.equals(result.getDate("time"))) {
			// throw new EvaluationStoreException(
			// "Times are different for evaluatorId="
			// + evaluatorId + " and hashId="
			// + hashId.toString());
			// }
		    }
		    SourceCodeLocation alternateSourceCodeLocation = extractSourceCodeLocation(resultSet);
		    if (sourceCodeLocation == null) {
			sourceCodeLocation = alternateSourceCodeLocation;
		    } else {
			if (!sourceCodeLocation.equals(alternateSourceCodeLocation)) {
			    throw new EvaluationStoreException("Source code locations are different for evaluatorId="
				    + evaluatorId + " and hashId=" + hashId.toString());
			}
		    }
		    evaluatorVersion = getEvaluatorVersionAndCheckConsistency(resultSet, hashId, evaluatorId,
			    evaluatorVersion);
		    String parameterName = resultSet.getString("parameter_name");
		    MetricParameter<?> metricsParameter = extractParameter(resultSet);
		    if (metricsParameter == null) {
			continue;
		    }
		    parameters = new MetricParameter<?>[] { metricsParameter };
		    CodeRangeType codeRangeType = CodeRangeType.valueOf(resultSet.getString("code_range_type"));
		    String codeRangeName = resultSet.getString("code_range_name");
		    Map<Parameter<?>, MetricValue<?>> parameterBuffer;
		    if (buffer.containsKey(codeRangeType)) {
			Map<String, Map<Parameter<?>, MetricValue<?>>> codeRangeTypeBuffer = buffer.get(codeRangeType);
			if (codeRangeTypeBuffer.containsKey(codeRangeName)) {
			    parameterBuffer = codeRangeTypeBuffer.get(codeRangeName);
			    if (parameterBuffer.containsKey(metricsParameter)) {
				throw new EvaluationStoreException("Multiple parameters with same name '"
					+ parameterName + "' are different for evaluatorId=" + evaluatorId
					+ " and hashId=" + hashId.toString());
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
		    double value = resultSet.getDouble("metric");
		    MetricValue<?> metricValue = MetricValue.create(metricsParameter, value);
		    parameterBuffer.put(metricsParameter, metricValue);
		}

		GenericFileMetrics fileMetrics = new GenericFileMetrics(evaluatorId, evaluatorVersion, hashId,
			sourceCodeLocation, time, parameters);
		for (Entry<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> codeRangeTypeEntry : buffer
			.entrySet()) {
		    CodeRangeType codeRangeType = codeRangeTypeEntry.getKey();
		    for (Entry<String, Map<Parameter<?>, MetricValue<?>>> codeRangeNameEntry : codeRangeTypeEntry
			    .getValue().entrySet()) {
			String codeRangeName = codeRangeNameEntry.getKey();
			Map<String, MetricValue<?>> values = new HashMap<>();
			for (Entry<Parameter<?>, MetricValue<?>> parameterEntry : codeRangeNameEntry.getValue()
				.entrySet()) {
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
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
	}
    }

    @Override
    public GenericDirectoryMetrics readDirectoryResults(HashId hashId, String evaluatorId)
	    throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "time, " + "hashid, "
		+ "evaluator_version, " + "parameter_name, " + "parameter_unit, " + "parameter_description, "
		+ "parameter_type, " + "level_of_measurement, " + "metric " + "FROM "
		+ HBaseElementNames.EVALUATION_DIRECTORY_METRICS_TABLE + " WHERE hashid=? AND evaluator_id=?")) {
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, evaluatorId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		Date time = null;
		Version evaluatorVersion = null;
		Map<Parameter<?>, MetricValue<?>> buffer = new HashMap<>();
		MetricParameter<?>[] parameters = new MetricParameter<?>[] {};
		while (resultSet.next()) {
		    if (time == null) {
			time = resultSet.getDate("time");
		    } else {
			if (!time.equals(resultSet.getDate("time"))) {
			    throw new EvaluationStoreException("Times are different for evaluatorId=" + evaluatorId
				    + " and hashId=" + hashId.toString());
			}
		    }
		    evaluatorVersion = getEvaluatorVersionAndCheckConsistency(resultSet, hashId, evaluatorId,
			    evaluatorVersion);
		    String parameterName = resultSet.getString("parameter_name");
		    MetricParameter<?> metricsParameter = extractParameter(resultSet);
		    if (metricsParameter == null) {
			continue;
		    }
		    parameters = new MetricParameter<?>[] { metricsParameter };
		    if (buffer.containsKey(metricsParameter)) {
			throw new EvaluationStoreException("Multiple parameters with same name '" + parameterName
				+ "' are different for evaluatorId=" + evaluatorId + " and hashId="
				+ hashId.toString());
		    }
		    double value = resultSet.getDouble("metric");
		    MetricValue<?> metricValue = MetricValue.create(metricsParameter, value);
		    buffer.put(metricsParameter, metricValue);
		}

		Map<String, MetricValue<?>> values = new HashMap<>();
		for (Entry<Parameter<?>, MetricValue<?>> parameterEntry : buffer.entrySet()) {
		    Parameter<?> parameter = parameterEntry.getKey();
		    MetricValue<?> value = parameterEntry.getValue();
		    values.put(parameter.getName(), value);
		}
		GenericDirectoryMetrics directoryMetrics = new GenericDirectoryMetrics(evaluatorId, evaluatorVersion,
			hashId, time, parameters, values);
		return directoryMetrics;
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read directory results.", e);
	}
    }

    /*
     * FIXME
     */
    @Override
    public GenericProjectMetrics readProjectResults(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "time, " + "project_id, "
		+ "run_id, " + "evaluator_version, " + "parameter_name, " + "metric " + "FROM "
		+ HBaseElementNames.EVALUATION_PROJECT_METRICS_TABLE + " WHERE hashid=? AND evaluator_id=?")) {
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.setString(3, evaluatorId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		MetricParameter<?>[] parameters = new MetricParameter<?>[] {};
		Date time = null;
		if (!resultSet.next()) {
		    if (time == null) {
			time = resultSet.getDate("time");
		    }
		}
		// GenericMetrics metrics = new
		// GenericMetrics(SourceCodeLocationCreator.createFromSerialization(properties),
		// codeRangeType, codeRangeName, parameters, values)
		Map<String, MetricValue<?>> metrics = null;
		Version evaluatorVersion = new Version(1, 0, 0); // FIXME
		GenericProjectMetrics directoryMetrics = new GenericProjectMetrics(evaluatorId, evaluatorVersion,
			projectId, runId, time, parameters, metrics);
		return directoryMetrics;
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read project results.", e);
	}
    }

    @Override
    public GenericRunMetrics readRunMetrics(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "time, " + "hashid, "
		+ "evaluator_version, " + "code_range_name, " + "source_code_location, " + "code_range_type, "
		+ "parameter_name, " + "parameter_unit, " + "parameter_type, " + "metric, " + "level_of_measurement, "
		+ "parameter_description FROM " + HBaseElementNames.EVALUATION_METRICS_TABLE + " WHERE "
		+ "project_id=? AND run_id=? AND evaluator_id=?")) {
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.setString(3, evaluatorId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		Map<HashId, Set<MetricParameter<?>>> parametersBuffer = new HashMap<>();
		Map<HashId, CodeRangeType> hashIdTypes = new HashMap<>();
		Date minTime = null;
		Map<HashId, Date> timeBuffer = new HashMap<HashId, Date>();
		Version evaluatorVersion = null;
		Map<HashId, SourceCodeLocation> sourceCodeLocationBuffer = new HashMap<>();
		Map<HashId, Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>>> buffer = new HashMap<>();
		while (resultSet.next()) {
		    HashId hashId = HashId.valueOf(resultSet.getString("hashid"));
		    Date time = getTimeAndCheckConsistency(evaluatorId, timeBuffer, resultSet, hashId);
		    minTime = minTime == null ? time : (minTime.getTime() <= time.getTime() ? minTime : time);
		    getSourceCodeLocationAndCheckConsistency(resultSet, hashId, evaluatorId, sourceCodeLocationBuffer);
		    evaluatorVersion = getEvaluatorVersionAndCheckConsistency(resultSet, hashId, evaluatorId,
			    evaluatorVersion);
		    MetricParameter<?> metricsParameter = extractParameter(resultSet);
		    if (metricsParameter == null) {
			continue;
		    }
		    Set<MetricParameter<?>> parameters = parametersBuffer.get(hashId);
		    if (parameters == null) {
			parameters = new HashSet<MetricParameter<?>>();
			parametersBuffer.put(hashId, parameters);
		    }
		    parameters.add(metricsParameter);
		    CodeRangeType codeRangeType = CodeRangeType.valueOf(resultSet.getString("code_range_type"));
		    if (!hashIdTypes.containsKey(hashId)) {
			if (codeRangeType == CodeRangeType.DIRECTORY) {
			    hashIdTypes.put(hashId, CodeRangeType.DIRECTORY);
			} else {
			    hashIdTypes.put(hashId, CodeRangeType.FILE);

			}
		    }
		    String codeRangeName = resultSet.getString("code_range_name");
		    Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> hashIdBuffer = buffer
			    .get(hashId);
		    if (hashIdBuffer == null) {
			hashIdBuffer = new HashMap<>();
			buffer.put(hashId, hashIdBuffer);
		    }
		    Map<Parameter<?>, MetricValue<?>> parameterBuffer;
		    Map<String, Map<Parameter<?>, MetricValue<?>>> codeRangeTypeBuffer = hashIdBuffer
			    .get(codeRangeType);
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
		    double value = resultSet.getDouble("metric");
		    MetricValue<?> metricValue = MetricValue.create(metricsParameter, value);
		    parameterBuffer.put(metricsParameter, metricValue);
		}
		GenericRunMetrics metrics = new GenericRunMetrics(evaluatorId, evaluatorVersion, minTime,
			(MetricParameter<?>[]) parametersBuffer.values().toArray());
		for (HashId hashId : buffer.keySet()) {
		    MetricParameter<?>[] parameters = (MetricParameter<?>[]) parametersBuffer.get(hashId).toArray();
		    Map<CodeRangeType, Map<String, Map<Parameter<?>, MetricValue<?>>>> hashIdBuffer = buffer
			    .get(hashId);
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
				fileMetrics.addCodeRangeMetrics(new GenericCodeRangeMetrics(sourceCodeLocation,
					codeRangeType, codeRangeName, parameters, metricValues));
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
			GenericDirectoryMetrics directoryMetrics = new GenericDirectoryMetrics(evaluatorId,
				evaluatorVersion, hashId, timeBuffer.get(hashId), parameters, metricValues);
			metrics.add(directoryMetrics);
		    }
		}
		return metrics;
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read run results.", e);
	}
    }

    private Date getTimeAndCheckConsistency(String evaluatorId, Map<HashId, Date> timeBuffer, ResultSet resultSet,
	    HashId hashId) throws EvaluationStoreException, SQLException {
	// Get time, check consistency and get min time
	Date time = timeBuffer.get(hashId);
	if (time == null) {
	    time = resultSet.getDate("time");
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

    private void getSourceCodeLocationAndCheckConsistency(ResultSet resultSet, HashId hashId, String evaluatorId,
	    Map<HashId, SourceCodeLocation> sourceCodeLocationBuffer) throws EvaluationStoreException, SQLException {
	// Get source code location and check for consistency
	SourceCodeLocation alternateSourceCodeLocation = extractSourceCodeLocation(resultSet);
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

    private Version getEvaluatorVersionAndCheckConsistency(ResultSet resultSet, HashId hashId, String evaluatorId,
	    Version evaluatorVersion) throws EvaluationStoreException, SQLException {
	// Get evaluator version and check consistency
	Version alternateEvaluatorVersion = Version.valueOf(resultSet.getString("evaluator_version"));
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
