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

public class GrammarRenderer extends AbstractRenderer {

	private final static int BORDER_SPACE = 10;

	private final ParserTree grammarAST;
	private List<Renderer> renderers = new ArrayList<Renderer>();
	int preferredWidth = 0;
	int preferredHeight = 0;

	public GrammarRenderer(ParserTree grammarAST) throws RenderException {
		super();
		this.grammarAST = grammarAST;

		createRenderers();
	}

	private void createRenderers() throws RenderException {
		try {
			for (ParserTree ast : grammarAST.getChild("Productions")
					.getChild("ProductionDefinitions")
					.getChildren("ProductionDefinition")) {
				Renderer renderer = new ProductionDefinitionRenderer(ast);
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
	public void render(Graphics graphics, int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.abs(x2 - x1) + 1;
		int h = Math.abs(y2 - y1) + 1;
		graphics.setColor(Color.WHITE);
		graphics.fillRect(x, y, w - 1, h - 1);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(x, y, w - 1, h - 1);

		float scaleX = (float) w / (float) preferredWidth;
		float scaleY = (float) h / (float) preferredHeight;

		int borderSpaceX = (int) ((float) BORDER_SPACE * scaleX);
		int borderSpaceY = (int) ((float) BORDER_SPACE * scaleY);

		for (Renderer renderer : renderers) {
			Dimension preferredSize = renderer.getPreferredSize();
			renderer.render(graphics, x + borderSpaceX, y + borderSpaceY, x
					+ borderSpaceX + (int) (preferredSize.getWidth() * scaleX),
					y + borderSpaceY
							+ (int) (preferredSize.getHeight() * scaleY));
			y += preferredSize.height;
		}
	}
}
