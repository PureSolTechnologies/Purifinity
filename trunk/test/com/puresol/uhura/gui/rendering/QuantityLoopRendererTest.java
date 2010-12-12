package com.puresol.uhura.gui.rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swingx.Dialog;
import javax.swingx.Panel;
import javax.swingx.rendering.Renderer;

import com.puresol.uhura.grammar.Quantity;
import com.puresol.uhura.gui.rendering.QuantityLoopRenderer;
import com.puresol.uhura.gui.rendering.TextBoxRenderer;

public class QuantityLoopRendererTest {

	public static void main(String args[]) {
		Dialog frame = new Dialog();
		frame.add(new Panel() {
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
