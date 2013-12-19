package com.puresoltechnologies.purifinity.jboss.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Filter;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.objenesis.ObjenesisStd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.jboss.test.arquillian.EnterpriseArchiveUtils;
import com.puresoltechnologies.purifinity.jboss.test.arquillian.ResourceFilterForSuffixExclusion;
import com.puresoltechnologies.purifinity.jboss.test.arquillian.ResourceUtils;
import com.puresoltechnologies.purifinity.jboss.test.arquillian.WebArchiveUtils;
import com.puresoltechnologies.purifinity.jboss.test.mockito.Mock;
import com.puresoltechnologies.purifinity.jboss.test.mockito.MockitoExtension;

/**
 * This is an abstract test class for Arquillian driven server tests.
 * 
 * @author Rick-Rainer Ludwig
 */
@RunWith(Arquillian.class)
public abstract class AbstractServerTest {
	/** The name of the test archive. */
	public static final String TEST_JAR_NAME = "test.jar";

	private static final String DEPLOYMENT_STRUCTURE_FILE = "META-INF/jboss-deployment-structure.xml";

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractServerTest.class);

	private static final String DUMMY_WAR = "dummy.war";
	private static final String WAR_EXTENSION = ".war";
	private static final String EAR_EXTENSION = ".ear";
	private static final String APP_TARGET = "../app/target";
	private static final String TARGET = "target/";
	private static final String TEST_RESOURCES = "src/test/resources/";

	/**
	 * The {@link MockitoExtension}.
	 */
	@Inject
	private MockitoExtension mockitoExtension;

	/**
	 * Creates the EAR for the server-test.
	 * 
	 * @return the EAR
	 */
	@Deployment
	public static EnterpriseArchive createArchive() {
		// first, locate the paths to the EAR and the -test.jar of the actual
		// facility
		File testJarDir = new File(TARGET);
		File appDir = new File(APP_TARGET);

		if (!appDir.exists() || !testJarDir.exists()) {
			String msg = "Build Application with Maven first!";
			LOG.error(msg);
			throw new IllegalStateException(msg);
		}

		// now, search for the EAR and the -test.jar
		File[] earFiles = appDir.listFiles(new FilenameSuffixFilter(
				EAR_EXTENSION));
		if (earFiles.length != 1) {
			String msg = "Can not find EAR of the project. Build Application with Maven first!";
			LOG.error(msg);
			throw new IllegalStateException(msg);
		}

		File earFile = earFiles[0];

		// create the EAR and JAR from the file
		EnterpriseArchive ear = ShrinkWrap.createFromZipFile(
				EnterpriseArchive.class, earFile);

		// create a JAR with our test class
		JavaArchive testJar = createTestJar();

		// add the test.jar to the EAR, so all test will be deployed
		ear.addAsModule(testJar);

		// replace app deployment descriptor with the provided by the test
		File testDeploymentDesc = new File(TEST_RESOURCES + "/"
				+ DEPLOYMENT_STRUCTURE_FILE);
		if (testDeploymentDesc.exists()) {
			Node deplStructNode = ear.get(DEPLOYMENT_STRUCTURE_FILE);
			if (deplStructNode != null) {
				ear.delete(deplStructNode.getPath());
				ear.addAsApplicationResource(testDeploymentDesc);
			}
		}

		try {

			// now it gets tricky. If there is still a WAR in the EAR, its fine,
			// but if not, we need to add a dummy.war, because of an Arquillian
			// bug
			Map<ArchivePath, Node> contents = ear
					.getContent(new Filter<ArchivePath>() {

						@Override
						public boolean include(ArchivePath path) {
							return path.get().endsWith(WAR_EXTENSION);
						}
					});

			boolean webModuleMissing = contents.isEmpty();

			// there is no WAR in the EAR, so add a dummy.war
			if (webModuleMissing) {

				// add the dummy WAR to the EAR
				WebArchive dummyWar = WebArchiveUtils
						.createWebArchiveWithBeanXML(DUMMY_WAR);
				ear.addAsModule(dummyWar);
			}

			// modify existing application.xml, because we added a new JAR with
			// the tests

			StringBuilder sb = new StringBuilder("<module><ejb>"
					+ testJar.getName() + "</ejb></module>");

			// if we added a new WAR, then also add this to the application.xml
			if (webModuleMissing) {
				sb.append("<module><web><web-uri>dummy.war</web-uri><context-root>dummy</context-root></web></module>");
			}

			// create the modified application.xml
			sb.append("</application>");
			String content = EnterpriseArchiveUtils.getApplicationXml(ear);
			content = content.replaceFirst("</application>", sb.toString());

			// set the new application.xml
			EnterpriseArchiveUtils.setApplicationXml(ear, content);

		} catch (IOException e) {
			String msg = "Could not modify application.xml";
			LOG.error(msg);
			throw new IllegalStateException(msg);
		}

		// return the EAR ready for deployment
		return ear;
	}

	/**
	 * Resets all mocks after test execution.
	 */
	@Before
	public void resetMocks() {
		if (mockitoExtension != null) {
			mockitoExtension.resetMocks();
		}
	}

	/**
	 * Creates the JAR file containing the test infrastructure.
	 * 
	 * @return The jar file.
	 */
	private static JavaArchive createTestJar() {
		JavaArchive testJar = ShrinkWrap.create(JavaArchive.class,
				TEST_JAR_NAME);

		// add resources
		File testResourcesDir = new File(TEST_RESOURCES);
		if (testResourcesDir.exists()) {
			ResourceUtils.addResources(testJar, testResourcesDir,
					new ResourceFilterForSuffixExclusion(
							DEPLOYMENT_STRUCTURE_FILE));
		}

		// add common test class to the test.jar
		testJar.addClass(AbstractServerTest.class);
		// add Mockito
		testJar.addPackages(true, Mockito.class.getPackage());
		testJar.addPackages(true, ObjenesisStd.class.getPackage());
		testJar.addPackage(Mock.class.getPackage());
		// add CDI extension descriptors
		addServices(testJar, Extension.class);
		return testJar;
	}

	/**
	 * Merges the service loader descriptors for the given class and adds them
	 * to the jar archive.
	 * 
	 * @param jar
	 *            The jar archive.
	 * @param serviceClass
	 *            The service class.
	 */
	private static void addServices(JavaArchive jar, Class<?> serviceClass) {
		try {
			File file = File.createTempFile(Extension.class.getName(), "");
			file.deleteOnExit();
			Enumeration<URL> resources = AbstractServerTest.class
					.getClassLoader().getResources(
							"META-INF/services/" + serviceClass.getName());
			List<String> extensions = new ArrayList<String>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				@SuppressWarnings("unchecked")
				List<String> lines = IOUtils.readLines(resource.openStream());
				extensions.addAll(lines);
			}
			try (FileWriter writer = new FileWriter(file)) {
				IOUtils.writeLines(extensions, null, writer);
				writer.close();
				jar.addAsManifestResource(file,
						"services/" + serviceClass.getName());
			}
		} catch (IOException e) {
			throw new IllegalStateException("Cannot read files", e);
		}
	}

}
