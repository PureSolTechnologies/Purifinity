package com.puresol.uhura.ui.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import com.puresol.rendering.AbstractRenderer;
import com.puresol.rendering.RenderException;
import com.puresol.rendering.Renderer;
import com.puresol.trees.TreeException;
import com.puresol.uhura.grammar.Quantity;
import com.puresol.uhura.parser.ParserTree;

public class ConstructionIdentifierRenderer extends AbstractRenderer {

    private final Font font = new Font(
	    UhuraRenderProperties.getIdentifierFontFamily(), Font.TRUETYPE_FONT
		    | Font.BOLD | Font.ITALIC,
	    UhuraRenderProperties.getIdentifierFontSize());
    private final Renderer quantityLoopRenderer;

    public ConstructionIdentifierRenderer(ParserTree constructionIdentifier)
	    throws RenderException {
	super();
	String identifier;
	try {
	    identifier = constructionIdentifier.getChild("IDENTIFIER")
		    .getText();
	} catch (TreeException e) {
	    identifier = "ERROR!";
	}
	Quantity quantity = Quantity.EXPECT;
	try {
	    ParserTree quantifierAST = constructionIdentifier
		    .getChild("Quantifier");
	    if ((quantifierAST != null) && (quantifierAST.hasChildren())) {
		ParserTree quantifier = quantifierAST.getChildren().get(0);
		if ("QUESTION_MARK".equals(quantifier.getName())) {
		    quantity = Quantity.ACCEPT;
		} else if ("STAR".equals(quantifier.getName())) {
		    quantity = Quantity.ACCEPT_MANY;
		} else if ("PLUS".equals(quantifier.getName())) {
		    quantity = Quantity.EXPECT_MANY;
		} else {
		    throw new RenderException("Invalid Quantifier '"
			    + quantifier.getName() + "' was found!");
		}
	    }
	} catch (TreeException e) {
	    throw new RenderException(e.getMessage(), e);
	}
	Renderer constructionRenderer = new ConstructionRenderer(font,
		Color.BLACK, identifier);
	quantityLoopRenderer = new QuantityLoopRenderer(constructionRenderer,
		quantity);
    }

    @Override
    public Dimension getPreferredSize() {
	return quantityLoopRenderer.getPreferredSize();
    }

    @Override
    public void render(Graphics graphics, int x1, int y1, int x2, int y2) {
	quantityLoopRenderer.render(graphics, x1, y1, x2, y2);
    }
}
