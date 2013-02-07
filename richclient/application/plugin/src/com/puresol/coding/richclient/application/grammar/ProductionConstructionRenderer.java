package com.puresol.coding.richclient.application.grammar;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresol.trees.TreeException;
import com.puresol.uhura.parser.ParserTree;

public class ProductionConstructionRenderer extends AbstractRenderer {

	private final static int ARROW_LENGTH = UhuraRenderProperties
			.getBoxArrowLength();

	private final ParserTree productionConstruction;
	private final List<Renderer> renderers = new ArrayList<Renderer>();
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
	public void render(GC graphics, int x1, int y1, int x2, int y2) {
		final int x = Math.min(x1, x2);
		final int y = Math.min(y1, y2);
		final int w = Math.abs(x2 - x1) + 1;
		final int h = Math.abs(y2 - y1) + 1;
		final float scaleX = (float) w / (float) preferredWidth;
		final int arrowLength = (int) (ARROW_LENGTH + scaleX);

		RGB black = new RGB(0, 0, 0);
		Color color = new Color(graphics.getDevice(), black);
		try {
			graphics.setForeground(color);
			graphics.drawLine(x, y + h / 2, x + arrowLength, y + h / 2);

			int currentXPos = x + arrowLength + 1;
			for (int index = 0; index < renderers.size(); index++) {
				Renderer renderer = renderers.get(index);
				Dimension size = renderer.getPreferredSize();
				renderer.render(graphics, currentXPos, y, currentXPos
						+ (int) (size.getWidth() * scaleX), y + h - 1);
				currentXPos += (int) (size.getWidth() * scaleX);
			}
			graphics.drawLine(currentXPos, y + h / 2, x + w - 1, y + h / 2);
		} finally {
			color.dispose();
		}
	}
}
