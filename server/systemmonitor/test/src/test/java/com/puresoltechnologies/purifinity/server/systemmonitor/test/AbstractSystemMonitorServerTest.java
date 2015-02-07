package com.puresoltechnologies.purifinity.server.systemmonitor.test;

import javax.ws.rs.BeanParam;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;

import com.puresoltechnologies.purifinity.server.common.test.PerformanceTest;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractSystemMonitorServerTest extends
	AbstractServerTest {

    @EnhanceDeployment
    public static final void enhanceDeployment(JavaArchive archive) {
	archive.addPackages(true,
		AbstractSystemMonitorServerTest.class.getPackage());
	archive.addPackages(true, PerformanceTest.class.getPackage());
    }

    @Before
    public void cleanup() {
	SystemMonitorTester.cleanupDatabase();
    }
}
