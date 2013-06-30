package com.puresol.purifinity.uhura.source;

public class StringWithLocation {

	private final String text;
	private final SourceCodeLine[] sourceLineReferences;

	public StringWithLocation(SourceCode sourceCode) {
		super();
		StringBuffer buffer = new StringBuffer();
		for (SourceCodeLine line : sourceCode.getLines()) {
			buffer.append(line.getLine());
		}
		this.text = buffer.toString();

		sourceLineReferences = new SourceCodeLine[text.length()];
		int position = 0;
		for (SourceCodeLine line : sourceCode.getLines()) {
			for (int i = 0; i < line.getLine().length(); i++) {
				sourceLineReferences[position] = line;
				position++;
			}
		}
	}

	public String getText() {
		return text;
	}

	public SourceCodeLine getSource(int position) {
		return sourceLineReferences[position];
	}

}
