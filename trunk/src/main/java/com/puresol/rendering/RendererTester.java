package com.puresol.rendering;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.puresol.gui.Dialog;

public class RendererTester extends Dialog {

	private static final long serialVersionUID = -6972450146648013502L;

	private class RendererPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private final Renderer renderer;

		public RendererPanel(Renderer renderer) {
			this.renderer = renderer;
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

	private final RendererPanel panel;

	public RendererTester(Renderer renderer) {
		super("Renderer Test", true);

		getContentPane().setLayout(new BorderLayout());
		panel = new RendererPanel(renderer);
		getContentPane().add(panel, BorderLayout.CENTER);
	}

	public Renderer getRenderer() {
		return panel.getRenderer();
	}

}
