package com.puresol.coding.lang.cpp.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.puresol.coding.lang.cpp.internal.DefinedMacros;

public class DefinedMacrosTest {

    @Test
    public void testDefineAndUndefine() {
	DefinedMacros macros = new DefinedMacros();
	assertFalse(macros.isDefined("Test"));
	macros.define("Test", "Hallo");
	assertTrue(macros.isDefined("Test"));
	assertTrue(macros.undefine("Test"));
	assertFalse(macros.isDefined("Test"));
    }

}
