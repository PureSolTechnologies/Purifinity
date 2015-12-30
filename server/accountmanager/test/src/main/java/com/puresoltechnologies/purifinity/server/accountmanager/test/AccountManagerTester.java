package com.puresoltechnologies.purifinity.server.accountmanager.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileGraphHelper;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;

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
     * 
     * @throws SQLException
     * @throws IOException
     */
    public static void cleanupDatabase() throws SQLException, IOException {
	try (Connection connection = HBaseHelper.connect()) {
	    cleanupDatabase(connection);
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
     * @param connection
     *            is the cluster where the keyspace
     *            {@link PasswordStoreBean#PASSWORD_STORE_KEYSPACE_NAME} can be
     *            found.
     * @throws SQLException
     * @throws IOException
     */
    public static void cleanupDatabase(Connection connection) throws SQLException, IOException {
	PasswordStoreTester.cleanupDatabase();

	DuctileGraph titanGraph = DuctileGraphHelper.connect();
	try {
	    GraphTraversal<Vertex, Vertex> userVertices = titanGraph.traversal().V();
	    while (userVertices.hasNext()) {
		Vertex userVertex = userVertices.next();
		String email = (String) userVertex.property(DuctileDBElementNames.USER_EMAIL_PROPERTY).value();
		if (isDefaultAccount(email)) {
		    continue;
		}
		userVertex.remove();
	    }
	    titanGraph.tx().commit();

	    GraphTraversal<Vertex, Vertex> roleVertices = titanGraph.traversal().V();
	    while (roleVertices.hasNext()) {
		Vertex roleVertex = roleVertices.next();
		String roleId = (String) roleVertex.property(DuctileDBElementNames.ROLE_ID_PROPERTY).value();
		if (isDefaultRole(roleId)) {
		    continue;
		}
		roleVertex.remove();
	    }
	    titanGraph.tx().commit();
	} finally {
	    titanGraph.close();
	}
    }

    public static boolean isDefaultAccount(String email) {
	return PasswordStoreTester.isDefaultAccount(email);
    }

    public static boolean isDefaultRole(String roleId) {
	if (roleId == null) {
	    return false;
	}
	if (roleId.equals("unprivileged") || roleId.equals("engineer") || roleId.equals("administrator")) {
	    return true;
	}
	return false;
    }
}
