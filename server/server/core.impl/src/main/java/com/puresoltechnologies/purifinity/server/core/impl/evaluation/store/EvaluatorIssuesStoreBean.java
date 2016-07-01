package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Classification;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.CodeRangeIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.DirectoryIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.DirectoryIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.FileIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.FileIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Issue;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.IssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.ProjectIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.ProjectIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.RunIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.SingleIssue;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.EvaluationParameter;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluationService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStore;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStoreRemote;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.EvaluatorStoreConnection;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;
import com.puresoltechnologies.purifinity.server.database.hbase.HBasePreparedStatements;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.versioning.Version;

@Stateless
public class EvaluatorIssuesStoreBean extends AbstractEvaluatorStore
	implements EvaluatorIssuesStore, EvaluatorIssuesStoreRemote {

    @Inject
    private Logger logger;

    @Inject
    @EvaluatorStoreConnection
    private Connection connection;

    @Inject
    private HBasePreparedStatements preparedStatements;

    @Inject
    private EvaluationService evaluationService;

    private void throwUnsupportedException() {
	throw new IllegalStateException("Design issues can only be stored for files.");
    }

    @Override
    public boolean hasFileResults(HashId hashId, CodeRangeType codeRangeType, String codeRangeName, String evaluatorId,
	    String parameterName) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		    "SELECT hashid FROM " + HBaseElementNames.EVALUATION_FILE_ISSUES_TABLE
			    + " WHERE hashid=? AND code_range_type=? AND code_range_name=? AND evaluator_id=? AND parameter_name=?");
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
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		    "SELECT hashid FROM " + HBaseElementNames.EVALUATION_FILE_ISSUES_TABLE
			    + " WHERE hashid=? AND evaluator_id=?");
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
	throwUnsupportedException();
	return false;
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	return false;
    }

    @Override
    public boolean hasProjectResults(String projectId, long runId, String evaluatorId, String parameterName) {
	return false;
    }

    @Override
    public boolean hasProjectResults(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	throwUnsupportedException();
	return false;
    }

    @Override
    public void storeFileResults(AnalysisRun analysisRun, AnalysisInformation analysisInformation, FileIssues results)
	    throws EvaluationStoreException {
	try {
	    storeFileIssuesAsValues(analysisRun, analysisInformation.getHashId(), results);
	    storeFileResultsInBigTable(analysisRun, analysisInformation, results);
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

    private void storeFileIssuesAsValues(AnalysisRun analysisRun, HashId hashId, FileIssues results)
	    throws SQLException {
	PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		"UPSERT INTO " + HBaseElementNames.EVALUATION_FILE_ISSUES_TABLE + " (time, " + "hashid, "
			+ "source_code_location, " + "code_range_type, " + "code_range_name, " + "evaluator_id, "
			+ "evaluator_version, " + "issue_id, " + "description, " + "weight, " + "start_line, "
			+ "start_column, " + "line_count, " + "length, " + "severity, " + "classification" + ") VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	Date time = results.getTime();
	SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();
	String evaluatorId = results.getEvaluatorId();
	Version evaluatorVersion = results.getEvaluatorVersion();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter.getInstance();
	String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	for (CodeRangeIssues codeRangeIssues : results.getCodeRangeIssues()) {
	    String codeRangeName = codeRangeIssues.getCodeRangeName();
	    CodeRangeType codeRangeType = codeRangeIssues.getCodeRangeType();

	    for (IssueParameter parameter : results.getParameters()) {
		String parameterName = parameter.getName();
		String description = parameter.getDescription();
		if (parameterName.equals(codeRangeNameParameterName)
			|| parameterName.equals(codeRangeTypeParameterName)) {
		    continue;
		}
		List<Issue> issues = codeRangeIssues.getIssues(parameter);
		if (issues == null) {
		    // There is not value assigned for the parameter. So we
		    // can
		    // safely skip it.
		    continue;
		}
		for (Issue issue : issues) {
		    int numericValue = issue.getValue().intValue();
		    preparedStatement.setTime(1, new Time(time.getTime()));
		    preparedStatement.setString(2, hashId.toString());
		    preparedStatement.setString(3, PropertiesUtils.toString(sourceCodeLocation.getSerialization()));
		    preparedStatement.setString(4, codeRangeType.name());
		    preparedStatement.setString(5, codeRangeName);
		    preparedStatement.setString(6, evaluatorId);
		    preparedStatement.setString(7, evaluatorVersion.toString());
		    preparedStatement.setString(8, parameterName);
		    preparedStatement.setString(9, description);
		    preparedStatement.setDouble(10, numericValue);
		    preparedStatement.setInt(11, issue.getStartLine());
		    preparedStatement.setInt(12, issue.getStartColumn());
		    preparedStatement.setInt(13, issue.getLineCount());
		    preparedStatement.setInt(14, issue.getLength());
		    preparedStatement.setString(15, issue.getSeverity().name());
		    preparedStatement.setString(16, issue.getClassification().name());
		    preparedStatement.execute();
		}
	    }
	}
    }

    @Override
    public void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directory, DirectoryIssues results)
	    throws EvaluationStoreException {
	throwUnsupportedException();
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directory, ProjectIssues results)
	    throws EvaluationStoreException {
	throwUnsupportedException();
    }

    @Override
    public FileIssuesImpl readFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		    "SELECT " + "time, " + "code_range_type, " + "code_range_name, " + "evaluator_version, "
			    + "issue_id, " + "description, " + "weight, " + "start_line, " + "start_column, "
			    + "line_count, " + "length, " + "source_code_location, " + "severity, " + "classification "
			    + "FROM " + HBaseElementNames.EVALUATION_FILE_ISSUES_TABLE
			    + " WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, evaluatorId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		Date time = null;
		SourceCodeLocation sourceCodeLocation = null;
		Version evaluatorVersion = null;
		Map<CodeRangeType, Map<String, Map<Parameter<?>, Issue>>> buffer = new HashMap<>();
		IssueParameter[] parameters = new IssueParameter[] {};
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
		    String parameterName = resultSet.getString("issue_id");
		    IssueParameter issueParameter = extractIssueParameter(resultSet);
		    if (issueParameter == null) {
			continue;
		    }
		    parameters = new IssueParameter[] { issueParameter };
		    CodeRangeType codeRangeType = extractCodeRangeType(resultSet);
		    String codeRangeName = resultSet.getString("code_range_name");
		    Map<Parameter<?>, Issue> parameterBuffer;
		    if (buffer.containsKey(codeRangeType)) {
			Map<String, Map<Parameter<?>, Issue>> codeRangeTypeBuffer = buffer.get(codeRangeType);
			if (codeRangeTypeBuffer.containsKey(codeRangeName)) {
			    parameterBuffer = codeRangeTypeBuffer.get(codeRangeName);
			    if (parameterBuffer.containsKey(issueParameter)) {
				throw new EvaluationStoreException("Multiple parameters with same name '"
					+ parameterName + "' are different for evaluatorId=" + evaluatorId
					+ " and hashId=" + hashId.toString());
			    }
			} else {
			    parameterBuffer = new HashMap<>();
			    codeRangeTypeBuffer.put(codeRangeName, parameterBuffer);
			}
		    } else {
			Map<String, Map<Parameter<?>, Issue>> codeRangeTypeBuffer = new HashMap<>();
			parameterBuffer = new HashMap<>();
			codeRangeTypeBuffer.put(codeRangeName, parameterBuffer);
			buffer.put(codeRangeType, codeRangeTypeBuffer);
		    }
		    int weight = resultSet.getInt("weight");
		    int startLine = resultSet.getInt("start_line");
		    int startColumn = resultSet.getInt("start_column");
		    int lineCount = resultSet.getInt("line_count");
		    int length = resultSet.getInt("length");
		    Severity severity = extractSeverity(resultSet);
		    Classification classification = extractClassification(resultSet);
		    Issue metricValue = new Issue(severity, classification, startLine, startColumn, lineCount, length,
			    weight, issueParameter);
		    parameterBuffer.put(issueParameter, metricValue);
		}

		FileIssuesImpl fileIssues = new FileIssuesImpl(evaluatorId, evaluatorVersion, hashId,
			sourceCodeLocation, time, parameters);
		for (Entry<CodeRangeType, Map<String, Map<Parameter<?>, Issue>>> codeRangeTypeEntry : buffer
			.entrySet()) {
		    CodeRangeType codeRangeType = codeRangeTypeEntry.getKey();
		    for (Entry<String, Map<Parameter<?>, Issue>> codeRangeNameEntry : codeRangeTypeEntry.getValue()
			    .entrySet()) {
			String codeRangeName = codeRangeNameEntry.getKey();
			Map<String, List<Issue>> values = new HashMap<>();
			for (Entry<Parameter<?>, Issue> parameterEntry : codeRangeNameEntry.getValue().entrySet()) {
			    Parameter<?> parameter = parameterEntry.getKey();
			    Issue value = parameterEntry.getValue();
			    List<Issue> issueList = values.get(parameter.getName());
			    if (issueList == null) {
				issueList = new ArrayList<>();
				values.put(parameter.getName(), issueList);
			    }
			    issueList.add(value);
			}
			fileIssues.addCodeRangeIssue(new CodeRangeIssues(sourceCodeLocation, codeRangeType,
				codeRangeName, parameters, values));
		    }
		}
		return fileIssues;
	    }
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
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

    private IssueParameter extractIssueParameter(ResultSet resultSet) throws SQLException {
	String parameterName = resultSet.getString("issue_id");
	String description = resultSet.getString("description");
	return new IssueParameter(parameterName, "", description);
    }

    @Override
    public DirectoryIssuesImpl readDirectoryResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	throwUnsupportedException();
	return null;
    }

    @Override
    public ProjectIssuesImpl readProjectResults(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException {
	throwUnsupportedException();
	return null;
    }

    @Override
    public void storeFileResultsInBigTable(AnalysisRun analysisRun, AnalysisInformation analysisInformation,
	    FileIssues results) throws EvaluationStoreException {
	try {
	    storeIssuesInBigTableWithoutCommit(analysisRun, analysisInformation, results);
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

    private void storeIssuesInBigTableWithoutCommit(AnalysisRun analysisRun, AnalysisInformation analysisInformation,
	    FileIssues results) throws SQLException {
	PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		"UPSERT INTO " + HBaseElementNames.EVALUATION_ISSUES_TABLE + " (time," + "project_id, " + "run_id, "
			+ "hashid, " + "internal_directory, " + "file_name, " + "source_code_location, "
			+ "language_name, " + "language_version, " + "evaluator_id, " + "evaluator_version, "
			+ "code_range_name, " + "code_range_type, " + "issue_id, " + "start_line, " + "start_column, "
			+ "line_count, " + "length, " + "weight, " + "classification, " + "severity, "
			+ "description ) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	Date time = results.getTime();
	String projectId = analysisRun.getInformation().getProjectId();
	long runId = analysisRun.getInformation().getRunId();
	HashId hashId = analysisInformation.getHashId();
	AnalysisFileTree analysisTreeNode = analysisRun.findTreeNode(hashId);
	File pathFile = analysisTreeNode.getPathFile(false);
	String internalPath = pathFile.getParent();
	String fileName = pathFile.getName();
	String sourceCodeLocation = PropertiesUtils
		.toString(analysisTreeNode.getSourceCodeLocation().getSerialization());
	String languageName = analysisInformation.getLanguageName();
	String languageVersion = analysisInformation.getLanguageVersion();
	String evaluatorId = results.getEvaluatorId();
	Version evaluatorVersion = results.getEvaluatorVersion();
	CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter.getInstance();
	String codeRangeNameParameterName = codeRangeNameParameter.getName();
	CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter.getInstance();
	String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	for (CodeRangeIssues issues : results.getCodeRangeIssues()) {
	    String codeRangeName = issues.getCodeRangeName();
	    CodeRangeType codeRangeType = issues.getCodeRangeType();

	    for (IssueParameter parameter : results.getParameters()) {
		String issueId = parameter.getName();
		if (issueId.equals(codeRangeNameParameterName) || issueId.equals(codeRangeTypeParameterName)) {
		    continue;
		}
		if (!issues.hasIssue(parameter)) {
		    continue;
		}
		for (Issue issue : issues.getIssues(parameter)) {
		    int weight = issue.getValue();
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
		    preparedStatement.setString(14, issueId);
		    preparedStatement.setInt(15, issue.getStartLine());
		    preparedStatement.setInt(16, issue.getStartColumn());
		    preparedStatement.setInt(17, issue.getLineCount());
		    preparedStatement.setInt(18, issue.getLength());
		    preparedStatement.setDouble(19, weight);
		    preparedStatement.setString(20, issue.getClassification().name());
		    preparedStatement.setString(21, issue.getSeverity().name());
		    preparedStatement.setString(22, parameter.getDescription());
		    preparedStatement.execute();
		}
	    }
	}
    }

    @Override
    public void storeDirectoryResultsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    DirectoryIssues results) throws EvaluationStoreException {
	throwUnsupportedException();
    }

    @Override
    public void storeProjectResultsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    ProjectIssues metrics) throws EvaluationStoreException {
	throwUnsupportedException();
    }

    @Override
    public Collection<SingleIssue> readRunResults(String projectId, long runId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		    "SELECT " //
			    + "EVALUATOR_ID" //
			    + ", ISSUE_ID" //
			    + ", CODE_RANGE_TYPE" //
			    + ", HASHID" //
			    + ", CODE_RANGE_NAME" //
			    + ", TIME" //
			    + ", EVALUATOR_VERSION" //
			    + ", SOURCE_CODE_LOCATION" //
			    + ", LANGUAGE_NAME" //
			    + ", LANGUAGE_VERSION" //
			    + ", START_LINE" //
			    + ", START_COLUMN" //
			    + ", LINE_COUNT" //
			    + ", LENGTH" //
			    + ", WEIGHT" //
			    + ", CLASSIFICATION" //
			    + ", SEVERITY" //
			    + ", DESCRIPTION" //
			    + " FROM " + HBaseElementNames.EVALUATION_ISSUES_TABLE //
			    + " WHERE project_id=? AND run_id=?");
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    List<SingleIssue> runResults = new ArrayList<>();
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		while (resultSet.next()) {
		    String evaluatorId = resultSet.getString("EVALUATOR_ID");
		    String issueId = resultSet.getString("ISSUE_ID");
		    CodeRangeType codeRangeType = extractCodeRangeType(resultSet);
		    HashId hashId = extractHashId(resultSet);
		    String codeRangeName = resultSet.getString("CODE_RANGE_NAME");
		    Date time = resultSet.getDate("TIME");
		    Version evaluatorVersion = extractEvaluatorVersion(resultSet);
		    SourceCodeLocation sourceCodeLocation = extractSourceCodeLocation(resultSet);
		    String languageName = resultSet.getString("LANGUAGE_NAME");
		    String languageVersion = resultSet.getString("LANGUAGE_VERSION");
		    int startLine = resultSet.getInt("START_LINE");
		    int startColumn = resultSet.getInt("START_COLUMN");
		    int lineCount = resultSet.getInt("LINE_COUNT");
		    int length = resultSet.getInt("LENGTH");
		    int weight = resultSet.getInt("WEIGHT");
		    Classification classification = extractClassification(resultSet);
		    Severity severity = extractSeverity(resultSet);

		    EvaluatorServiceInformation evaluator = evaluationService.getEvaluator(evaluatorId);
		    IssueParameter issueParameter = null;
		    for (EvaluationParameter<?> parameter : evaluator.getParameters()) {
			if (parameter.getName().equals(issueId)) {
			    issueParameter = (IssueParameter) parameter;
			}
		    }
		    if (issueParameter != null) {
			SingleIssue singleIssue = new SingleIssue(projectId, runId, evaluatorId, evaluatorVersion,
				codeRangeType, hashId, codeRangeName, time, sourceCodeLocation, languageName,
				languageVersion, severity, classification, startLine, startColumn, lineCount, length,
				weight, issueParameter);
			runResults.add(singleIssue);
		    }
		}
	    }
	    return runResults;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
	}
    }

    @Override
    public Collection<SingleIssue> readRunResults(String projectId, long runId, Classification classification)
	    throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		    "SELECT " //
			    + "EVALUATOR_ID" //
			    + ", ISSUE_ID" //
			    + ", CODE_RANGE_TYPE" //
			    + ", HASHID" //
			    + ", CODE_RANGE_NAME" //
			    + ", TIME" //
			    + ", EVALUATOR_VERSION" //
			    + ", SOURCE_CODE_LOCATION" //
			    + ", LANGUAGE_NAME" //
			    + ", LANGUAGE_VERSION" //
			    + ", START_LINE" //
			    + ", START_COLUMN" //
			    + ", LINE_COUNT" //
			    + ", LENGTH" //
			    + ", WEIGHT" //
			    + ", SEVERITY" //
			    + ", DESCRIPTION" //
			    + " FROM " + HBaseElementNames.EVALUATION_ISSUES_TABLE //
			    + " WHERE project_id=? AND run_id=? AND CLASSIFICATION=?");
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.setString(3, classification.name());
	    List<SingleIssue> runResults = new ArrayList<>();
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		while (resultSet.next()) {
		    String evaluatorId = resultSet.getString("EVALUATOR_ID");
		    String issueId = resultSet.getString("ISSUE_ID");
		    CodeRangeType codeRangeType = extractCodeRangeType(resultSet);
		    HashId hashId = extractHashId(resultSet);
		    String codeRangeName = resultSet.getString("CODE_RANGE_NAME");
		    Date time = resultSet.getDate("TIME");
		    Version evaluatorVersion = extractEvaluatorVersion(resultSet);
		    SourceCodeLocation sourceCodeLocation = extractSourceCodeLocation(resultSet);
		    String languageName = resultSet.getString("LANGUAGE_NAME");
		    String languageVersion = resultSet.getString("LANGUAGE_VERSION");
		    int startLine = resultSet.getInt("START_LINE");
		    int startColumn = resultSet.getInt("START_COLUMN");
		    int lineCount = resultSet.getInt("LINE_COUNT");
		    int length = resultSet.getInt("LENGTH");
		    int weight = resultSet.getInt("WEIGHT");
		    Severity severity = extractSeverity(resultSet);

		    EvaluatorServiceInformation evaluator = evaluationService.getEvaluator(evaluatorId);
		    IssueParameter issueParameter = null;
		    for (EvaluationParameter<?> parameter : evaluator.getParameters()) {
			if (parameter.getName().equals(issueId)) {
			    issueParameter = (IssueParameter) parameter;
			}
		    }
		    if (issueParameter != null) {
			SingleIssue singleIssue = new SingleIssue(projectId, runId, evaluatorId, evaluatorVersion,
				codeRangeType, hashId, codeRangeName, time, sourceCodeLocation, languageName,
				languageVersion, severity, classification, startLine, startColumn, lineCount, length,
				weight, issueParameter);
			runResults.add(singleIssue);
		    }
		}
	    }
	    return runResults;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
	}
    }

    @Override
    public RunIssues readRunResults(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	throwUnsupportedException();
	return null;
    }

    @Override
    public Map<Severity, Integer> getRunIssuesByServerity(String projectId, long runId)
	    throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		    "SELECT SEVERITY, COUNT(SEVERITY) FROM " + HBaseElementNames.EVALUATION_ISSUES_TABLE
			    + " WHERE project_id=? AND run_id=? GROUP BY SEVERITY");
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    Map<Severity, Integer> result = new HashMap<>();
	    for (Severity severity : Severity.values()) {
		result.put(severity, 0);
	    }
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		while (resultSet.next()) {
		    Severity severity = extractSeverity(resultSet);
		    int num = resultSet.getInt(2);
		    result.put(severity, num);
		}
	    }
	    return result;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
	}
    }

    @Override
    public Map<Classification, Integer> getRunIssuesByClassification(String projectId, long runId)
	    throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		    "SELECT CLASSIFICATION, COUNT(CLASSIFICATION) FROM " + HBaseElementNames.EVALUATION_ISSUES_TABLE
			    + " WHERE project_id=? AND run_id=? GROUP BY CLASSIFICATION");
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    Map<Classification, Integer> result = new HashMap<>();
	    for (Classification issueType : Classification.values()) {
		result.put(issueType, 0);
	    }
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		while (resultSet.next()) {
		    Classification issueType = extractClassification(resultSet);
		    int num = resultSet.getInt(2);
		    result.put(issueType, num);
		}
	    }
	    return result;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
	}
    }

    @Override
    public Map<Severity, Integer> getRunIssueArchitectureSeverities(String projectId, long runId)
	    throws EvaluationStoreException {
	return getRunIssueSeverities(projectId, runId, Classification.ARCHITECTURE_ISSUE);
    }

    @Override
    public Map<Severity, Integer> getRunIssueDesignSeverities(String projectId, long runId)
	    throws EvaluationStoreException {
	return getRunIssueSeverities(projectId, runId, Classification.DESIGN_ISSUE);
    }

    @Override
    public Map<Severity, Integer> getRunIssueDefectSeverities(String projectId, long runId)
	    throws EvaluationStoreException {
	return getRunIssueSeverities(projectId, runId, Classification.DEFECT);
    }

    @Override
    public Map<Severity, Integer> getRunIssueStyleSeverities(String projectId, long runId)
	    throws EvaluationStoreException {
	return getRunIssueSeverities(projectId, runId, Classification.STYLE_ISSUE);
    }

    private Map<Severity, Integer> getRunIssueSeverities(String projectId, long runId, Classification classification)
	    throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(connection,
		    "SELECT SEVERITY, COUNT(SEVERITY) FROM " + HBaseElementNames.EVALUATION_ISSUES_TABLE
			    + " WHERE project_id=? AND run_id=? AND classification=? GROUP BY SEVERITY");
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.setString(3, classification.name());
	    Map<Severity, Integer> result = new HashMap<>();
	    for (Severity severity : Severity.values()) {
		result.put(severity, 0);
	    }
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		while (resultSet.next()) {
		    Severity severity = extractSeverity(resultSet);
		    int num = resultSet.getInt(2);
		    result.put(severity, num);
		}
	    }
	    return result;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
	}
    }

}
