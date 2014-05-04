package com.puresoltechnologies.purifinity.wildfly.test;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

/**
 * This is an abstract test class for Arquillian driven client tests.
 * 
 * @author Rick-Rainer Ludwig
 */
@RunWith(Arquillian.class)
@RunAsClient
public abstract class AbstractGlobalClientTest extends AbstractArquillianTest {

}