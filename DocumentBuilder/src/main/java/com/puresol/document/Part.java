package com.puresol.document;

public class Part extends AbstractDocumentPart {

	private static final long serialVersionUID = 3658777130676944179L;

	private final String title;

	public Part(AbstractDocumentPart parent, String name, String title) {
		super(parent, name);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
