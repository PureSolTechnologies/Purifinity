package com.puresol.document.convert.gui;

import java.awt.Font;

import javax.swing.JLabel;

public class SubSubSectionTitle extends JLabel {

	private static final long serialVersionUID = 9092367603009498393L;

	public SubSubSectionTitle(String text) {
		super(text, JLabel.LEFT);
		this.setFont(new Font("Verdana", 0, 14));
	}

}
