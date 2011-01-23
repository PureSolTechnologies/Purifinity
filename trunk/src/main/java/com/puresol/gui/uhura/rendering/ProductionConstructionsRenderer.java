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
import com.puresol.uhura.ast.ParserTree;

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
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.abs(x2 - x1) + 1;
		int h = Math.abs(y2 - y1) + 1;
		float scaleX = (float) w / (float) preferredWidth;
		float scaleY = (float) h / (float) preferredHeight;

		Arrow arrow = new Arrow(graphics);
		arrow.setTipAngle(ARROW_TIP_ANGLE);
		arrow.setTipLength((int) ((float) ARROW_TIP_LENGTH * scaleX));
		arrow.setType(ArrowType.FANCY);
		graphics.setColor(Color.BLACK);

		final int arrowLength = (int) ((float) ARROW_LENGTH * scaleX);

		graphics.drawLine(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);
		for (Renderer renderer : renderers) {
			Dimension size = renderer.getPreferredSize();
			renderer.render(graphics, x + arrowLength, y, x + arrowLength
					+ (int) (size.getWidth() * scaleX),
					y + (int) (size.getHeight() * scaleY));
			graphics.drawLine(x + (int) (size.getWidth() * scaleX), y
					+ (int) (size.getHeight() * scaleY / 2.0), x + w
					- arrowLength, y + (int) (size.getHeight() * scaleY / 2.0));
			y += renderer.getPreferredSize().height;
		}
		y = Math.min(y1, y2);
		graphics.drawLine(x + w - ARROW_LENGTH, y + h / 2, x + w - 1, y + h / 2);
		
		int verticalLineY1 = y + renderers.get(0).getPreferredSize().height / 2;
		int verticalLineY2 = y + h
				- renderers.get(renderers.size() - 1).getPreferredSize().height
				/ 2;
		graphics.drawLine(x + ARROW_LENGTH, verticalLineY1, x + ARROW_LENGTH,
				verticalLineY2);
		graphics.drawLine(x + w - ARROW_LENGTH, verticalLineY1, x + w
				- ARROW_LENGTH, verticalLineY2);
	}
}
