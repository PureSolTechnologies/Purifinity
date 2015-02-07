package com.puresoltechnologies.purifinity.server.accountmanager.test;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.purifinity.server.database.cassandra.CassandraClusterHelper;
import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.purifinity.server.database.titan.TitanGraphHelper;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

/**
 * This class contains several methods which support testing the AccountManager
 * and which help other facility tests to handle the AccountManager for testing.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AccountManagerTester {

    /**
     * This method clean the password store completely, except of the four
     * standard accounts:
     * <ol>
     * <li>user@puresol-technologies.com</li>
     * <li>engineer@puresol-technologies.com</li>
     * <li>administrator@puresol-technologies.com</li>
     * <li>ludwig@puresol-technologies.com</li>
     * </ol>
     */
    public static void cleanupDatabase() {
	try (Cluster cluster = CassandraClusterHelper.connect()) {
	    cleanupDatabase(cluster);
	}
    }

    /**
     * This method clean the password store completely, except of the four
     * standard accounts:
     * <ol>
     * <li>user@puresol-technologies.com</li>
     * <li>engineer@puresol-technologies.com</li>
     * <li>administrator@puresol-technologies.com</li>
     * <li>ludwig@puresol-technologies.com</li>
     * </ol>
     * 
     * @param cluster
     *            is the cluster where the keyspace
     *            {@link PasswordStoreBean#PASSWORD_STORE_KEYSPACE_NAME} can be
     *            found.
     */
    public static void cleanupDatabase(Cluster cluster) {
	PasswordStoreTester.cleanupDatabase(cluster);

	TitanGraph titanGraph = TitanGraphHelper.connect();
	try {
	    @SuppressWarnings("unchecked")
	    Iterable<Vertex> userVertices = titanGraph.query()
		    .has("_xo_discriminator_user").vertices();
	    for (Vertex userVertex : userVertices) {
		String email = userVertex
			.getProperty(TitanElementNames.USER_EMAIL_PROPERTY);
		if (isDefaultAccount(email)) {
		    continue;
		}
		titanGraph.removeVertex(userVertex);
	    }
	    titanGraph.commit();

	    @SuppressWarnings("unchecked")
	    Iterable<Vertex> roleVertices = titanGraph.query()
		    .has("_xo_discriminator_role").vertices();
	    for (Vertex roleVertex : roleVertices) {
		String roleId = roleVertex
			.getProperty(TitanElementNames.ROLE_ID_PROPERTY);
		if (isDefaultRole(roleId)) {
		    continue;
		}
		titanGraph.removeVertex(roleVertex);
	    }
	    titanGraph.commit();
	} finally {
	    titanGraph.shutdown();
	}
    }

    public static boolean isDefaultAccount(String email) {
	return PasswordStoreTester.isDefaultAccount(email);
    }

    public static boolean isDefaultRole(String roleId) {
	if (roleId.equals("unprivileged") || roleId.equals("engineer")
		|| roleId.equals("administrator")) {
	    return true;
	}
	return false;
    }
}
