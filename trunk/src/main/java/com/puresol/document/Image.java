package com.puresol.document;

import com.puresol.rendering.ImageRenderer;

/**
 * This object keeps the information about a single table element within the
 * document.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Image extends AbstractDocumentPart {

	private static final long serialVersionUID = -3314964484840281581L;

	private final ImageRenderer imageRenderer;

	private String caption = "";

	public Image(AbstractDocumentPart parent, String name,
			ImageRenderer imageRenderer) {
		super(parent, name);
		this.imageRenderer = imageRenderer;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public ImageRenderer getImageRenderer() {
		return imageRenderer;
	}

}
