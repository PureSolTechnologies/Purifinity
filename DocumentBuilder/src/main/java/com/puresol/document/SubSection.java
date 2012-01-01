package com.puresol.document;

public class SubSection extends AbstractDocumentPart {

	private static final long serialVersionUID = 5762220312873644480L;

	private final String title;

	public SubSection(AbstractDocumentPart parent, String name, String title) {
		super(parent, name);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
