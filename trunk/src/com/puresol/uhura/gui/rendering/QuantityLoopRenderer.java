package com.puresol.uhura.gui.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.puresol.rendering.AbstractRenderer;
import com.puresol.rendering.Arrow;
import com.puresol.rendering.Arrow.ArrowType;
import com.puresol.rendering.Renderer;
import com.puresol.uhura.grammar.Quantity;

public class QuantityLoopRenderer extends AbstractRenderer {

	private final static int ARROW_LENGTH = RenderProperties
			.getBoxArrowLength();
	private final static int ARROW_MARGIN = RenderProperties
			.getBoxArrowMargin();
	private final static int ARROW_TIP_LENGTH = RenderProperties
			.getArrowTipLength();
	private final static int ARROW_TIP_ANGLE = RenderProperties
			.getArrowTipAngle();

	private final Graphics graphics;
	private final Renderer loopContentRenderer;
	private final Quantity quantity;

	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public QuantityLoopRenderer(Graphics graphics,
			Renderer loopContentRenderer, Quantity quantity) {
		super();
		this.graphics = graphics;
		this.loopContentRenderer = loopContentRenderer;
		this.quantity = quantity;
		preferredWidth = loopContentRenderer.getPreferredSize().width + 2
				* ARROW_LENGTH;
		preferredHeight = loopContentRenderer.getPreferredSize().height + 2
				* ARROW_MARGIN;
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
		graphics.setColor(Color.BLACK);
		graphics.drawLine(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);
		loopContentRenderer.setPosition(x + ARROW_LENGTH, y + ARROW_MARGIN);
		loopContentRenderer.render();
		graphics.drawLine(x + w - ARROW_LENGTH, y + h / 2, x + w - 1, y + h / 2);
		if ((quantity == Quantity.ACCEPT) || (quantity == Quantity.ACCEPT_MANY)) {
			graphics.drawLine(x + ARROW_LENGTH / 3, y + h / 2, x + ARROW_LENGTH
					/ 3, y + ARROW_MARGIN / 2);
			graphics.drawLine(x + ARROW_LENGTH / 3, y + ARROW_MARGIN / 2, x + w
					- ARROW_LENGTH / 3, y + ARROW_MARGIN / 2);
			arrow.draw(x + w - ARROW_LENGTH / 3, y + ARROW_MARGIN / 2, x + w
					- ARROW_LENGTH / 3, y + h / 2);
		}
		if ((quantity == Quantity.EXPECT_MANY)
				|| (quantity == Quantity.ACCEPT_MANY)) {
			arrow.draw(x + ARROW_LENGTH * 2 / 3, y + h - ARROW_MARGIN / 2, x
					+ ARROW_LENGTH * 2 / 3, y + h / 2);
			graphics.drawLine(x + ARROW_LENGTH * 2 / 3, y + h - ARROW_MARGIN
					/ 2, x + w - ARROW_LENGTH * 2 / 3, y + h - ARROW_MARGIN / 2);
			graphics.drawLine(x + w - ARROW_LENGTH * 2 / 3, y + h
					- ARROW_MARGIN / 2, x + w - ARROW_LENGTH * 2 / 3, y + h / 2);
		}
	}
}
