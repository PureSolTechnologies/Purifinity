package com.puresol.coding.lang.java.grammar.parts.chapter14_BlocksAndStatements;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.JavaGrammarPartTester;

public class MethodInvocationTest {

	@Test
	public void test1() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(JavaGrammarPartTester.test("MethodInvocation",
				"System.err.println(\"\")"));
	}

	@Test
	public void test2() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		Logger.getRootLogger().info("Start");
		assertTrue(JavaGrammarPartTester.test("MethodInvocation",
				"com.sun.deploy.services.ServiceManager.setService()"));
		Logger.getRootLogger().info("Stop");
	}

	@Test
	public void test3() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		Logger.getRootLogger().info("Start");
		assertTrue(JavaGrammarPartTester.test("MethodInvocation",
				"com.sun.deploy.services.ServiceManager.setService(integer)"));
		Logger.getRootLogger().info("Stop");
	}

	@Test
	public void test4() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(JavaGrammarPartTester
				.test("MethodInvocation",
						"com.sun.deploy.services.ServiceManager.setService(com.sun.deploy.services.PlatformType.STANDALONE_TIGER_WIN32)"));
	}

}
