package com.puresoltechnologies.purifinity.server.accountmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.buschmais.xo.api.XOException;
import com.buschmais.xo.api.XOManager;
import com.buschmais.xo.api.XOManagerFactory;
import com.buschmais.xo.api.bootstrap.XO;
import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.RoleVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UserVertex;
import com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo.UsersXOManager;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileGraphHelper;

public class XOEntitiesIT {

    private static XOManagerFactory xoManagerFactory;
    private static DuctileGraph ductileGraph;

    private XOManager xoManager;

    @BeforeClass
    public static void connecXO() throws IOException {
	xoManagerFactory = XO.createXOManagerFactory(UsersXOManager.XO_UNIT_NAME);
	ductileGraph = DuctileGraphHelper.connect();
    }

    @AfterClass
    public static void disconnect() throws IOException {
	try {
	    if (ductileGraph != null) {
		ductileGraph.close();
	    }
	} finally {
	    if (xoManagerFactory != null) {
		xoManagerFactory.close();
	    }
	}
    }

    @Before
    public void createXOManager() throws SQLException, IOException {
	AccountManagerTester.cleanupDatabase();
	xoManager = xoManagerFactory.createXOManager();
	GraphTraversal<Vertex, Vertex> vertices = ((Graph) ductileGraph).traversal().V();
	int counter = 0;
	while (vertices.hasNext()) {
	    Vertex vertex = vertices.next();
	    counter++;
	    Iterator<VertexProperty<Object>> properties = vertex.properties();
	    while (properties.hasNext()) {
		VertexProperty<Object> property = properties.next();
		System.out.println(property.key() + ": " + property.value());
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
