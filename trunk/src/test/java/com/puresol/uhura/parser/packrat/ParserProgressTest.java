package com.puresol.uhura.parser.packrat;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.parser.ParserTree;

public class ParserProgressTest {

	@Test
	public void testFactoryMethods() {
		assertNotNull(ParserProgress.success(1, 2, 3, null));
		assertNotNull(ParserProgress.none());
		assertNotNull(ParserProgress.failure());
	}

	@Test
	public void testInitialValuesForSuccess() {
		ParserTree tree = new ParserTree("TEST");
		ParserProgress success = ParserProgress.success(1, 2, 3, tree);
		assertEquals(1, success.getDeltaPosition());
		assertEquals(2, success.getDeltaId());
		assertEquals(3, success.getDeltaLine());
		assertSame(tree, success.getTree());
	}

	@Test
	public void testInitialValuesForNone() {
		ParserProgress success = ParserProgress.none();
		assertEquals(0, success.getDeltaPosition());
		assertEquals(0, success.getDeltaId());
		assertEquals(0, success.getDeltaLine());
		assertSame(null, success.getTree());
	}

	@Test
	public void testInitialValuesForFailure() {
		ParserProgress success = ParserProgress.failure();
		assertEquals(-1, success.getDeltaPosition());
		assertEquals(-1, success.getDeltaId());
		assertEquals(-1, success.getDeltaLine());
		assertSame(null, success.getTree());
	}

	@Test
	public void testCompareTo() {
		ParserProgress success = ParserProgress.success(1, 2, 3,
				new ParserTree("TEST"));
		ParserProgress success2 = ParserProgress.success(2, 3, 4,
				new ParserTree("TEST2"));
		ParserProgress none = ParserProgress.none();
		ParserProgress failure = ParserProgress.failure();

		assertEquals(1, none.compareTo(failure));
		assertEquals(1, success.compareTo(none));
		assertEquals(1, success2.compareTo(success));

		assertEquals(-1, failure.compareTo(none));
		assertEquals(-1, none.compareTo(success));
		assertEquals(-1, success.compareTo(success2));

		assertEquals(0, failure.compareTo(failure));
		assertEquals(0, none.compareTo(none));
		assertEquals(0, success.compareTo(success));
		assertEquals(0, success2.compareTo(success2));
	}

}
