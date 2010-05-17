package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

/**
 * ('[' ']' )+
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DimsTest extends TestCase {

	@Test
	public void testValids() {
//		assertTrue(JavaGrammarTester.valid("[]", Dims.class));
//		assertTrue(JavaGrammarTester.valid("[][]", Dims.class));
		assertTrue(JavaGrammarTester.valid("[][][]", Dims.class));
	}

}
