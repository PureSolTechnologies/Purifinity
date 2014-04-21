package com.puresoltechnologies.purifinity.server.purifinityserver.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;
import com.puresoltechnologies.purifinity.framework.store.db.ValueSerializer;
import com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.CassandraKeyspaces;
import com.puresoltechnologies.purifinity.server.purifinityserver.core.api.ChartDataProvider;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.ChartData1D;

@Stateless
public class ChartDataProviderBean implements ChartDataProvider {

	@Inject
	@Named(CassandraKeyspaces.EVALUATION)
	private Session session;

	@Override
	public ChartData1D loadValues(UUID analysisProject, UUID analysisRun,
			String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) {
		Map<HashId, List<Value<?>>> values = new HashMap<>();
		if (parameter.isNumeric()) {
			PreparedStatement preparedStatement = CassandraConnection
					.getPreparedStatement(
							session,
							"SELECT hashid, numeric_value FROM "
									+ CassandraElementNames.EVALUATION_METRICS_TABLE
									+ " WHERE project_uuid=? AND run_uuid=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
			BoundStatement boundStatement = preparedStatement.bind(
					analysisProject, analysisRun, evaluatorName,
					parameter.getName(), codeRangeType.name());
			ResultSet result = session.execute(boundStatement);
			while (!result.isExhausted()) {
				Row row = result.one();
				HashId hashId = HashId.valueOf(row.getString(0));
				Double value = row.getDouble(1);

				ParameterWithArbitraryUnit<Double> metricParameter = new ParameterWithArbitraryUnit<>(
						parameter.getName(), parameter.getUnit(),
						parameter.getLevelOfMeasurement(),
						parameter.getDescription(), Double.class);
				List<Value<?>> valueList = values.get(hashId);
				if (valueList == null) {
					valueList = new ArrayList<>();
					values.put(hashId, valueList);
				}
				valueList.add(new GeneralValue<>(value, metricParameter));
			}
		} else {
			PreparedStatement preparedStatement = CassandraConnection
					.getPreparedStatement(
							session,
							"SELECT hashid, string_value FROM "
									+ CassandraElementNames.EVALUATION_METRICS_TABLE
									+ " WHERE project_uuid=? AND run_uuid=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
			BoundStatement boundStatement = preparedStatement.bind(
					analysisProject, analysisRun, evaluatorName,
					parameter.getName(), codeRangeType.name());
			ResultSet result = session.execute(boundStatement);
			while (!result.isExhausted()) {
				Row row = result.one();
				HashId hashId = HashId.valueOf(row.getString(0));
				String stringValue = row.getString(1);
				Value<?> value = ValueSerializer.fromString(stringValue);

				List<Value<?>> valueList = values.get(hashId);
				if (valueList == null) {
					valueList = new ArrayList<>();
					values.put(hashId, valueList);
				}
				valueList.add(value);
			}
		}
		return new ChartData1D(values);
	}

}
