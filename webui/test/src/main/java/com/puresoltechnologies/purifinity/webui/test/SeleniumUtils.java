package com.puresoltechnologies.purifinity.webui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * This class contains functionality to extract common functionality needed for
 * Selenium testing.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class SeleniumUtils {

    /**
     * Builds the AngularJS URL for the given page (name of file without
     * '.html') and view name as defined in routing including all URL path
     * parameters.
     * 
     * @param page
     * @param view
     * @return
     */
    public static String buildViewURL(String page, String view) {
	StringBuilder builder = new StringBuilder(SeleniumTestConstants.WEBUI_BASE_URL);
	builder.append('/');
	if ((page != null) && (!page.isEmpty())) {
	    builder.append(page);
	    builder.append(".html");
	}
	if ((view != null) && (!view.isEmpty())) {
	    builder.append("#/");
	    builder.append(view);
	}
	return builder.toString();
    }

    public static boolean isShown(WebDriver webDriver, String page, String view) {
	return webDriver.getCurrentUrl().equals(buildViewURL(page, view));
    }

    public static void waitForView(WebDriver webDriver, String page, String view) {
	DefaultWebDriverWait driverWait = new DefaultWebDriverWait(webDriver);
	driverWait.withMessage("Page '" + page + "' with view '" + view
		+ "' was not opened as expected within defined time of " + driverWait.getTimeoutSeconds() + " seconds.")
		.until(PurifinityCondition.isViewShow(webDriver, page, view));
    }

    public static boolean isShown(final WebDriver webDriver, final By by) {
	try {
	    WebElement element = webDriver.findElement(by);
	    return element.isDisplayed() && element.isEnabled();
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    public static void waitForElement(final WebDriver webDriver, final By by) {
	DefaultWebDriverWait driverWait = new DefaultWebDriverWait(webDriver);
	driverWait.withMessage("Element found by '" + by + "'  was not found as expected within defined time of "
		+ driverWait.getTimeoutSeconds() + " seconds.").until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver input) {
			try {
			    WebElement element = webDriver.findElement(by);
			    return element.isDisplayed() && element.isEnabled();
			} catch (NoSuchElementException | StaleElementReferenceException e) {
			    return false;
			}
		    }
		});
    }

    /**
     * Private default constructor to avoid instantiation.
     */
    private SeleniumUtils() {
    }

}
