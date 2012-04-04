package com.puresol.jboss.arquillian;

import static org.junit.Assert.assertNotNull;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@RunAsClient
public class ArquillianIT {

    @Deployment
    public static WebArchive deploy() {
	return ShrinkWrap.create(WebArchive.class, "test.war");
    }

    @BeforeClass
    public static void initialize() {
	assertNotNull(ArquillianIT.class.getResource("/arquillian.xml"));
    }

    @Test
    public void test() {

    }

}
