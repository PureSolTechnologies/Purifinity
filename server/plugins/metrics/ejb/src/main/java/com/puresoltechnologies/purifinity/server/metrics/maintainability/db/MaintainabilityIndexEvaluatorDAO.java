package com.puresoltechnologies.purifinity.server.metrics.maintainability.db;

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
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexResult;

public class MaintainabilityIndexEvaluatorDAO
	implements MetricsDAO<MaintainabilityIndexFileResult, MaintainabilityIndexDirectoryResults> {

    @Inject
    @MaintainabilityIndexEvaluatorStoreConnection
    private Connection connection;

    @Override
    public void storeFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    MaintainabilityIndexFileResult maintainabilityIndex) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("INSERT INTO maintainability_index.file_results (hashid, " + "evaluator_id, "
			    + "source_code_location, " + "code_range_type, " + "code_range_name, " + "MIwoc, "
			    + "MIcw, " + "MI) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	    MaintainabilityIndexResult maintainabilityIndexResult = maintainabilityIndex
		    .getMaintainabilityIndexResult();
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, MaintainabilityIndexEvaluator.ID);
	    preparedStatement.setString(3, PropertiesUtils.toString(sourceCodeLocation.getSerialization()));
	    preparedStatement.setString(4, codeRange.getType().name());
	    preparedStatement.setString(5, codeRange.getSimpleName());
	    preparedStatement.setDouble(6, maintainabilityIndexResult.getMIwoc());
	    preparedStatement.setDouble(7, maintainabilityIndexResult.getMIcw());
	    preparedStatement.setDouble(8, maintainabilityIndexResult.getMI());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not write results.", e);
	}
    }

    @Override
    public List<MaintainabilityIndexFileResult> readFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    List<MaintainabilityIndexFileResult> mcCabeResults = new ArrayList<>();
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "source_code_location, "
		    + "code_range_type, " + "code_range_name, " + "MIwoc, " + "MIcw " + "FROM "
		    + "maintainability_index.file_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, MaintainabilityIndexEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
		Properties sourceCodeLocationProperties = PropertiesUtils.fromString(resultSet.getString(1));
		SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
			.createFromSerialization(sourceCodeLocationProperties);
		CodeRangeType codeRangeType = CodeRangeType.valueOf(resultSet.getString(2));
		String codeRangeName = resultSet.getString(3);
		double miWoc = resultSet.getDouble(4);
		double miCw = resultSet.getDouble(5);
		MaintainabilityIndexResult result = new MaintainabilityIndexResult(miWoc, miCw);
		MaintainabilityIndexFileResult maintainabilityIndexFileResult = new MaintainabilityIndexFileResult(
			sourceCodeLocation, codeRangeType, codeRangeName, result);
		mcCabeResults.add(maintainabilityIndexFileResult);
	    }
	    return mcCabeResults;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read results.", e);
	}
    }

    @Override
    public void storeDirectoryResults(HashId hashId, MaintainabilityIndexDirectoryResults maintainabilityIndex) {
	/*
	 * intentionally left empty, because Maintainability index has no
	 * directory results.
	 */
    }

    @Override
    public MaintainabilityIndexDirectoryResults readDirectoryResults(HashId hashId) {
	/*
	 * intentionally left empty, because Maintainability index has no
	 * directory results.
	 */
	return null;
    }

    @Override
    public boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("SELECT " + "hashid " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, MaintainabilityIndexEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    return resultSet.next();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for results.", e);
	}
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId) {
	/*
	 * intentionally returning false, because Maintainability Index has no
	 * directory results.
	 */
	return false;
    }

}
