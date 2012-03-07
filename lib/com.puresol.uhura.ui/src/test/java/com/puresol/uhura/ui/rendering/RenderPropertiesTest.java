package com.puresol.uhura.ui.rendering;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.uhura.ui.rendering.UhuraRenderProperties;

public class RenderPropertiesTest {

	@Test
	public void testLoading() {
		String value = UhuraRenderProperties.getProperty("box.padding");
		assertNotNull(value);
		assertFalse(value.isEmpty());
	}

}
