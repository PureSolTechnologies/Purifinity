package com.puresoltechnologies.purifinity.server.test.database.cassandra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerServerTest;

@RunWith(Arquillian.class)
public class SessionProducerIT extends AbstractPurifinityServerServerTest {

	@Inject
	@AnalysisStoreKeyspace
	private Session analysisSession1;

	@Inject
	@EvaluationStoreKeyspace
	private Session evaluationSession1;

	@Test
	public void testAnalysisKeyspace() {
		assertNotNull(analysisSession1);
		assertFalse(analysisSession1.isClosed());
		assertEquals("analysis_store", analysisSession1.getLoggedKeyspace());
	}

	// @Test
	// public void testAnalysisKeyspaceSingleton() {
	// Session session1 = getInstance(Session.class,
	// AnalysisStoreKeyspace.class);
	// assertNotNull(session1);
	// Session session2 = getInstance(Session.class,
	// AnalysisStoreKeyspace.class);
	// assertNotNull(session2);
	// assertSame(session1, session2);
	// }

	@Test
	public void testEvaluationKeyspace() {
		System.err.println("!!!!!!!!!!!!!!");
		assertNotNull(evaluationSession1);
		assertFalse(evaluationSession1.isClosed());
		assertEquals("evaluation_store", evaluationSession1.getLoggedKeyspace());
	}

	// @Test
	// public void testEvaluationKeyspaceSingleton() {
	// Session session1 = getInstance(Session.class,
	// EvaluationStoreKeyspace.class);
	// assertNotNull(session1);
	// Session session2 = getInstance(Session.class,
	// EvaluationStoreKeyspace.class);
	// assertNotNull(session2);
	// assertSame(session1, session2);
	// }

	// /**
	// * We check here for the existence of multiple producers for multiple
	// * {@link Session}s.
	// */
	// @Test(expected = AmbiguousResolutionException.class)
	// public void testAmbiguousResolutionException() {
	// getInstance(Session.class);
	// }

}
