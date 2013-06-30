package com.puresol.purifinity.client.common.analysis.grammar;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresol.purifinity.trees.TreeException;
import com.puresol.purifinity.uhura.parser.ParserTree;

public final class GrammarRenderer extends AbstractRenderer {

    private final static int BORDER_SPACE = 10;

    private final List<Renderer> renderers = new ArrayList<Renderer>();

    private ParserTree grammarAST = null;
    private int preferredWidth = 0;
    private int preferredHeight = 0;

    public GrammarRenderer() {
	super();
    }

    public GrammarRenderer(ParserTree grammarAST) throws RenderException {
	super();
	this.grammarAST = grammarAST;

	createRenderers();
    }

    private void createRenderers() throws RenderException {
	try {
	    synchronized (renderers) {
		renderers.clear();
		preferredHeight = 0;
		preferredWidth = 0;
		if (grammarAST != null) {
		    for (ParserTree ast : grammarAST.getChild("Productions")
			    .getChild("ProductionDefinitions")
			    .getChildren("ProductionDefinition")) {
			Renderer renderer = new ProductionDefinitionRenderer(
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
		}
	    }
	} catch (TreeException e) {
	    e.printStackTrace();
	}
    }

    public final void setGrammar(ParserTree grammarAST) throws RenderException {
	this.grammarAST = grammarAST;
	createRenderers();
    }

    @Override
    public final Dimension getPreferredSize() {
	return new Dimension(preferredWidth, preferredHeight);
    }

    @Override
    public final void render(GC graphics, int x1, int y1, int x2, int y2) {
	synchronized (renderers) {
	    int x = Math.min(x1, x2);
	    int y = Math.min(y1, y2);
	    int w = Math.abs(x2 - x1) + 1;
	    int h = Math.abs(y2 - y1) + 1;
	    RGB red = new RGB(255, 0, 0);
	    Color frameColor = new Color(graphics.getDevice(), red);
	    try {
		graphics.setForeground(frameColor);
		graphics.drawRectangle(x, y, w - 1, h - 1);

		float scaleX = (float) w / (float) preferredWidth;
		float scaleY = h / (float) preferredHeight;

		int borderSpaceX = (int) (BORDER_SPACE * scaleX);
		int borderSpaceY = (int) (BORDER_SPACE * scaleY);

		for (Renderer renderer : renderers) {
		    Dimension preferredSize = renderer.getPreferredSize();
		    renderer.render(graphics, x + borderSpaceX, y
			    + borderSpaceY, x + borderSpaceX
			    + (int) (preferredSize.getWidth() * scaleX), y
			    + borderSpaceY
			    + (int) (preferredSize.getHeight() * scaleY));
		    y += preferredSize.height;
		}
	    } finally {
		frameColor.dispose();
	    }
	}
    }
}
