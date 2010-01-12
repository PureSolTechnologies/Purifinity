package com.puresol.parser.defaulttokendef;

import org.junit.Test;

import com.puresol.coding.java.symbols.WhiteSpace;

import junit.framework.Assert;

public class WhiteSpaceTokenTest {

    @Test
    public void testSpaceStart() {
	WhiteSpace token = new WhiteSpace();
	Assert.assertTrue(token.atStart(" A"));
	Assert.assertTrue(token.included(" A"));
	Assert.assertEquals(" ", token.getTokenAtStart(" A"));
	Assert.assertEquals(" ", token.getIncludedToken(" A"));
    }

    @Test
    public void testTabStart() {
	WhiteSpace token = new WhiteSpace();
	Assert.assertTrue(token.atStart("\tA"));
	Assert.assertTrue(token.included("\tA"));
	Assert.assertEquals("\t", token.getTokenAtStart("\tA"));
	Assert.assertEquals("\t", token.getTokenAtStart("\tA"));
    }

    @Test
    public void testSpace() {
	WhiteSpace token = new WhiteSpace();
	Assert.assertTrue(token.included("A A"));
	Assert.assertFalse(token.atStart("A A"));
	Assert.assertEquals("", token.getTokenAtStart("A A"));
	Assert.assertEquals(" ", token.getIncludedToken("A A"));
    }

    @Test
    public void testTab() {
	WhiteSpace token = new WhiteSpace();
	Assert.assertTrue(token.included("A\tA"));
	Assert.assertFalse(token.atStart("A\tA"));
	Assert.assertEquals("", token.getTokenAtStart("A\tA"));
	Assert.assertEquals("\t", token.getIncludedToken("A\tA"));
    }
}
