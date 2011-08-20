package com.puresol.reporting.html;

public class Link {

	public static final String PURESOL_TECHNOLOGIES_WEBPAGE = ("http://www.puresol-technologies.com");

	public static Link getPureSolTechnolgiesHomePage() {
		return new Link(PURESOL_TECHNOLOGIES_WEBPAGE, "PureSol-Technologies",
				LinkTarget.TOP);
	}

	private final String url;
	private final String text;
	private final LinkTarget target;

	public Link(String url) {
		this(url, url.toString());
	}

	public Link(String url, String text) {
		this(url, text, LinkTarget.DEFAULT);
	}

	public Link(String url, String text, LinkTarget target) {
		this.url = url;
		this.text = text;
		this.target = target;
	}

	public String toHTML() {
		String targetString = "";
		if (target != LinkTarget.DEFAULT) {
			targetString = " target=\"" + target.getKeyword() + "\"";
		}
		String link = "<a href=\"" + url.toString() + "\"" + targetString + ">"
				+ text + "</a>";
		return link;
	}

	public String getUrl() {
		return url;
	}

	public String getText() {
		return text;
	}

	public LinkTarget getTarget() {
		return target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
