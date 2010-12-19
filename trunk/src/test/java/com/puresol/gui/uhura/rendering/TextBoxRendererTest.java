package com.puresol.gui.uhura.rendering;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swingx.Dialog;
import javax.swingx.Panel;

import org.junit.Before;
import org.junit.Test;

import com.puresol.gui.uhura.rendering.TextBoxRenderer;
import com.puresol.rendering.Renderer;

/**
 * This test shows a renderer box with a rendered text.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TextBoxRendererTest {

	private Graphics graphics;

	@Before
	public void createEnvironment() {
		BufferedImage image = new BufferedImage(10, 10,
				BufferedImage.TYPE_INT_RGB);
		graphics = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.createGraphics(image);
	}

	@Test
	public void testInstance() {
		assertNotNull(new TextBoxRenderer(graphics, new Font("Verdana",
				Font.TRUETYPE_FONT | Font.BOLD, 12), Color.RED, "TestString"));
	}

	public static void main(String args[]) {
		Dialog frame = new Dialog();
		frame.add(new Panel() {
			private static final long serialVersionUID = 8456563011009861087L;

			public void paint(Graphics graphics) {
				super.paint(graphics);
				Renderer renderer = new TextBoxRenderer(graphics, new Font(
						"Verdana", Font.TRUETYPE_FONT | Font.BOLD, 12),
						Color.RED, "TestString");
				renderer.setPosition(0, 0);
				renderer.render();
			}
		});
		frame.run();
	}

}
