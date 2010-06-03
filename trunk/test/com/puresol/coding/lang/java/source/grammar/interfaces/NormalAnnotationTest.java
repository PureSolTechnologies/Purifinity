package com.puresol.coding.lang.java.source.grammar.interfaces;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class NormalAnnotationTest extends TestCase {

	@Test
	public void testValids() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		assertTrue(JavaGrammarTester
				.valid(
						"@XmlRootElement(name = \"location\", namespace = \"http://ludwig.endofinternet.net\")",
						NormalAnnotation.class));
	}
}
