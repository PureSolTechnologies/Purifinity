package com.puresol.coding;

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

}
