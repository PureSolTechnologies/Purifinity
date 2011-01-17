package com.puresol.document.convert.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.puresol.document.Chapter;

public class GUIChapter {

	public static JPanel convert(Chapter chapter) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new ChapterTitle(chapter.getName()), BorderLayout.NORTH);
		JPanel childPanel = new JPanel();
		childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
		panel.add(childPanel, BorderLayout.CENTER);
		for (JPanel child : GUIConverter.convertChildren(chapter.getChildren())) {
			childPanel.add(child);
		}
		return panel;
	}

}
