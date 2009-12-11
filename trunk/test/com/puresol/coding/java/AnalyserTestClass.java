package com.puresol.coding.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a test class for JavaAnalyser unit test.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyserTestClass {

	static final String[] lines = new String[] { "Line1", "Line2", "Line3" };

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

	public void testIf() {
		int a = 5;
		if (a > 4) {
			System.out.println("number is larger than 4");
		}
		if (a < 5) {
			System.out.println("number is smaller than 5");
		} else {
			System.out.println("number is 5 or greater");
		}
		if (a < 5) {
			System.out.println("number is smaller than 5");
		} else if (a > 5) {
			System.out.println("number is greater than 5");
		} else {
			System.out.println("number is 5");
		}
		boolean bool = true;
		if (bool) {
			System.out.print("true");
		} else
			System.out.println("false");
		if (!bool)
			System.out.print("false");
		else {
			System.out.println("true");
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

	public List<Float> testGenerics() {
		List<Float> test = new ArrayList<Float>();
		return test;
	}

	public void testEquation() {
		int a = 0;
		int b = 1;
		if ((a == 0) && (b == 1)) {
			System.out.println("true");
		}
	}

	public static void main(String[] args) {
		JavaAnalyser analyser = new JavaAnalyser(new File(
				"test/com/puresol/coding/java/AnalyserTestClass.java"));
		analyser.hashCode();
	}
}
