package com.puresoltechnologies.purifinity.webui.test;

import org.openqa.selenium.WebDriver;

/**
 * This class is an abstraction for the main page of Purifinity's WebUI.
 * 
 * @author Rick-Rainer Ludwig
 */
public class MainPage {

    /**
     * Checks whether the current open page is the Purifinity Main Page.
     * 
     * @param webDriver
     *            is the current {@link WebDriver}.
     * @return <code>true</code> is returned if the current open page is the
     *         main page. <code>false</code> is returned otherwise.
     */
    public static boolean isOpened(WebDriver webDriver) {
	String currentUrl = webDriver.getCurrentUrl();
	if (currentUrl.equals(SeleniumTestConstants.WEBUI_BASE_URL)
		|| currentUrl.equals(SeleniumTestConstants.WEBUI_BASE_URL + "/")) {
	    return true;
	}
	if (currentUrl.startsWith("http://localhost:8080/index.html")
		|| currentUrl.startsWith("http://localhost:8080/#")) {
	    return true;
	}
	return false;
    }

}
