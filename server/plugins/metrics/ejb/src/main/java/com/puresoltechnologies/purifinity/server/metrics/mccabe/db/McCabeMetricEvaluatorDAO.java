package com.puresoltechnologies.purifinity.server.metrics.mccabe.db;

import java.util.ArrayList;
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
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.server.common.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.server.core.api.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;

public class McCabeMetricEvaluatorDAO {

    @Inject
    @McCabeMetricEvaluatorStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    public void storeFileResults(HashId hashId,
	    SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    McCabeMetricResult mcCabeResult) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session,
			"INSERT INTO file_results (hashid, " + "evaluator_id, "
				+ "source_code_location, "
				+ "code_range_type, " + "code_range_name, "
				+ "vg) " + "VALUES (?, ?, ?, ?, ?, ?);");
	BoundStatement boundStatement = preparedStatement
		.bind(hashId.toString(), SLOCMetricCalculator.ID,
			PropertiesUtils.toString(sourceCodeLocation
				.getSerialization()), codeRange.getType()
				.name(), codeRange.getSimpleName(),
			mcCabeResult.getCyclomaticComplexity());
	session.execute(boundStatement);
    }

    public List<McCabeMetricResult> readFileResults(HashId hashId) {
	List<McCabeMetricResult> mcCabeResults = new ArrayList<>();
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "source_code_location, " + "code_range_type, "
			+ "code_range_name, " + "vg FROM file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), SLOCMetricCalculator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	while (!resultSet.isExhausted()) {
	    Row row = resultSet.one();
	    Properties sourceCodeLocationProperties = PropertiesUtils
		    .fromString(row.getString(0));
	    SourceCodeLocation sourceCodeLocation = SourceCodeLocationCreator
		    .createFromSerialization(sourceCodeLocationProperties);
	    CodeRangeType codeRangeType = CodeRangeType.valueOf(row
		    .getString(1));
	    String codeRangeName = row.getString(2);
	    int vg = row.getInt(3);
	    McCabeMetricResult mcCabeResult = new McCabeMetricResult(
		    sourceCodeLocation, codeRangeType, codeRangeName, vg);
	    mcCabeResults.add(mcCabeResult);
	}
	return mcCabeResults;
    }

    public void storeDirectoryResults(HashId hashId,
	    McCabeMetricResult mcCabeResult) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session,
			"INSERT INTO directory_results (hashid, "
				+ "evaluator_id, " + "vg) "
				+ "VALUES (?, ?, ?);");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), SLOCMetricCalculator.ID,
		mcCabeResult.getCyclomaticComplexity());
	session.execute(boundStatement);
    }

    public McCabeMetricResult readDirectoryResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT " + "vg "
			+ " FROM directory_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), SLOCMetricCalculator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	if (resultSet.isExhausted()) {
	    return null;
	}
	Row row = resultSet.one();
	int vg = row.getInt(0);
	McCabeMetricResult slocResult = new McCabeMetricResult(null,
		CodeRangeType.DIRECTORY, "", vg);
	return slocResult;

    }
}
