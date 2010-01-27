package com.puresol.gui.coding;

import java.awt.Font;

import javax.swingx.TextArea;

public class CodeViewer extends TextArea {

    private static final long serialVersionUID = -6881015535418668742L;

    private boolean showLineNumbers = false;

    public CodeViewer() {
	super();
	setFont(new Font(Font.MONOSPACED, 0, 10));
    }

    public void setShowLineNumbers(boolean show) {
	showLineNumbers = show;
    }

    public boolean isShowLineNumbers() {
	return showLineNumbers;
    }

    public void setText(String text) {
	if (showLineNumbers) {
	    text = addLineNumbers(text);
	}
	super.setText(text);
    }

    private String addLineNumbers(String text) {
	String[] lines = text.split("\n");
	text = "";
	for (int index = 0; index < lines.length; index++) {
	    text += getLineString(index + 1) + lines[index] + "\n";
	}
	return text;
    }

    private String getLineString(int lineNumber) {
	String line = lineNumber + ":";
	while (line.length() < 8) {
	    line += " ";
	}
	return line;
    }
}
