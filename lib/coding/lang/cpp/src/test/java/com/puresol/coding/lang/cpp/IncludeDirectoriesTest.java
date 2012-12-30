package com.puresol.coding.lang.cpp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresol.coding.lang.cpp.IncludeDirectories;

public class IncludeDirectoriesTest {

    @Test
    public void testSingleton() {
	IncludeDirectories instance = IncludeDirectories.getInstance();
	assertNotNull(instance);
	assertSame(instance, IncludeDirectories.getInstance());
    }

}
