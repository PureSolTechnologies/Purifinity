package com.puresol.gui.uhura.rendering;

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

	private final static int ARROW_LENGTH = UhuraRenderProperties
			.getBoxArrowLength();
	private final static int ARROW_TIP_LENGTH = UhuraRenderProperties
			.getArrowTipLength();
	private final static int ARROW_TIP_ANGLE = UhuraRenderProperties
			.getArrowTipAngle();

	private final Font font = new Font(
			UhuraRenderProperties.getIdentifierFontFamily(), Font.TRUETYPE_FONT
					| Font.BOLD | Font.ITALIC,
			UhuraRenderProperties.getIdentifierFontSize());

	private final String identifier;
	private final Renderer productionConstructionsRenderer;

	private int preferredWidth = 0;
	private int preferredHeight = 0;

	public ProductionDefinitionRenderer(ParserTree productionDefinition)
			throws RenderException {
		super();
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
					productionDefinition.getChild("ProductionConstructions"));
		} catch (TreeException e) {
			throw new RenderException(e.getMessage(), e);
		}
		Dimension size = productionConstructionsRenderer.getPreferredSize();
		preferredHeight = size.height;
		preferredWidth = size.width;
		preferredWidth += 2 * ARROW_LENGTH;

		preferredWidth += UhuraRenderProperties.getAssumedFontSize().width
				* identifier.length();
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

		graphics.setColor(Color.BLACK);
		graphics.setFont(font);
		graphics.drawString(identifier, x, y + h / 2);
		x += graphics.getFontMetrics().stringWidth(identifier);
		graphics.drawLine(x, y + h / 2,
				x + UhuraRenderProperties.getAssumedFontSize().width
						* identifier.length()
						- graphics.getFontMetrics().stringWidth(identifier), y
						+ h / 2);

		Arrow arrow = new Arrow(graphics);
		arrow.setTipAngle(ARROW_TIP_ANGLE);
		arrow.setTipLength((int) ((float) ARROW_TIP_LENGTH * scaleX));
		arrow.setType(ArrowType.FANCY);

		final int arrowLength = (int) ((float) ARROW_LENGTH * scaleX);

		graphics.drawLine(x, y + h / 2, x + arrowLength, y + h / 2);

		Dimension size = productionConstructionsRenderer.getPreferredSize();
		productionConstructionsRenderer.render(graphics, x + arrowLength, y, x
				+ arrowLength + (int) (size.getWidth() * scaleX), y
				+ (int) (size.getHeight() * scaleY));

		x = Math.min(x1, x2);
		arrow.draw(x + w - arrowLength, y + h / 2, x + w - 1, y + h / 2);

		graphics.drawLine(x + w - 1, y + h / 2 - arrowLength / 2, x + w - 1, y
				+ h / 2 + arrowLength / 2);
	}
}
