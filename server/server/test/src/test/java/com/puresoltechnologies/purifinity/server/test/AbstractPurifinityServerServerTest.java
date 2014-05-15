package com.puresoltechnologies.purifinity.server.test;

import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import com.puresoltechnologies.purifinity.server.wildfly.utils.BeanUtilities;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPurifinityServerServerTest extends
		AbstractServerTest {

	@EnhanceDeployment
	public static void removeNotNeededWARFiles(
			EnterpriseArchive enterpriseArchive) throws Exception {
		removeWAR(enterpriseArchive, "server.socket.impl.war");
	}

	@EnhanceDeployment
	public static void addTestClasses(JavaArchive javaArchive) {
		javaArchive.addPackages(true,
				AbstractPurifinityServerServerTest.class.getPackage());
		javaArchive.addPackages(true, BeanUtilities.class.getPackage());
	}
}
