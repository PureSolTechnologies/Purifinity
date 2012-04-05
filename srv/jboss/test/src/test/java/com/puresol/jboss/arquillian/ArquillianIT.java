package com.puresol.jboss.arquillian;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@RunAsClient
public class ArquillianIT {

    @BeforeClass
    public static void initialize() throws Exception {
	URL resource = ArquillianIT.class.getResource("/arquillian.xml");
	assertNotNull(resource);
	InputStream inStream = resource.openStream();
	try {
	    BufferedReader bufferedReader = new BufferedReader(
		    new InputStreamReader(inStream));
	    String line;
	    while ((line = bufferedReader.readLine()) != null) {
		System.out.println(line);
	    }
	} finally {
	    inStream.close();
	}
    }

    @Test
    public void test() {

    }

}
