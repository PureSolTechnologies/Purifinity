package com.puresol.coding.java;

import java.io.File;

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

	private void testVariableAssignments() {
		@SuppressWarnings("unused")
		String string = "This is a string.";
	}

	public void testMethodCalls() {
		testVariableAssignments();
	}

	public int testMath(int a, String b, boolean c) {
		int x = 1;
		int y = 2;
		int z = x + 1;
		return z + y;
	}

	public void testFor() {
		for (int index = 0; index < Math.log10(10); index++) {
			System.out.println("Hallo Welt!");
		}
	}

	public void testTryCatch() {
		try {
			File file = new File("test");
			file.hashCode();
		} catch (IllegalArgumentException e) {
			System.out.println("Exception");
		} finally {
			System.out.println("Finally");
		}
	}

	public static void main(String[] args) {
		JavaAnalyser analyser = new JavaAnalyser(new File(
				"src/com/puresol/coding/java/JavaAnalyser.java"));
		analyser.hashCode();
	}
}
