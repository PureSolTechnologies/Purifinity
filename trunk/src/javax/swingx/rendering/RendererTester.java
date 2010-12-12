package javax.swingx.rendering;

import java.awt.Graphics;

import javax.swingx.Dialog;
import javax.swingx.Panel;

public class RendererTester extends Dialog {

	private static final long serialVersionUID = -6972450146648013502L;

	private class RendererPanel extends Panel {

		private static final long serialVersionUID = 1L;

		private final Renderer renderer;

		public RendererPanel(Renderer renderer) {
			this.renderer = renderer;
		}

		public void paint(Graphics graphics) {
			super.paint(graphics);
			renderer.setPosition(0, 0);
			renderer.render();
		}

	}

	public RendererTester(Renderer renderer) {
		super("Renderer Test", true);
		add(new RendererPanel(renderer));
	}
}
