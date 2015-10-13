package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoFileResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.SoftwareProject;

public class BasicCoCoMoEvaluatorDAO implements MetricsDAO<BasicCoCoMoFileResults, BasicCoCoMoDirectoryResults> {

    @Inject
    @BasicCoCoMoEvaluatorStoreConnection
    private Connection connection;

    @Override
    public void storeFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    BasicCoCoMoFileResults fileResults) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("INSERT INTO file_results (hashid, " + "evaluator_id, " + "source_code_location, "
			    + "code_range_type, " + "code_range_name, " + "phyLOC, " + "ksloc, " + "personMonth, "
			    + "personYears, " + "scheduledMonth, " + "scheduledYears, " + "teamSize, "
			    + "estimatedCosts, " + "c1, " + "c2, " + "c3, " + "complexity, " + "averageSalary, "
			    + "currency) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, BasicCoCoMoEvaluator.ID);
	    preparedStatement.setString(3, PropertiesUtils.toString(sourceCodeLocation.getSerialization()));
	    preparedStatement.setString(4, codeRange.getType().name());
	    preparedStatement.setString(5, codeRange.getSimpleName());
	    preparedStatement.setInt(6, fileResults.getPhyLOC());
	    preparedStatement.setDouble(7, fileResults.getKsloc());
	    preparedStatement.setDouble(8, fileResults.getPersonMonth());
	    preparedStatement.setDouble(9, fileResults.getPersonYears());
	    preparedStatement.setDouble(10, fileResults.getScheduledMonth());
	    preparedStatement.setDouble(11, fileResults.getScheduledYears());
	    preparedStatement.setDouble(12, fileResults.getTeamSize());
	    preparedStatement.setDouble(13, fileResults.getEstimatedCosts());
	    preparedStatement.setDouble(14, fileResults.getC1());
	    preparedStatement.setDouble(15, fileResults.getC2());
	    preparedStatement.setDouble(16, fileResults.getC3());
	    preparedStatement.setString(17, fileResults.getComplexity().name());
	    preparedStatement.setDouble(18, fileResults.getAverageSalary());
	    preparedStatement.setString(19, fileResults.getCurrency());

	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not store results.", e);
	}
    }

    @Override
    public List<BasicCoCoMoFileResults> readFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    List<BasicCoCoMoFileResults> fileResults = new ArrayList<>();
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "SELECT " + "source_code_location, " + "phyLOC, " + "complexity, " + "averageSalary, " + "currency "
			    + "FROM file_results " + "WHERE hashid=? AND evaluator_id=?;");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, BasicCoCoMoEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
		Properties sourceCodeLocationProperties = PropertiesUtils.fromString(resultSet.getString(1));
		SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
			.createFromSerialization(sourceCodeLocationProperties);
		int phyLOC = resultSet.getInt(2);
		SoftwareProject complexity = SoftwareProject.valueOf(resultSet.getString(3));
		double averageSalary = resultSet.getDouble(4);
		String currency = resultSet.getString(5);

		BasicCoCoMoFileResults results = new BasicCoCoMoFileResults(BasicCoCoMoEvaluator.ID,
			BasicCoCoMoEvaluator.PLUGIN_VERSION, hashId, sourceCodeLocation, new Date());
		results.setAverageSalary(averageSalary, currency);
		results.setComplexity(complexity);
		results.setSloc(phyLOC);
		fileResults.add(results);
	    }
	    return fileResults;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read results.", e);
	}
    }

    @Override
    public void storeDirectoryResults(HashId hashId, BasicCoCoMoDirectoryResults directoryResult)
	    throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO directory_results (hashid, "
		    + "evaluator_id, " + "phyLOC, " + "ksloc, " + "personMonth, " + "personYears, " + "scheduledMonth, "
		    + "scheduledYears, " + "teamSize, " + "estimatedCosts, " + "c1, " + "c2, " + "c3, " + "complexity, "
		    + "averageSalary, " + "currency) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, BasicCoCoMoEvaluator.ID);
	    preparedStatement.setInt(3, directoryResult.getPhyLOC());
	    preparedStatement.setDouble(4, directoryResult.getKsloc());
	    preparedStatement.setDouble(5, directoryResult.getPersonMonth());
	    preparedStatement.setDouble(6, directoryResult.getPersonYears());
	    preparedStatement.setDouble(7, directoryResult.getScheduledMonth());
	    preparedStatement.setDouble(8, directoryResult.getScheduledYears());
	    preparedStatement.setDouble(9, directoryResult.getTeamSize());
	    preparedStatement.setDouble(10, directoryResult.getEstimatedCosts());
	    preparedStatement.setDouble(11, directoryResult.getC1());
	    preparedStatement.setDouble(12, directoryResult.getC2());
	    preparedStatement.setDouble(13, directoryResult.getC3());
	    preparedStatement.setString(14, directoryResult.getComplexity().name());
	    preparedStatement.setDouble(15, directoryResult.getAverageSalary());
	    preparedStatement.setString(16, directoryResult.getCurrency());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not store results.", e);
	}
    }

    @Override
    public BasicCoCoMoDirectoryResults readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("SELECT " + "phyLOC, " + "complexity, " + "averageSalary, " + "currency "
			    + "FROM directory_results " + "WHERE hashid=? AND evaluator_id=?;");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, BasicCoCoMoEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (!resultSet.next()) {
		return null;
	    }
	    int phyLOC = resultSet.getInt(1);
	    SoftwareProject complexity = SoftwareProject.valueOf(resultSet.getString(2));
	    double averageSalary = resultSet.getDouble(3);
	    String currency = resultSet.getString(4);

	    BasicCoCoMoDirectoryResults results = new BasicCoCoMoDirectoryResults(BasicCoCoMoEvaluator.ID,
		    BasicCoCoMoEvaluator.PLUGIN_VERSION, hashId, new Date());
	    results.setAverageSalary(averageSalary, currency);
	    results.setComplexity(complexity);
	    results.setSloc(phyLOC);
	    return results;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not store results.", e);
	}
    }

    @Override
    public boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("SELECT " + "hashid FROM file_results " + "WHERE hashid=? AND evaluator_id=?;");

	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, BasicCoCoMoEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    return resultSet.next();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for results.", e);
	}
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "SELECT " + "hashid FROM directory_results " + "WHERE hashid=? AND evaluator_id=?;");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, BasicCoCoMoEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    return resultSet.next();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for results.", e);
	}
    }
}
