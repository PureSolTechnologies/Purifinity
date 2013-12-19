package com.puresoltechnologies.purifinity.server.logger;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

@RunWith(Arquillian.class)
public class LoggerProducerIT {

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class);
		javaArchive.addClass(LoggerProducer.class);
		javaArchive.addAsResource("META-INF/beans.xml", "");
		return javaArchive;
	}

	@Inject
	private Logger logger;

	@Test
	public void test() {
		assertNotNull(logger);
	}

}
