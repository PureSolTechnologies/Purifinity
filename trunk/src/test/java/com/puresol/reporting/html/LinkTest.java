package com.puresol.reporting.html;

import org.junit.Test;

import com.puresol.reporting.html.Link;
import com.puresol.reporting.html.LinkTarget;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LinkTest extends TestCase {

	@Test
	public void testGetLinkURL() {
		Assert
				.assertEquals(
						"<a href=\"http://www.puresol-technologies.com\">http://www.puresol-technologies.com</a>",
						new Link("http://www.puresol-technologies.com")
								.toHTML());
	}

	@Test
	public void testGetLinkURLAndText() {
		Assert
				.assertEquals(
						"<a href=\"http://www.puresol-technologies.com\">PureSol-Technologies</a>",
						new Link("http://www.puresol-technologies.com",
								"PureSol-Technologies").toHTML());
	}

	@Test
	public void testGetLinkURLAndTextAndTargets() {
		Assert
				.assertEquals(
						"<a href=\"http://www.puresol-technologies.com\" target=\"_blank\">PureSol-Technologies</a>",
						new Link("http://www.puresol-technologies.com",
								"PureSol-Technologies", LinkTarget.BLANK)
								.toHTML());
	}
}
