package com.puresol.purifinity.trees;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import com.puresol.purifinity.commons.test.AbstractBundleTest;

public class TreesBundleTest extends AbstractBundleTest {

	@BeforeClass
	public static void installNeededBundles() throws BundleException,
			IOException {
		InputStream bundleStream = TreesBundleTest.class
				.getResourceAsStream("/bundles/org.slf4j.api_1.6.4.v20120130-2120.jar");
		assertNotNull(bundleStream);
		try {
			Bundle bundle = installBundle(bundleStream);
			bundle.start();
		} finally {
			bundleStream.close();
		}
	}

	@Test
	public void test() throws BundleException, IOException,
			InterruptedException {
		File bundleFile = findBundleJar();
		Bundle bundle = installBundle(bundleFile);
		bundle.start();
		Thread.sleep(1000);
		bundle.stop();
	}

}
