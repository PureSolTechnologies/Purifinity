package com.puresol.coding.richclient.application.grammar;

import java.awt.Dimension;

import org.eclipse.swt.graphics.GC;

import com.puresoltechnologies.trees.TreeException;
import com.puresoltechnologies.uhura.grammar.Quantity;
import com.puresoltechnologies.uhura.parser.ParserTree;

public class ConstructionGroupRenderer extends AbstractRenderer {

	private final Renderer quantityLoopRenderer;

	public ConstructionGroupRenderer(ParserTree constructionLiteral)
			throws RenderException {
		super();
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
		Renderer constructionRenderer;
		try {
			constructionRenderer = new ProductionConstructionsRenderer(
					constructionLiteral.getChild("ProductionConstructions"));
		} catch (TreeException e) {
			throw new RenderException(e.getMessage(), e);
		}
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
