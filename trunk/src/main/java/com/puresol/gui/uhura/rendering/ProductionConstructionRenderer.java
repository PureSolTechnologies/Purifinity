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

	private final static int ARROW_LENGTH = UhuraRenderProperties
			.getBoxArrowLength();

	private final ParserTree productionConstruction;
	private List<Renderer> renderers = new ArrayList<Renderer>();
	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public ProductionConstructionRenderer(ParserTree productionConstruction)
			throws RenderException {
		super();
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
					renderer = new ConstructionIdentifierRenderer(
							productionPart);
				} else if ("ConstructionLiteral".equals(productionPart
						.getName())) {
					renderer = new ConstructionLiteralRenderer(productionPart);
				} else if ("ConstructionGroup".equals(productionPart.getName())) {
					renderer = new ConstructionGroupRenderer(productionPart);
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
	public void render(Graphics graphics, int x1, int y1, int x2, int y2) {
		graphics.setColor(Color.BLACK);
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.abs(x2 - x1) + 1;
		int h = Math.abs(y2 - y1) + 1;
		graphics.drawLine(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);

		float scaleX = (float) w / (float) preferredWidth;
		float scaleY = (float) h / (float) preferredHeight;

		x += ARROW_LENGTH;
		for (Renderer renderer : renderers) {
			Dimension size = renderer.getPreferredSize();
			renderer.render(graphics, x, y + h / 2
					- (int) ((float) size.height * scaleY / 2.0), x
					+ (int) (size.getWidth() * scaleX),
					y + h / 2 - (int) ((float) size.height * scaleY / 2.0)
							+ (int) (size.getHeight() * scaleY));
			x += (int) (renderer.getPreferredSize().getWidth() * scaleX);
		}
		graphics.drawLine(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);
	}
}
