package com.puresol.reporting.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.reporting.html.Link;
import com.puresol.reporting.html.LinkTarget;

public class LinkTest {

	@Test
	public void testGetLinkURL() {
		assertEquals(
				"<a href=\"http://www.puresol-technologies.com\">http://www.puresol-technologies.com</a>",
				new Link("http://www.puresol-technologies.com").toHTML());
	}

	@Test
	public void testGetLinkURLAndText() {
		assertEquals(
				"<a href=\"http://www.puresol-technologies.com\">PureSol-Technologies</a>",
				new Link("http://www.puresol-technologies.com",
						"PureSol-Technologies").toHTML());
	}

	@Test
	public void testGetLinkURLAndTextAndTargets() {
		assertEquals(
				"<a href=\"http://www.puresol-technologies.com\" target=\"_blank\">PureSol-Technologies</a>",
				new Link("http://www.puresol-technologies.com",
						"PureSol-Technologies", LinkTarget.BLANK).toHTML());
	}
}
