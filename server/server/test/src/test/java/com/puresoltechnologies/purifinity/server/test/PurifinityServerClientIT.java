package com.puresoltechnologies.purifinity.server.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.puresoltechnologies.purifinity.commons.test.PerformanceTest;
import com.puresoltechnologies.purifinity.commons.test.PerformanceTestResult;
import com.puresoltechnologies.purifinity.commons.test.PerformanceTester;
import com.puresoltechnologies.purifinity.server.client.socket.PurifinityServerClient;

public class PurifinityServerClientIT extends
		AbstractPurifinityServerClientTest {

	@Test
	public void testSingleStatusRequest() throws IOException,
			InterruptedException {
		PurifinityServerClient client = new PurifinityServerClient();
		try {
			assertNotNull(client.getServerStatus());
		} finally {
			client.close();
		}
	}

	@Test
	public void testStatusRequestPerformance() throws InterruptedException,
			IOException {
		final PurifinityServerClient client = new PurifinityServerClient();
		try {
			PerformanceTestResult<Void> performanceResult = PerformanceTester
					.runPerformanceTest(4, 25, new PerformanceTest<Void>() {
						@Override
						public Void start(int threadId, int eventId)
								throws Exception {
							assertNotNull(client.getServerStatus());
							return null;
						}
					});
			System.out.println(performanceResult);
			assertFalse(performanceResult.hadErrror());
			assertTrue(performanceResult.getSpeed() > 5);
		} finally {
			client.close();
		}
	}
}
