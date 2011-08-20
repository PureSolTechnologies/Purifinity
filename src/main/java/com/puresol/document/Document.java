package com.puresol.document;

import java.util.Date;

/**
 * This is the top part of any document. Here are the main information kept
 * about the document. All other parts are aligned as tree below this document
 * top part.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Document extends AbstractDocumentPart {

	private static final long serialVersionUID = -5340129628920736949L;

	private String author = "";
	private String version = "";
	private Date creationDate = new Date();

	public Document(String name) {
		super(null, name);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
