package com.puresoltechnologies.purifinity.client.common.analysis.grammar;

import java.awt.Dimension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresoltechnologies.commons.trees.api.TreeException;
import com.puresoltechnologies.parsers.impl.grammar.Quantity;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;

public class ConstructionIdentifierRenderer extends AbstractRenderer {

	private final FontData font = new FontData(
			UhuraRenderProperties.getIdentifierFontFamily(),
			UhuraRenderProperties.getIdentifierFontSize(), SWT.BOLD
					| SWT.ITALIC);
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
		Renderer constructionRenderer = new ConstructionRenderer(font, new RGB(
				0, 0, 0), identifier);
		quantityLoopRenderer = new QuantityLoopRenderer(constructionRenderer,
				quantity);
	}

	@Override
	public Dimension getPreferredSize() {
		return quantityLoopRenderer.getPreferredSize();
	}

	@Override
	public void render(GC graphics, int x1, int y1, int x2, int y2) {
		quantityLoopRenderer.render(graphics, x1, y1, x2, y2);
	}
}
