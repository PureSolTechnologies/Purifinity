package com.puresol.html;

public class Paragraph {

    public static String generate(String content) {
	return new Paragraph(content).toHTML();
    }

    private final String content;

    public Paragraph(String content) {
	this.content = content;
    }

    public String toHTML() {
	String anchor = "<p>";
	anchor += content;
	anchor += "</p>\n";
	return anchor;
    }
}
