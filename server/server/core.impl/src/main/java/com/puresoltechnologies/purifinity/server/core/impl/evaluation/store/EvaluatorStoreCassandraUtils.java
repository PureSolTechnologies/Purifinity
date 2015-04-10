package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.net.URL;
import java.util.Date;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.versioning.Version;

public class EvaluatorStoreCassandraUtils {

	@Inject
	@EvaluationStoreKeyspace
	private Session session;

	@Inject
	private CassandraPreparedStatements cassandraPreparedStatements;

	public void deleteFileEvaluation(HashId hashId) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_FILE_METRICS_TABLE
						+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

	public void deleteDirectoryEvaluation(HashId hashId) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"DELETE FROM "
								+ CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
								+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

	public void registerPluginInformation(PluginInformation pluginInformation,
			EvaluatorServiceInformation serviceInformation) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.EVALUATION_PARAMETERS_TABLE
								+ " (time, evaluator_id, evaluator_name, evaluator_version, plugin_id, plugin_name, plugin_version, vendor, vendor_url, plugin_ui_path, parameter_name, parameter_unit, level_of_measurement, parameter_description)"
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) IF NOT EXISTS;");
		for (MetricParameter<?> parameter : serviceInformation.getParameters()) {
			Date timestamp = new Date();
			String evaluatorId = serviceInformation.getId();
			String evaluatorName = serviceInformation.getName();
			Version evaluatorVersion = pluginInformation.getVersion();
			String pluginId = pluginInformation.getId();
			String pluginName = pluginInformation.getName();
			Version pluginVersion = pluginInformation.getVersion();
			String vendor = pluginInformation.getVendor();
			URL vendorURL = pluginInformation.getVendorURL();
			String pathToUI = pluginInformation.getPathToUI();
			String parameterName = parameter.getName();
			String parameterUnit = parameter.getUnit();
			LevelOfMeasurement levelOfMeasurement = parameter
					.getLevelOfMeasurement();
			String description = parameter.getDescription();
			BoundStatement boundStatement = preparedStatement.bind(timestamp,
					evaluatorId, evaluatorName, evaluatorVersion.toString(),
					pluginId, pluginName, pluginVersion.toString(), vendor,
					vendorURL.toString(), pathToUI, parameterName,
					parameterUnit, levelOfMeasurement.name(), description);
			session.execute(boundStatement);
		}
	}
}
