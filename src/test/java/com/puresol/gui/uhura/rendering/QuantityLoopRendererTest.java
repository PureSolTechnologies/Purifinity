package com.puresol.gui.uhura.rendering;

import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.Font;

import org.junit.Before;
import org.junit.Test;

import com.puresol.gui.uhura.rendering.QuantityLoopRenderer;
import com.puresol.gui.uhura.rendering.TextBoxRenderer;
import com.puresol.rendering.Renderer;
import com.puresol.rendering.RendererTester;
import com.puresol.uhura.grammar.Quantity;

public class QuantityLoopRendererTest {

    @Before
    public void createEnvironment() {
    }

    @Test
    public void testInstance() {
	assertNotNull(new QuantityLoopRenderer(new TextBoxRenderer(new Font(
		"Verdana", Font.TRUETYPE_FONT | Font.BOLD, 12), Color.BLACK,
		"TestString"), Quantity.ACCEPT_MANY));
    }

    public static void main(String args[]) {
	Renderer renderer = new QuantityLoopRenderer(new TextBoxRenderer(
		new Font("Verdana", Font.TRUETYPE_FONT | Font.BOLD, 12),
		Color.BLACK, "TestString"), Quantity.ACCEPT_MANY);
	RendererTester tester = new RendererTester(renderer);
	tester.setVisible(true);
    }

}
