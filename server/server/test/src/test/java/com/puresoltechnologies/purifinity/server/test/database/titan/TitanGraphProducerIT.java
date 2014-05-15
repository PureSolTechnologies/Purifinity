package com.puresoltechnologies.purifinity.server.test.database.titan;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerServerTest;
import com.puresoltechnologies.purifinity.server.wildfly.utils.BeanUtilities;
import com.thinkaurelius.titan.core.TitanGraph;

public class TitanGraphProducerIT extends AbstractPurifinityServerServerTest {

	@Inject
	private TitanGraph titanGraph;

	@Test
	public void test() {
		assertNotNull(titanGraph);
		assertTrue(titanGraph.isOpen());
	}

	@Test
	public void testSingleton() {
		assertNotNull(titanGraph);
		TitanGraph titanGraph2 = BeanUtilities.getBean(TitanGraph.class);
		assertNotNull(titanGraph2);
		assertSame(titanGraph, titanGraph2);
	}

}
