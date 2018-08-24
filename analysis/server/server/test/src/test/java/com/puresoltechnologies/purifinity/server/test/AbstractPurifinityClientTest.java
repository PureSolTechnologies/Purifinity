package com.puresoltechnologies.purifinity.server.test;

import org.jboss.shrinkwrap.api.spec.JavaArchive;

import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractClientTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPurifinityClientTest extends AbstractClientTest {

    @EnhanceDeployment
    public static void addNeededClasses(JavaArchive javaArchive) {
	javaArchive.addClass(PasswordStoreTester.class);
    }

}
