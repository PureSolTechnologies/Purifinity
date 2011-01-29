package com.puresol.gui.uhura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;

import com.puresol.gui.Saveable;
import com.puresol.gui.uhura.rendering.GrammarRenderer;
import com.puresol.gui.uhura.rendering.ProductionDefinitionRenderer;
import com.puresol.rendering.RenderException;
import com.puresol.rendering.Renderer;
import com.puresol.trees.TreeException;
import com.puresol.uhura.parser.ParserTree;

/**
 * This panel draws a complete grammar with all its productions in a flow
 * diagram.
 * 
 * @see GrammarRenderer
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarSchematic extends JPanel implements Saveable {

	private static final long serialVersionUID = 1804025428672500990L;

	private static final Logger logger = Logger
			.getLogger(GrammarSchematic.class);

	private ParserTree grammarAST = null;

	public GrammarSchematic() {
		super();
	}

	public GrammarSchematic(ParserTree grammarAST) {
		super();
		setGrammarAST(grammarAST);
	}

	public ParserTree getGrammarAST() {
		return grammarAST;
	}

	public void setGrammarAST(ParserTree grammarAST) {
		this.grammarAST = grammarAST;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		try {
			long start = System.currentTimeMillis();
			super.paintComponent(graphics);
			Renderer renderer = new GrammarRenderer(grammarAST);
			Dimension preferredSize = renderer.getPreferredSize();
			Dimension currentSize = getSize();
			if ((currentSize.width < preferredSize.width)
					|| (currentSize.height < preferredSize.height)) {
				System.out.println("Changed size! " + preferredSize + " -> "
						+ currentSize);
				setMinimumSize(preferredSize);
				setPreferredSize(preferredSize);
				setSize(preferredSize.width, preferredSize.height);
			}
			renderer.render(graphics, 0, 0, renderer.getPreferredSize().width,
					renderer.getPreferredSize().height);
			long stop = System.currentTimeMillis();
			System.out.println("Rendering time: "
					+ ((double) (stop - start) / 1000.0) + "s");
		} catch (RenderException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileFilter[] getPossibleFileFilters() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(File directory) throws IOException {
		try {
			if (!directory.isDirectory()) {
				throw new IOException(
						"A directory was awaited as storage destination!");
			}
			ParserTree productionDefinitionsAST = grammarAST.getChild(
					"Productions").getChild("ProductionDefinitions");
			for (ParserTree productionDefinition : productionDefinitionsAST
					.getChildren("ProductionDefinition")) {
				String identifier = productionDefinition.getChild("IDENTIFIER")
						.getText();
				File file = new File(directory, identifier + ".png");
				logger.info("Saving " + file.toString() + "...");
				FileOutputStream outputStream = null;
				try {
					BufferedImage image = new BufferedImage(1, 1,
							BufferedImage.TYPE_INT_RGB);
					Renderer renderer = new ProductionDefinitionRenderer(
							productionDefinition);
					Dimension size = renderer.getPreferredSize();
					image = new BufferedImage(size.width, size.height,
							BufferedImage.TYPE_INT_RGB);
					Graphics graphics = image.getGraphics();
					graphics.setColor(Color.WHITE);
					graphics.fillRect(0, 0, size.width, size.height);
					renderer = new ProductionDefinitionRenderer(
							productionDefinition);
					renderer.render(graphics, 0, 0,
							renderer.getPreferredSize().width,
							renderer.getPreferredSize().height);
					outputStream = new FileOutputStream(file);
					ImageIO.write(image, "png", outputStream);
					logger.info("done.");
				} catch (RenderException e) {
					logger.info("aborted!");
					throw new IOException(e.getMessage());
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
			}
		} catch (TreeException e) {
			throw new IOException(e.getMessage(), e);
		}
	}

}
