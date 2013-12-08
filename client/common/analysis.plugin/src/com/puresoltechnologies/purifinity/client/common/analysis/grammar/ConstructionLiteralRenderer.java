package com.puresoltechnologies.purifinity.client.common.analysis.grammar;

import java.awt.Dimension;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresoltechnologies.commons.trees.api.TreeException;
import com.puresoltechnologies.parsers.impl.grammar.Quantity;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;

public class ConstructionLiteralRenderer extends AbstractRenderer {

	private final FontData font = new FontData(
			UhuraRenderProperties.getLiteralFontFamily(),
			UhuraRenderProperties.getLiteralFontSize(), SWT.NONE);
	private final Renderer quantityLoopRenderer;

	public ConstructionLiteralRenderer(ParserTree constructionLiteral)
			throws RenderException {
		super();
		String literal;
		try {
			literal = constructionLiteral.getChild("STRING_LITERAL").getText();
		} catch (TreeException e) {
			throw new RenderException(
					"Literal construction found without string literal!");
		}
		Quantity quantity = Quantity.EXPECT;
		try {
			ParserTree quantifierAST = constructionLiteral
					.getChild("Quantifier");
			if (quantifierAST.hasChildren()) {
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
				0, 0, 0), literal);
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
