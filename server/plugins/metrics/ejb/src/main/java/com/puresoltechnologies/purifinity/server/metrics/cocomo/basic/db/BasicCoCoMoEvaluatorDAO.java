package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.BasicCoCoMoFileResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.SoftwareProject;

public class BasicCoCoMoEvaluatorDAO implements
	MetricsDAO<BasicCoCoMoFileResults, BasicCoCoMoDirectoryResults> {

    @Inject
    @BasicCoCoMoEvaluatorStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    @Override
    public void storeFileResults(HashId hashId,
	    SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    BasicCoCoMoFileResults fileResults) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO file_results (hashid, "
				+ "evaluator_id, "
				+ "source_code_location, "
				+ "code_range_type, "
				+ "code_range_name, "
				+ "phyLOC, "
				+ "ksloc, "
				+ "personMonth, "
				+ "personYears, "
				+ "scheduledMonth, "
				+ "scheduledYears, "
				+ "teamSize, "
				+ "estimatedCosts, "
				+ "c1, "
				+ "c2, "
				+ "c3, "
				+ "complexity, "
				+ "averageSalary, "
				+ "currency) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

	BoundStatement boundStatement = preparedStatement
		.bind(hashId.toString(),
			BasicCoCoMoEvaluator.ID, //
			PropertiesUtils.toString(sourceCodeLocation
				.getSerialization()), //
			codeRange.getType().name(), //
			codeRange.getSimpleName(),//
			fileResults.getPhyLOC(),//
			fileResults.getKsloc(),//
			fileResults.getPersonMonth(),//
			fileResults.getPersonYears(),//
			fileResults.getScheduledMonth(),//
			fileResults.getScheduledYears(),//
			fileResults.getTeamSize(),//
			fileResults.getEstimatedCosts(),//
			fileResults.getC1(),//
			fileResults.getC2(),//
			fileResults.getC3(),//
			fileResults.getComplexity().name(),//
			fileResults.getAverageSalary(),//
			fileResults.getCurrency()//
		);
	session.execute(boundStatement);
    }

    @Override
    public List<BasicCoCoMoFileResults> readFileResults(HashId hashId) {
	List<BasicCoCoMoFileResults> fileResults = new ArrayList<>();
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "source_code_location, " + "phyLOC, "
			+ "complexity, " + "averageSalary, " + "currency "
			+ "FROM file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), BasicCoCoMoEvaluator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	while (!resultSet.isExhausted()) {
	    Row row = resultSet.one();
	    Properties sourceCodeLocationProperties = PropertiesUtils
		    .fromString(row.getString(0));
	    SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
		    .createFromSerialization(sourceCodeLocationProperties);
	    int phyLOC = row.getInt(1);
	    SoftwareProject complexity = SoftwareProject.valueOf(row
		    .getString(2));
	    double averageSalary = row.getDouble(3);
	    String currency = row.getString(4);

	    BasicCoCoMoFileResults results = new BasicCoCoMoFileResults(
		    BasicCoCoMoEvaluator.ID,
		    BasicCoCoMoEvaluator.PLUGIN_VERSION, hashId,
		    sourceCodeLocation, new Date());
	    results.setAverageSalary(averageSalary, currency);
	    results.setComplexity(complexity);
	    results.setSloc(phyLOC);
	    fileResults.add(results);
	}
	return fileResults;
    }

    @Override
    public void storeDirectoryResults(HashId hashId,
	    BasicCoCoMoDirectoryResults directoryResult) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO directory_results (hashid, "
				+ "evaluator_id, "
				+ "phyLOC, "
				+ "ksloc, "
				+ "personMonth, "
				+ "personYears, "
				+ "scheduledMonth, "
				+ "scheduledYears, "
				+ "teamSize, "
				+ "estimatedCosts, "
				+ "c1, "
				+ "c2, "
				+ "c3, "
				+ "complexity, "
				+ "averageSalary, "
				+ "currency) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), BasicCoCoMoEvaluator.ID,
		directoryResult.getPhyLOC(),//
		directoryResult.getKsloc(),//
		directoryResult.getPersonMonth(),//
		directoryResult.getPersonYears(),//
		directoryResult.getScheduledMonth(),//
		directoryResult.getScheduledYears(),//
		directoryResult.getTeamSize(),//
		directoryResult.getEstimatedCosts(),//
		directoryResult.getC1(),//
		directoryResult.getC2(),//
		directoryResult.getC3(),//
		directoryResult.getComplexity().name(),//
		directoryResult.getAverageSalary(),//
		directoryResult.getCurrency()//
		);
	session.execute(boundStatement);
    }

    @Override
    public BasicCoCoMoDirectoryResults readDirectoryResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT " + "phyLOC, "
			+ "complexity, " + "averageSalary, " + "currency "
			+ "FROM directory_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), BasicCoCoMoEvaluator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	if (resultSet.isExhausted()) {
	    return null;
	}
	Row row = resultSet.one();

	int phyLOC = row.getInt(0);
	SoftwareProject complexity = SoftwareProject.valueOf(row.getString(1));
	double averageSalary = row.getDouble(2);
	String currency = row.getString(3);

	BasicCoCoMoDirectoryResults results = new BasicCoCoMoDirectoryResults(
		BasicCoCoMoEvaluator.ID, BasicCoCoMoEvaluator.PLUGIN_VERSION,
		hashId, new Date());
	results.setAverageSalary(averageSalary, currency);
	results.setComplexity(complexity);
	results.setSloc(phyLOC);
	return results;
    }

    @Override
    public boolean hasFileResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "hashid FROM file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), BasicCoCoMoEvaluator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	return !resultSet.isExhausted();
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "hashid FROM directory_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), BasicCoCoMoEvaluator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	return !resultSet.isExhausted();
    }
}
