package com.puresol.coding.lang.fortran.source.symbols;

import org.junit.Test;

import junit.framework.TestCase;

public class LineLeadTest extends TestCase {

    @Test
    public void testValidEmpty() {
	LineLead lineLead = new LineLead();
	assertTrue(lineLead.atStart("      "));
    }

    @Test
    public void testValidCommentary() {
	LineLead lineLead = new LineLead();
	assertTrue(lineLead.atStart("C This"));
	assertTrue(lineLead.atStart("c is  "));
	assertTrue(lineLead.atStart("* a   "));
	assertTrue(lineLead.atStart("!short"));
	assertTrue(lineLead.atStart("comment"));
	assertTrue(lineLead.atStart("*******"));
    }

    @Test
    public void testValidLabels() {
	LineLead lineLead = new LineLead();
	assertTrue(lineLead.atStart(" 12345"));
	assertTrue(lineLead.atStart(" 1234 "));
	assertTrue(lineLead.atStart(" 123  "));
	assertTrue(lineLead.atStart(" 12   "));
	assertTrue(lineLead.atStart(" 1    "));
	assertTrue(lineLead.atStart("  2345"));
	assertTrue(lineLead.atStart("   345"));
	assertTrue(lineLead.atStart("    45"));
	assertTrue(lineLead.atStart("     5"));
    }

    @Test
    public void testValidContinuation() {
	LineLead lineLead = new LineLead();
	assertTrue(lineLead.atStart("     !"));
	assertTrue(lineLead.atStart("     ;"));
    }
}
