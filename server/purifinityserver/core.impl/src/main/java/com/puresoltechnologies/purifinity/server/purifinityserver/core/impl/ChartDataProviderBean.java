package com.puresoltechnologies.purifinity.server.purifinityserver.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
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
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.store.cassandra.ValueSerializer;
import com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.CassandraKeyspaces;
import com.puresoltechnologies.purifinity.server.purifinityserver.core.api.ChartDataProvider;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.HistogramChartData;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.MetricsMapData;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.ParetoChartData;

@Stateless
public class ChartDataProviderBean implements ChartDataProvider {

	@Inject
	@Named(CassandraKeyspaces.EVALUATION)
	private Session session;

	private PreparedStatement preparedNumericHistogramChartDataStatement = null;
	private PreparedStatement preparedCategoryHistogramChartDataStatement = null;
	private PreparedStatement preparedParetoChartDataStatement = null;

	@PostConstruct
	public void initialize() {
		preparedNumericHistogramChartDataStatement = session
				.prepare("SELECT hashid, numeric_value FROM "
						+ CassandraElementNames.EVALUATION_METRICS_TABLE
						+ " WHERE project_uuid=? AND run_uuid=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
		preparedCategoryHistogramChartDataStatement = session
				.prepare("SELECT hashid, string_value FROM "
						+ CassandraElementNames.EVALUATION_METRICS_TABLE
						+ " WHERE project_uuid=? AND run_uuid=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
		preparedParetoChartDataStatement = session
				.prepare("SELECT hashid, code_range_name, numeric_value FROM "
						+ CassandraElementNames.EVALUATION_METRICS_TABLE
						+ " WHERE project_uuid=? AND run_uuid=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
	}

	@Override
	public HistogramChartData loadHistogramChartData(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) {
		Map<HashId, List<Value<?>>> values = new HashMap<>();
		if (parameter.isNumeric()) {
			BoundStatement boundStatement = preparedNumericHistogramChartDataStatement
					.bind(analysisProject, analysisRun, evaluatorName,
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
			BoundStatement boundStatement = preparedCategoryHistogramChartDataStatement
					.bind(analysisProject, analysisRun, evaluatorName,
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
		return new HistogramChartData(values);
	}

	@Override
	public ParetoChartData loadParetoChartData(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) {
		Map<HashId, Map<String, Value<? extends Number>>> values = new HashMap<>();
		if (parameter.isNumeric()) {
			BoundStatement boundStatement = preparedParetoChartDataStatement
					.bind(analysisProject, analysisRun, evaluatorName,
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
									+ " WHERE project_uuid=? AND run_uuid=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
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
									+ " WHERE project_uuid=? AND run_uuid=? AND evaluator_name=? AND parameter_name=? AND code_range_type=?");
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
