package com.puresol.utils;

public class TestClass4Instance {

    private String text;

    public TestClass4Instance() {
	this.text = "default";
    }

    public TestClass4Instance(String text) {
	this.text = text;
    }

    public String getText() {
	return text;
    }

    public static Integer returnValuedMethod(Integer i) {
	return i * i;
    }

    public static void voidMethod() {
    }
}
