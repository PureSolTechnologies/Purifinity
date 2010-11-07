package com.puresol.uhura.parser.parsetable;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.grammar.GrammarException;

public class ParserActionSetTest {

	@Test
	public void testInstance() {
		assertNotNull(new ParserActionSet());
	}

	@Test
	public void testDefaultValues() {
		try {
			ParserActionSet set = new ParserActionSet();
			assertEquals(1, set.getActionNumber());
			assertEquals(new ParserAction(ActionType.ERROR, -1),
					set.getAction(0));
			assertEquals(new ParserAction(ActionType.ERROR, -1),
					set.getAction());
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testAddAction() {
		ParserActionSet set = new ParserActionSet();

		set.addAction(new ParserAction(ActionType.SHIFT, 2));

		assertEquals(1, set.getActionNumber());
		assertEquals(new ParserAction(ActionType.SHIFT, 2), set.getAction(0));
		try {
			assertEquals(new ParserAction(ActionType.SHIFT, 2), set.getAction());
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}

		set.addAction(new ParserAction(ActionType.REDUCE, 12));

		assertEquals(2, set.getActionNumber());
		assertEquals(new ParserAction(ActionType.SHIFT, 2), set.getAction(0));
		assertEquals(new ParserAction(ActionType.REDUCE, 12), set.getAction(1));
		try {
			assertEquals(new ParserAction(ActionType.SHIFT, 2), set.getAction());
			fail("A grammar exception was expected!");
		} catch (GrammarException e) {
			// nothing to catch...
		}
	}

}
