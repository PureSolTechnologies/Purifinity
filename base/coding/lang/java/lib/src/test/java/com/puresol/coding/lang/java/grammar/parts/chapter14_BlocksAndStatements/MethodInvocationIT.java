package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class MethodInvocationIT {

    @Test
    public void test1() throws Exception {
	assertTrue(JavaGrammarPartTester.test("MethodInvocation",
		"System.err.println(\"\")"));
    }

    @Test
    public void test2() throws Exception {
	assertTrue(JavaGrammarPartTester.test("MethodInvocation",
		"com.sun.deploy.services.ServiceManager.setService()"));
    }

    @Test
    public void test3() throws Exception {
	assertTrue(JavaGrammarPartTester.test("MethodInvocation",
		"com.sun.deploy.services.ServiceManager.setService(integer)"));
    }

    @Test
    public void test4() throws Exception {
	assertTrue(JavaGrammarPartTester
		.test("MethodInvocation",
			"com.sun.deploy.services.ServiceManager.setService(com.sun.deploy.services.PlatformType.STANDALONE_TIGER_WIN32)"));
    }

}
