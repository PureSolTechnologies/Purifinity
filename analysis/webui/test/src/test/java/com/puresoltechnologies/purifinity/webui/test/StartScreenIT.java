package com.puresoltechnologies.purifinity.webui.test;

import static com.puresoltechnologies.purifinity.webui.test.SeleniumUtils.waitForElement;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StartScreenIT extends AbstractUITest {

    @Test
    public void testFirefox() {
	WebDriver webDriver = getFirefoxDriver();
	runTest(webDriver);
    }

    @Test
    public void testChrome() {
	WebDriver webDriver = getChromeDriver();
	runTest(webDriver);
    }

    private void runTest(WebDriver webDriver) {
	try {
	    webDriver.get(SeleniumTestConstants.WEBUI_BASE_URL);
	    assertThat("Window title is different than expected.", webDriver.getTitle(),
		    is(equalTo("Purifinity Web Client")));
	    LoginView loginView = new LoginView(webDriver);
	    loginView.waitForView();
	    waitForElement(webDriver, By.id("loginLink"));
	    loginView.login("administrator", "password");
	    waitForElement(webDriver, By.id("logoutLink"));
	    loginView.logout();
	    waitForElement(webDriver, By.id("loginLink"));
	} finally {
	    webDriver.close();
	}
    }

}
