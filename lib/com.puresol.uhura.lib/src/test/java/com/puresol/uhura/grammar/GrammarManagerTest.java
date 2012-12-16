package com.puresol.uhura.grammar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Test;

public class GrammarManagerTest {

    @Test
    public void testURLHandlingForClass() {
	URL url = GrammarManagerTest.class
		.getResource("/com/puresol/uhura/grammar/TestGrammar.g");
	assertNotNull(url);
	assertTrue(url.toString().endsWith(
		"/com/puresol/uhura/grammar/TestGrammar.g"));
	assertEquals("file", url.getProtocol());
    }

}
