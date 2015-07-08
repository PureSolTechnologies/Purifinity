package com.puresoltechnologies.purifinity.webui.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SeleniumUtilsTest {

    @Test
    public void testBuildViewURLWithoutPageOrView() {
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/", SeleniumUtils.buildViewURL(null, null));
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/", SeleniumUtils.buildViewURL("", null));
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/", SeleniumUtils.buildViewURL(null, ""));
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/", SeleniumUtils.buildViewURL("", ""));
    }

    @Test
    public void testBuildViewURLWithPageOnly() {
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/page.html", SeleniumUtils.buildViewURL("page", null));
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/page.html", SeleniumUtils.buildViewURL("page", ""));
    }

    @Test
    public void testBuildViewURLWithViewOnly() {
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/#/view", SeleniumUtils.buildViewURL(null, "view"));
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/#/view", SeleniumUtils.buildViewURL("", "view"));
    }

    @Test
    public void testBuildViewURL() {
	assertEquals(SeleniumTestConstants.WEBUI_BASE_URL + "/page.html#/view",
		SeleniumUtils.buildViewURL("page", "view"));
    }

}
