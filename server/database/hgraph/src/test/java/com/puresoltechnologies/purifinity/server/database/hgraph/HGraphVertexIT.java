package com.puresoltechnologies.purifinity.server.database.hgraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tinkerpop.blueprints.Vertex;

public class HGraphVertexIT {

    @BeforeClass
    public static void removeTables() throws IOException {
	try (HGraph graph = GraphFactory.createGraph()) {
	    Connection connection = ((HGraphImpl) graph).getConnection();
	    Admin admin = connection.getAdmin();
	    TableName vertexTableName = TableName.valueOf(HGraphImpl.VERTICES_TABLE_NAME);
	    admin.disableTable(vertexTableName);
	    admin.deleteTable(vertexTableName);
	    admin.deleteNamespace(HGraphImpl.NAMESPACE_NAME);
	}
    }

    @Test
    public void testVertexCreation() throws IOException {
	try (HGraph graph = GraphFactory.createGraph()) {
	    Vertex vertex = graph.addVertex("TestId");
	    graph.commit();
	    Vertex vertex2 = graph.getVertex(vertex.getId());
	    assertEquals("TestId", vertex.getId().toString());
	    assertEquals(vertex, vertex2);
	}
    }

    @Test
    public void testVertexDelete() throws IOException {
	try (HGraph graph = GraphFactory.createGraph()) {
	    Vertex vertex = graph.addVertex("DeletionTest");
	    graph.commit();

	    Vertex vertex2 = graph.getVertex(vertex.getId());
	    assertEquals("DeletionTest", vertex.getId().toString());
	    assertEquals(vertex, vertex2);

	    graph.removeVertex(vertex);
	    graph.commit();
	    assertNull(graph.getVertex(vertex.getId()));
	}
    }

    @Test
    public void testSetProperty() throws IOException {
	try (HGraph graph = GraphFactory.createGraph()) {
	    Vertex vertex = graph.addVertex("DeletionTest");
	    vertex.setProperty("property1", "value1");
	    graph.commit();
	    assertEquals("DeletionTest", vertex.getId().toString());
	    assertEquals("value1", vertex.getProperty("property1"));

	    Vertex vertex2 = graph.getVertex(vertex.getId());
	    assertEquals(vertex, vertex2);

	    vertex.removeProperty("property1");
	    graph.commit();
	    assertNull(graph.getVertex(vertex.getProperty("property1")));
	}
    }

    @Test
    public void testVertexCreationPerformance() throws IOException {
	try (HGraph graph = GraphFactory.createGraph()) {
	    long start = System.currentTimeMillis();
	    int number = 10000;
	    for (int i = 0; i < number; ++i) {
		Vertex vertex = graph.addVertex("TestId" + i);
		assertEquals("TestId" + i, vertex.getId().toString());
	    }
	    graph.commit();
	    long stop = System.currentTimeMillis();
	    long duration = stop - start;
	    double speed = (double) number / (double) duration * 1000.0;
	    System.out.println("time: " + duration + "ms");
	    System.out.println("speed: " + speed + "vertices/s");
	    assertTrue(duration < 10000);
	}
    }

    @Test
    public void testSetPropertyPerformance() throws IOException {
	try (HGraph graph = GraphFactory.createGraph()) {
	    Vertex vertex = graph.addVertex("setPropertyNode");
	    long start = System.currentTimeMillis();
	    int number = 10000;
	    for (int i = 0; i < number; ++i) {
		vertex.setProperty("key" + i, i);
		assertEquals(i, ((Integer) vertex.getProperty("key" + i)).intValue());
	    }
	    graph.commit();
	    long stop = System.currentTimeMillis();
	    long duration = stop - start;
	    double speed = (double) number / (double) duration * 1000.0;
	    System.out.println("time: " + duration + "ms");
	    System.out.println("speed: " + speed + "properties/s");
	    assertTrue(duration < 10000);
	}
    }

}
