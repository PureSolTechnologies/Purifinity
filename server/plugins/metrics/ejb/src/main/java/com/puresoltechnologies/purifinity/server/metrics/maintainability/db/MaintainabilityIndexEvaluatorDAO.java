package com.puresoltechnologies.purifinity.server.metrics.maintainability.db;

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
import com.puresoltechnologies.purifinity.server.metrics.MetricsDAO;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexResult;

public class MaintainabilityIndexEvaluatorDAO
	implements
	MetricsDAO<MaintainabilityIndexFileResult, MaintainabilityIndexDirectoryResults> {

    @Inject
    @MaintainabilityIndexEvaluatorStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements preparedStatements;

    @Override
    public void storeFileResults(HashId hashId,
	    SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    MaintainabilityIndexFileResult maintainabilityIndex) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session,
			"INSERT INTO file_results (hashid, " + "evaluator_id, "
				+ "source_code_location, "
				+ "code_range_type, " + "code_range_name, "
				+ "MIwoc, " + "MIcw, " + "MI) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
	MaintainabilityIndexResult maintainabilityIndexResult = maintainabilityIndex
		.getMaintainabilityIndexResult();
	BoundStatement boundStatement = preparedStatement
		.bind(hashId.toString(), MaintainabilityIndexEvaluator.ID,
			PropertiesUtils.toString(sourceCodeLocation
				.getSerialization()), codeRange.getType()
				.name(), codeRange.getSimpleName(),
			maintainabilityIndexResult.getMIwoc(),
			maintainabilityIndexResult.getMIcw(),
			maintainabilityIndexResult.getMI());
	session.execute(boundStatement);
    }

    @Override
    public List<MaintainabilityIndexFileResult> readFileResults(HashId hashId) {
	List<MaintainabilityIndexFileResult> mcCabeResults = new ArrayList<>();
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT "
			+ "source_code_location, " + "code_range_type, "
			+ "code_range_name, " + "MIwoc, " + "MIcw " + "FROM "
			+ "file_results "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), MaintainabilityIndexEvaluator.ID);
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
	    double miWoc = row.getDouble(3);
	    double miCw = row.getDouble(4);
	    MaintainabilityIndexResult result = new MaintainabilityIndexResult(
		    miWoc, miCw);
	    MaintainabilityIndexFileResult maintainabilityIndexFileResult = new MaintainabilityIndexFileResult(
		    sourceCodeLocation, codeRangeType, codeRangeName, result);
	    mcCabeResults.add(maintainabilityIndexFileResult);
	}
	return mcCabeResults;
    }

    @Override
    public void storeDirectoryResults(HashId hashId,
	    MaintainabilityIndexDirectoryResults maintainabilityIndex) {
	// TODO
    }

    @Override
    public MaintainabilityIndexDirectoryResults readDirectoryResults(
	    HashId hashId) {
	return null;
    }

    @Override
    public boolean hasFileResults(HashId hashId) {
	PreparedStatement preparedStatement = preparedStatements
		.getPreparedStatement(session, "SELECT " + "hashid "
			+ "WHERE hashid=? AND evaluator_id=?;");
	BoundStatement boundStatement = preparedStatement.bind(
		hashId.toString(), MaintainabilityIndexEvaluator.ID);
	ResultSet resultSet = session.execute(boundStatement);
	return !resultSet.isExhausted();
    }

    @Override
    public boolean hasDirectoryResults(HashId hashId) {
	// TODO Auto-generated method stub
	return false;
    }

}
