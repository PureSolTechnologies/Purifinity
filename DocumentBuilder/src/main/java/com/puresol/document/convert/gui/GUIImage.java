package com.puresol.document.convert.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puresol.document.Image;
import com.puresol.rendering.RendererPanel;

public class GUIImage {

	public static JPanel convert(Image image) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new RendererPanel(image.getImageRenderer()));
		panel.add(new JLabel(image.getCaption()));
		return panel;
	}

}
