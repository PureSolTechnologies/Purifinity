package com.puresol.html;

public class Anchor {

    public static String generate(String name, String content) {
	return new Anchor(name, content).toHTML();
    }

    private final String name;
    private final String content;

    public Anchor(String name, String content) {
	this.name = name;
	this.content = content;
    }

    public String toHTML() {
	String anchor = "<a name=\"" + name + "\">";
	anchor += content;
	anchor += "</a>";
	return anchor;
    }
}
