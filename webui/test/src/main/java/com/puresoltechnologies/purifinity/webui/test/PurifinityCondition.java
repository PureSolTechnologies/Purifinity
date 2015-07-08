package com.puresoltechnologies.purifinity.webui.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PurifinityCondition {

    public static ExpectedCondition<Boolean> isViewShow(final WebDriver webDriver, final String page,
	    final String view) {
	return new ExpectedCondition<Boolean>() {
	    public Boolean apply(WebDriver input) {
		return SeleniumUtils.isShown(webDriver, page, view);
	    }
	};
    }

}
