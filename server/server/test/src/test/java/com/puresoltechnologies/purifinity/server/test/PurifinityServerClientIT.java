package com.puresoltechnologies.purifinity.server.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.client.socket.PurifinityServerClient;
import com.puresoltechnologies.purifinity.server.common.test.PerformanceTest;
import com.puresoltechnologies.purifinity.server.common.test.PerformanceTestResult;
import com.puresoltechnologies.purifinity.server.common.test.PerformanceTester;

public class PurifinityServerClientIT extends
		AbstractPurifinityServerClientTest {

	@Test
	public void testSingleStatusRequest() throws IOException,
			InterruptedException {
		try (PurifinityServerClient client = PurifinityServerClient
				.getInstance()) {
			assertNotNull(client.getServerStatus());
		}
	}

	@Test
	public void testStatusRequestPerformance() throws InterruptedException,
			IOException {
		try (PurifinityServerClient client = PurifinityServerClient
				.getInstance()) {
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
		}
	}
}
