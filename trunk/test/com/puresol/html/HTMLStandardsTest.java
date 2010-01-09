package com.puresol.html;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HTMLStandardsTest extends TestCase {

	@Test
	public void testGetLinkURL() {
		try {
			Assert
					.assertEquals(
							"<a href=\"http://www.puresol-technologies.com\">http://www.puresol-technologies.com</a>",
							HTMLStandards.getLink(new URL(
									"http://www.puresol-technologies.com")));
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
							HTMLStandards.getLink(new URL(
									"http://www.puresol-technologies.com"),
									"PureSol-Technologies"));
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
							HTMLStandards.getLink(new URL(
									"http://www.puresol-technologies.com"),
									"PureSol-Technologies", LinkTargets.BLANK));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
