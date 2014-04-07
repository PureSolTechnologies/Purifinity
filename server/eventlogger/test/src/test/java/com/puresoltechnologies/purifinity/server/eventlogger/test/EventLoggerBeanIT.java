package com.puresoltechnologies.purifinity.server.eventlogger.test;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.eventlogger.EventLogger;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;

public class EventLoggerBeanIT extends AbstractServerTest {

	@Inject
	private EventLogger eventLogger;

	@Test
	public void test() {
		assertNotNull(eventLogger);
		eventLogger.logUserAction("user", "message");
	}

}
