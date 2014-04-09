package com.puresoltechnologies.purifinity.server.systemmonitor.events;

import static org.junit.Assert.assertNotNull;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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

	@After
	public void tearDown() {
		((EventLoggerBean) eventLogger).disconnect();
	}

	@Test
	public void testSingleEntry() {
		eventLogger.logEvent(new Event(EventType.SYSTEM, EventSeverity.INFO,
				"Hallo"));
	}
}
