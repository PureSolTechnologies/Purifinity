package com.puresoltechnologies.purifinity.commons.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

public abstract class AbstractBundleTest {

	private static final String STORAGE_DIRECTORY = "target/osgi.cache";

	private static Framework osgiFramework = null;

	@BeforeClass
	public static void startOSGiContainer() throws BundleException, IOException {
		assertNull("OSGi framework is expected to be stopped.", osgiFramework);
		removeStorageDirectory();
		Map<String, String> map = new HashMap<>();
		map.put(Constants.FRAMEWORK_STORAGE, STORAGE_DIRECTORY);
		ServiceLoader<FrameworkFactory> frameworkFactory = ServiceLoader
				.load(FrameworkFactory.class);
		osgiFramework = frameworkFactory.iterator().next().newFramework(map);
		osgiFramework.start();
	}

	/**
	 * This method removes the storage directory to clean the OSGi environment.
	 * 
	 * @throws IOException
	 */
	public static void removeStorageDirectory() throws IOException {
		assertNull("OSGi framework is expected to be stopped.", osgiFramework);
		File directory = new File(STORAGE_DIRECTORY);
		if (directory.exists()) {
			assertTrue("Storage directory '" + directory
					+ "' is not a directory!", directory.isDirectory());
			FileUtils.deleteDirectory(directory);
		}
	}

	@AfterClass
	public static void stopOSGiContainer() throws BundleException,
			InterruptedException {
		assertNotNull("OSGi framework is expected to be started!",
				osgiFramework);
		osgiFramework.stop();
		osgiFramework.waitForStop(0);
		osgiFramework = null;
	}

	/**
	 * This method looks for the bundle of the current Maven module. It is
	 * assumed that this class is used in an appropriate xxx.text or xxx.bundle
	 * module. The bundle JAR is looked up in ../bundle/target directory.
	 * 
	 * An JUnit exception is thrown if an assert goes fail on the way.
	 * 
	 * @return A {@link File} is returned containing the file found.
	 */
	public static File findBundleJar() {
		String userDir = System.getProperty("user.dir");
		assertNotNull("No user.dir property set.", userDir);
		File bundleModuleDirectory = new File(new File(userDir, ".."), "bundle");
		assertTrue("Bundle directory '" + bundleModuleDirectory
				+ "' does not exist!", bundleModuleDirectory.exists());
		assertTrue("Bundle directory '" + bundleModuleDirectory
				+ "' is not a directory!", bundleModuleDirectory.isDirectory());
		File bundleModuleTargetDirectory = new File(bundleModuleDirectory,
				"target");
		assertTrue("Bundle target directory '" + bundleModuleTargetDirectory
				+ "' does not exist! Run a Maven build to create it.",
				bundleModuleTargetDirectory.exists());
		assertTrue("Bundle target directory '" + bundleModuleTargetDirectory
				+ "' is not a directory!",
				bundleModuleTargetDirectory.isDirectory());
		File[] jarFiles = bundleModuleTargetDirectory
				.listFiles(new FilenameFilter() {

					@Override
					public boolean accept(File directory, String fileName) {
						return fileName.endsWith(".jar");
					}
				});
		assertEquals("Only one JAR file was expected in target directory '"
				+ bundleModuleTargetDirectory + "'", 1, jarFiles.length);
		return jarFiles[0];
	}

	/**
	 * This method returns the current bundle context which is the context of
	 * the current OSGi framework.
	 * 
	 * @return A {@link BundleContext} is returned containing the OSGi framework
	 *         context.
	 */
	public static BundleContext getBundleContext() {
		return osgiFramework.getBundleContext();
	}

	/**
	 * This method installs a new bundle specified by an {@link File}.
	 * 
	 * @param bundleFile
	 *            is a {@link File} which contains the path to the bundle JAR
	 *            which is to be installed.
	 * @return A {@link Bundle} is returned which contains the information of
	 *         the bundle which was installed.
	 * @throws BundleException
	 *             is thrown in cases of bundle issues.
	 * @throws IOException
	 *             in cases of IO issues like file not found and file read
	 *             errors.
	 */
	public static Bundle installBundle(File bundleFile) throws BundleException,
			IOException {
		InputStream inputStream = new FileInputStream(bundleFile);
		try {
			return installBundle(inputStream);
		} finally {
			inputStream.close();
		}
	}

	/**
	 * This method installs a new bundle specified by an {@link InputStream}.
	 * 
	 * @param inputStream
	 *            is an {@link InputStream} which contains the bundle JAR which
	 *            is to be installed.
	 * @return A {@link Bundle} is returned which contains the information of
	 *         the bundle which was installed.
	 * @throws BundleException
	 *             is thrown in cases of bundle issues.
	 */
	public static Bundle installBundle(InputStream inputStream)
			throws BundleException {
		Bundle bundle = getBundleContext().installBundle("TestBundle",
				inputStream);
		assertNotNull("Bundle couldn not be installed!", bundle);
		return bundle;
	}
}
