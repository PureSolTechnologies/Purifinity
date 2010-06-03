package com.puresol.coding.lang.java.source.grammar.expressions;

import org.junit.Test;

import com.puresol.coding.lang.java.source.grammar.JavaGrammarTester;

import junit.framework.TestCase;

/**
 * This is a test for the literal grammar part.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LiteralTest extends TestCase {

    /**
     * This tests checks for correct integer implemenation. A sign in front of
     * the integer is not part of the literal.
     */
    @Test
    public void testIntegers() {
	assertTrue(JavaGrammarTester.valid("0", Literal.class));
	assertTrue(JavaGrammarTester.valid("1", Literal.class));
	assertTrue(JavaGrammarTester.valid("42", Literal.class));
    }

    /**
     * This tests checks for correct floating point implemenation. A sign in
     * front of the number is not part of the literal.
     */
    @Test
    public void testFloating() {
	assertTrue(JavaGrammarTester.valid("0.0", Literal.class));
	assertTrue(JavaGrammarTester.valid("1.0", Literal.class));
	assertTrue(JavaGrammarTester.valid("1.2345e+12", Literal.class));
	assertTrue(JavaGrammarTester.valid("1.2345e-12", Literal.class));
    }

    @Test
    public void testBoolean() {
	assertTrue(JavaGrammarTester.valid("true", Literal.class));
	assertTrue(JavaGrammarTester.valid("false", Literal.class));
    }

    @Test
    public void testCharacter() {
	assertTrue(JavaGrammarTester.valid("'c'", Literal.class));
    }

    @Test
    public void testString() {
	assertTrue(JavaGrammarTester.valid("\"String\"", Literal.class));
    }

    @Test
    public void testNull() {
	assertTrue(JavaGrammarTester.valid("null", Literal.class));
    }

}
