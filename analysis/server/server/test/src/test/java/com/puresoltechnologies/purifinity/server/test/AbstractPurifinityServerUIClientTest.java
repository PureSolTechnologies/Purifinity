package com.puresoltechnologies.purifinity.server.test;

import org.jboss.arquillian.container.test.api.Testable;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPurifinityServerUIClientTest extends
	AbstractPurifinityClientTest {

    @EnhanceDeployment
    public static void selectTestableWAR(EnterpriseArchive enterpriseArchive)
	    throws Exception {
	WebArchive testableWar = enterpriseArchive.getAsType(WebArchive.class,
		"server.ui.war");
	enterpriseArchive.delete("server.ui.war");
	enterpriseArchive.addAsModule(Testable.archiveToTest(testableWar));
    }

}
