package com.puresol.document.convert.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.puresol.document.SubSubSection;

public class GUISubSubSection {

	public static JPanel convert(SubSubSection subsubsection) {
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout());
		panel.add(new SubSubSectionTitle(subsubsection.getTitle()),
				BorderLayout.NORTH);
		JPanel childPanel = new JPanel();
		childPanel.setOpaque(true);
		childPanel.setBackground(Color.WHITE);
		childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
		panel.add(childPanel, BorderLayout.CENTER);
		for (JPanel child : GUIConverter.convertChildren(subsubsection
				.getChildren())) {
			childPanel.add(child);
		}
		return panel;
	}

}
