package com.puresol.document.convert.gui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.puresol.document.Paragraph;

public class GUIParagraph {

	public static JPanel convert(Paragraph paragraph) {
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JTextArea textArea = new JTextArea(paragraph.getName());
		textArea.setOpaque(true);
		textArea.setEditable(false);
		panel.add(textArea);
		return panel;
	}
}
