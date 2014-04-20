package com.puresoltechnologies.purifinity.wildfly.test;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.runner.RunWith;

/**
 * This is an abstract test class for Arquillian driven client tests.
 * 
 * @author Rick-Rainer Ludwig
 */
@RunWith(Arquillian.class)
@RunAsClient
public abstract class AbstractClientTest extends AbstractArquillianTest {

	@Deployment
	public static EnterpriseArchive createArchive() {
		File earFile = findProjectEARFile();
		return ShrinkWrap.createFromZipFile(EnterpriseArchive.class, earFile);
	}

}