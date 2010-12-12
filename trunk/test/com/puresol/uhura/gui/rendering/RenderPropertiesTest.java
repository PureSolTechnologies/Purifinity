package com.puresol.uhura.gui.rendering;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.gui.rendering.RenderProperties;

public class RenderPropertiesTest {

	@Test
	public void testLoading() {
		String value = RenderProperties.getProperty("box.padding");
		assertNotNull(value);
		assertFalse(value.isEmpty());
	}

}
