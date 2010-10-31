package com.puresol.coding.lang.java.grammar.parts.chapter8_Classes;

import static org.junit.Assert.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.lang.java.grammar.GrammarPartTester;

public class EnumDeclarationTest {

	@Test
	public void testWithoutInitializer() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("EnumDeclaration",
				"    public enum State {\n" + "SAME,\n" + "NEW,\n"
						+ "DELETED\n" + "}\n"));
	}
}
