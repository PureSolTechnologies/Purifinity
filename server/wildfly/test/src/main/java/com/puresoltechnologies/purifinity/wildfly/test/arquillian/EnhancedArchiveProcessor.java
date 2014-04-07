package com.puresoltechnologies.purifinity.wildfly.test.arquillian;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.arquillian.container.test.spi.client.deployment.ApplicationArchiveProcessor;
import org.jboss.arquillian.test.spi.TestClass;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Filter;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.asset.ArchiveAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;

/**
 * This class is the enhanced deployment process for Arquillian.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EnhancedArchiveProcessor implements ApplicationArchiveProcessor {

	private static final String SIGNATURE_MISMATCH_ERROR = "Methods annotated with @"
			+ EnhanceDeployment.class.getSimpleName()
			+ " must match signature: public static methodName("
			+ EnterpriseArchive.class.getSimpleName()
			+ "|"
			+ JavaArchive.class.getSimpleName() + ")";

	private static final String ENHANCE_ERROR = "Deployment could not be enhanced";

	private static final Logger LOG = LoggerFactory
			.getLogger(EnhancedArchiveProcessor.class);

	@Override
	public void process(Archive<?> ear, TestClass testClass) {
		if (ear != null) {
			Node testJarNode = ear.get(AbstractServerTest.TEST_JAR_NAME);
			if (testJarNode != null) {
				JavaArchive testArchive = (JavaArchive) ((ArchiveAsset) testJarNode
						.getAsset()).getArchive();
				Class<?> javaClass = testClass.getJavaClass();
				// add test class it-self
				testArchive.addClass(javaClass);

				// add all classes within the test package, which are no
				// integration test classes
				Package testPackage = javaClass.getPackage();
				// if (!testPackage.getName().endsWith("server")) {
				// String msg = "The test "
				// + javaClass
				// +
				// " is not in the expected \"[facility-test].server\" package";
				// LOG.error(msg);
				// throw new IllegalStateException(msg);
				// }
				testArchive.addPackages(true, new NonIntegrationTestFilter(),
						testPackage);

				// add additional deployment artefacts, defined by the test
				// class
				provideAdditionalDeployments(ear, testArchive, testClass);
			} else {
				// add additional deployment artefacts, defined by the test
				// class
				provideAdditionalDeployments(ear, null, testClass);
			}
		}
	}

	/**
	 * Performs additional deployments for the given EAR or test archive.
	 * 
	 * @param ear
	 *            the EAR
	 * @param testArchive
	 *            the test archive
	 * @param testClass
	 *            the test class
	 */
	private void provideAdditionalDeployments(Archive<?> ear,
			JavaArchive testArchive, TestClass testClass) {
		// invoke all methods, marked with AdditionalDeployment
		Method[] deploymentMethods = testClass
				.getMethods(EnhanceDeployment.class);
		for (Method method : deploymentMethods) {
			if (!matchSignature(method)) {
				LOG.error(SIGNATURE_MISMATCH_ERROR);
				throw new IllegalStateException(SIGNATURE_MISMATCH_ERROR);
			}

			Class<?>[] params = method.getParameterTypes();
			try {
				if (testArchive != null
						&& JavaArchive.class.isAssignableFrom(params[0])) {
					method.invoke(null, testArchive);
				} else if (EnterpriseArchive.class.isAssignableFrom(params[0])) {
					method.invoke(null, ear);
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException(ENHANCE_ERROR, e);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException(ENHANCE_ERROR, e);
			} catch (InvocationTargetException e) {
				throw new IllegalStateException(ENHANCE_ERROR, e);
			}
		}
	}

	/**
	 * Returns <code>true</code> if the signature of the method matches the
	 * expected {@link EnhanceDeployment} signature.
	 * 
	 * @param method
	 *            the method to check
	 * @return <code>true</code> if signature is ok
	 */
	private boolean matchSignature(Method method) {
		Class<?>[] params = method.getParameterTypes();
		return Modifier.isPublic(method.getModifiers())
				&& Modifier.isStatic(method.getModifiers())
				&& params != null
				&& params.length == 1
				&& (JavaArchive.class.isAssignableFrom(params[0]) || EnterpriseArchive.class
						.isAssignableFrom(params[0]));
	}

	/**
	 * Filters all Java classes, which are no IntegrationTests.
	 */
	private static class NonIntegrationTestFilter implements
			Filter<ArchivePath> {
		/** Don't match classes ending with IT (or inner classes of them). */
		private static final Pattern PATTERN = Pattern
				.compile("^.*IT(?:\\$.*)?\\.class$");

		@Override
		public boolean include(ArchivePath path) {
			Matcher matcher = PATTERN.matcher(path.get());
			return !matcher.matches();
		}

	}

}
