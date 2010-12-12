package com.puresol.uhura.gui.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.puresol.rendering.AbstractRenderer;

public class TextBoxRenderer extends AbstractRenderer {

	private final static int PADDING = RenderProperties.getBoxPadding();

	private final Graphics graphics;
	private final Font font;
	private final Color color;
	private final String text;

	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public TextBoxRenderer(Graphics graphics, Font font, Color color,
			String text) {
		super();
		this.graphics = graphics;
		this.font = font;
		this.color = color;
		this.text = text;
		FontMetrics fontMetrics = graphics.getFontMetrics(font);
		preferredWidth = fontMetrics.stringWidth(text) + 2 * PADDING;
		preferredHeight = (int) (fontMetrics.getHeight() * 1.5) + 2 * PADDING;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(preferredWidth, preferredHeight);
	}

	@Override
	public void render() {
		int x = getX();
		int y = getY();
		int w = preferredWidth;
		int h = preferredHeight;
		graphics.setColor(color);
		graphics.setFont(font);
		graphics.drawRect(x, y, w - 1, h - 1);
		graphics.drawString(text, x + PADDING, y + (int) (h * 2 / 3));
	}
}
