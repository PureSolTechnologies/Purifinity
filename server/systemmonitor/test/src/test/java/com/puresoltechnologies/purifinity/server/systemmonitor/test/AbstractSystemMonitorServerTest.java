package com.puresoltechnologies.purifinity.server.systemmonitor.test;

import org.jboss.shrinkwrap.api.spec.JavaArchive;

import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractSystemMonitorServerTest extends
	AbstractServerTest {

    @EnhanceDeployment
    public static final void enhanceDeployment(JavaArchive archive) {
	archive.addPackages(
		true,
		com.puresoltechnologies.purifinity.server.systemmonitor.test.AbstractSystemMonitorServerTest.class
			.getPackage());
	archive.addPackages(
		true,
		com.puresoltechnologies.purifinity.server.common.test.PerformanceTest.class
			.getPackage());
    }

}
