package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCOCOMOAttribute;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoFileResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.Rating;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.SoftwareProject;

public class IntermediateCoCoMoEvaluatorDAO
	implements
	MetricsDAO<IntermediateCoCoMoFileResults, IntermediateCoCoMoDirectoryResults> {

    @Inject
    @IntermediateCoCoMoEvaluatorStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    @Override
    public void storeFileResults(HashId hashId,
	    SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    IntermediateCoCoMoFileResults fileResults) {
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
				+ "project, "
				+ "averageSalary, "
				+ "currency, "
				+ "attributes) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	Map<String, String> attributes = attributesToMap(fileResults);
	BoundStatement boundStatement = preparedStatement
		.bind(hashId.toString(),
			IntermediateCoCoMoEvaluator.ID, //
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
			fileResults.getProject().name(),//
			fileResults.getAverageSalary(),//
			fileResults.getCurrency(),//
			attributes);
	session.execute(boundStatement);
    }

    private Map<String, String> attributesToMap(
	    IntermediateCoCoMoResults fileResults) {
	Map<String, String> attributes = new HashMap<>();
	for (Entry<IntermediateCOCOMOAttribute, Rating> attributeEntry : fileResults
		.getAttributes().entrySet()) {
	    attributes.put(attributeEntry.getKey().getName(), attributeEntry
		    .getValue().name());
	}
	return attributes;
    }

    @Override
    public List<IntermediateCoCoMoFileResults> readFileResults(HashId hashId) {
	List<IntermediateCoCoMoFileResults> fileResults = new ArrayList<>();
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "source_code_location, " + "phyLOC, " + "project, "
			+ "averageSalary, " + "currency, " + "attributes "
			+ "FROM file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), IntermediateCoCoMoEvaluator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	while (!resultSet.isExhausted()) {
	    Row row = resultSet.one();
	    Properties sourceCodeLocationProperties = PropertiesUtils
		    .fromString(row.getString(0));
	    SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
		    .createFromSerialization(sourceCodeLocationProperties);
	    int phyLOC = row.getInt(1);
	    SoftwareProject project = SoftwareProject.valueOf(row.getString(2));
	    double averageSalary = row.getDouble(3);
	    String currency = row.getString(4);
	    Map<String, String> attributeMap = row.getMap(5, String.class,
		    String.class);
	    Map<IntermediateCOCOMOAttribute, Rating> attributes = mapToAttributes(attributeMap);

	    IntermediateCoCoMoFileResults results = new IntermediateCoCoMoFileResults(
		    IntermediateCoCoMoEvaluator.ID,
		    IntermediateCoCoMoEvaluator.PLUGIN_VERSION, hashId,
		    sourceCodeLocation, new Date());
	    results.setAverageSalary(averageSalary, currency);
	    results.setProject(project);
	    results.setSloc(phyLOC);
	    results.setAttributes(attributes);
	    fileResults.add(results);
	}
	return fileResults;
    }

    private Map<IntermediateCOCOMOAttribute, Rating> mapToAttributes(
	    Map<String, String> attributeMap) {
	Map<IntermediateCOCOMOAttribute, Rating> attributes = new HashMap<>();
	for (Entry<String, String> attributeMapEntry : attributeMap.entrySet()) {
	    attributes.put(IntermediateCOCOMOAttribute
		    .valueOf(attributeMapEntry.getKey()), Rating
		    .valueOf(attributeMapEntry.getValue()));
	}
	return attributes;
    }

    @Override
    public void storeDirectoryResults(HashId hashId,
	    IntermediateCoCoMoDirectoryResults directoryResult) {
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
				+ "project, "
				+ "averageSalary, "
				+ "currency, "
				+ "attributes) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	Map<String, String> attributes = attributesToMap(directoryResult);
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), IntermediateCoCoMoEvaluator.ID,
		directoryResult.getPhyLOC(),//
		directoryResult.getKsloc(),//
		directoryResult.getPersonMonth(),//
		directoryResult.getPersonYears(),//
		directoryResult.getScheduledMonth(),//
		directoryResult.getScheduledYears(),//
		directoryResult.getTeamSize(),//
		directoryResult.getEstimatedCosts(),//
		directoryResult.getProject().name(),//
		directoryResult.getAverageSalary(),//
		directoryResult.getCurrency(),//
		attributes);
	session.execute(boundStatement);
    }

    @Override
    public IntermediateCoCoMoDirectoryResults readDirectoryResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT " + "phyLOC, "
			+ "project, " + "averageSalary, " + "currency, "
			+ "attributes " + "FROM directory_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), IntermediateCoCoMoEvaluator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	if (resultSet.isExhausted()) {
	    return null;
	}
	Row row = resultSet.one();

	int phyLOC = row.getInt(0);
	SoftwareProject project = SoftwareProject.valueOf(row.getString(1));
	double averageSalary = row.getDouble(2);
	String currency = row.getString(3);
	Map<String, String> attributeMap = row.getMap(4, String.class,
		String.class);
	Map<IntermediateCOCOMOAttribute, Rating> attributes = mapToAttributes(attributeMap);

	IntermediateCoCoMoDirectoryResults results = new IntermediateCoCoMoDirectoryResults(
		IntermediateCoCoMoEvaluator.ID,
		IntermediateCoCoMoEvaluator.PLUGIN_VERSION, hashId, new Date());
	results.setAverageSalary(averageSalary, currency);
	results.setProject(project);
	results.setSloc(phyLOC);
	results.setAttributes(attributes);
	return results;
    }

    @Override
    public boolean hasFileResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "hashid FROM file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), IntermediateCoCoMoEvaluator.ID);
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
		hashId.toString(), IntermediateCoCoMoEvaluator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	return !resultSet.isExhausted();
    }
}
