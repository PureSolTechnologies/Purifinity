package com.puresol.gui.uhura.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.puresol.rendering.AbstractRenderer;

public class TextBoxRenderer extends AbstractRenderer {

	private final static int PADDING = UhuraRenderProperties.getBoxPadding();

	private final Font font;
	private final Color color;
	private final String text;

	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public TextBoxRenderer(Font font, Color color, String text) {
		super();
		this.font = font;
		this.color = color;
		this.text = text;
		Dimension assumedFontSize = UhuraRenderProperties.getAssumedFontSize();
		preferredWidth = assumedFontSize.width * text.length() + 2 * PADDING;
		preferredHeight = (int) ((float) assumedFontSize.height * 1.5) + 2
				* PADDING;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(preferredWidth, preferredHeight);
	}

	@Override
	public void render(Graphics graphics, int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.abs(x2 - x1) + 1;
		int h = Math.abs(y2 - y1) + 1;
		float scaleX = (float) w / (float) preferredWidth;
		float scaleY = (float) h / (float) preferredHeight;

		graphics.setColor(color);
		Font scaledFont = new Font(font.getFamily(), font.getStyle(),
				(int) ((float) font.getSize() * Math.min(scaleX, scaleY)));
		graphics.setFont(scaledFont);
		graphics.drawRect(x, y, w - 1, h - 1);

		FontMetrics fontMetrics = graphics.getFontMetrics(scaledFont);
		int textWidth = fontMetrics.stringWidth(text);
		int textHeight = fontMetrics.getHeight();

		graphics.drawString(text, x
				+ (int) ((float) w / 2.0 - (float) textWidth / 2.0), y
				+ (int) (h / 2 + textHeight * 1 / 3));
	}
}
