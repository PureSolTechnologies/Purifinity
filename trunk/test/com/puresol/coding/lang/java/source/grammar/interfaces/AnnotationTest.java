package com.puresol.coding.lang.java.source.grammar.interfaces;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class AnnotationTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester
				.valid(
						"@XmlRootElement(name = \"location\", namespace = \"http://ludwig.endofinternet.net\")",
						Annotation.class));
		assertTrue(JavaGrammarTester.valid(
				"@XmlAccessorType(XmlAccessType.FIELD)", Annotation.class));

	}
}
