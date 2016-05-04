package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
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
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssue;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericCodeRangeDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericDirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericFileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericProjectDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericRunDesignIssues;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.design.EvaluatorDesignIssuesStore;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.EvaluatorStoreConnection;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;
import com.puresoltechnologies.versioning.Version;

public class EvaluatorDesignIssuesStoreServiceBean implements EvaluatorDesignIssuesStore {

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
		+ HBaseElementNames.EVALUATION_DESIGN_ISSUES_TABLE
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
		+ HBaseElementNames.EVALUATION_DESIGN_ISSUES_TABLE + " WHERE hashid=? AND evaluator_id=?")) {
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
	throwUnsupportedException();
	return false;
    }

    @Override
    public boolean hasProjectResults(String projectId, long runId, String evaluatorId, String parameterName) {
	throwUnsupportedException();
	return false;
    }

    @Override
    public boolean hasProjectResults(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	throwUnsupportedException();
	return false;
    }

    @Override
    public void storeFileResults(AnalysisRun analysisRun, CodeAnalysis codeAnalysis, GenericFileDesignIssues results)
	    throws EvaluationStoreException {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + HBaseElementNames.EVALUATION_DESIGN_ISSUES_TABLE + " (time, "
			+ "hashid, " + "source_code_location, " + "code_range_type, " + "code_range_name, "
			+ "evaluator_id, " + "evaluator_version, " + "design_issue_id, " + "parameter_type, "
			+ "description, " + "value) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
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
	    for (GenericCodeRangeDesignIssues codeRangeIssues : results.getCodeRangeDesginIssues()) {
		String codeRangeName = codeRangeIssues.getCodeRangeName();
		CodeRangeType codeRangeType = codeRangeIssues.getCodeRangeType();

		for (DesignIssueParameter parameter : results.getParameters()) {
		    String parameterName = parameter.getName();
		    String unit = parameter.getUnit();
		    Class<?> type = parameter.getType();
		    String description = parameter.getDescription();
		    LevelOfMeasurement levelOfMeasurement = parameter.getLevelOfMeasurement();
		    if (parameterName.equals(codeRangeNameParameterName)
			    || parameterName.equals(codeRangeTypeParameterName)) {
			continue;
		    }
		    List<DesignIssue> issues = codeRangeIssues.getIssues(parameter);
		    if (issues == null) {
			// There is not value assigned for the parameter. So we
			// can
			// safely skip it.
			continue;
		    }
		    for (DesignIssue issue : issues) {
			int numericValue = issue.getValue().intValue();
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
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback file result storage.", e1);
	    }
	    throw new EvaluationStoreException("Could not store file results.", e);
	}
    }

    @Override
    public void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryDesignIssues results) throws EvaluationStoreException {
	throwUnsupportedException();
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericProjectDesignIssues results) throws EvaluationStoreException {
	throwUnsupportedException();
    }

    @Override
    public GenericFileDesignIssues readFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public GenericDirectoryDesignIssues readDirectoryResults(HashId hashId, String evaluatorId)
	    throws EvaluationStoreException {
	throwUnsupportedException();
	return null;
    }

    @Override
    public GenericProjectDesignIssues readProjectResults(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException {
	throwUnsupportedException();
	return null;
    }

    @Override
    public void storeFileResultsInBigTable(AnalysisRun analysisRun, CodeAnalysis codeAnalysis,
	    GenericFileDesignIssues results) throws EvaluationStoreException {
	// TODO Auto-generated method stub

    }

    @Override
    public void storeDirectoryResultsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryDesignIssues results) throws EvaluationStoreException {
	// TODO Auto-generated method stub

    }

    @Override
    public void storeProjectResultsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericProjectDesignIssues metrics) throws EvaluationStoreException {
	// TODO Auto-generated method stub

    }

    @Override
    public GenericRunDesignIssues readRunResults(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

}
