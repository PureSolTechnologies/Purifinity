package com.puresol.document.convert.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.puresol.document.SubSubSection;

public class GUISubSubSection {

	public static JPanel convert(SubSubSection subsubsection) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new SubSubSectionTitle(subsubsection.getName()),
				BorderLayout.NORTH);
		JPanel childPanel = new JPanel();
		childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
		panel.add(childPanel, BorderLayout.CENTER);
		for (JPanel child : GUIConverter.convertChildren(subsubsection
				.getChildren())) {
			childPanel.add(child);
		}
		return panel;
	}

}
