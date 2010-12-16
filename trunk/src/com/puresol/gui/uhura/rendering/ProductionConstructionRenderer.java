package com.puresol.gui.uhura.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.puresol.rendering.AbstractRenderer;
import com.puresol.rendering.RenderException;
import com.puresol.rendering.Renderer;
import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;

public class ProductionConstructionRenderer extends AbstractRenderer {

	private final static int ARROW_LENGTH = RenderProperties
			.getBoxArrowLength();

	private final Graphics graphics;
	private final ParserTree productionConstruction;
	private List<Renderer> renderers = new ArrayList<Renderer>();
	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public ProductionConstructionRenderer(Graphics graphics,
			ParserTree productionConstruction) throws RenderException {
		super();
		this.graphics = graphics;
		this.productionConstruction = productionConstruction;
		createRenderer();
	}

	private void createRenderer() throws RenderException {
		try {
			ParserTree productionParts = productionConstruction
					.getChild("ProductionParts");
			if (productionParts == null) {
				return;
			}
			for (ParserTree productionPart : productionParts.getChildren()) {
				Renderer renderer = null;
				if ("ConstructionIdentifier".equals(productionPart.getName())) {
					renderer = new ConstructionIdentifierRenderer(graphics,
							productionPart);
				} else if ("ConstructionLiteral".equals(productionPart
						.getName())) {
					renderer = new ConstructionLiteralRenderer(graphics,
							productionPart);
				} else if ("ConstructionGroup".equals(productionPart.getName())) {
					renderer = new ConstructionGroupRenderer(graphics,
							productionPart);
				} else {
					continue;
				}
				Dimension preferredSize = renderer.getPreferredSize();
				if (preferredHeight < preferredSize.height) {
					preferredHeight = preferredSize.height;
				}
				preferredWidth += preferredSize.width;
				renderers.add(renderer);
			}
			preferredWidth += 2 * ARROW_LENGTH;
		} catch (TreeException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(preferredWidth, preferredHeight);
	}

	@Override
	public void render() {
		graphics.setColor(Color.BLACK);
		int x = getX();
		int y = getY();
		int h = preferredHeight;
		graphics.drawLine(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);
		x += ARROW_LENGTH;
		for (Renderer renderer : renderers) {
			Dimension size = renderer.getPreferredSize();
			renderer.setPosition(x, y + h / 2 - size.height / 2);
			renderer.render();
			x += renderer.getPreferredSize().width;
		}
		graphics.drawLine(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);
	}
}
