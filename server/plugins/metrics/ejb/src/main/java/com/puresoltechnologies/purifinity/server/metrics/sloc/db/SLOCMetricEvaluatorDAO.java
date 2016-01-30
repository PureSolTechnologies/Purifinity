package com.puresoltechnologies.purifinity.server.metrics.sloc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.statistics.Statistics;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCResult;

public class SLOCMetricEvaluatorDAO implements MetricsDAO<SLOCResult, SLOCResult> {

    @Inject
    @SLOCEvaluatorStoreConnection
    private Connection connection;

    @Override
    public void storeFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    SLOCResult slocResult) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("UPSERT INTO sloc_metric.file_results (hashid, " + "evaluator_id, "
			    + "source_code_location, " + "code_range_type, " + "code_range_name, " + "phyLOC, "
			    + "proLOC, " + "comLOC, " + "blLOC, " + "line_length_count, " + "line_length_min, "
			    + "line_length_max, " + "line_length_avg, " + "line_length_median, "
			    + "line_length_stdDev) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    SLOCMetric slocMetric = slocResult.getSLOCMetric();
	    Statistics lineStatistics = slocMetric.getLineStatistics();
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, SLOCMetricCalculator.ID);
	    preparedStatement.setString(3, PropertiesUtils.toString(sourceCodeLocation.getSerialization()));
	    preparedStatement.setString(4, codeRange.getType().name());
	    preparedStatement.setString(5, codeRange.getSimpleName());
	    preparedStatement.setInt(6, slocMetric.getPhyLOC());
	    preparedStatement.setInt(7, slocMetric.getProLOC());
	    preparedStatement.setInt(8, slocMetric.getComLOC());
	    preparedStatement.setInt(9, slocMetric.getBlLOC());
	    preparedStatement.setInt(10, lineStatistics.getCount());
	    preparedStatement.setDouble(11, lineStatistics.getMin());
	    preparedStatement.setDouble(12, lineStatistics.getMax());
	    preparedStatement.setDouble(13, lineStatistics.getAvg());
	    preparedStatement.setDouble(14, lineStatistics.getMedian());
	    preparedStatement.setDouble(15, lineStatistics.getStdDev());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not write results.", e);
	}
    }

    @Override
    public boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "SELECT " + "hashid FROM sloc_metric.file_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, SLOCMetricCalculator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    return resultSet.next();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for results.", e);
	}
    }

    @Override
    public List<SLOCResult> readFileResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "source_code_location, "
		    + "code_range_type, " + "code_range_name, " + "phyLOC, " + "proLOC, " + "comLOC, " + "blLOC, "
		    + "line_length_count, " + "line_length_min, " + "line_length_max, " + "line_length_avg, "
		    + "line_length_median, " + "line_length_stdDev FROM sloc_metric.file_results "
		    + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, SLOCMetricCalculator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (!resultSet.next()) {
		return null;
	    }
	    List<SLOCResult> slocResults = new ArrayList<>();
	    do {
		Properties sourceCodeLocationProperties = PropertiesUtils.fromString(resultSet.getString(1));
		SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
			.createFromSerialization(sourceCodeLocationProperties);
		CodeRangeType codeRangeType = CodeRangeType.valueOf(resultSet.getString(2));
		String codeRangeName = resultSet.getString(3);
		int phyLOC = resultSet.getInt(4);
		int proLOC = resultSet.getInt(5);
		int comLOC = resultSet.getInt(6);
		int blLOC = resultSet.getInt(7);
		int count = resultSet.getInt(8);
		double min = resultSet.getDouble(9);
		double max = resultSet.getDouble(10);
		double avg = resultSet.getDouble(11);
		double median = resultSet.getDouble(12);
		double stdDev = resultSet.getDouble(13);
		Statistics lineStatistics = new Statistics(count, min, max, avg, median, stdDev);
		SLOCMetric slocMetric = new SLOCMetric(phyLOC, proLOC, comLOC, blLOC, lineStatistics);
		SLOCResult slocResult = new SLOCResult(sourceCodeLocation, codeRangeType, codeRangeName, slocMetric);
		slocResults.add(slocResult);
	    } while (resultSet.next());
	    return slocResults;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read results.", e);
	}
    }

    @Override
    public void storeDirectoryResults(HashId hashId, SLOCResult slocResult) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("UPSERT INTO sloc_metric.directory_results (hashid, " + "evaluator_id, "
			    + "phyLOC, " + "proLOC, " + "comLOC, " + "blLOC, " + "line_length_count, "
			    + "line_length_min, " + "line_length_max, " + "line_length_avg, " + "line_length_median, "
			    + "line_length_stdDev) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    SLOCMetric slocMetric = slocResult.getSLOCMetric();
	    Statistics lineStatistics = slocMetric.getLineStatistics();
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, SLOCMetricCalculator.ID);
	    preparedStatement.setInt(3, slocMetric.getPhyLOC());
	    preparedStatement.setInt(4, slocMetric.getProLOC());
	    preparedStatement.setInt(5, slocMetric.getComLOC());
	    preparedStatement.setInt(6, slocMetric.getBlLOC());
	    preparedStatement.setInt(7, lineStatistics.getCount());
	    preparedStatement.setDouble(8, lineStatistics.getMin());
	    preparedStatement.setDouble(9, lineStatistics.getMax());
	    preparedStatement.setDouble(10, lineStatistics.getAvg());
	    preparedStatement.setDouble(11, lineStatistics.getMedian());
	    preparedStatement.setDouble(12, lineStatistics.getStdDev());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not write results.", e);
	}
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "SELECT " + "hashid FROM sloc_metric.directory_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, SLOCMetricCalculator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    return resultSet.next();
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not check for results.", e);
	}
    }

    @Override
    public SLOCResult readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement("SELECT " + "phyLOC, " + "proLOC, "
		    + "comLOC, " + "blLOC, " + "line_length_count, " + "line_length_min, " + "line_length_max, "
		    + "line_length_avg, " + "line_length_median, "
		    + "line_length_stdDev FROM sloc_metric.directory_results " + "WHERE hashid=? AND evaluator_id=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.setString(2, SLOCMetricCalculator.ID);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (!resultSet.next()) {
		return null;
	    }
	    int phyLOC = resultSet.getInt(1);
	    int proLOC = resultSet.getInt(2);
	    int comLOC = resultSet.getInt(3);
	    int blLOC = resultSet.getInt(4);
	    int count = resultSet.getInt(5);
	    double min = resultSet.getDouble(6);
	    double max = resultSet.getDouble(7);
	    double avg = resultSet.getDouble(8);
	    double median = resultSet.getDouble(9);
	    double stdDev = resultSet.getDouble(10);
	    Statistics lineStatistics = new Statistics(count, min, max, avg, median, stdDev);
	    SLOCMetric slocMetric = new SLOCMetric(phyLOC, proLOC, comLOC, blLOC, lineStatistics);
	    SLOCResult slocResult = new SLOCResult(null, CodeRangeType.DIRECTORY, "", slocMetric);
	    return slocResult;
	} catch (SQLException e) {
	    throw new EvaluationStoreException("Could not read results.", e);
	}
    }
}
