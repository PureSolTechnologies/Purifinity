package com.puresoltechnologies.purifinity.framework.store.db.charts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.puresoltechnologies.purifinity.framework.store.api.HistogramChartDataProvider;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;

public class HistogramChartDataProviderImpl implements
		HistogramChartDataProvider {

	@Override
	public Map<HashId, List<Value<Double>>> loadValues(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) {
		Session session = CassandraConnection.getEvaluationSession();
		Map<HashId, List<Value<Double>>> values = new HashMap<>();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"SELECT hashid, metric FROM "
								+ CassandraElementNames.EVALUATION_METRICS_TABLE
								+ " WHERE analysis_project=? AND analysis_run=? AND evaluator_name=? AND name=? AND code_range_type=? ALLOW FILTERING");
		BoundStatement boundStatement = preparedStatement.bind(analysisProject,
				analysisRun, evaluatorName, parameter.getName(),
				codeRangeType.name());
		ResultSet result = session.execute(boundStatement);
		while (!result.isExhausted()) {
			Row row = result.one();
			HashId hashId = HashId.valueOf(row.getString(0));
			Double value = row.getDouble(1);
			ParameterWithArbitraryUnit<Double> metricParameter = new ParameterWithArbitraryUnit<>(
					parameter.getName(), parameter.getUnit(),
					parameter.getLevelOfMeasurement(),
					parameter.getDescription(), Double.class);
			List<Value<Double>> valueList = values.get(hashId);
			if (valueList == null) {
				valueList = new ArrayList<>();
				values.put(hashId, valueList);

			}
			valueList.add(new GeneralValue<>(value, metricParameter));
		}
		return values;
	}
}
