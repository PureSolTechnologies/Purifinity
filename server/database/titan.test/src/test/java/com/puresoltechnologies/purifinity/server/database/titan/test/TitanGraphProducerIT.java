package com.puresoltechnologies.purifinity.server.database.titan.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.test.AbstractWeldTest;
import com.thinkaurelius.titan.core.TitanGraph;

public class TitanGraphProducerIT extends AbstractWeldTest {

	@Test
	public void test() {
		TitanGraph titanGraph = getInstance(TitanGraph.class);
		assertNotNull(titanGraph);
		assertTrue(titanGraph.isOpen());
	}

	@Test
	public void testSingleton() {
		TitanGraph titanGraph1 = getInstance(TitanGraph.class);
		assertNotNull(titanGraph1);
		TitanGraph titanGraph2 = getInstance(TitanGraph.class);
		assertNotNull(titanGraph2);
		assertSame(titanGraph1, titanGraph2);
	}

}
