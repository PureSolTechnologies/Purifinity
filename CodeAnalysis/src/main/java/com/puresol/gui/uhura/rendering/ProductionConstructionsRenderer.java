package com.puresol.gui.uhura.rendering;

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
import com.puresol.uhura.parser.ParserTree;

public class ProductionConstructionsRenderer extends AbstractRenderer {

	private final static int ARROW_LENGTH = UhuraRenderProperties
			.getBoxArrowLength();
	private final static int ARROW_TIP_LENGTH = UhuraRenderProperties
			.getArrowTipLength();
	private final static int ARROW_TIP_ANGLE = UhuraRenderProperties
			.getArrowTipAngle();

	private final ParserTree productionConstructions;
	private final List<Renderer> renderers = new ArrayList<Renderer>();

	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public ProductionConstructionsRenderer(ParserTree productionConstructions)
			throws RenderException {
		super();
		this.productionConstructions = productionConstructions;
		createRenderer();
	}

	private void createRenderer() throws RenderException {
		try {
			for (ParserTree productionConstruction : productionConstructions
					.getChildren("ProductionConstruction")) {
				Renderer renderer = new ProductionConstructionRenderer(
						productionConstruction);
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
	public void render(Graphics graphics, int x1, int y1, int x2, int y2) {
		final int x = Math.min(x1, x2);
		final int y = Math.min(y1, y2);
		final int w = Math.abs(x2 - x1) + 1;
		final int h = Math.abs(y2 - y1) + 1;
		final float scaleX = (float) w / (float) preferredWidth;
		final int arrowLength = (int) ((float) ARROW_LENGTH * scaleX);
		final int constructionHeight = h / renderers.size();

		Arrow arrow = new Arrow(graphics);
		arrow.setTipAngle(ARROW_TIP_ANGLE);
		arrow.setTipLength((int) ((float) ARROW_TIP_LENGTH * scaleX));
		arrow.setType(ArrowType.FANCY);
		graphics.setColor(Color.BLACK);

		graphics.drawLine(x, y + h / 2, x + arrowLength, y + h / 2);

		for (int i = 0; i < renderers.size(); i++) {
			Renderer renderer = renderers.get(i);
			renderer.render(graphics, x + arrowLength, y + i
					* constructionHeight, x + w - 1 - arrowLength - 1, y
					+ (i + 1) * constructionHeight);
		}

		graphics.drawLine(x + w - 1 - arrowLength, y + h / 2, x + w - 1, y + h
				/ 2);

		final int verticalLineY1 = y + constructionHeight / 2;
		final int verticalLineY2 = y + h - 1 - constructionHeight / 2;
		graphics.drawLine(x + arrowLength, verticalLineY1, x + arrowLength,
				verticalLineY2);
		graphics.drawLine(x + w - 1 - arrowLength, verticalLineY1, x + w - 1
				- arrowLength, verticalLineY2);
	}
}
