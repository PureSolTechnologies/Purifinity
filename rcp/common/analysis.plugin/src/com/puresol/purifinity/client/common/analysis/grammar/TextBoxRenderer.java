package com.puresol.purifinity.client.common.analysis.grammar;

import java.awt.Dimension;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

public class TextBoxRenderer extends AbstractRenderer {

    private final static int PADDING = UhuraRenderProperties.getBoxPadding();

    private final FontData fontData;
    private final RGB rgb;
    private final String text;

    private int preferredWidth = 0;
    private int preferredHeight = 0;

    public TextBoxRenderer(FontData font, RGB rgb, String text) {
	super();
	this.fontData = font;
	this.rgb = rgb;
	this.text = text;
	Dimension assumedFontSize = UhuraRenderProperties.getAssumedFontSize();
	preferredWidth = assumedFontSize.width * text.length() + 2 * PADDING;
	preferredHeight = (int) (assumedFontSize.height * 1.5) + 2 * PADDING;
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(preferredWidth, preferredHeight);
    }

    @Override
    public void render(GC graphics, int x1, int y1, int x2, int y2) {
	int x = Math.min(x1, x2);
	int y = Math.min(y1, y2);
	int w = Math.abs(x2 - x1) + 1;
	int h = Math.abs(y2 - y1) + 1;
	float scaleX = (float) w / (float) preferredWidth;
	float scaleY = (float) h / (float) preferredHeight;

	Color color = new Color(graphics.getDevice(), rgb);
	try {
	    graphics.setForeground(color);
	    Font scaledFont = new Font(graphics.getDevice(), new FontData(
		    fontData.getName(), (int) (fontData.getHeight() * Math.min(
			    scaleX, scaleY)), fontData.getStyle()));
	    try {
		graphics.setFont(scaledFont);
		graphics.drawRectangle(x, y, w - 1, h - 1);

		FontMetrics fontMetrics = graphics.getFontMetrics();
		int textWidth = fontMetrics.getAverageCharWidth()
			* text.length();
		int textHeight = fontMetrics.getHeight();

		graphics.drawString(text,
			x + (int) (w / 2.0 - textWidth / 2.0), y
				+ (h / 2 + textHeight * 1 / 3));
	    } finally {
		scaledFont.dispose();
	    }
	} finally {
	    color.dispose();
	}
    }
}
