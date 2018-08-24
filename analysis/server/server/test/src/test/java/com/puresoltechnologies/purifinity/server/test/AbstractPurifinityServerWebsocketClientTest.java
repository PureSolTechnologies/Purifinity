package com.puresoltechnologies.purifinity.server.test;

import org.jboss.arquillian.container.test.api.Testable;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPurifinityServerWebsocketClientTest extends
	AbstractPurifinityClientTest {

    @EnhanceDeployment
    public static void selectTestableWAR(EnterpriseArchive enterpriseArchive)
	    throws Exception {
	WebArchive testableWar = enterpriseArchive.getAsType(WebArchive.class,
		"server.socket.impl.war");
	enterpriseArchive.delete("server.socket.impl.war");
	enterpriseArchive.addAsModule(Testable.archiveToTest(testableWar));
    }

}
