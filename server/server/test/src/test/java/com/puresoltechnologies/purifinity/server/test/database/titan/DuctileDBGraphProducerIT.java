package com.puresoltechnologies.purifinity.server.test.database.titan;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import javax.inject.Inject;

import org.junit.Test;

import com.puresoltechnologies.ductiledb.tinkerpop.DuctileGraph;
import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerServerTest;
import com.puresoltechnologies.purifinity.server.wildfly.utils.BeanUtilities;

public class DuctileDBGraphProducerIT extends AbstractPurifinityServerServerTest {

    @Inject
    private DuctileGraph ductileGraph;

    @Test
    public void test() {
	assertNotNull(ductileGraph);
    }

    @Test
    public void testSingleton() {
	assertNotNull(ductileGraph);
	DuctileGraph titanGraph2 = BeanUtilities.getBean(DuctileGraph.class);
	assertNotNull(titanGraph2);
	assertSame(ductileGraph, titanGraph2);
    }

}
