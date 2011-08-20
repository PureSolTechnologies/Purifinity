package com.puresol.document.convert.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.puresol.document.Document;

public class GUIDocument {

	public static JPanel convert(Document document) {
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(true);
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		panel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.add(new DocumentTitle(document.getName()));
		if (!document.getAuthor().isEmpty()) {
			titlePanel.add(new JLabel(document.getAuthor()));
		}
		if (!document.getVersion().isEmpty()) {
			titlePanel.add(new JLabel(document.getVersion()));
		}
		JPanel childPanel = new JPanel();
		childPanel.setOpaque(true);
		childPanel.setBackground(Color.WHITE);
		childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
		panel.add(childPanel, BorderLayout.CENTER);
		for (JPanel child : GUIConverter
				.convertChildren(document.getChildren())) {
			childPanel.add(child);
		}
		panel.add(new JLabel(document.getCreationDate().toString()),
				BorderLayout.SOUTH);
		return panel;
	}
}
