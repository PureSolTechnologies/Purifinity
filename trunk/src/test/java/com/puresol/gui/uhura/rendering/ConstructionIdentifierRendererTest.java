package com.puresol.gui.uhura.rendering;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import com.puresol.gui.uhura.rendering.ConstructionIdentifierRenderer;
import com.puresol.rendering.RenderException;
import com.puresol.rendering.Renderer;
import com.puresol.rendering.RendererTester;
import com.puresol.trees.TreeException;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.lexer.Token;

public class ConstructionIdentifierRendererTest {

	private Graphics graphics;
	private ParserTree ast;

	@Before
	public void createEnvironment() {
		try {
			BufferedImage image = new BufferedImage(10, 10,
					BufferedImage.TYPE_INT_RGB);
			graphics = GraphicsEnvironment.getLocalGraphicsEnvironment()
					.createGraphics(image);
			assertNotNull(graphics);

			ast = new ParserTree("ConstructionIdentifier");
			assertNotNull(ast);
			ParserTree identifier = new ParserTree(new Token("IDENTIFIER",
					"TEST", null, null));
			assertNotNull(identifier);
			identifier.setParent(ast);
			ast.addChild(identifier);
		} catch (TreeException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testInstance() {
		try {
			assertNotNull(new ConstructionIdentifierRenderer(ast));
		} catch (RenderException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	public static void main(String args[]) {
		try {
			ParserTree ast = new ParserTree("ConstructionIdentifier");
			ParserTree identifier = new ParserTree(new Token("IDENTIFIER",
					"TEST", null, null));
			identifier.setParent(ast);
			ast.addChild(identifier);
			Renderer renderer = new ConstructionIdentifierRenderer(ast);
			RendererTester tester = new RendererTester(renderer);
			tester.run();
		} catch (TreeException e) {
			e.printStackTrace();
		} catch (RenderException e) {
			e.printStackTrace();
		}
	}

}
