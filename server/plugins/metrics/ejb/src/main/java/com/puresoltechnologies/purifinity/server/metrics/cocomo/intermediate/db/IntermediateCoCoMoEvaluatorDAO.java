package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCOCOMOAttribute;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoFileResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.IntermediateCoCoMoResults;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.Rating;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.SoftwareProject;

public class IntermediateCoCoMoEvaluatorDAO
	implements MetricsDAO<IntermediateCoCoMoFileResults, IntermediateCoCoMoDirectoryResults> {

    @Inject
    @IntermediateCoCoMoEvaluatorStoreConnection
    private Connection connection;

    @Override
    public void storeFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    IntermediateCoCoMoFileResults fileResults) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("INSERT INTO intermediate_cocomo.file_results (hashid, " + "evaluator_id, "
			    + "source_code_location, " + "code_range_type, " + "code_range_name, " + "phyLOC, "
			    + "ksloc, " + "personMonth, " + "personYears, " + "scheduledMonth, " + "scheduledYears, "
			    + "teamSize, " + "estimatedCosts, " + "project, " + "averageSalary, " + "currency, "
			    + "attributes) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    Map<String, String> attributes = attributesToMap(fileResults);
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, IntermediateCoCoMoEvaluator.ID);
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
	    preparedStatement.setString(14, fileResults.getProject().name());
	    preparedStatement.setDouble(15, fileResults.getAverageSalary());
	    preparedStatement.setString(16, fileResults.getCurrency());
	    HBaseHelper.writeMap(connection, preparedStatement, 17, 18, String.class, String.class, attributes);
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not write results.", e);
	}
    }

    private Map<String, String> attributesToMap(IntermediateCoCoMoResults fileResults) {
	Map<String, String> attributes = new HashMap<>();
	for (Entry<IntermediateCOCOMOAttribute, Rating> attributeEntry : fileResults.getAttributes().entrySet()) {
	    attributes.put(attributeEntry.getKey().getName(), attributeEntry.getValue().name());
	}
	return attributes;
    }

    @Override
    public List<IntermediateCoCoMoFileResults> readFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    List<IntermediateCoCoMoFileResults> fileResults = new ArrayList<>();
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "source_code_location, "
		    + "phyLOC, " + "project, " + "averageSalary, " + "currency, " + "attributes "
		    + "FROM intermediate_cocomo.file_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, IntermediateCoCoMoEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
		Properties sourceCodeLocationProperties = PropertiesUtils.fromString(resultSet.getString(1));
		SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
			.createFromSerialization(sourceCodeLocationProperties);
		int phyLOC = resultSet.getInt(2);
		SoftwareProject project = SoftwareProject.valueOf(resultSet.getString(3));
		double averageSalary = resultSet.getDouble(4);
		String currency = resultSet.getString(5);
		Map<String, String> attributeMap = HBaseHelper.getMap(resultSet, 5, 6, String.class, String.class);
		Map<IntermediateCOCOMOAttribute, Rating> attributes = mapToAttributes(attributeMap);

		IntermediateCoCoMoFileResults results = new IntermediateCoCoMoFileResults(
			IntermediateCoCoMoEvaluator.ID, IntermediateCoCoMoEvaluator.PLUGIN_VERSION, hashId,
			sourceCodeLocation, new Date());
		results.setAverageSalary(averageSalary, currency);
		results.setProject(project);
		results.setSloc(phyLOC);
		results.setAttributes(attributes);
		fileResults.add(results);
	    }
	    return fileResults;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read results.", e);
	}
    }

    private Map<IntermediateCOCOMOAttribute, Rating> mapToAttributes(Map<String, String> attributeMap) {
	Map<IntermediateCOCOMOAttribute, Rating> attributes = new HashMap<>();
	for (Entry<String, String> attributeMapEntry : attributeMap.entrySet()) {
	    attributes.put(IntermediateCOCOMOAttribute.valueOf(attributeMapEntry.getKey()),
		    Rating.valueOf(attributeMapEntry.getValue()));
	}
	return attributes;
    }

    @Override
    public void storeDirectoryResults(HashId hashId, IntermediateCoCoMoDirectoryResults directoryResult)
	    throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("INSERT INTO intermediate_cocomo.directory_results (hashid, " + "evaluator_id, "
			    + "phyLOC, " + "ksloc, " + "personMonth, " + "personYears, " + "scheduledMonth, "
			    + "scheduledYears, " + "teamSize, " + "estimatedCosts, " + "project, " + "averageSalary, "
			    + "currency, " + "attributes_keys, " + "attributes_values" + ") "
			    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    Map<String, String> attributes = attributesToMap(directoryResult);
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, IntermediateCoCoMoEvaluator.ID);
	    preparedStatement.setInt(3, directoryResult.getPhyLOC());
	    preparedStatement.setDouble(4, directoryResult.getKsloc());
	    preparedStatement.setDouble(5, directoryResult.getPersonMonth());
	    preparedStatement.setDouble(6, directoryResult.getPersonYears());
	    preparedStatement.setDouble(7, directoryResult.getScheduledMonth());
	    preparedStatement.setDouble(8, directoryResult.getScheduledYears());
	    preparedStatement.setDouble(9, directoryResult.getTeamSize());
	    preparedStatement.setDouble(10, directoryResult.getEstimatedCosts());
	    preparedStatement.setString(11, directoryResult.getProject().name());
	    preparedStatement.setDouble(12, directoryResult.getAverageSalary());
	    preparedStatement.setString(13, directoryResult.getCurrency());
	    HBaseHelper.writeMap(connection, preparedStatement, 14, 15, String.class, String.class, attributes);
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not write results.", e);
	}
    }

    @Override
    public IntermediateCoCoMoDirectoryResults readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "SELECT " + "phyLOC, " + "project, " + "averageSalary, " + "currency, " + "attributes "
			    + "FROM intermediate_cocomo.directory_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, IntermediateCoCoMoEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (!resultSet.next()) {
		return null;
	    }

	    int phyLOC = resultSet.getInt(1);
	    SoftwareProject project = SoftwareProject.valueOf(resultSet.getString(2));
	    double averageSalary = resultSet.getDouble(3);
	    String currency = resultSet.getString(4);
	    Map<String, String> attributeMap = HBaseHelper.getMap(resultSet, 5, 6, String.class, String.class);
	    Map<IntermediateCOCOMOAttribute, Rating> attributes = mapToAttributes(attributeMap);

	    IntermediateCoCoMoDirectoryResults results = new IntermediateCoCoMoDirectoryResults(
		    IntermediateCoCoMoEvaluator.ID, IntermediateCoCoMoEvaluator.PLUGIN_VERSION, hashId, new Date());
	    results.setAverageSalary(averageSalary, currency);
	    results.setProject(project);
	    results.setSloc(phyLOC);
	    results.setAttributes(attributes);
	    return results;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read results.", e);
	}
    }

    @Override
    public boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "SELECT " + "hashid FROM intermediate_cocomo.file_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, IntermediateCoCoMoEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    return resultSet.next();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for results.", e);
	}
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT "
		    + "hashid FROM intermediate_cocomo.directory_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, IntermediateCoCoMoEvaluator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    return resultSet.next();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for results.", e);
	}
    }
}
