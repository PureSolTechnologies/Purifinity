package com.puresol.rendering;

import java.awt.BorderLayout;

import com.puresol.gui.Dialog;

public class RendererTester extends Dialog {

	private static final long serialVersionUID = -6972450146648013502L;

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
