package com.puresol.parser.java.tokendef;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class StringLiteralTest extends TestCase {

    private static final StringLiteral stringLiteral = new StringLiteral();

    @Test
    public void test() {
	Assert.assertTrue(stringLiteral.included("AA \"Test\" AA"));
	Assert.assertEquals("\"Test\"", stringLiteral
		.getIncludedToken("AA \"Test\" AA"));
    }

}
