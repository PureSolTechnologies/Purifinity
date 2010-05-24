package com.puresol.coding.lang.java.source.grammar.interfaces;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class SingleElementAnnotationTest extends TestCase {

	@Test
	public void testValids() {
		assertTrue(JavaGrammarTester.valid(
				"@XmlAccessorType(XmlAccessType.FIELD)",
				SingleElementAnnotation.class));

	}
}
