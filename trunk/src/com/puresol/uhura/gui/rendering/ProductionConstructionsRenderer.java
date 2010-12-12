package com.puresol.uhura.gui.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.puresol.rendering.AbstractRenderer;
import com.puresol.rendering.Arrow;
import com.puresol.rendering.Arrow.ArrowType;
import com.puresol.rendering.RenderException;
import com.puresol.rendering.Renderer;
import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;

public class ProductionConstructionsRenderer extends AbstractRenderer {

	private final static int ARROW_LENGTH = RenderProperties
			.getBoxArrowLength();
	private final static int ARROW_TIP_LENGTH = RenderProperties
			.getArrowTipLength();
	private final static int ARROW_TIP_ANGLE = RenderProperties
			.getArrowTipAngle();

	private final Graphics graphics;
	private final ParserTree productionConstructions;
	private final List<Renderer> renderers = new ArrayList<Renderer>();

	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public ProductionConstructionsRenderer(Graphics graphics,
			ParserTree productionConstructions) throws RenderException {
		super();
		this.graphics = graphics;
		this.productionConstructions = productionConstructions;
		createRenderer();
	}

	private void createRenderer() throws RenderException {
		try {
			for (ParserTree productionConstruction : productionConstructions
					.getChildren("ProductionConstruction")) {
				Renderer renderer = new ProductionConstructionRenderer(
						graphics, productionConstruction);
				Dimension preferredSize = renderer.getPreferredSize();
				if (preferredWidth < preferredSize.width) {
					preferredWidth = preferredSize.width;
				}
				preferredHeight += preferredSize.height;
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
		int x = getX();
		int y = getY();
		int w = preferredWidth;
		int h = preferredHeight;

		Arrow arrow = new Arrow(graphics);
		arrow.setTipAngle(ARROW_TIP_ANGLE);
		arrow.setTipLength(ARROW_TIP_LENGTH);
		arrow.setType(ArrowType.FANCY);
		graphics.setColor(Color.BLACK);

		graphics.drawLine(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);
		for (Renderer renderer : renderers) {
			renderer.setPosition(x + ARROW_LENGTH, y);
			renderer.render();
			Dimension size = renderer.getPreferredSize();
			graphics.drawLine(x + size.width, y + size.height / 2, x + w
					- ARROW_LENGTH, y + size.height / 2);
			y += renderer.getPreferredSize().height;
		}
		y = getY();
		graphics.drawLine(x + w - ARROW_LENGTH, y + h / 2, x + w - 1, y + h / 2);
		int y1 = y + renderers.get(0).getPreferredSize().height / 2;
		int y2 = y + h
				- renderers.get(renderers.size() - 1).getPreferredSize().height
				/ 2;
		graphics.drawLine(x + ARROW_LENGTH, y1, x + ARROW_LENGTH, y2);
		graphics.drawLine(x + w - ARROW_LENGTH, y1, x + w - ARROW_LENGTH, y2);
	}
}
