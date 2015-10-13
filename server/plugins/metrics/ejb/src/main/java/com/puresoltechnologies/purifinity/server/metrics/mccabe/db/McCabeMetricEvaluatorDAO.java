package com.puresoltechnologies.purifinity.server.metrics.mccabe.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetric;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricResult;

public class McCabeMetricEvaluatorDAO {

    @Inject
    @McCabeMetricEvaluatorStoreConnection
    private Connection connection;

    public void storeFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    McCabeMetricResult mcCabeResult) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("INSERT INTO file_results (hashid, " + "evaluator_id, " + "source_code_location, "
			    + "code_range_type, " + "code_range_name, " + "vg) " + "VALUES (?, ?, ?, ?, ?, ?);");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, McCabeMetric.ID);
	    preparedStatement.setString(3, PropertiesUtils.toString(sourceCodeLocation.getSerialization()));
	    preparedStatement.setString(4, codeRange.getType().name());
	    preparedStatement.setString(5, codeRange.getSimpleName());
	    preparedStatement.setInt(6, mcCabeResult.getCyclomaticComplexity());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not write results.", e);
	}
    }

    public List<McCabeMetricResult> readFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    List<McCabeMetricResult> mcCabeResults = new ArrayList<>();
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("SELECT " + "source_code_location, " + "code_range_type, " + "code_range_name, "
			    + "vg FROM file_results " + "WHERE hashid=? AND evaluator_id=?;");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, McCabeMetric.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
		Properties sourceCodeLocationProperties = PropertiesUtils.fromString(resultSet.getString(1));
		SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
			.createFromSerialization(sourceCodeLocationProperties);
		CodeRangeType codeRangeType = CodeRangeType.valueOf(resultSet.getString(2));
		String codeRangeName = resultSet.getString(3);
		int vg = resultSet.getInt(4);
		McCabeMetricResult mcCabeResult = new McCabeMetricResult(sourceCodeLocation, codeRangeType,
			codeRangeName, vg);
		mcCabeResults.add(mcCabeResult);
	    }
	    return mcCabeResults;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read results.", e);
	}
    }

    public void storeDirectoryResults(HashId hashId, McCabeMetricResult mcCabeResult) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "INSERT INTO directory_results (hashid, " + "evaluator_id, " + "vg) " + "VALUES (?, ?, ?);");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, McCabeMetric.ID);
	    preparedStatement.setInt(3, mcCabeResult.getCyclomaticComplexity());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not write results.", e);
	}
    }

    public McCabeMetricResult readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "SELECT " + "vg " + " FROM directory_results " + "WHERE hashid=? AND evaluator_id=?;");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, McCabeMetric.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (!resultSet.next()) {
		return null;
	    }
	    int vg = resultSet.getInt(1);
	    McCabeMetricResult slocResult = new McCabeMetricResult(null, CodeRangeType.DIRECTORY, "", vg);
	    return slocResult;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read results.", e);
	}
    }
}
