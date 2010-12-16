package com.puresol.gui.uhura.rendering;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.gui.uhura.rendering.RenderProperties;

public class RenderPropertiesTest {

	@Test
	public void testLoading() {
		String value = RenderProperties.getProperty("box.padding");
		assertNotNull(value);
		assertFalse(value.isEmpty());
	}

}
