package com.puresoltechnologies.purifinity.server.test.analysis.store;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.puresoltechnologies.purifinity.server.test.analysis.AnalysisStoreDatabaseHelper;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;
import com.thinkaurelius.titan.core.TitanGraph;

public abstract class AbstractAnalysisStoreServiceServerTest extends
	AbstractServerTest {

    @Inject
    private Cluster cluster;

    @Inject
    @AnalysisStoreKeyspace
    private Session session;

    @Inject
    private TitanGraph titanGraph;

    @EnhanceDeployment
    public static void removeWARFile(EnterpriseArchive enterpriseArchive)
	    throws Exception {
	removeWAR(enterpriseArchive, "server.socket.impl.war");
    }

    @EnhanceDeployment
    public static final void enhanceDeployment(JavaArchive archive) {
	archive.addPackages(true,
		org.apache.commons.io.IOUtils.class.getPackage());
    }

    @Before
    public void cleanupAnalysisStore() {
	assertNotNull("Cassandra cluster was not connected.", cluster);
	assertNotNull("Session for '" + AnalysisStoreKeyspace.NAME
		+ "' was not opened.", session);
	PasswordStoreTester.cleanupDatabase(cluster);
	AnalysisStoreDatabaseHelper.cleanAnalysisStore(cluster, titanGraph);
    }

}
