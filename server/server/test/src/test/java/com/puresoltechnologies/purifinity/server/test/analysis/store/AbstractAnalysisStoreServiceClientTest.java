package com.puresoltechnologies.purifinity.server.test.analysis.store;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.http.HttpEntity;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;

import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileGraphHelper;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerRestClientTest;
import com.puresoltechnologies.purifinity.server.test.analysis.AnalysisStoreDatabaseHelper;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractAnalysisStoreServiceClientTest extends AbstractPurifinityServerRestClientTest {

    @BeforeClass
    public static void cleanupAnalysisStore() throws SQLException, IOException {
	try (Connection connection = HBaseHelper.connect()) {
	    DuctileGraph titanGraph = DuctileGraphHelper.connect();
	    try {
		AnalysisStoreDatabaseHelper.cleanAnalysisStore(connection, titanGraph);
	    } finally {
		titanGraph.close();
	    }
	}
    }

    @EnhanceDeployment
    public static void additionalResources(JavaArchive javaArchive) {
	javaArchive.addPackages(true, HttpEntity.class.getPackage());
    }
}
