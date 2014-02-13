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
import com.puresoltechnologies.purifinity.framework.store.api.MetricsMapData;
import com.puresoltechnologies.purifinity.framework.store.api.MetricsMapDataProvider;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;
import com.puresoltechnologies.purifinity.framework.store.db.ValueSerializer;

public class MetricsMapDataProviderImpl implements MetricsMapDataProvider {

	@Override
	public MetricsMapData loadMapValues(UUID analysisProject, UUID analysisRun,
			String mapEvaluatorName, Parameter<?> mapParameter,
			String colorEvaluatorName, Parameter<?> colorParameter) {
		Session session = CassandraConnection.getEvaluationSession();
		Map<HashId, Map<String, Value<? extends Number>>> mapValues = readMapValues(
				session, analysisProject, analysisRun, mapEvaluatorName,
				mapParameter, CodeRangeType.FILE);
		mapValues.putAll(readMapValues(session, analysisProject, analysisRun,
				mapEvaluatorName, mapParameter, CodeRangeType.DIRECTORY));
		Map<HashId, Map<String, Value<?>>> colorValues = readColorValues(
				session, analysisProject, analysisRun, colorEvaluatorName,
				colorParameter, CodeRangeType.FILE);
		colorValues.putAll(readColorValues(session, analysisProject,
				analysisRun, colorEvaluatorName, colorParameter,
				CodeRangeType.DIRECTORY));
		return new MetricsMapData(mapValues, colorValues);
	}

	private Map<HashId, Map<String, Value<? extends Number>>> readMapValues(
			Session session, UUID analysisProject, UUID analysisRun,
			String mapEvaluatorName, Parameter<?> mapParameter,
			CodeRangeType codeRangeType) {
		Map<HashId, Map<String, Value<? extends Number>>> values = new HashMap<>();
		if (mapParameter.isNumeric()) {
			PreparedStatement preparedStatement = CassandraConnection
					.getPreparedStatement(
							session,
							"SELECT hashid, code_range_name, numeric_value FROM "
									+ CassandraElementNames.EVALUATION_METRICS_TABLE
									+ " WHERE analysis_project=? AND analysis_run=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
			BoundStatement boundStatement = preparedStatement.bind(
					analysisProject, analysisRun, mapEvaluatorName,
					mapParameter.getName(), codeRangeType.name());
			ResultSet result = session.execute(boundStatement);
			while (!result.isExhausted()) {
				Row row = result.one();
				HashId hashId = HashId.valueOf(row.getString(0));
				String codeRangeName = row.getString(1);
				Double value = row.getDouble(2);

				ParameterWithArbitraryUnit<Double> metricParameter = new ParameterWithArbitraryUnit<>(
						mapParameter.getName(), mapParameter.getUnit(),
						mapParameter.getLevelOfMeasurement(),
						mapParameter.getDescription(), Double.class);
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
		return values;
	}

	private Map<HashId, Map<String, Value<?>>> readColorValues(Session session,
			UUID analysisProject, UUID analysisRun, String colorEvaluatorName,
			Parameter<?> colorParameter, CodeRangeType codeRangeType) {
		Map<HashId, Map<String, Value<?>>> values = new HashMap<>();
		if (colorParameter.isNumeric()) {
			PreparedStatement preparedStatement = CassandraConnection
					.getPreparedStatement(
							session,
							"SELECT hashid, code_range_name, numeric_value FROM "
									+ CassandraElementNames.EVALUATION_METRICS_TABLE
									+ " WHERE analysis_project=? AND analysis_run=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
			BoundStatement boundStatement = preparedStatement.bind(
					analysisProject, analysisRun, colorEvaluatorName,
					colorParameter.getName(), codeRangeType.name());
			ResultSet result = session.execute(boundStatement);
			while (!result.isExhausted()) {
				Row row = result.one();
				HashId hashId = HashId.valueOf(row.getString(0));
				String codeRangeName = row.getString(1);
				Double value = row.getDouble(2);

				ParameterWithArbitraryUnit<Double> metricParameter = new ParameterWithArbitraryUnit<>(
						colorParameter.getName(), colorParameter.getUnit(),
						colorParameter.getLevelOfMeasurement(),
						colorParameter.getDescription(), Double.class);
				Map<String, Value<?>> valueList = values.get(hashId);
				if (valueList == null) {
					valueList = new HashMap<>();
					values.put(hashId, valueList);
				}
				valueList.put(codeRangeName, new GeneralValue<>(value,
						metricParameter));
			}
		} else {
			PreparedStatement preparedStatement = CassandraConnection
					.getPreparedStatement(
							session,
							"SELECT hashid, code_range_name, string_value FROM "
									+ CassandraElementNames.EVALUATION_METRICS_TABLE
									+ " WHERE analysis_project=? AND analysis_run=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
			BoundStatement boundStatement = preparedStatement.bind(
					analysisProject, analysisRun, colorEvaluatorName,
					colorParameter.getName(), codeRangeType.name());
			ResultSet result = session.execute(boundStatement);
			while (!result.isExhausted()) {
				Row row = result.one();
				HashId hashId = HashId.valueOf(row.getString(0));
				String codeRangeName = row.getString(1);
				String value = row.getString(2);

				Map<String, Value<?>> valueList = values.get(hashId);
				if (valueList == null) {
					valueList = new HashMap<>();
					values.put(hashId, valueList);
				}
				valueList.put(codeRangeName, ValueSerializer.fromString(value));
			}
		}
		return values;
	}
}
