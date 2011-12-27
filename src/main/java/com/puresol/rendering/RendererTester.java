package com.puresol.rendering;

import java.awt.BorderLayout;

import com.puresol.gui.Application;
import com.puresol.gui.PureSolDialog;

public class RendererTester extends PureSolDialog {

    private static final long serialVersionUID = -6972450146648013502L;

    private final RendererPanel panel;

    public RendererTester(Renderer renderer) {
	super(Application.getInstance(), "Renderer Test", true);

	panel = new RendererPanel(renderer);
	getContentPane().add(panel, BorderLayout.CENTER);
    }

    public Renderer getRenderer() {
	return panel.getRenderer();
    }

}
