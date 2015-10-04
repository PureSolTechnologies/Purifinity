package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.EvaluatorStoreConnection;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.versioning.Version;

public class EvaluatorStoreUtils {

    @Inject
    private Logger logger;

    @Inject
    @EvaluatorStoreConnection
    private Connection connection;

    public void deleteFileEvaluation(HashId hashId) {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"DELETE FROM " + CassandraElementNames.EVALUATION_FILE_METRICS_TABLE + " WHERE hashid=?;")) {
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback file evaluation deletion.", e1);
	    }
	    throw new RuntimeException("Could not delete file evaluation.", e);
	}
    }

    public void deleteDirectoryEvaluation(HashId hashId) {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"DELETE FROM " + CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE + " WHERE hashid=?;")) {
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback directory evaluation deletion.", e1);
	    }
	    throw new RuntimeException("Could not delete directory evaluation.", e);
	}
    }

    public void registerPluginInformation(PluginInformation pluginInformation,
	    EvaluatorServiceInformation serviceInformation) {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + CassandraElementNames.EVALUATION_PARAMETERS_TABLE
			+ " (time, evaluator_id, evaluator_name, evaluator_version, plugin_id, plugin_name, plugin_version, vendor, vendor_url, plugin_ui_path, parameter_name, parameter_unit, level_of_measurement, parameter_description)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) IF NOT EXISTS;")) {
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
		LevelOfMeasurement levelOfMeasurement = parameter.getLevelOfMeasurement();
		String description = parameter.getDescription();
		preparedStatement.setTime(1, new Time(timestamp.getTime()));
		preparedStatement.setString(2, evaluatorId);
		preparedStatement.setString(3, evaluatorName);
		preparedStatement.setString(4, evaluatorVersion.toString());
		preparedStatement.setString(5, pluginId);
		preparedStatement.setString(6, pluginName);
		preparedStatement.setString(7, pluginVersion.toString());
		preparedStatement.setString(8, vendor);
		preparedStatement.setString(9, vendorURL.toString());
		preparedStatement.setString(10, pathToUI);
		preparedStatement.setString(11, parameterName);
		preparedStatement.setString(12, parameterUnit);
		preparedStatement.setString(13, levelOfMeasurement.name());
		preparedStatement.setString(14, description);
		connection.commit();
	    }
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback Plugin Information registration.", e1);
	    }
	    throw new RuntimeException("Could not register Plugin Information.", e);
	}
    }
}
