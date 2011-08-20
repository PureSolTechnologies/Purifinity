package com.puresol.document.convert.gui;

import java.awt.Font;

import javax.swing.JLabel;

public class PartTitle extends JLabel {

	private static final long serialVersionUID = 9092367603009498393L;

	public PartTitle(String text) {
		super(text, JLabel.LEFT);
		this.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 22));
	}

}
