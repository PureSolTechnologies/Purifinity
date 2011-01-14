package com.puresol.gui.uhura.rendering;

import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import com.puresol.gui.Dialog;
import com.puresol.gui.uhura.rendering.QuantityLoopRenderer;
import com.puresol.gui.uhura.rendering.TextBoxRenderer;
import com.puresol.rendering.Renderer;
import com.puresol.uhura.grammar.Quantity;

public class QuantityLoopRendererTest {

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
		assertNotNull(new QuantityLoopRenderer(graphics, new TextBoxRenderer(
				graphics, new Font("Verdana", Font.TRUETYPE_FONT | Font.BOLD,
						12), Color.BLACK, "TestString"), Quantity.ACCEPT_MANY));
	}

	public static void main(String args[]) {
		Dialog frame = new Dialog();
		frame.add(new JPanel() {
			private static final long serialVersionUID = 8456563011009861087L;

			public void paint(Graphics graphics) {
				super.paint(graphics);
				Renderer renderer = new QuantityLoopRenderer(graphics,
						new TextBoxRenderer(graphics, new Font("Verdana",
								Font.TRUETYPE_FONT | Font.BOLD, 12),
								Color.BLACK, "TestString"),
						Quantity.ACCEPT_MANY);
				renderer.setPosition(0, 0);
				renderer.render();
			}
		});
		frame.run();
	}

}
