package com.puresol.gui.uhura.rendering;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.gui.uhura.rendering.UhuraRenderProperties;

public class RenderPropertiesTest {

	@Test
	public void testLoading() {
		String value = UhuraRenderProperties.getProperty("box.padding");
		assertNotNull(value);
		assertFalse(value.isEmpty());
	}

}
