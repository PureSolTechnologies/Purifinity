package com.puresol.uhura.gui.rendering;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swingx.rendering.AbstractRenderer;
import javax.swingx.rendering.RenderException;
import javax.swingx.rendering.Renderer;

import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.grammar.Quantity;

public class ConstructionGroupRenderer extends AbstractRenderer {

	private final Renderer quantityLoopRenderer;

	public ConstructionGroupRenderer(Graphics graphics, ParserTree constructionLiteral)
			throws RenderException {
		super();
		Quantity quantity = Quantity.EXPECT;
		try {
			ParserTree quantifierAST = constructionLiteral.getChild("Quantifier");
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
					graphics,
					constructionLiteral.getChild("ProductionConstructions"));
		} catch (TreeException e) {
			throw new RenderException(e.getMessage(), e);
		}
		quantityLoopRenderer = new QuantityLoopRenderer(graphics,
				constructionRenderer, quantity);
	}

	@Override
	public Dimension getPreferredSize() {
		return quantityLoopRenderer.getPreferredSize();
	}

	@Override
	public void render() {
		quantityLoopRenderer.setPosition(getX(), getY());
		quantityLoopRenderer.render();
	}
}
