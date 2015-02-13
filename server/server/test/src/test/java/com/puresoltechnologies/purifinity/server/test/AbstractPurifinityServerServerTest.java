package com.puresoltechnologies.purifinity.server.test;

import org.jboss.arquillian.container.test.api.Testable;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.puresoltechnologies.purifinity.server.wildfly.utils.BeanUtilities;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPurifinityServerServerTest extends
	AbstractServerTest {

    @EnhanceDeployment
    public static void selectTestableWAR(EnterpriseArchive enterpriseArchive)
	    throws Exception {
	WebArchive testableWar = enterpriseArchive.getAsType(WebArchive.class,
		"server.ui.war");
	enterpriseArchive.delete("server.ui.war");
	enterpriseArchive.addAsModule(Testable.archiveToTest(testableWar));
    }

    @EnhanceDeployment
    public static void addTestClasses(JavaArchive javaArchive) {
	javaArchive.addPackages(true,
		AbstractPurifinityServerServerTest.class.getPackage());
	javaArchive.addPackages(true, BeanUtilities.class.getPackage());
	javaArchive.addClass(PasswordStoreTester.class);
    }
}
