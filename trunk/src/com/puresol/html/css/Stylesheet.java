package com.puresol.html.css;

import java.util.ArrayList;

public class Stylesheet {

	private final ArrayList<Style> styles = new ArrayList<Style>();

	public void addStyle(Style style) {
		styles.add(style);
	}

	public String toCSSString() {
		String css = "";
		for (Style style : styles) {
			if (!css.isEmpty()) {
				css += "\n";
			}
			css += style.toCSSString();
		}
		return css;
	}

}
