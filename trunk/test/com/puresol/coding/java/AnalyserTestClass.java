package com.puresol.coding.java;

/**
 * This is a test class for JavaAnalyser unit test.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyserTestClass {

	public static final Integer INTEGER_CONSTANT = 0;
	public static final int INT_CONSTANT = 42;

	/**
	 * This is a test constructor...
	 */
	public AnalyserTestClass() {
	}

	@SuppressWarnings("unchecked")
	private void testVariableAssignments() {
		@SuppressWarnings("unused")
		String string = "This is a string.";
	}

	public void testMethodCalls() {
		testVariableAssignments();
	}

	public void testMath(int a, String b, boolean c) {
		int x = 1;
		int y = 2;
		int z = x + 1;
		// return z;
	}
}
