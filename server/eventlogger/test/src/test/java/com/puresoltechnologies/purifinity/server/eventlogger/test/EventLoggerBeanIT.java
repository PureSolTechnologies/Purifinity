package com.puresoltechnologies.purifinity.server.eventlogger.test;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;

import com.puresoltechnologies.purifinity.jboss.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.server.eventlogger.EventLogger;

public class EventLoggerBeanIT extends AbstractServerTest {

	@Inject
	private EventLogger eventLogger;

	@Test
	public void test() {
		assertNotNull(eventLogger);
		eventLogger.logUserAction("user", "message");
	}

}
