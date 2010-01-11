package com.puresol.parser.defaulttokendef;

import org.junit.Test;

import com.puresol.parser.defaulttokens.LineBreak;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LineBreakTokenTest extends TestCase {

    @Test
    public void testUnix() {
	LineBreak token = new LineBreak();
	Assert.assertTrue(token.atStart("\nA"));
	Assert.assertTrue(token.included("\nA"));
	Assert.assertFalse(token.atStart("A\nA"));
	Assert.assertTrue(token.included("A\nA"));
    }

    @Test
    public void testWindows() {
	LineBreak token = new LineBreak();
	Assert.assertTrue(token.atStart("\r\nA"));
	Assert.assertTrue(token.included("\r\nA"));
	Assert.assertFalse(token.atStart("A\r\nA"));
	Assert.assertTrue(token.included("A\r\nA"));
    }
}
