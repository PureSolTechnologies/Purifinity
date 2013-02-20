package com.puresol.coding.client.grammar;

import java.awt.Dimension;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.grammar.Arrow.ArrowType;
import com.puresol.uhura.grammar.Quantity;

public class QuantityLoopRenderer extends AbstractRenderer {

    private final static int ARROW_LENGTH = UhuraRenderProperties
	    .getBoxArrowLength();
    private final static int ARROW_MARGIN = UhuraRenderProperties
	    .getBoxArrowMargin();
    private final static int ARROW_TIP_LENGTH = UhuraRenderProperties
	    .getArrowTipLength();
    private final static int ARROW_TIP_ANGLE = UhuraRenderProperties
	    .getArrowTipAngle();

    private final Renderer loopContentRenderer;
    private final Quantity quantity;

    private int preferredWidth = 0;
    private int preferredHeight = 0;

    public QuantityLoopRenderer(Renderer loopContentRenderer, Quantity quantity) {
	super();
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
    public void render(GC graphics, int x1, int y1, int x2, int y2) {
	int x = Math.min(x1, x2);
	int y = Math.min(y1, y2);
	int w = Math.abs(x2 - x1) + 1;
	int h = Math.abs(y2 - y1) + 1;
	float scaleX = (float) w / (float) preferredWidth;
	float scaleY = (float) h / (float) preferredHeight;

	Arrow arrow = new Arrow(graphics);
	arrow.setTipAngle(ARROW_TIP_ANGLE);
	arrow.setTipLength((int) (ARROW_TIP_LENGTH * scaleY));
	arrow.setType(ArrowType.FANCY);

	final int arrowLength = (int) (ARROW_LENGTH * scaleX);
	final int arrowMargin = (int) (ARROW_MARGIN * scaleY);

	RGB black = new RGB(0, 0, 0);
	Color color = new Color(graphics.getDevice(), black);
	try {
	    graphics.setForeground(color);
	    graphics.drawLine(x, y + h / 2, x + arrowLength, y + h / 2);
	    Dimension size = loopContentRenderer.getPreferredSize();
	    loopContentRenderer.render(graphics, x + arrowLength, y
		    + arrowMargin, x + arrowLength
		    + (int) (size.getWidth() * scaleX), y + arrowMargin
		    + (int) (size.getHeight() * scaleY));
	    graphics.drawLine(x + w - arrowLength, y + h / 2, x + w - 1, y + h
		    / 2);
	    if ((quantity == Quantity.ACCEPT)
		    || (quantity == Quantity.ACCEPT_MANY)) {
		graphics.drawLine(x + arrowLength / 3, y + h / 2, x
			+ arrowLength / 3, y + arrowMargin / 2);
		graphics.drawLine(x + arrowLength / 3, y + arrowMargin / 2, x
			+ w - arrowLength / 3, y + arrowMargin / 2);
		arrow.draw(x + w - arrowLength / 3, y + arrowMargin / 2, x + w
			- arrowLength / 3, y + h / 2, graphics.getForeground());
	    }
	    if ((quantity == Quantity.EXPECT_MANY)
		    || (quantity == Quantity.ACCEPT_MANY)) {
		arrow.draw(x + arrowLength * 2 / 3, y + h - arrowMargin / 2, x
			+ arrowLength * 2 / 3, y + h / 2,
			graphics.getForeground());
		graphics.drawLine(x + arrowLength * 2 / 3, y + h - arrowMargin
			/ 2, x + w - arrowLength * 2 / 3, y + h - arrowMargin
			/ 2);
		graphics.drawLine(x + w - arrowLength * 2 / 3, y + h
			- arrowMargin / 2, x + w - arrowLength * 2 / 3, y + h
			/ 2);
	    }
	} finally {
	    color.dispose();
	}
    }
}
