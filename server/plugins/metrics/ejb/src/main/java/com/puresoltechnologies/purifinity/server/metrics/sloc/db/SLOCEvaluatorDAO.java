package com.puresoltechnologies.purifinity.server.metrics.sloc.db;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import org.slf4j.Logger;

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
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCResult;

public class SLOCEvaluatorDAO {

    @Inject
    private Logger logger;

    @Inject
    @SLOCEvaluatorStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    public void storeFileResults(HashId hashId, CodeRange codeRange,
	    SLOCMetric slocMetric) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO file_results (hashid, "
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
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	Statistics lineStatistics = slocMetric.getLineStatistics();
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), codeRange.getType().name(),
		codeRange.getSimpleName(), slocMetric.getPhyLOC(),
		slocMetric.getProLOC(), slocMetric.getComLOC(),
		slocMetric.getBlLOC(), lineStatistics.getCount(),
		lineStatistics.getMin(), lineStatistics.getMax(),
		lineStatistics.getAvg(), lineStatistics.getMedian(),
		lineStatistics.getStdDev());
	session.execute(boundStatement);
    }

    public void storeFileResults(HashId hashId, SLOCResult slocResult) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(
			session,
			"INSERT INTO file_results (hashid, "
				+ "evaluator_id"
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
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	SLOCMetric slocMetric = slocResult.getSLOCMetric();
	Statistics lineStatistics = slocMetric.getLineStatistics();
	BoundStatement boundStatement = preparedStatement.bind(hashId,
		SLOCMetricCalculator.ID, slocResult.getCodeRangeType().name(),
		slocResult.getCodeRangeName(), slocMetric.getPhyLOC(),
		slocMetric.getProLOC(), slocMetric.getComLOC(),
		slocMetric.getBlLOC(), lineStatistics.getCount(),
		lineStatistics.getMin(), lineStatistics.getMax(),
		lineStatistics.getAvg(), lineStatistics.getMedian(),
		lineStatistics.getStdDev());
	session.execute(boundStatement);
    }

    public List<SLOCResult> readFileResults(HashId hashId, String evaluatorId) {
	List<SLOCResult> slocResults = new ArrayList<>();
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "source_code_location, " + "source_code_location, "
			+ "code_range_type, " + "code_range_name, "
			+ "phyLOC, " + "proLOC, " + "comLOC, " + "blLOC, "
			+ "line_length_count, " + "line_length_min, "
			+ "line_length_max, " + "line_length_avg, "
			+ "line_length_median, "
			+ "line_length_stdDev FROM file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), evaluatorId);
	ResultSet resultSet = session.execute(boundStatement);
	while (!resultSet.isExhausted()) {
	    Row row = resultSet.one();
	    Properties sourceCodeLocationProperties = new Properties();
	    try (ByteArrayInputStream inStream = new ByteArrayInputStream(row
		    .getString(0).getBytes())) {
		sourceCodeLocationProperties.load(inStream);
	    } catch (IOException e) {
		logger.error("Could not read source code location.", e);
	    }
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
}
