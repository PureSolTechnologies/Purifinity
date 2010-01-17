package com.puresol.html.css;

import java.util.ArrayList;
import java.util.Collections;

public class Stylesheet {

	private final ArrayList<Style> styles = new ArrayList<Style>();

	public void addStyle(Style style) {
		styles.add(style);
	}

	public String toCSSString() {
		Collections.sort(styles);
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
