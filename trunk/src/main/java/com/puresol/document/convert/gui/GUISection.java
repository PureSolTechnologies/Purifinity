package com.puresol.document.convert.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.puresol.document.Section;

public class GUISection {

	public static JPanel convert(Section section) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new SectionTitle(section.getName()), BorderLayout.NORTH);
		JPanel childPanel = new JPanel();
		childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
		panel.add(childPanel, BorderLayout.CENTER);
		for (JPanel child : GUIConverter.convertChildren(section.getChildren())) {
			childPanel.add(child);
		}
		return panel;
	}

}
