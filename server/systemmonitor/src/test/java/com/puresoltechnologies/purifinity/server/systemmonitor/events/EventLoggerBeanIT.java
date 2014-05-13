package com.puresoltechnologies.purifinity.server.systemmonitor.events;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.test.PerformanceTest;
import com.puresoltechnologies.purifinity.server.common.test.PerformanceTestResult;
import com.puresoltechnologies.purifinity.server.common.test.PerformanceTester;

public class EventLoggerBeanIT {

	private static Weld weld;
	private static WeldContainer weldContainer;

	@BeforeClass
	public static void initialize() {
		weld = new Weld();
		weldContainer = weld.initialize();
	}

	@AfterClass
	public static void destroy() {
		weldContainer = null;
		weld.shutdown();
		weld = null;
	}

	private EventLogger eventLogger;

	@Before
	public void setup() {
		eventLogger = weldContainer.instance().select(EventLogger.class).get();
		assertNotNull(eventLogger);
	}

	@Test
	public void testSingleMinimumEntry() {
		eventLogger.logEvent(new Event("component", 0x42, EventType.SYSTEM,
				EventSeverity.INFO, "Hallo"));
	}

	@Test
	public void testSingleFullEntry() {
		Event event = new Event("componentName", 0x21, EventType.USER_ACTION,
				EventSeverity.INFO, "Hallo");
		event.setClientHostname("clientHostName");
		event.setThrowable(new IOException("This is a test exception",
				new IllegalArgumentException(
						"This is a test cause for the test exception.")));
		event.setUserEmail("user@email.com");
		event.setUserId(123456);
		eventLogger.logEvent(event);
	}

	@Test
	public void testParallelFullEntries() throws InterruptedException {
		int NUMBER_OF_THREADS = 8;
		final int NUMBER_OF_EVENTS_PER_THREAD = 125;

		final IOException testException = new IOException(
				"This is a test exception", new IllegalArgumentException(
						"This is a test cause for the test exception."));

		PerformanceTestResult<Void> performanceResult = PerformanceTester
				.runPerformanceTest(NUMBER_OF_THREADS,
						NUMBER_OF_EVENTS_PER_THREAD,
						new PerformanceTest<Void>() {

							@Override
							public Void start(int threadId, int eventId) {
								String componentName = "Component " + threadId;
								Event event = new Event(componentName, eventId,
										EventType.USER_ACTION,
										EventSeverity.INFO, "Hallo");
								event.setClientHostname("clientHostName");
								event.setThrowable(testException);
								event.setUserEmail("user@email.com");
								event.setUserId(123456);
								eventLogger.logEvent(event);
								return null;
							}
						});

		System.out.println(performanceResult.toString());

		double speed = performanceResult.getSpeed();
		System.out.println("EventLogger speed: " + speed + " events/s");
		assertTrue(speed > 500);
	}
}
