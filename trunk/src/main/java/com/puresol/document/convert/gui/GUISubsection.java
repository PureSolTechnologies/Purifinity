package com.puresol.document.convert.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.puresol.document.Subsection;

public class GUISubsection {

	public static JPanel convert(Subsection subsection) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new SubSectionTitle(subsection.getName()), BorderLayout.NORTH);
		JPanel childPanel = new JPanel();
		childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
		panel.add(childPanel, BorderLayout.CENTER);
		for (JPanel child : GUIConverter.convertChildren(subsection
				.getChildren())) {
			childPanel.add(child);
		}
		return panel;
	}

}
