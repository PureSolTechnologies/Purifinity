package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.xo.api.XOException;
import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOManagerFactory;
import com.buschmais.xo.api.bootstrap.XO;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.RoleVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UserVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UsersXOManager;
import com.puresoltechnologies.purifinity.server.database.titan.TitanGraphHelper;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

public class XOEntitiesIT {

    private static XOManagerFactory xoManagerFactory;
    private static TitanGraph titanGraph;

    private XOManager xoManager;

    @BeforeClass
    public static void connecXO() {
	xoManagerFactory = XO.createXOManagerFactory(UsersXOManager.XO_UNIT_NAME);
	titanGraph = TitanGraphHelper.connect();
    }

    @AfterClass
    public static void disconnect() {
	titanGraph.shutdown();
	xoManagerFactory.close();
    }

    @Before
    public void createXOManager() throws SQLException {
	AccountManagerTester.cleanupDatabase();
	xoManager = xoManagerFactory.createXOManager();
	Iterable<Vertex> vertices = titanGraph.query().vertices();
	int counter = 0;
	for (Vertex vertex : vertices) {
	    counter++;
	    for (String key : vertex.getPropertyKeys()) {
		System.out.println(key + ": " + vertex.getProperty(key));
	    }
	}
	assertEquals(7, counter);
    }

    @After
    public void closeXOManager() {
	xoManager.close();
    }

    @Test
    public void testUserVertex() {
	xoManager.currentTransaction().begin();
	try {
	    UserVertex user = xoManager.find(UserVertex.class, "ludwig@puresol-technologies.com").getSingleResult();
	    assertEquals("ludwig@puresol-technologies.com", user.getEmail());
	    assertEquals("Rick-Rainer Ludwig", user.getName());
	    RoleVertex role = user.getRole();
	    assertNotNull(role);
	    assertEquals("administrator", role.getId());
	    assertEquals("Administrator", role.getName());
	    assertNotNull(role.getUsers());
	    assertEquals(2, role.getUsers().size());
	} finally {
	    xoManager.currentTransaction().rollback();
	}
    }

    @Test
    public void testRoleVertex() {
	xoManager.currentTransaction().begin();
	try {
	    RoleVertex administratorVertex = xoManager.find(RoleVertex.class, "administrator").getSingleResult();
	    assertNotNull(administratorVertex);
	    RoleVertex engineerVertex = xoManager.find(RoleVertex.class, "engineer").getSingleResult();
	    assertNotNull(engineerVertex);
	    RoleVertex unprivilegedVertex = xoManager.find(RoleVertex.class, "unprivileged").getSingleResult();
	    assertNotNull(unprivilegedVertex);
	} finally {
	    xoManager.currentTransaction().rollback();

	}
    }

    @Test
    public void testCreateUser() {
	xoManager.currentTransaction().begin();
	try {
	    UserVertex userVertex = xoManager.create(UserVertex.class);
	    RoleVertex roleVertex = xoManager.create(RoleVertex.class);
	    userVertex.setRole(roleVertex);
	    xoManager.currentTransaction().commit();
	} catch (XOException e) {
	    xoManager.currentTransaction().rollback();
	}
    }

}
