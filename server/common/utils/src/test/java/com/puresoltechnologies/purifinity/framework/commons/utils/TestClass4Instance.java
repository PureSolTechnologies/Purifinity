package com.puresoltechnologies.purifinity.framework.commons.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestClass4Instance {

    private String text = "default";

    public void setText(String text) {
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

    @Test
    public void testInstance() {
	assertNotNull(new TestClass4Instance());
    }

    @Test
    public void testInitialValues() {
	TestClass4Instance test = new TestClass4Instance();
	assertEquals("default", test.getText());
    }
}
