package com.puresol.jboss.arquillian;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;

public class ArquillianConfigurationTest {

    @Test
    public void initialize() throws Exception {
	URL resource = ArquillianConfigurationTest.class
		.getResource("/arquillian.xml");
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

}
