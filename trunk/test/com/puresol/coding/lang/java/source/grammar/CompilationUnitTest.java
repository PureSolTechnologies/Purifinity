package com.puresol.coding.lang.java.source.grammar;

import org.junit.Test;

import com.puresol.coding.lang.java.JavaParser;
import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

public class CompilationUnitTest extends TestCase {

    @Test
    public void testEmptyCompilationUnit() {
	assertTrue(JavaGrammarTester.valid("", JavaParser.class));
    }
}
