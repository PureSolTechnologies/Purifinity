package com.puresoltechnologies.purifinity.webui.test;

/**
 * This method contains constants use all over Selenium tests.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class SeleniumTestConstants {

    public static final String WEBUI_HOST = "localhost";
    public static final int WEBUI_PORT = 8080;
    public static final String WEBUI_BASE_URL = "http://" + WEBUI_HOST + ":" + WEBUI_PORT;

    /**
     * Private default constructor to avoid instantiation.
     */
    private SeleniumTestConstants() {
    }

}
