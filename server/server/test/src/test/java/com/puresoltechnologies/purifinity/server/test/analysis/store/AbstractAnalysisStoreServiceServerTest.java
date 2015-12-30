package com.puresoltechnologies.purifinity.server.test.analysis.store;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;

import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.AnalysisServiceConnection;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerServerTest;
import com.puresoltechnologies.purifinity.server.test.analysis.AnalysisStoreDatabaseHelper;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractAnalysisStoreServiceServerTest extends AbstractPurifinityServerServerTest {

    @Inject
    @AnalysisServiceConnection
    private Connection connection;

    @Inject
    private DuctileGraph graph;

    @EnhanceDeployment
    public static final void enhanceDeployment(JavaArchive archive) {
	archive.addPackages(true, org.apache.commons.io.IOUtils.class.getPackage());
    }

    @Before
    public void cleanupAnalysisStore() throws SQLException {
	PasswordStoreTester.cleanupDatabase();
	AnalysisStoreDatabaseHelper.cleanAnalysisStore(connection, graph);
    }

}
