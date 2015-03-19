package com.puresoltechnologies.purifinity.server.metrics.sloc.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCResult;

public class SLOCMetricEvaluatorDAO implements
	MetricsDAO<SLOCResult, SLOCResult> {

    @Inject
    @SLOCEvaluatorStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    @Override
    public void storeFileResults(HashId hashId,
	    SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    SLOCResult slocResult) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO file_results (hashid, "
				+ "evaluator_id, "
				+ "source_code_location, "
				+ "code_range_type, "
				+ "code_range_name, "
				+ "phyLOC, "
				+ "proLOC, "
				+ "comLOC, "
				+ "blLOC, "
				+ "line_length_count, "
				+ "line_length_min, "
				+ "line_length_max, "
				+ "line_length_avg, "
				+ "line_length_median, "
				+ "line_length_stdDev) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	SLOCMetric slocMetric = slocResult.getSLOCMetric();
	Statistics lineStatistics = slocMetric.getLineStatistics();
	BoundStatement boundStatement = preparedStatement
		.bind(hashId.toString(), SLOCMetricCalculator.ID,
			PropertiesUtils.toString(sourceCodeLocation
				.getSerialization()), codeRange.getType()
				.name(), codeRange.getSimpleName(), slocMetric
				.getPhyLOC(), slocMetric.getProLOC(),
			slocMetric.getComLOC(), slocMetric.getBlLOC(),
			lineStatistics.getCount(), lineStatistics.getMin(),
			lineStatistics.getMax(), lineStatistics.getAvg(),
			lineStatistics.getMedian(), lineStatistics.getStdDev());
	session.execute(boundStatement);
    }

    @Override
    public boolean hasFileResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "hashid FROM file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), SLOCMetricCalculator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	return resultSet.one() != null;
    }

    @Override
    public List<SLOCResult> readFileResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "source_code_location, " + "code_range_type, "
			+ "code_range_name, " + "phyLOC, " + "proLOC, "
			+ "comLOC, " + "blLOC, " + "line_length_count, "
			+ "line_length_min, " + "line_length_max, "
			+ "line_length_avg, " + "line_length_median, "
			+ "line_length_stdDev FROM file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), SLOCMetricCalculator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	if (resultSet.isExhausted()) {
	    return null;
	}
	List<SLOCResult> slocResults = new ArrayList<>();
	while (!resultSet.isExhausted()) {
	    Row row = resultSet.one();
	    Properties sourceCodeLocationProperties = PropertiesUtils
		    .fromString(row.getString(0));
	    SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
		    .createFromSerialization(sourceCodeLocationProperties);
	    CodeRangeType codeRangeType = CodeRangeType.valueOf(row
		    .getString(1));
	    String codeRangeName = row.getString(2);
	    int phyLOC = row.getInt(3);
	    int proLOC = row.getInt(4);
	    int comLOC = row.getInt(5);
	    int blLOC = row.getInt(6);
	    int count = row.getInt(7);
	    double min = row.getDouble(8);
	    double max = row.getDouble(9);
	    double avg = row.getDouble(10);
	    double median = row.getDouble(11);
	    double stdDev = row.getDouble(12);
	    Statistics lineStatistics = new Statistics(count, min, max, avg,
		    median, stdDev);
	    SLOCMetric slocMetric = new SLOCMetric(phyLOC, proLOC, comLOC,
		    blLOC, lineStatistics);
	    SLOCResult slocResult = new SLOCResult(sourceCodeLocation,
		    codeRangeType, codeRangeName, slocMetric);
	    slocResults.add(slocResult);
	}
	return slocResults;
    }

    @Override
    public void storeDirectoryResults(HashId hashId, SLOCResult slocResult) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO directory_results (hashid, "
				+ "evaluator_id, "
				+ "phyLOC, "
				+ "proLOC, "
				+ "comLOC, "
				+ "blLOC, "
				+ "line_length_count, "
				+ "line_length_min, "
				+ "line_length_max, "
				+ "line_length_avg, "
				+ "line_length_median, "
				+ "line_length_stdDev) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	SLOCMetric slocMetric = slocResult.getSLOCMetric();
	Statistics lineStatistics = slocMetric.getLineStatistics();
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), SLOCMetricCalculator.ID,
		slocMetric.getPhyLOC(), slocMetric.getProLOC(),
		slocMetric.getComLOC(), slocMetric.getBlLOC(),
		lineStatistics.getCount(), lineStatistics.getMin(),
		lineStatistics.getMax(), lineStatistics.getAvg(),
		lineStatistics.getMedian(), lineStatistics.getStdDev());
	session.execute(boundStatement);
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "hashid FROM directory_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), SLOCMetricCalculator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	return resultSet.one() != null;
    }

    @Override
    public SLOCResult readDirectoryResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT " + "phyLOC, "
			+ "proLOC, " + "comLOC, " + "blLOC, "
			+ "line_length_count, " + "line_length_min, "
			+ "line_length_max, " + "line_length_avg, "
			+ "line_length_median, "
			+ "line_length_stdDev FROM directory_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), SLOCMetricCalculator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	if (resultSet.isExhausted()) {
	    return null;
	}
	Row row = resultSet.one();
	int phyLOC = row.getInt(0);
	int proLOC = row.getInt(1);
	int comLOC = row.getInt(2);
	int blLOC = row.getInt(3);
	int count = row.getInt(4);
	double min = row.getDouble(5);
	double max = row.getDouble(6);
	double avg = row.getDouble(7);
	double median = row.getDouble(8);
	double stdDev = row.getDouble(9);
	Statistics lineStatistics = new Statistics(count, min, max, avg,
		median, stdDev);
	SLOCMetric slocMetric = new SLOCMetric(phyLOC, proLOC, comLOC, blLOC,
		lineStatistics);
	SLOCResult slocResult = new SLOCResult(null, CodeRangeType.DIRECTORY,
		"", slocMetric);
	return slocResult;
    }
}
