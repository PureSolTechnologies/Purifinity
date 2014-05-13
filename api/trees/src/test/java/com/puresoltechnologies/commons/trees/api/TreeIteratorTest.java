package com.puresoltechnologies.commons.trees.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresoltechnologies.commons.trees.TreeIterator;

public class TreeIteratorTest {

	@Test
	public void testInstance() {
		TreeImpl tree = new TreeImpl("Root");
		assertNotNull(new TreeIterator<TreeImpl>(tree));
	}

	@Test
	public void testInitValues() {
		TreeImpl tree = new TreeImpl("Root");
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		assertSame(tree, iterator.getCurrentNode());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullTree() {
		new TreeIterator<TreeImpl>(null);
	}

	@Test
	public void testGotoStart() {
		TreeImpl tree = TreeImpl.getSampleTree();
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		assertEquals("root", iterator.getCurrentNode().getName());
		iterator.gotoStart();
		assertEquals("root", iterator.getCurrentNode().getName());
	}

	@Test
	public void testGotoStartPart() {
		TreeImpl tree = TreeImpl.getSampleTree().getChildren().get(1);
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		assertEquals("n2", iterator.getCurrentNode().getName());
		iterator.gotoStart();
		assertEquals("n2", iterator.getCurrentNode().getName());
	}

	@Test
	public void testGotoEnd() {
		TreeImpl tree = TreeImpl.getSampleTree();
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		assertEquals("root", iterator.getCurrentNode().getName());
		iterator.gotoEnd();
		assertEquals("n33", iterator.getCurrentNode().getName());
	}

	@Test
	public void testGotoEndPart() {
		TreeImpl tree = TreeImpl.getSampleTree().getChildren().get(1);
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		assertEquals("n2", iterator.getCurrentNode().getName());
		iterator.gotoEnd();
		assertEquals("n23", iterator.getCurrentNode().getName());
	}

	@Test
	public void testGoForward() {
		TreeImpl tree = TreeImpl.getSampleTree();
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		assertEquals("root", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n1", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n11", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n12", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n13", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n2", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n21", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n22", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n23", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n3", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n31", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n32", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n33", iterator.getCurrentNode().getName());
		assertFalse(iterator.goForward());
	}

	@Test
	public void testGoForwardPart() {
		TreeImpl tree = TreeImpl.getSampleTree().getChildren().get(1);
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		assertEquals("n2", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n21", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n22", iterator.getCurrentNode().getName());
		assertTrue(iterator.goForward());
		assertEquals("n23", iterator.getCurrentNode().getName());
		assertFalse(iterator.goForward());
	}

	@Test
	public void testGoBackward() {
		TreeImpl tree = TreeImpl.getSampleTree();
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		iterator.gotoEnd();
		assertEquals("n33", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n32", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n31", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n3", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n23", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n22", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n21", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n2", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n13", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n12", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n11", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n1", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("root", iterator.getCurrentNode().getName());
		assertFalse(iterator.goBackward());
	}

	@Test
	public void testGoBackwardPart() {
		TreeImpl tree = TreeImpl.getSampleTree().getChildren().get(1);
		TreeIterator<TreeImpl> iterator = new TreeIterator<TreeImpl>(tree);
		iterator.gotoEnd();
		assertEquals("n23", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n22", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n21", iterator.getCurrentNode().getName());
		assertTrue(iterator.goBackward());
		assertEquals("n2", iterator.getCurrentNode().getName());
		assertFalse(iterator.goBackward());
	}
}
