package com.puresoltechnologies.purifinity.server.database.cassandra.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.jboss.weld.exceptions.AmbiguousResolutionException;
import org.junit.Test;

import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.common.test.AbstractWeldTest;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;

public class SessionProducerIT extends AbstractWeldTest {

	@Test
	public void testAnalysisKeyspace() {
		Session session = getInstance(Session.class,
				AnalysisStoreKeyspace.class);
		assertNotNull(session);
		assertFalse(session.isClosed());
		assertEquals("analysis_store", session.getLoggedKeyspace());
	}

	@Test
	public void testAnalysisKeyspaceSingleton() {
		Session session1 = getInstance(Session.class,
				AnalysisStoreKeyspace.class);
		assertNotNull(session1);
		Session session2 = getInstance(Session.class,
				AnalysisStoreKeyspace.class);
		assertNotNull(session2);
		assertSame(session1, session2);
	}

	@Test
	public void testEvaluationKeyspace() {
		Session session = getInstance(Session.class,
				EvaluationStoreKeyspace.class);
		assertNotNull(session);
		assertFalse(session.isClosed());
		assertEquals("evaluation_store", session.getLoggedKeyspace());
	}

	@Test
	public void testEvaluationKeyspaceSingleton() {
		Session session1 = getInstance(Session.class,
				EvaluationStoreKeyspace.class);
		assertNotNull(session1);
		Session session2 = getInstance(Session.class,
				EvaluationStoreKeyspace.class);
		assertNotNull(session2);
		assertSame(session1, session2);
	}

	/**
	 * We check here for the existence of multiple producers for multiple
	 * {@link Session}s.
	 */
	@Test(expected = AmbiguousResolutionException.class)
	public void testAmbiguousResolutionException() {
		getInstance(Session.class);
	}

}
