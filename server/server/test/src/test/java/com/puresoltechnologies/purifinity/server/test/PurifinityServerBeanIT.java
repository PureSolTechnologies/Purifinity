package com.puresoltechnologies.purifinity.server.test;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.core.api.PurifinityServer;
import com.puresoltechnologies.purifinity.server.domain.PurifinityServerStatus;

public class PurifinityServerBeanIT extends AbstractPurifinityServerServerTest {

	@Inject
	private PurifinityServer purifinityServer;

	@Before
	public void checkConditions() {
		assertNotNull(purifinityServer);
	}

	@Test
	public void testLogger() {
		PurifinityServerStatus status = purifinityServer.getStatus();
		assertNotNull(status);
	}

}
