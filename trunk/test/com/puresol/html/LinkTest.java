package com.puresol.html;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LinkTest extends TestCase {

	@Test
	public void testGetLinkURL() {
		try {
			Assert
					.assertEquals(
							"<a href=\"http://www.puresol-technologies.com\">http://www.puresol-technologies.com</a>",
							new Link(new URL(
									"http://www.puresol-technologies.com"))
									.toHTMLText());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testGetLinkURLAndText() {
		try {
			Assert
					.assertEquals(
							"<a href=\"http://www.puresol-technologies.com\">PureSol-Technologies</a>",
							new Link(new URL(
									"http://www.puresol-technologies.com"),
									"PureSol-Technologies").toHTMLText());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testGetLinkURLAndTextAndTargets() {
		try {
			Assert
					.assertEquals(
							"<a href=\"http://www.puresol-technologies.com\" target=\"_blank\">PureSol-Technologies</a>",
							new Link(new URL(
									"http://www.puresol-technologies.com"),
									"PureSol-Technologies", LinkTarget.BLANK)
									.toHTMLText());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
