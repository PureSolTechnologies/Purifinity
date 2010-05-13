package com.puresol.coding.lang.java.source.grammar;

import org.junit.Test;

import com.puresol.coding.lang.java.JavaParser;
import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CompilationUnitTest extends TestCase {

	@Test
	public void testEmptyCompilationUnit() {
		Assert.assertTrue(JavaGrammarTester.valid("", JavaParser.class));
	}
}
