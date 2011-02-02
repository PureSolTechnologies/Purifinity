package com.puresol.document;

public class Section extends AbstractDocumentPart {

	private static final long serialVersionUID = -2184434413148411926L;

	private final String title;

	public Section(AbstractDocumentPart parent, String name, String title) {
		super(parent, name);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
