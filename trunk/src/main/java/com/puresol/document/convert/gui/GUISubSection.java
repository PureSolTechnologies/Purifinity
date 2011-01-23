package com.puresol.document.convert.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.puresol.document.SubSection;

public class GUISubSection {

	public static JPanel convert(SubSection subsection) {
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout());
		panel.add(new SubSectionTitle(subsection.getName()), BorderLayout.NORTH);
		JPanel childPanel = new JPanel();
		childPanel.setOpaque(true);
		childPanel.setBackground(Color.WHITE);
		childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
		panel.add(childPanel, BorderLayout.CENTER);
		for (JPanel child : GUIConverter.convertChildren(subsection
				.getChildren())) {
			childPanel.add(child);
		}
		return panel;
	}

}
