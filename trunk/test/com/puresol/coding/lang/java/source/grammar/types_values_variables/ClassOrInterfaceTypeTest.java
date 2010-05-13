package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class ClassOrInterfaceTypeTest extends TestCase {

	@Test
	public void testValids() {
		JavaGrammarTester.valid("a", ClassOrInterfaceType.class);
		JavaGrammarTester.valid("a.b", ClassOrInterfaceType.class);
		JavaGrammarTester.valid("a<c>", ClassOrInterfaceType.class);
		JavaGrammarTester.valid("a.b<c,d>", ClassOrInterfaceType.class);
	}

}
