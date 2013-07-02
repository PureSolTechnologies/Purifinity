package com.puresol.commons.trees;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.commons.trees.TreePrinter;
import com.puresol.commons.trees.TreeUtils;

public class TreeUtilsTest {

    @Test
    public void testCountNodes() {
	TreeImpl tree = TreeImpl.getSampleTree();
	new TreePrinter(System.out).println(tree);
	int count = TreeUtils.countNodes(tree);
	assertEquals(13, count);
    }
}
