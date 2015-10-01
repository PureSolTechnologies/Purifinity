package com.puresoltechnologies.purifinity.server.test.analysis.store;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.http.HttpEntity;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;

import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
import com.puresoltechnologies.purifinity.server.database.titan.TitanGraphHelper;
import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerRestClientTest;
import com.puresoltechnologies.purifinity.server.test.analysis.AnalysisStoreDatabaseHelper;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;
import com.thinkaurelius.titan.core.TitanGraph;

public abstract class AbstractAnalysisStoreServiceClientTest extends AbstractPurifinityServerRestClientTest {

    @BeforeClass
    public static void cleanupAnalysisStore() throws SQLException {
	try (Connection connection = HBaseHelper.connect()) {
	    TitanGraph titanGraph = TitanGraphHelper.connect();
	    try {
		AnalysisStoreDatabaseHelper.cleanAnalysisStore(connection, titanGraph);
	    } finally {
		titanGraph.shutdown();
	    }
	}
    }

    @EnhanceDeployment
    public static void additionalResources(JavaArchive javaArchive) {
	javaArchive.addPackages(true, HttpEntity.class.getPackage());
    }
}
