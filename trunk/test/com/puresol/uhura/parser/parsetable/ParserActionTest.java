package com.puresol.uhura.parser.parsetable;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserActionTest {

	@Test
	public void testInstance() {
		assertNotNull(new ParserAction(ActionType.SHIFT, 1));
	}

	@Test
	public void testInitialValue() {
		ParserAction action = new ParserAction(ActionType.SHIFT, 1);
		assertEquals(ActionType.SHIFT, action.getAction());
		assertEquals(1, action.getParameter());
	}

}
