package com.puresol.purifinity.uhura.ust.eval;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresol.purifinity.uhura.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresol.purifinity.uhura.ust.eval.Value;

public class ValueTest {

    private void testAdd(Value left, Value right, Value expectedResult)
	    throws UniversalSyntaxTreeEvaluationException {
	Value result = left.add(right);
	assertEquals(expectedResult, result);
    }

    @Test
    public void testAdd() throws Exception {
	testAdd(new Value("int", 1), new Value("int", 1), new Value("int", 2));
	testAdd(new Value("int", 1.23), new Value("int", 4.56), new Value(
		"int", 5.79));
    }

}
