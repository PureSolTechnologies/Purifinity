package com.puresoltechnologies.purifinity.server.test;

import org.jboss.arquillian.container.test.api.Testable;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.puresoltechnologies.purifinity.server.wildfly.utils.BeanUtilities;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPurifinityServerServerTest extends
	AbstractServerTest {

    @EnhanceDeployment
    public static void removeNotNeededWARFiles(
	    EnterpriseArchive enterpriseArchive) throws Exception {
	WebArchive testableWar = enterpriseArchive.getAsType(WebArchive.class,
		"/server.ui.war");
	enterpriseArchive.delete("/server.ui.war");
	enterpriseArchive.addAsModule(Testable.archiveToTest(testableWar));
	// removeWAR(enterpriseArchive, "server.socket.impl.war");
	// removeWAR(enterpriseArchive, "server.rest.impl.war");
	// removeWAR(enterpriseArchive, "server.ui.war");
    }

    @EnhanceDeployment
    public static void addTestClasses(JavaArchive javaArchive) {
	javaArchive.addPackages(true,
		AbstractPurifinityServerServerTest.class.getPackage());
	javaArchive.addPackages(true, BeanUtilities.class.getPackage());
    }
}
