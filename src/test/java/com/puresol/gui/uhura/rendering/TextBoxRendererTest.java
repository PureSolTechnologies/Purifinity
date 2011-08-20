package com.puresol.gui.uhura.rendering;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;

import org.junit.Test;

import com.puresol.gui.uhura.rendering.TextBoxRenderer;
import com.puresol.rendering.Renderer;
import com.puresol.rendering.RendererTester;

/**
 * This test shows a renderer box with a rendered text.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TextBoxRendererTest {

	@Test
	public void testInstance() {
		assertNotNull(new TextBoxRenderer(new Font("Verdana",
				Font.TRUETYPE_FONT | Font.BOLD, 12), Color.RED, "TestString"));
	}

	public static void main(String args[]) {
		Renderer renderer = new TextBoxRenderer(new Font("Verdana",
				Font.TRUETYPE_FONT | Font.BOLD, 12), Color.RED, "TestString");
		RendererTester tester = new RendererTester(renderer);
		tester.run();
	}

}
