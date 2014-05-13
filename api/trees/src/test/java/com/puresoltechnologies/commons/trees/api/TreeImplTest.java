package com.puresoltechnologies.commons.trees.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.commons.trees.Tree;

public class TreeImplTest {

    @Test
    public void testInstance() {
	assertNotNull(new TreeImpl(null, "Name"));
	assertNotNull(new TreeImpl("Name"));
    }

    @Test
    public void testInitValues() {
	Tree<TreeImpl> tree = new TreeImpl("Name");
	assertEquals("Name", tree.getName());
	assertNotNull(tree.getChildren());
	assertFalse(tree.hasChildren());
	assertEquals(0, tree.getChildren().size());
	assertNull(tree.getParent());
    }

    @Test
    public void testInitValues2() {
	TreeImpl parent = new TreeImpl("Parent");
	TreeImpl tree = new TreeImpl(parent, "Name");
	parent.addChild(tree);
	assertEquals("Name", tree.getName());
	assertNotNull(tree.getChildren());
	assertFalse(tree.hasChildren());
	assertEquals(0, tree.getChildren().size());
	assertSame(parent, tree.getParent());
    }

}
