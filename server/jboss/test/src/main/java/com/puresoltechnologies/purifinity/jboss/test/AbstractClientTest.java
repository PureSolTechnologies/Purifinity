package com.puresoltechnologies.purifinity.jboss.test;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.runner.RunWith;

/**
 * This is an abstract test class for Arquillian driven client tests.
 * 
 * @author Rick-Rainer Ludwig
 */
@RunWith(Arquillian.class)
@RunAsClient
public abstract class AbstractClientTest {

	private static final File APPLICATION_DIRECTORY = new File("../app/target");

	@Deployment
	public static EnterpriseArchive createArchive() {
		if (!APPLICATION_DIRECTORY.exists()) {
			throw new IllegalStateException("The directory '"
					+ APPLICATION_DIRECTORY + "' does not exist! "
					+ "There is maybe an issue with the project setup.");
		}
		File[] earFiles = APPLICATION_DIRECTORY
				.listFiles(new FilenameSuffixFilter(".ear"));
		if (earFiles.length == 0) {
			throw new IllegalStateException(
					"Cannot find EAR file! Application needs to be built.");
		}
		if (earFiles.length > 1) {
			throw new IllegalStateException(
					"Multiple EAR files were found! There is only on EAR file allow. "
							+ "Maybe this is an issue with the project setup.");
		}
		return ShrinkWrap.createFromZipFile(EnterpriseArchive.class,
				earFiles[0]);
	}

}