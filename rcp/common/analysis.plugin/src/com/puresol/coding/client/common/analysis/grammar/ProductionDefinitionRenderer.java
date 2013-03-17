package com.puresol.coding.client.common.analysis.grammar;

import java.awt.Dimension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.analysis.grammar.Arrow.ArrowType;
import com.puresol.trees.TreeException;
import com.puresol.uhura.parser.ParserTree;

public class ProductionDefinitionRenderer extends AbstractRenderer {

    private final static int ARROW_LENGTH = UhuraRenderProperties
	    .getBoxArrowLength();
    private final static int ARROW_TIP_LENGTH = UhuraRenderProperties
	    .getArrowTipLength();
    private final static int ARROW_TIP_ANGLE = UhuraRenderProperties
	    .getArrowTipAngle();

    private final FontData fontData = new FontData(
	    UhuraRenderProperties.getIdentifierFontFamily(),
	    UhuraRenderProperties.getIdentifierFontSize(), SWT.BOLD
		    | SWT.ITALIC);

    private final String identifier;
    private final Renderer productionConstructionsRenderer;

    private int preferredWidth = 0;
    private int preferredHeight = 0;

    public ProductionDefinitionRenderer(ParserTree productionDefinition)
	    throws RenderException {
	super();
	String identifier;
	try {
	    ParserTree identifierAST = productionDefinition
		    .getChild("IDENTIFIER");
	    if (identifierAST != null) {
		identifier = identifierAST.getText();
	    } else {
		throw new RenderException(
			"A production definition without identifier was found!");
	    }
	} catch (TreeException e) {
	    throw new RenderException(e.getMessage(), e);
	}
	this.identifier = identifier;
	try {
	    productionConstructionsRenderer = new ProductionConstructionsRenderer(
		    productionDefinition.getChild("ProductionConstructions"));
	} catch (TreeException e) {
	    throw new RenderException(e.getMessage(), e);
	}
	Dimension size = productionConstructionsRenderer.getPreferredSize();
	preferredHeight = size.height;
	preferredWidth = size.width;
	preferredWidth += 2 * ARROW_LENGTH;

	preferredWidth += UhuraRenderProperties.getAssumedFontSize().width
		* identifier.length();
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(preferredWidth, preferredHeight);
    }

    @Override
    public void render(GC graphics, int x1, int y1, int x2, int y2) {
	final int x = Math.min(x1, x2);
	final int y = Math.min(y1, y2);
	final int h = Math.abs(y2 - y1) + 1;

	RGB black = new RGB(0, 0, 0);
	Color color = new Color(graphics.getDevice(), black);
	try {
	    graphics.setForeground(color);
	    Font font = new Font(graphics.getDevice(), fontData);
	    try {
		graphics.setFont(font);
		graphics.drawString(identifier, x, y + h / 2);

		renderDefinition(graphics, x
			+ graphics.getFontMetrics().getAverageCharWidth()
			* identifier.length(), y1, x2, y + h - 1);
	    } finally {
		font.dispose();
	    }
	} finally {
	    color.dispose();
	}
    }

    private void renderDefinition(GC graphics, int x1, int y1, int x2, int y2) {
	final int x = Math.min(x1, x2);
	final int y = Math.min(y1, y2);
	final int w = Math.abs(x2 - x1) + 1;
	final int h = Math.abs(y2 - y1) + 1;
	final float scaleX = (float) w / (float) preferredWidth;

	final int arrowLength = (int) (ARROW_LENGTH * scaleX);

	graphics.drawLine(x, y + h / 2, x + arrowLength, y + h / 2);

	Arrow arrow = new Arrow(graphics);
	arrow.setTipAngle(ARROW_TIP_ANGLE);
	arrow.setTipLength((int) (ARROW_TIP_LENGTH * scaleX));
	arrow.setType(ArrowType.SIMPLE);

	graphics.drawLine(x, y + h / 2, x + arrowLength, y + h / 2);

	productionConstructionsRenderer.render(graphics, x + arrowLength, y, x
		+ w - 1 - arrowLength, y + h - 1);

	arrow.draw(x + w - 1 - arrowLength, y + h / 2, x + w - 1, y + h / 2,
		graphics.getForeground());

	graphics.drawLine(x + w - 1, y + h / 2 - arrowLength / 2, x + w - 1, y
		+ h / 2 + arrowLength / 2);
    }
}
