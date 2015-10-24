package com.puresoltechnologies.purifinity.server.database.hgraph;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.junit.BeforeClass;
import org.junit.Test;

public class GraphFactoryIT {

    @BeforeClass
    public static void removeTables() throws IOException {
	try (HGraph graph = GraphFactory.createGraph()) {
	    Connection connection = ((HGraphImpl) graph).getConnection();
	    Admin admin = connection.getAdmin();
	    admin.deleteTable(TableName.valueOf(HGraphImpl.VERTICES_TABLE_NAME));
	    admin.deleteNamespace(HGraphImpl.NAMESPACE_NAME);
	}
    }

    @Test
    public void testConnection() throws IOException {
	try (HGraph graph = GraphFactory.createGraph()) {

	}
    }

}
