package com.puresol.uhura.gui.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swingx.rendering.AbstractRenderer;
import javax.swingx.rendering.Arrow;
import javax.swingx.rendering.Renderer;
import javax.swingx.rendering.Arrow.ArrowType;

public class ConstructionRenderer extends AbstractRenderer {

	private final static int ARROW_LENGTH = RenderProperties
			.getBoxArrowLength();

	private final static int ARROW_TIP_LENGTH = RenderProperties
			.getArrowTipLength();
	private final static int ARROW_TIP_ANGLE = RenderProperties
			.getArrowTipAngle();

	private final Graphics graphics;
	private final Color color;
	private final Renderer textBoxRenderer;

	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public ConstructionRenderer(Graphics graphics, Font font, Color color,
			String text) {
		super();
		this.graphics = graphics;
		this.color = color;
		textBoxRenderer = new TextBoxRenderer(graphics, font, Color.BLACK, text);
		preferredWidth = textBoxRenderer.getPreferredSize().width + 2
				* ARROW_LENGTH;
		preferredHeight = textBoxRenderer.getPreferredSize().height;
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

		Arrow arrow = new Arrow(graphics);
		arrow.setTipAngle(ARROW_TIP_ANGLE);
		arrow.setTipLength(ARROW_TIP_LENGTH);
		arrow.setType(ArrowType.FANCY);

		graphics.setColor(color);
		arrow.draw(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);
		textBoxRenderer.setPosition(x + ARROW_LENGTH, y);
		textBoxRenderer.render();
		graphics.drawLine(x + w - ARROW_LENGTH, y + h / 2, x + w, y + h / 2);
	}
}
