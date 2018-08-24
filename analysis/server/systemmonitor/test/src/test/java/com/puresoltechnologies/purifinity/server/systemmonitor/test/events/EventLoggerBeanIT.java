package com.puresoltechnologies.purifinity.server.systemmonitor.test.events;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Test;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.common.test.PerformanceTest;
import com.puresoltechnologies.purifinity.server.common.test.PerformanceTestResult;
import com.puresoltechnologies.purifinity.server.common.test.PerformanceTester;
import com.puresoltechnologies.purifinity.server.systemmonitor.test.AbstractSystemMonitorServerTest;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLogger;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

public class EventLoggerBeanIT extends AbstractSystemMonitorServerTest {

    @Inject
    private EventLogger eventLogger;

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
	event.setUserEmail(new EmailAddress("user@email.com"));
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
				event.setUserEmail(new EmailAddress(
					"user@email.com"));
				event.setUserId(123456);
				eventLogger.logEvent(event);
				return null;
			    }
			});

	System.out.println(performanceResult.toString());

	double speed = performanceResult.getSpeed();
	System.out.println("EventLogger speed: " + speed + " events/s");
	assertTrue(speed > 250);
    }
}
