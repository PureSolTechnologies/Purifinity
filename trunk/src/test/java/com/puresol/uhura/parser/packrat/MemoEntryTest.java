package com.puresol.uhura.parser.packrat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresol.uhura.parser.ParserTree;

public class MemoEntryTest {

	@Test
	public void testFactoryMethods() {
		assertNotNull(MemoEntry.success(1, 2, 3, null));
		assertNotNull(MemoEntry.failure());
	}

	@Test
	public void testInitialValuesForSuccess() {
		ParserTree tree = new ParserTree("TEST");
		MemoEntry success = MemoEntry.success(1, 2, 3, tree);
		assertEquals(1, success.getDeltaPosition());
		assertSame(tree, success.getTree());
	}

	@Test
	public void testInitialValuesForFailure() {
		MemoEntry success = MemoEntry.failure();
		assertEquals(-1, success.getDeltaPosition());
		assertSame(null, success.getTree());
	}

	@Test
	public void testCompareTo() {
		MemoEntry success = MemoEntry.success(1, 2, 3, new ParserTree("TEST"));
		MemoEntry success2 = MemoEntry
				.success(2, 3, 4, new ParserTree("TEST2"));
		MemoEntry failure = MemoEntry.failure();

		assertEquals(1, success2.compareTo(success));
		assertEquals(-1, success.compareTo(success2));

		assertEquals(0, failure.compareTo(failure));
		assertEquals(0, success.compareTo(success));
		assertEquals(0, success2.compareTo(success2));
	}

}
