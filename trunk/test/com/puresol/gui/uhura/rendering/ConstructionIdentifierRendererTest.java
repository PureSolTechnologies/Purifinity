package com.puresol.gui.uhura.rendering;

import java.awt.Graphics;

import javax.swingx.Dialog;
import javax.swingx.Panel;

import com.puresol.gui.uhura.rendering.ConstructionIdentifierRenderer;
import com.puresol.rendering.RenderException;
import com.puresol.rendering.Renderer;
import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.lexer.Token;

public class ConstructionIdentifierRendererTest {

	public static void main(String args[]) {
		Dialog frame = new Dialog();
		frame.add(new Panel() {
			private static final long serialVersionUID = 8456563011009861087L;

			public void paint(Graphics graphics) {
				super.paint(graphics);
				try {
					ParserTree ast = new ParserTree("ConstructionIdentifier");
					ParserTree identifier = new ParserTree(new Token(
							"IDENTIFIER", "TEST", null, null));
					identifier.setParent(ast);
					ast.addChild(identifier);
					Renderer renderer = new ConstructionIdentifierRenderer(
							graphics, ast);
					renderer.setPosition(0, 0);
					renderer.render();
				} catch (TreeException e) {
					e.printStackTrace();
				} catch (RenderException e) {
					e.printStackTrace();
				}
			}
		});
		frame.run();
	}

}
