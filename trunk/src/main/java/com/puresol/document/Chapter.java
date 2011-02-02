package com.puresol.document;

public class Chapter extends AbstractDocumentPart {

	private static final long serialVersionUID = 1558579284131369607L;

	private final String title;

	public Chapter(AbstractDocumentPart parent, String name, String title) {
		super(parent, name);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
