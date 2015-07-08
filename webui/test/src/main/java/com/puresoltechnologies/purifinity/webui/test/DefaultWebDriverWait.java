package com.puresoltechnologies.purifinity.webui.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is the default wait for Selenium WebUI tests. The standardized wait
 * times shall a concise testing.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class DefaultWebDriverWait extends WebDriverWait {

    private static final int TIMEOUT_SECONDS = 10;
    private static final int POLL_MILLISECONDS = 100;

    public DefaultWebDriverWait(WebDriver driver) {
	super(driver, TIMEOUT_SECONDS, POLL_MILLISECONDS);
    }

    public int getTimeoutSeconds() {
	return TIMEOUT_SECONDS;
    }
}
