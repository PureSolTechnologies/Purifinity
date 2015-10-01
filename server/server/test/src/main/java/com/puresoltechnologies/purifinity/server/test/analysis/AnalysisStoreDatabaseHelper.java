package com.puresoltechnologies.purifinity.server.test.analysis;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

public class AnalysisStoreDatabaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisStoreDatabaseHelper.class);

    public static void cleanAnalysisStore(Connection connection, TitanGraph titanGraph) {
	try (PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS ?")) {
	    Field[] fields = HBaseElementNames.class.getDeclaredFields();
	    for (Field field : fields) {
		String fieldName = field.getName();
		if (fieldName.startsWith("ANALYSIS_") && fieldName.endsWith("_TABLE")) {
		    String tableName = (String) field.get(null);
		    preparedStatement.setString(1, tableName);
		    preparedStatement.execute();
		}
	    }
	    connection.commit();
	} catch (IllegalArgumentException | IllegalAccessException e) {
	    throw new RuntimeException("Could not clean analysis store.", e);
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback table deletions.", e1);
	    }
	    throw new RuntimeException("Could not clean analysis store.", e);
	}
	Iterable<Vertex> vertices = titanGraph.query().vertices();
	for (Vertex vertex : vertices) {
	    vertex.remove();
	}
	titanGraph.commit();
    }
}
