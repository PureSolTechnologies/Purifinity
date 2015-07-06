package com.puresoltechnologies.purifinity.webui.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class StartScreenIT extends AbstractUITest {

    private static WebDriver webDriver;

    @BeforeClass
    public static void initialize() {
	webDriver = getWebDriver();
    }

    @Test
    public void test() {
	assertEquals("Window title of Purifinity is not correct.", "Purifinity Web Client", webDriver.getTitle());
    }

}
