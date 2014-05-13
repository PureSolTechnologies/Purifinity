package com.puresoltechnologies.commons.trees.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.commons.trees.TreePrinter;
import com.puresoltechnologies.commons.trees.TreeUtils;

public class TreeUtilsTest {

    @Test
    public void testCountNodes() {
	TreeImpl tree = TreeImpl.getSampleTree();
	new TreePrinter(System.out).println(tree);
	int count = TreeUtils.countNodes(tree);
	assertEquals(13, count);
    }
}
