package com.puresol.parser;

import org.junit.Test;

import com.puresol.testing.Tester;

import junit.framework.TestCase;

public class TokenTest extends TestCase {

    @Test
    public void testStandards() {
	Tester.testStandards(Token.class);
    }

    @Test
    public void testSettersAndGetters() {
	Tester.testGetterAndSetter(Token.class);
    }
}
