package com.puresol.rendering;

import java.awt.Graphics;

import javax.swing.JPanel;

public class RendererPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Renderer renderer;

	public RendererPanel(Renderer renderer) {
		this.renderer = renderer;
		setDoubleBuffered(true);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		if (renderer != null) {
			renderer.render(graphics, 0, 0, getSize().width - 1,
					getSize().height - 1);
		}
	}

	public Renderer getRenderer() {
		return renderer;
	}

}
