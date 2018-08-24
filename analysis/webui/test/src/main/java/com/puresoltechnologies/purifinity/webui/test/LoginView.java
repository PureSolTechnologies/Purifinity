package com.puresoltechnologies.purifinity.webui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This is an abstaction for the single login
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class LoginView {

    private static final String NAME = "login";

    public static boolean isShown(WebDriver webDriver) {
	return SeleniumUtils.isShown(webDriver, null, NAME);
    }

    public static void waitForView(WebDriver webDriver) {
	SeleniumUtils.waitForView(webDriver, null, NAME);
    }

    private final WebDriver webDriver;

    public LoginView(WebDriver webDriver) {
	this.webDriver = webDriver;
    }

    public boolean isShown() {
	return LoginView.isShown(webDriver);
    }

    public void waitForView() {
	waitForView(webDriver);
    }

    public void login(String account, String password) {
	WebElement accountInput = webDriver.findElement(By.id("accountInput"));
	WebElement passwordInput = webDriver.findElement(By.id("passwordInput"));
	WebElement signInButton = webDriver.findElement(By.id("signInButton"));
	accountInput.sendKeys(account);
	passwordInput.sendKeys(password);
	signInButton.click();
    }

    public void logout() {
	WebElement logoutLink = webDriver.findElement(By.id("logoutLink"));
	logoutLink.click();
    }
}
