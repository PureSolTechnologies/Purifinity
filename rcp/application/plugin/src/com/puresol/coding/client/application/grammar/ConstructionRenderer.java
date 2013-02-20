package com.puresol.coding.client.application.grammar;

import java.awt.Dimension;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.application.grammar.Arrow.ArrowType;

public class ConstructionRenderer extends AbstractRenderer {

    private final static int ARROW_LENGTH = UhuraRenderProperties
	    .getBoxArrowLength();

    private final static int ARROW_TIP_LENGTH = UhuraRenderProperties
	    .getArrowTipLength();
    private final static int ARROW_TIP_ANGLE = UhuraRenderProperties
	    .getArrowTipAngle();

    private final RGB rgb;
    private final Renderer textBoxRenderer;

    private int preferredWidth = 0;
    private int preferredHeight = 0;

    public ConstructionRenderer(FontData fontData, RGB rgb, String text) {
	super();
	this.rgb = rgb;
	textBoxRenderer = new TextBoxRenderer(fontData, new RGB(0, 0, 0), text);
	preferredWidth = textBoxRenderer.getPreferredSize().width + 2
		* ARROW_LENGTH;
	preferredHeight = textBoxRenderer.getPreferredSize().height;
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

	Arrow arrow = new Arrow(graphics);
	arrow.setTipAngle(ARROW_TIP_ANGLE);
	arrow.setTipLength((int) (ARROW_TIP_LENGTH * scaleX));
	arrow.setType(ArrowType.FANCY);

	final int arrowLength = (int) (ARROW_LENGTH * scaleX);

	Color color = new Color(graphics.getDevice(), rgb);
	try {
	    graphics.setForeground(color);
	    arrow.draw(x, y + h / 2, x + arrowLength, y + h / 2,
		    graphics.getForeground());
	    textBoxRenderer.render(graphics, x + arrowLength, y, x + w
		    - arrowLength - 1, y + h - 1);
	    graphics.drawLine(x + w - arrowLength, y + h / 2, x + w, y + h / 2);
	} finally {
	    color.dispose();
	}
    }
}
