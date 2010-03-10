package com.puresol.coding.analysis.evaluator.duplication;

public class DummyClassForDuplicationScannerTest {

    public void method1() {
	int a = 0;
	for (int i = 0; i < 10; i++) {
	    for (int j = 0; j < 20; j++) {
		a += i * j;
	    }
	}
    }

    public void method2() {
	int b = 0;
	for (int i = 0; i < 10; i++) {
	    for (int k = 0; k < 20; k++) {
		b += i * k;
	    }
	}
    }

    public void copy() {
	/*
	 * This is a copied and pasted test text for the Copy&Paste
	 * scanner.
	 */
	System.out.println("Hello, I was copied and pasted!");
	System.out.println(":-D");
	System.out.println("See you!");
	System.out.println("Bye!");
    }

    public void paste() {
	/*
	 * This is a copied and pasted test text for the Copy&Paste
	 * scanner.
	 */
	System.out.println("Hello, I was copied and pasted!");
	System.out.println(":-D");
	System.out.println("See you!");
	System.out.println("Bye!");
    }

}
