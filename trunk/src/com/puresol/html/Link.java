package com.puresol.html;

import java.net.MalformedURLException;
import java.net.URL;

import com.puresol.exceptions.StrangeSituationException;

public class Link {

	public static Link getPureSolTechnolgiesHomePage() {
		try {
			return new Link(new URL("http://www.puresol-technologies.com"),
					"PureSol-Technologies", LinkTarget.BLANK);
		} catch (MalformedURLException e) {
			throw new StrangeSituationException(e);
		}
	}

	private final URL url;
	private final String text;
	private final LinkTarget target;

	public Link(URL url) {
		this(url, url.toString());
	}

	public Link(URL url, String text) {
		this(url, text, LinkTarget.DEFAULT);
	}

	public Link(URL url, String text, LinkTarget target) {
		this.url = url;
		this.text = text;
		this.target = target;
	}

	public String toHTMLText() {
		String targetString = "";
		if (target != LinkTarget.DEFAULT) {
			targetString = " target=\"" + target.getKeyword() + "\"";
		}
		String link = "<a href=\"" + url.toString() + "\"" + targetString + ">"
				+ text + "</a>";
		return link;
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
