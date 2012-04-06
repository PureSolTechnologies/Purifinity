package com.puresol.jboss.arquillian;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@RunAsClient
public class ArquillianIT {

    @Test
    public void test() {
	System.out.println("Test!");
    }

}
