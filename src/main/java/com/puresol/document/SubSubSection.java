package com.puresol.document;

public class SubSubSection extends AbstractDocumentPart {

	private static final long serialVersionUID = 6847589210583731489L;

	private final String title;

	public SubSubSection(AbstractDocumentPart parent, String name, String title) {
		super(parent, name);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
