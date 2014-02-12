package com.puresoltechnologies.purifinity.framework.store.db.charts;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import com.puresoltechnologies.purifinity.framework.store.api.ParetoChartData;
import com.puresoltechnologies.purifinity.framework.store.api.ParetoChartDataProvider;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;

public class ParetoChartDataProviderImpl implements ParetoChartDataProvider {

	@Override
	public ParetoChartData loadValues(UUID analysisProject, UUID analysisRun,
			String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) {
		Map<HashId, Map<String, Value<? extends Number>>> values = new HashMap<>();
		Session session = CassandraConnection.getEvaluationSession();
		if (parameter.isNumeric()) {
			PreparedStatement preparedStatement = CassandraConnection
					.getPreparedStatement(
							session,
							"SELECT hashid, code_range_name, numeric_value FROM "
									+ CassandraElementNames.EVALUATION_METRICS_TABLE
									+ " WHERE analysis_project=? AND analysis_run=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
			BoundStatement boundStatement = preparedStatement.bind(
					analysisProject, analysisRun, evaluatorName,
					parameter.getName(), codeRangeType.name());
			ResultSet result = session.execute(boundStatement);
			while (!result.isExhausted()) {
				Row row = result.one();
				HashId hashId = HashId.valueOf(row.getString(0));
				String codeRangeName = row.getString(1);
				Double value = row.getDouble(2);

				ParameterWithArbitraryUnit<Double> metricParameter = new ParameterWithArbitraryUnit<>(
						parameter.getName(), parameter.getUnit(),
						parameter.getLevelOfMeasurement(),
						parameter.getDescription(), Double.class);
				Map<String, Value<? extends Number>> valueList = values
						.get(hashId);
				if (valueList == null) {
					valueList = new HashMap<>();
					values.put(hashId, valueList);
				}
				valueList.put(codeRangeName, new GeneralValue<>(value,
						metricParameter));
			}
		}
		return new ParetoChartData(values);
	}
}
