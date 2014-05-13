package com.puresoltechnologies.commons.trees.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.commons.trees.TreeWalker;
import com.puresoltechnologies.commons.trees.WalkingAction;

public class TreeWalkerTest {

    @Test
    public void testInstance() {
	assertNotNull(new TreeWalker<TreeImpl>(TreeImpl.getSampleTree()));
    }

    @Test
    public void testInitValues() {
	TreeImpl tree = TreeImpl.getSampleTree();
	TreeWalker<TreeImpl> walker = new TreeWalker<TreeImpl>(tree);
	assertSame(tree, walker.getTree());
    }

    @Test
    public void testCompleteWalk() {
	TreeImpl tree = TreeImpl.getSampleTree();
	TreeWalker<TreeImpl> walker = new TreeWalker<TreeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	walker.walk(visitor);
	assertEquals("rootn1n11n12n13n2n21n22n23n3n31n32n33",
		visitor.getNodeString());
    }

    @Test
    public void testAbortWalk() {
	TreeImpl tree = TreeImpl.getSampleTree();
	TreeWalker<TreeImpl> walker = new TreeWalker<TreeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	visitor.addAction("n2", WalkingAction.ABORT);
	walker.walk(visitor);
	assertEquals("rootn1n11n12n13n2", visitor.getNodeString());
    }

    @Test
    public void testLeaveBranchWalk() {
	TreeImpl tree = TreeImpl.getSampleTree();
	TreeWalker<TreeImpl> walker = new TreeWalker<TreeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	visitor.addAction("n2", WalkingAction.LEAVE_BRANCH);
	walker.walk(visitor);
	assertEquals("rootn1n11n12n13n2n3n31n32n33", visitor.getNodeString());
    }

    @Test
    public void testCompleteWalkBackward() {
	TreeImpl tree = TreeImpl.getSampleTree();
	TreeWalker<TreeImpl> walker = new TreeWalker<TreeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	walker.walkBackward(visitor);
	assertEquals("n33n32n31n3n23n22n21n2n13n12n11n1root",
		visitor.getNodeString());
    }

    @Test
    public void testAbortWalkBackward() {
	TreeImpl tree = TreeImpl.getSampleTree();
	TreeWalker<TreeImpl> walker = new TreeWalker<TreeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	visitor.addAction("n2", WalkingAction.ABORT);
	walker.walkBackward(visitor);
	assertEquals("n33n32n31n3n23n22n21n2", visitor.getNodeString());
    }

    @Test
    public void testLeaveBranchWalkBackward() {
	TreeImpl tree = TreeImpl.getSampleTree();
	TreeWalker<TreeImpl> walker = new TreeWalker<TreeImpl>(tree);
	TreeVisitorTestImpl visitor = new TreeVisitorTestImpl();
	visitor.addAction("n23", WalkingAction.LEAVE_BRANCH);
	walker.walkBackward(visitor);
	assertEquals("n33n32n31n3n23n13n12n11n1root", visitor.getNodeString());
    }
}
