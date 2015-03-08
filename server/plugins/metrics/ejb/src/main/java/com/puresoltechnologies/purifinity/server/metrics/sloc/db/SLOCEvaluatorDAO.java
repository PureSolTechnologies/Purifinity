package com.puresoltechnologies.purifinity.server.metrics.sloc.db;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCResult;

public class SLOCEvaluatorDAO {

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
		slocResult.getCodeRangeType().name(),
		slocResult.getCodeRangeName(), slocMetric.getPhyLOC(),
		slocMetric.getProLOC(), slocMetric.getComLOC(),
		slocMetric.getBlLOC(), lineStatistics.getCount(),
		lineStatistics.getMin(), lineStatistics.getMax(),
		lineStatistics.getAvg(), lineStatistics.getMedian(),
		lineStatistics.getStdDev());
	session.execute(boundStatement);
    }
}
