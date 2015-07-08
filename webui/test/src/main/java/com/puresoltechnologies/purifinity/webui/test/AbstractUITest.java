package com.puresoltechnologies.purifinity.webui.test;

import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.puresoltechnologies.purifinity.wildfly.test.AbstractArquillianTest;

/**
 * This is an abstract test class for Arquillian driven client tests.
 * 
 * @author Rick-Rainer Ludwig
 */
@RunWith(Arquillian.class)
@RunAsClient
public abstract class AbstractUITest extends AbstractArquillianTest {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    private static WebDriver webDriver;

    @BeforeClass
    public static void initializeSelenium() {
	webDriver = new FirefoxDriver();
	webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	webDriver.get("http://localhost:8080");
    }

    @AfterClass
    public static void closeSelenium() {
	if (webDriver != null) {
	    webDriver.close();
	}
    }

    protected static WebDriver getWebDriver() {
	return webDriver;
    }

    /**
     * Returns the host name for the client connections.
     * 
     * @return A {@link String} is returned with the hostname.
     */
    protected String getHost() {
	return HOST;
    }

    /**
     * Returns the port for the client connections.
     * 
     * @return An int is returned.
     */
    protected int getPort() {
	return PORT;
    }

    /**
     * Creates an {@link CloseableHttpClient} which can be used to check REST
     * interfaces detailed with custom made content.
     * 
     * @return A {@link CloseableHttpClient} is returned.
     */
    protected CloseableHttpClient createHttpClient() {
	HttpClientBuilder clientBuilder = HttpClientBuilder.create();
	return clientBuilder.build();
    }

    /**
     * Returns the {@link HttpHost} for the HTTP client. This is a combination
     * of {@link #getHost()} and {@link #getPort()}.
     * 
     * @return A {@link HttpHost} object is returned.
     */
    protected HttpHost getHttpHost() {
	return new HttpHost(getHost(), getPort());
    }

}