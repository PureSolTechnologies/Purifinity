package com.puresoltechnologies.purifinity.server.database.cassandra.pivot;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.CassandraClusterHelper;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;

public class PivotTableMetaDataIT {

    private static Cluster cluster;

    @BeforeClass
    public static void connec() {
	cluster = CassandraClusterHelper.connect();
    }

    @AfterClass
    public static void disconnect() {
	cluster.close();
    }

    @Test
    public void testReadTableMetaData() {
	Session session = cluster.connect(EvaluationStoreKeyspace.NAME);
	Set<PivotTableMetaData> tableMetaDataSet = PivotTableMetaData
		.readFromDatabase(session);
	for (PivotTableMetaData tableMetaData : tableMetaDataSet) {
	    Set<PivotColumnMetaData<?>> filters = tableMetaData
		    .getFilterColumns();
	    for (PivotColumnMetaData<?> filter : filters) {
		if (filter.getColumnName().equals("evaluator_name")) {
		    Set<?> filterValues = tableMetaData.getFilterValues(filter);
		    for (Object o : filterValues) {
			System.out.println(o);
		    }
		}
	    }
	}
    }
}
