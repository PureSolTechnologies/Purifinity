package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
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
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.Classification;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
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
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStoreService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.EvaluatorStoreConnection;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;
import com.puresoltechnologies.versioning.Version;

@Stateless
public class EvaluatorIssuesStoreServiceBean implements EvaluatorIssuesStoreService, EvaluatorIssuesStoreServiceRemote {

    @Inject
    private Logger logger;

    @Inject
    @EvaluatorStoreConnection
    private Connection connection;

    private void throwUnsupportedException() {
	throw new IllegalStateException("Design issues can only be stored for files.");
    }

    @Override
    public boolean hasFileResults(HashId hashId, CodeRangeType codeRangeType, String codeRangeName, String evaluatorId,
	    String parameterName) throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT hashid FROM "
		+ HBaseElementNames.EVALUATION_FILE_ISSUES_TABLE
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
		+ HBaseElementNames.EVALUATION_FILE_ISSUES_TABLE + " WHERE hashid=? AND evaluator_id=?")) {
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
    public void storeFileResults(AnalysisRun analysisRun, CodeAnalysis codeAnalysis, FileIssues results)
	    throws EvaluationStoreException {
	try {
	    storeFileIssuesAsValues(analysisRun, codeAnalysis, results);
	    storeFileResultsInBigTable(analysisRun, codeAnalysis, results);
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

    private void storeFileIssuesAsValues(AnalysisRun analysisRun, CodeAnalysis codeAnalysis, FileIssues results)
	    throws SQLException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + HBaseElementNames.EVALUATION_FILE_ISSUES_TABLE + " (time, "
			+ "hashid, " + "source_code_location, " + "code_range_type, " + "code_range_name, "
			+ "evaluator_id, " + "evaluator_version, " + "design_issue_id, " + "description, " + "weight, "
			+ "start_line, " + "start_column, " + "line_count, " + "length, " + "severity, "
			+ "classification" + ") VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
	    Date time = results.getTime();
	    AnalysisInformation analysisInformation = codeAnalysis.getAnalysisInformation();
	    HashId hashId = analysisInformation.getHashId();
	    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();
	    String evaluatorId = results.getEvaluatorId();
	    Version evaluatorVersion = results.getEvaluatorVersion();
	    CodeRangeNameParameter codeRangeNameParameter = CodeRangeNameParameter.getInstance();
	    String codeRangeNameParameterName = codeRangeNameParameter.getName();
	    CodeRangeTypeParameter codeRangeTypeParameter = CodeRangeTypeParameter.getInstance();
	    String codeRangeTypeParameterName = codeRangeTypeParameter.getName();
	    for (CodeRangeIssues codeRangeIssues : results.getCodeRangeDesignIssues()) {
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
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT " + "time, " + "code_range_type, " + "code_range_name, "
			+ "evaluator_version, " + "design_issue_id, " + "description, " + "weight, " + "start_line, "
			+ "start_column, " + "line_count, " + "length, " + "source_code_location " + "FROM "
			+ HBaseElementNames.EVALUATION_FILE_ISSUES_TABLE + " WHERE hashid=? AND evaluator_id=?")) {
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
		    SourceCodeLocation alternateSourceCodeLocation = EvaluatorStoreUtils
			    .extractSourceCodeLocation(resultSet);
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
		    String parameterName = resultSet.getString("design_issue_id");
		    IssueParameter designIssueParameter = extractDesignIssueParameter(resultSet);
		    if (designIssueParameter == null) {
			continue;
		    }
		    parameters = new IssueParameter[] { designIssueParameter };
		    CodeRangeType codeRangeType = CodeRangeType.valueOf(resultSet.getString("code_range_type"));
		    String codeRangeName = resultSet.getString("code_range_name");
		    Map<Parameter<?>, Issue> parameterBuffer;
		    if (buffer.containsKey(codeRangeType)) {
			Map<String, Map<Parameter<?>, Issue>> codeRangeTypeBuffer = buffer.get(codeRangeType);
			if (codeRangeTypeBuffer.containsKey(codeRangeName)) {
			    parameterBuffer = codeRangeTypeBuffer.get(codeRangeName);
			    if (parameterBuffer.containsKey(designIssueParameter)) {
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
		    Severity severity = Severity.valueOf(resultSet.getString("severity"));
		    Classification classification = Classification.valueOf(resultSet.getString("classification"));
		    Issue metricValue = new Issue(severity, classification, startLine, startColumn, lineCount, length,
			    weight, designIssueParameter);
		    parameterBuffer.put(designIssueParameter, metricValue);
		}

		FileIssuesImpl fileDesignIssues = new FileIssuesImpl(evaluatorId, evaluatorVersion, hashId,
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
			fileDesignIssues.addCodeRangeDesignIssue(new CodeRangeIssues(sourceCodeLocation, codeRangeType,
				codeRangeName, parameters, values));
		    }
		}
		return fileDesignIssues;
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

    private IssueParameter extractDesignIssueParameter(ResultSet resultSet) throws SQLException {
	String parameterName = resultSet.getString("design_issue_id");
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
    public void storeFileResultsInBigTable(AnalysisRun analysisRun, CodeAnalysis codeAnalysis, FileIssues results)
	    throws EvaluationStoreException {
	// TODO Auto-generated method stub

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
    public RunIssues readRunResults(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	throwUnsupportedException();
	return null;
    }

}
