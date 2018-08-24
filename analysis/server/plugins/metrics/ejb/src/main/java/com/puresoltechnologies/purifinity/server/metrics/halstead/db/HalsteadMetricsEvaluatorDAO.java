package com.puresoltechnologies.purifinity.server.metrics.halstead.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetric;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadResult;

public class HalsteadMetricsEvaluatorDAO implements MetricsDAO<HalsteadMetricResult, HalsteadMetricResult> {

    @Inject
    @HalsteadMetricEvaluatorStoreConnection
    private Connection connection;

    @Override
    public void storeFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    HalsteadMetricResult halsteadMetricResult) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("UPSERT INTO halstead_metric.file_results (hashid, " + "evaluator_id, "
			    + "source_code_location, " + "code_range_type, " + "code_range_name, " + "operators, "
			    + "operands, " + "differentOperators, " + "differentOperands, " + "totalOperators, "
			    + "totalOperands, " + "vocabularySize, " + "programLength, " + "halsteadLength, "
			    + "halsteadVolume, " + "difficulty, " + "programLevel, " + "implementationEffort, "
			    + "implementationTime, " + "estimatedBugs) "
			    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    HalsteadResult halsteadResult = halsteadMetricResult.getHalsteadResult();
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, HalsteadMetric.ID);
	    preparedStatement.setString(3, PropertiesUtils.toString(sourceCodeLocation.getSerialization()));
	    preparedStatement.setString(4, codeRange.getType().name());
	    preparedStatement.setString(5, codeRange.getSimpleName());
	    preparedStatement.setString(6, JSONSerializer.toJSONString(halsteadResult.getOperators()));
	    preparedStatement.setString(7, JSONSerializer.toJSONString(halsteadResult.getOperands()));
	    preparedStatement.setInt(8, halsteadResult.getDifferentOperators());
	    preparedStatement.setInt(9, halsteadResult.getDifferentOperands());
	    preparedStatement.setInt(10, halsteadResult.getTotalOperators());
	    preparedStatement.setInt(11, halsteadResult.getTotalOperands());
	    preparedStatement.setInt(12, halsteadResult.getVocabularySize());
	    preparedStatement.setInt(13, halsteadResult.getProgramLength());
	    preparedStatement.setDouble(14, halsteadResult.getHalsteadLength());
	    preparedStatement.setDouble(15, halsteadResult.getHalsteadVolume());
	    preparedStatement.setDouble(16, halsteadResult.getDifficulty());
	    preparedStatement.setDouble(17, halsteadResult.getProgramLevel());
	    preparedStatement.setDouble(18, halsteadResult.getImplementationEffort());
	    preparedStatement.setDouble(19, halsteadResult.getImplementationTime());
	    preparedStatement.setDouble(20, halsteadResult.getEstimatedBugs());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException | IOException e) {
	    throw new EvaluationStoreException("Could not store file results.", e);
	}
    }

    @Override
    public List<HalsteadMetricResult> readFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    List<HalsteadMetricResult> halsteadMetricResults = new ArrayList<>();
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "source_code_location, "
		    + "code_range_type, " + "code_range_name, " + "operators, " + "operands, " + "differentOperators, "
		    + "differentOperands, " + "totalOperators, " + "totalOperands, " + "vocabularySize, "
		    + "programLength, " + "halsteadLength, " + "halsteadVolume, " + "difficulty, " + "programLevel, "
		    + "implementationEffort, " + "implementationTime, " + "estimatedBugs "
		    + "FROM halstead_metric.file_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, HalsteadMetric.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    while (resultSet.next()) {
		Properties sourceCodeLocationProperties = PropertiesUtils.fromString(resultSet.getString(1));
		SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
			.createFromSerialization(sourceCodeLocationProperties);
		CodeRangeType codeRangeType = CodeRangeType.valueOf(resultSet.getString(2));
		String codeRangeName = resultSet.getString(3);
		@SuppressWarnings("unchecked")
		Map<String, Integer> operators = JSONSerializer.fromJSONString(resultSet.getString(4), Map.class);
		@SuppressWarnings("unchecked")
		Map<String, Integer> operands = JSONSerializer.fromJSONString(resultSet.getString(5), Map.class);
		int differentOperators = resultSet.getInt(6);
		int differentOperands = resultSet.getInt(7);
		int totalOperators = resultSet.getInt(8);
		int totalOperands = resultSet.getInt(9);
		int vocabularySize = resultSet.getInt(10);
		int programLength = resultSet.getInt(11);
		double halsteadLength = resultSet.getDouble(12);
		double halsteadVolume = resultSet.getDouble(13);
		double difficulty = resultSet.getDouble(14);
		double programLevel = resultSet.getDouble(15);
		double implementationEffort = resultSet.getDouble(16);
		double implementationTime = resultSet.getDouble(17);
		double estimatedBugs = resultSet.getDouble(18);
		HalsteadResult halsteadResult = new HalsteadResult(operators, operands, differentOperators,
			differentOperands, totalOperators, totalOperands, vocabularySize, programLength, halsteadLength,
			halsteadVolume, difficulty, programLevel, implementationEffort, implementationTime,
			estimatedBugs);
		HalsteadMetricResult halsteadMetricResult = new HalsteadMetricResult(sourceCodeLocation, codeRangeType,
			codeRangeName, halsteadResult);
		halsteadMetricResults.add(halsteadMetricResult);
	    }
	    return halsteadMetricResults;
	} catch (SQLException | IOException e) {
	    throw new EvaluationStoreException("Could not read file results.", e);
	}
    }

    @Override
    public void storeDirectoryResults(HashId hashId, HalsteadMetricResult halsteadMetricResult)
	    throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("UPSERT INTO halstead_metric.directory_results (hashid, " + "evaluator_id, "
			    + "operators, " + "operands, " + "differentOperators, " + "differentOperands, "
			    + "totalOperators, " + "totalOperands, " + "vocabularySize, " + "programLength, "
			    + "halsteadLength, " + "halsteadVolume, " + "difficulty, " + "programLevel, "
			    + "implementationEffort, " + "implementationTime, " + "estimatedBugs) "
			    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    HalsteadResult halsteadResult = halsteadMetricResult.getHalsteadResult();
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, HalsteadMetric.ID);
	    preparedStatement.setString(3, JSONSerializer.toJSONString(halsteadResult.getOperators()));
	    preparedStatement.setString(4, JSONSerializer.toJSONString(halsteadResult.getOperands()));
	    preparedStatement.setInt(5, halsteadResult.getDifferentOperators());
	    preparedStatement.setInt(6, halsteadResult.getDifferentOperands());
	    preparedStatement.setInt(7, halsteadResult.getTotalOperators());
	    preparedStatement.setInt(8, halsteadResult.getTotalOperands());
	    preparedStatement.setInt(9, halsteadResult.getVocabularySize());
	    preparedStatement.setInt(10, halsteadResult.getProgramLength());
	    preparedStatement.setDouble(11, halsteadResult.getHalsteadLength());
	    preparedStatement.setDouble(12, halsteadResult.getHalsteadVolume());
	    preparedStatement.setDouble(13, halsteadResult.getDifficulty());
	    preparedStatement.setDouble(14, halsteadResult.getProgramLevel());
	    preparedStatement.setDouble(15, halsteadResult.getImplementationEffort());
	    preparedStatement.setDouble(16, halsteadResult.getImplementationTime());
	    preparedStatement.setDouble(17, halsteadResult.getEstimatedBugs());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException | IOException e) {
	    throw new EvaluationStoreException("Could not store directory results.", e);
	}
    }

    @Override
    public HalsteadMetricResult readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "operators, " + "operands, "
		    + "differentOperators, " + "differentOperands, " + "totalOperators, " + "totalOperands, "
		    + "vocabularySize, " + "programLength, " + "halsteadLength, " + "halsteadVolume, " + "difficulty, "
		    + "programLevel, " + "implementationEffort, " + "implementationTime, " + "estimatedBugs "
		    + "FROM halstead_metric.directory_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, HalsteadMetric.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (!resultSet.next()) {
		return null;
	    }
	    @SuppressWarnings("unchecked")
	    Map<String, Integer> operators = JSONSerializer.fromJSONString(resultSet.getString(1), Map.class);
	    @SuppressWarnings("unchecked")
	    Map<String, Integer> operands = JSONSerializer.fromJSONString(resultSet.getString(2), Map.class);
	    int differentOperators = resultSet.getInt(3);
	    int differentOperands = resultSet.getInt(4);
	    int totalOperators = resultSet.getInt(5);
	    int totalOperands = resultSet.getInt(6);
	    int vocabularySize = resultSet.getInt(7);
	    int programLength = resultSet.getInt(8);
	    double halsteadLength = resultSet.getDouble(9);
	    double halsteadVolume = resultSet.getDouble(10);
	    double difficulty = resultSet.getDouble(11);
	    double programLevel = resultSet.getDouble(12);
	    double implementationEffort = resultSet.getDouble(13);
	    double implementationTime = resultSet.getDouble(14);
	    double estimatedBugs = resultSet.getDouble(15);
	    HalsteadResult halsteadResult = new HalsteadResult(operators, operands, differentOperators,
		    differentOperands, totalOperators, totalOperands, vocabularySize, programLength, halsteadLength,
		    halsteadVolume, difficulty, programLevel, implementationEffort, implementationTime, estimatedBugs);
	    HalsteadMetricResult halsteadMetricResult = new HalsteadMetricResult(null, CodeRangeType.DIRECTORY, "",
		    halsteadResult);
	    return halsteadMetricResult;
	} catch (SQLException | IOException e) {
	    throw new EvaluationStoreException("Could not read directory results.", e);
	}
    }

    @Override
    public boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "SELECT " + "hashid FROM halstead_metric.file_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, HalsteadMetric.ID);
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
		    "SELECT " + "hashid FROM halstead_metric.directory_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, HalsteadMetric.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    return resultSet.next();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for results.", e);
	}
    }
}
