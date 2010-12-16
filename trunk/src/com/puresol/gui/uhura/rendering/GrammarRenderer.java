package com.puresol.gui.uhura.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import com.puresol.rendering.AbstractRenderer;
import com.puresol.rendering.RenderException;
import com.puresol.rendering.Renderer;
import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;

public class GrammarRenderer extends AbstractRenderer {

	private final static int BORDER_SPACE = 10;

	private final Graphics graphics;
	private final ParserTree grammarAST;
	private List<Renderer> renderers = new ArrayList<Renderer>();
	int preferredWidth = 0;
	int preferredHeight = 0;

	public GrammarRenderer(Graphics graphics, ParserTree grammarAST)
			throws RenderException {
		super();
		this.graphics = graphics;
		this.grammarAST = grammarAST;

		Graphics2D g2 = (Graphics2D) graphics;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);

		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING,
				RenderingHints.VALUE_DITHER_DISABLE);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		// g2.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST,
		// RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		createRenderers();
	}

	private void createRenderers() throws RenderException {
		try {
			for (ParserTree ast : grammarAST.getChild("Productions")
					.getChild("ProductionDefinitions")
					.getChildren("ProductionDefinition")) {
				Renderer renderer = new ProductionDefinitionRenderer(graphics,
						ast);
				Dimension preferredSize = renderer.getPreferredSize();
				if (preferredWidth < preferredSize.width) {
					preferredWidth = preferredSize.width;
				}
				preferredHeight += preferredSize.height;
				renderers.add(renderer);
			}
			preferredWidth += 2 * BORDER_SPACE;
			preferredHeight += 2 * BORDER_SPACE;
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
		graphics.setColor(Color.WHITE);
		graphics.fillRect(x, y, w - 1, h - 1);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(x, y, w - 1, h - 1);
		for (Renderer renderer : renderers) {
			renderer.setPosition(x + BORDER_SPACE, y + BORDER_SPACE);
			renderer.render();
			y += renderer.getPreferredSize().height;
		}
	}

}
