package com.datastax.driver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import com.puresoltechnologies.purifinity.commons.test.AbstractBundleTest;

public class CassandraBundleIT extends AbstractBundleTest {

	private static File bundleJar = null;

	@BeforeClass
	public static void detectBundleJar() {
		bundleJar = new File("target/cassandra.bundle-0.1.1-SNAPSHOT.jar");
		assertTrue(bundleJar.exists());
	}

	@Test
	public void test() throws BundleException, IOException {
		Bundle bundle = installBundle(bundleJar);
		assertNotNull(bundle);
		bundle.start();
	}
}
