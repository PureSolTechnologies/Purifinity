package com.puresol.coding.lang.fortran;

import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.TestCase;

public class LexicalStructureTest extends TestCase {

    @Test
    public void testSignificant() {
	Pattern.matches(LexicalStructure.SIGNIFICANT, ".12345");
	Pattern.matches(LexicalStructure.SIGNIFICANT, "12345.");
	Pattern.matches(LexicalStructure.SIGNIFICANT, "12345.12345");
    }

    @Test
    public void testRealLiteralConstant() {
	// TODO
    }
}
