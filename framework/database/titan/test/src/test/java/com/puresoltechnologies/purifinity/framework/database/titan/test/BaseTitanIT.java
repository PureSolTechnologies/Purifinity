package com.puresoltechnologies.purifinity.framework.database.titan.test;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.junit.Test;

import com.puresoltechnologies.purifinity.commons.test.AbstractCassandraTest;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

public class BaseTitanIT extends AbstractCassandraTest {

	@Test
	public void test() {
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.backend", "cassandra");
		conf.setProperty("storage.hostname", "127.0.0.1");
		TitanGraph graph = TitanFactory.open(conf);
		assertTrue("Titan graph is not open.", graph.isOpen());
		try {
			String s = "projects";
			Vertex projectsVertex = graph.addVertex(s);
			projectsVertex.setProperty("property", "value");
			graph.commit();

			Iterable<Vertex> vertices = graph.query().has("property")
					.vertices();
			Iterator<Vertex> iterator = vertices.iterator();
			assertTrue(iterator.hasNext());
			while (iterator.hasNext()) {
				Vertex vertex = iterator.next();
				System.out.println(vertex.getPropertyKeys().toString());
				graph.removeVertex(vertex);
			}
		} finally {
			graph.shutdown();
		}
	}
}
