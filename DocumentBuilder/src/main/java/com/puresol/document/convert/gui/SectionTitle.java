package com.puresol.document.convert.gui;

import java.awt.Font;

import javax.swing.JLabel;

public class SectionTitle extends JLabel {

	private static final long serialVersionUID = 9092367603009498393L;

	public SectionTitle(String text) {
		super(text, JLabel.LEFT);
		this.setFont(new Font("Verdana", Font.BOLD, 18));
	}

}
