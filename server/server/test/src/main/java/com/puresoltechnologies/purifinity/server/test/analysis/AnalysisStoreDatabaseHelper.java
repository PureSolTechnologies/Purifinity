package com.puresoltechnologies.purifinity.server.test.analysis;

import java.lang.reflect.Field;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

public class AnalysisStoreDatabaseHelper {

	public static void cleanAnalysisStore(Cluster cluster, TitanGraph titanGraph) {
		try (Session session = cluster.connect(AnalysisStoreKeyspace.NAME)) {
			Field[] fields = CassandraElementNames.class.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				if (fieldName.startsWith("ANALYSIS_")
						&& fieldName.endsWith("_TABLE")) {
					String tableName = (String) field.get(null);
					session.execute("TRUNCATE " + tableName + ";");
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("Could not clean analysis store.", e);
		}
		Iterable<Vertex> vertices = titanGraph.query().vertices();
		for (Vertex vertex : vertices) {
			vertex.remove();
		}
	}
}
