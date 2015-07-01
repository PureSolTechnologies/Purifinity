package com.puresoltechnologies.purifinity.server.metrics.halstead.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetric;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadResult;

public class HalsteadMetricsEvaluatorDAO implements MetricsDAO<HalsteadMetricResult, HalsteadMetricResult> {

    @Inject
    @HalsteadMetricEvaluatorStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    @Override
    public void storeFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    HalsteadMetricResult halsteadMetricResult) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(session,
		    "INSERT INTO file_results (hashid, " + "evaluator_id, " + "source_code_location, "
			    + "code_range_type, " + "code_range_name, " + "operators, " + "operands, "
			    + "differentOperators, " + "differentOperands, " + "totalOperators, " + "totalOperands, "
			    + "vocabularySize, " + "programLength, " + "halsteadLength, " + "halsteadVolume, "
			    + "difficulty, " + "programLevel, " + "implementationEffort, " + "implementationTime, "
			    + "estimatedBugs) "
			    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	    HalsteadResult halsteadResult = halsteadMetricResult.getHalsteadResult();
	    BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), HalsteadMetric.ID,
		    PropertiesUtils.toString(sourceCodeLocation.getSerialization()), codeRange.getType().name(),
		    codeRange.getSimpleName(), JSONSerializer.toJSONString(halsteadResult.getOperators()),
		    JSONSerializer.toJSONString(halsteadResult.getOperands()), halsteadResult.getDifferentOperators(),
		    halsteadResult.getDifferentOperands(), halsteadResult.getTotalOperators(),
		    halsteadResult.getTotalOperands(), halsteadResult.getVocabularySize(),
		    halsteadResult.getProgramLength(), halsteadResult.getHalsteadLength(),
		    halsteadResult.getHalsteadVolume(), halsteadResult.getDifficulty(),
		    halsteadResult.getProgramLevel(), halsteadResult.getImplementationEffort(),
		    halsteadResult.getImplementationTime(), halsteadResult.getEstimatedBugs());
	    session.execute(boundStatement);
	} catch (IOException e) {
	    throw new EvaluationStoreException("Could not store file results.", e);
	}
    }

    @Override
    public List<HalsteadMetricResult> readFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    List<HalsteadMetricResult> halsteadMetricResults = new ArrayList<>();
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(session,
		    "SELECT " + "source_code_location, " + "code_range_type, " + "code_range_name, " + "operators, "
			    + "operands, " + "differentOperators, " + "differentOperands, " + "totalOperators, "
			    + "totalOperands, " + "vocabularySize, " + "programLength, " + "halsteadLength, "
			    + "halsteadVolume, " + "difficulty, " + "programLevel, " + "implementationEffort, "
			    + "implementationTime, " + "estimatedBugs " + "FROM file_results "
			    + "WHERE hashid=? AND evaluator_id=?;");
	    BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), HalsteadMetric.ID);
	    ResultSet resultSet = session.execute(boundStatement);
	    while (!resultSet.isExhausted()) {
		Row row = resultSet.one();
		Properties sourceCodeLocationProperties = PropertiesUtils.fromString(row.getString(0));
		SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
			.createFromSerialization(sourceCodeLocationProperties);
		CodeRangeType codeRangeType = CodeRangeType.valueOf(row.getString(1));
		String codeRangeName = row.getString(2);
		@SuppressWarnings("unchecked")
		Map<String, Integer> operators = JSONSerializer.fromJSONString(row.getString(3), Map.class);
		@SuppressWarnings("unchecked")
		Map<String, Integer> operands = JSONSerializer.fromJSONString(row.getString(4), Map.class);
		int differentOperators = row.getInt(5);
		int differentOperands = row.getInt(6);
		int totalOperators = row.getInt(7);
		int totalOperands = row.getInt(8);
		int vocabularySize = row.getInt(9);
		int programLength = row.getInt(10);
		double halsteadLength = row.getDouble(11);
		double halsteadVolume = row.getDouble(12);
		double difficulty = row.getDouble(13);
		double programLevel = row.getDouble(14);
		double implementationEffort = row.getDouble(15);
		double implementationTime = row.getDouble(16);
		double estimatedBugs = row.getDouble(17);
		HalsteadResult halsteadResult = new HalsteadResult(operators, operands, differentOperators,
			differentOperands, totalOperators, totalOperands, vocabularySize, programLength, halsteadLength,
			halsteadVolume, difficulty, programLevel, implementationEffort, implementationTime,
			estimatedBugs);
		HalsteadMetricResult halsteadMetricResult = new HalsteadMetricResult(sourceCodeLocation, codeRangeType,
			codeRangeName, halsteadResult);
		halsteadMetricResults.add(halsteadMetricResult);
	    }
	    return halsteadMetricResults;
	} catch (IOException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
	}
    }

    @Override
    public void storeDirectoryResults(HashId hashId, HalsteadMetricResult halsteadMetricResult)
	    throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(session,
		    "INSERT INTO directory_results (hashid, " + "evaluator_id, " + "operators, " + "operands, "
			    + "differentOperators, " + "differentOperands, " + "totalOperators, " + "totalOperands, "
			    + "vocabularySize, " + "programLength, " + "halsteadLength, " + "halsteadVolume, "
			    + "difficulty, " + "programLevel, " + "implementationEffort, " + "implementationTime, "
			    + "estimatedBugs) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	    HalsteadResult halsteadResult = halsteadMetricResult.getHalsteadResult();
	    BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), HalsteadMetric.ID,
		    JSONSerializer.toJSONString(halsteadResult.getOperators()),
		    JSONSerializer.toJSONString(halsteadResult.getOperands()), halsteadResult.getDifferentOperators(),
		    halsteadResult.getDifferentOperands(), halsteadResult.getTotalOperators(),
		    halsteadResult.getTotalOperands(), halsteadResult.getVocabularySize(),
		    halsteadResult.getProgramLength(), halsteadResult.getHalsteadLength(),
		    halsteadResult.getHalsteadVolume(), halsteadResult.getDifficulty(),
		    halsteadResult.getProgramLevel(), halsteadResult.getImplementationEffort(),
		    halsteadResult.getImplementationTime(), halsteadResult.getEstimatedBugs());
	    session.execute(boundStatement);
	} catch (IOException e) {
	    throw new EvaluationStoreException("Could not store directory results.", e);
	}
    }

    @Override
    public HalsteadMetricResult readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(session,
		    "SELECT " + "operators, " + "operands, " + "differentOperators, " + "differentOperands, "
			    + "totalOperators, " + "totalOperands, " + "vocabularySize, " + "programLength, "
			    + "halsteadLength, " + "halsteadVolume, " + "difficulty, " + "programLevel, "
			    + "implementationEffort, " + "implementationTime, " + "estimatedBugs "
			    + "FROM directory_results " + "WHERE hashid=? AND evaluator_id=?;");
	    BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), HalsteadMetric.ID);
	    ResultSet resultSet = session.execute(boundStatement);
	    if (resultSet.isExhausted()) {
		return null;
	    }
	    Row row = resultSet.one();
	    @SuppressWarnings("unchecked")
	    Map<String, Integer> operators = JSONSerializer.fromJSONString(row.getString(0), Map.class);
	    @SuppressWarnings("unchecked")
	    Map<String, Integer> operands = JSONSerializer.fromJSONString(row.getString(1), Map.class);
	    int differentOperators = row.getInt(2);
	    int differentOperands = row.getInt(3);
	    int totalOperators = row.getInt(4);
	    int totalOperands = row.getInt(5);
	    int vocabularySize = row.getInt(6);
	    int programLength = row.getInt(7);
	    double halsteadLength = row.getDouble(8);
	    double halsteadVolume = row.getDouble(9);
	    double difficulty = row.getDouble(10);
	    double programLevel = row.getDouble(11);
	    double implementationEffort = row.getDouble(12);
	    double implementationTime = row.getDouble(13);
	    double estimatedBugs = row.getDouble(14);
	    HalsteadResult halsteadResult = new HalsteadResult(operators, operands, differentOperators,
		    differentOperands, totalOperators, totalOperands, vocabularySize, programLength, halsteadLength,
		    halsteadVolume, difficulty, programLevel, implementationEffort, implementationTime, estimatedBugs);
	    HalsteadMetricResult halsteadMetricResult = new HalsteadMetricResult(null, CodeRangeType.DIRECTORY, "",
		    halsteadResult);
	    return halsteadMetricResult;
	} catch (IOException e) {
	    throw new EvaluationStoreException("Could not read directory results.", e);
	}
    }

    @Override
    public boolean hasFileResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(session,
		"SELECT " + "hashid FROM file_results " + "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), HalsteadMetric.ID);
	ResultSet resultSet = session.execute(boundStatement);
	return !resultSet.isExhausted();
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements.getPreparedStatement(session,
		"SELECT " + "hashid FROM directory_results " + "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString(), HalsteadMetric.ID);
	ResultSet resultSet = session.execute(boundStatement);
	return !resultSet.isExhausted();
    }
}
