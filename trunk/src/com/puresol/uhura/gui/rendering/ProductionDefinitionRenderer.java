package com.puresol.uhura.gui.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import com.puresol.rendering.AbstractRenderer;
import com.puresol.rendering.Arrow;
import com.puresol.rendering.Arrow.ArrowType;
import com.puresol.rendering.RenderException;
import com.puresol.rendering.Renderer;
import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;

public class ProductionDefinitionRenderer extends AbstractRenderer {

	private final static int ARROW_LENGTH = RenderProperties
			.getBoxArrowLength();
	private final static int ARROW_TIP_LENGTH = RenderProperties
			.getArrowTipLength();
	private final static int ARROW_TIP_ANGLE = RenderProperties
			.getArrowTipAngle();

	private final Font font = new Font(
			RenderProperties.getIdentifierFontFamily(), Font.TRUETYPE_FONT
					| Font.BOLD | Font.ITALIC,
			RenderProperties.getIdentifierFontSize());

	private final Graphics graphics;
	private final String identifier;
	private final Renderer productionConstructionsRenderer;

	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public ProductionDefinitionRenderer(Graphics graphics,
			ParserTree productionDefinition) throws RenderException {
		super();
		this.graphics = graphics;
		String identifier;
		try {
			ParserTree identifierAST = productionDefinition
					.getChild("IDENTIFIER");
			if (identifierAST != null) {
				identifier = identifierAST.getText();
			} else {
				throw new RenderException(
						"A production definition without identifier was found!");
			}
		} catch (TreeException e) {
			throw new RenderException(e.getMessage(), e);
		}
		this.identifier = identifier;
		try {
			productionConstructionsRenderer = new ProductionConstructionsRenderer(
					graphics,
					productionDefinition.getChild("ProductionConstructions"));
		} catch (TreeException e) {
			throw new RenderException(e.getMessage(), e);
		}
		Dimension size = productionConstructionsRenderer.getPreferredSize();
		preferredHeight = size.height;
		preferredWidth = size.width;
		preferredWidth += 2 * ARROW_LENGTH;
		graphics.setFont(font);
		preferredWidth += graphics.getFontMetrics().stringWidth(identifier);
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

		graphics.setColor(Color.BLACK);
		graphics.setFont(font);
		graphics.drawString(identifier, x, y + h / 2);
		x += graphics.getFontMetrics().stringWidth(identifier);

		Arrow arrow = new Arrow(graphics);
		arrow.setTipAngle(ARROW_TIP_ANGLE);
		arrow.setTipLength(ARROW_TIP_LENGTH);
		arrow.setType(ArrowType.FANCY);

		graphics.drawLine(x, y + h / 2, x + ARROW_LENGTH, y + h / 2);

		productionConstructionsRenderer.setPosition(x + ARROW_LENGTH, y);
		productionConstructionsRenderer.render();

		x = getX();
		arrow.draw(x + w - ARROW_LENGTH, y + h / 2, x + w - 1, y + h / 2);

		graphics.drawLine(x + w - 1, y + h / 2 - ARROW_LENGTH / 2, x + w - 1, y
				+ h / 2 + ARROW_LENGTH / 2);
	}
}
