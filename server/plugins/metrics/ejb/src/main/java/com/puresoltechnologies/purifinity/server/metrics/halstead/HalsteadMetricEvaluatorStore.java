package com.puresoltechnologies.purifinity.server.metrics.halstead;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

/**
 * This class represents a dedicated store for {@link HalsteadMetricEvaluator}.
 * This is needed because this evaluator needs to store some additional data.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class HalsteadMetricEvaluatorStore {

	@Inject
	private CassandraPreparedStatements cassandraPreparedStatements;

	@Inject
	@HalsteadEvaluatorKeyspace
	private Session session;

	/**
	 * This method stores code range results into Cassandra for later use.
	 * 
	 * @param hashId
	 * @param codeRange
	 * @param halsteadResults
	 */
	public void storeCodeRangeResults(HashId hashId, CodeRange codeRange,
			HalsteadResult halsteadResults) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"insert into file_results (hashid, code_range_type, code_range_name, operators, operands) values (?, ?, ?, ?, ?)");
		BoundStatement boundStatement = preparedStatement.bind(
				hashId.toString(), codeRange.getType().toString(),
				codeRange.getCanonicalName(), halsteadResults.getOperators(),
				halsteadResults.getOperands());
		session.execute(boundStatement);
	}

	/**
	 * This method stores directory results into Cassandra for later use.
	 * 
	 * @param hashId
	 * @param halsteadResults
	 * @throws EvaluationStoreException
	 */
	public HalsteadResult readCodeRangeResults(HashId hashId,
			CodeRangeType codeRangeType, String codeRangeName)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"select operators, operands from file_results where hashid=? and code_range_type=? and code_range_name=?");
		BoundStatement boundStatement = preparedStatement.bind(
				hashId.toString(), codeRangeType.name(),
				codeRangeName.toString());
		ResultSet result = session.execute(boundStatement);
		List<Row> rows = result.all();
		if (rows.size() == 0) {
			return null;
		} else if (rows.size() > 1) {
			throw new EvaluationStoreException(
					"There was only one result expected for hashid '" + hashId
							+ "'.");
		}
		Row row = rows.get(0);
		Map<String, Integer> operators = row.getMap("operators", String.class,
				Integer.class);
		Map<String, Integer> operands = row.getMap("operands", String.class,
				Integer.class);
		return new HalsteadResult(operands, operators);
	}

	/**
	 * This method stores directory results into Cassandra for later use.
	 * 
	 * @param hashId
	 * @param halsteadResults
	 */
	public void storeDirectoryResults(HashId hashId,
			HalsteadResult halsteadResults) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session,
						"insert into directory_results (hashid, operators, operands) values (?, ?, ?)");
		BoundStatement boundStatement = preparedStatement.bind(
				hashId.toString(), halsteadResults.getOperators(),
				halsteadResults.getOperands());
		session.execute(boundStatement);
	}

	/**
	 * This method stores directory results into Cassandra for later use.
	 * 
	 * @param hashId
	 * @param halsteadResults
	 * @throws EvaluationStoreException
	 */
	public HalsteadResult readDirectoryResults(HashId hashId)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session,
						"select operators, operands from directory_results where hashid=?");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		ResultSet result = session.execute(boundStatement);
		List<Row> rows = result.all();
		if (rows.size() == 0) {
			return null;
		} else if (rows.size() > 1) {
			throw new EvaluationStoreException(
					"There was only one result expected for hashid '" + hashId
							+ "'.");
		}
		Row row = rows.get(0);
		Map<String, Integer> operators = row.getMap("operators", String.class,
				Integer.class);
		Map<String, Integer> operands = row.getMap("operands", String.class,
				Integer.class);
		return new HalsteadResult(operands, operators);
	}

}
