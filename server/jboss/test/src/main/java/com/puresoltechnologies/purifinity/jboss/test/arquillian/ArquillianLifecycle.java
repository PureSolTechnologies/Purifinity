package com.puresoltechnologies.purifinity.jboss.test.arquillian;

import java.lang.reflect.Method;

import org.jboss.arquillian.container.spi.event.KillContainer;
import org.jboss.arquillian.container.spi.event.SetupContainer;
import org.jboss.arquillian.container.spi.event.StartContainer;
import org.jboss.arquillian.container.spi.event.StopContainer;
import org.jboss.arquillian.container.spi.event.container.AfterDeploy;
import org.jboss.arquillian.container.spi.event.container.AfterKill;
import org.jboss.arquillian.container.spi.event.container.AfterSetup;
import org.jboss.arquillian.container.spi.event.container.AfterStart;
import org.jboss.arquillian.container.spi.event.container.AfterStop;
import org.jboss.arquillian.container.spi.event.container.AfterUnDeploy;
import org.jboss.arquillian.container.spi.event.container.BeforeDeploy;
import org.jboss.arquillian.container.spi.event.container.BeforeKill;
import org.jboss.arquillian.container.spi.event.container.BeforeSetup;
import org.jboss.arquillian.container.spi.event.container.BeforeStart;
import org.jboss.arquillian.container.spi.event.container.BeforeStop;
import org.jboss.arquillian.container.spi.event.container.BeforeUnDeploy;
import org.jboss.arquillian.container.test.impl.client.deployment.event.GenerateDeployment;
import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.arquillian.test.spi.TestClass;
import org.jboss.arquillian.test.spi.event.suite.AfterSuite;
import org.jboss.arquillian.test.spi.event.suite.BeforeSuite;

/**
 * This class is used to enhance Arquillian with an additional life cycle to
 * enhance Arquillian tests.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ArquillianLifecycle {

	/**
	 * Use this method to do stuff that should run before the suite
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void beforeSuiteObserver(@Observes BeforeSuite event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run after the suite
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void afterSuiteObserver(@Observes AfterSuite event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run before container setup
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void beforeSetupObserver(@Observes BeforeSetup event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run after container setup
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void afterSetupObserver(@Observes AfterSetup event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run after arquillian start
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void afterStartObserver(@Observes AfterStart event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run before container stop
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void beforeStopObserver(@Observes BeforeStop event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run after container stop
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void afterStopObserver(@Observes AfterStop event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run before container kill
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void beforeKillObserver(@Observes BeforeKill event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run after container kill
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void afterKillObserver(@Observes AfterKill event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run when the deployment is
	 * generated
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void generateDeploymentObserver(
			@Observes GenerateDeployment event) throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run when the container is set up
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void setupContainerObserver(@Observes SetupContainer event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run when the container starts
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void startContainerObserver(@Observes StartContainer event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run when the container stops
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void stopContainerObserver(@Observes StopContainer event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run when the container is killed
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void killContainerObserver(@Observes KillContainer event)
			throws Throwable {
	}

	/**
	 * Use this method to do stuff that should run before arquillian starts
	 * 
	 * @param event
	 *            The observed event
	 * @throws Throwable
	 *             Exception while handling the event
	 */
	public final void beforeStartObserver(@Observes BeforeStart event)
			throws Throwable {
	}

	/**
	 * Execution method delegating the annotated method for "BeforeDeploy"
	 * 
	 * @param event
	 *            The observed event
	 * @param testClass
	 *            The testclass with the annotation
	 */
	public final void executeBeforeDeploy(@Observes BeforeDeploy event,
			TestClass testClass) {
		execute(testClass.getMethods(BeforeDeployment.class));
	}

	/**
	 * Execution method delegating the anotated method for "AfterDeploy"
	 * 
	 * @param event
	 *            The observed event
	 * @param testClass
	 *            The testclass with the annotation
	 */
	public final void executeAfterDeploy(@Observes AfterDeploy event,
			TestClass testClass) {
		execute(testClass.getMethods(AfterDeployment.class));
	}

	/**
	 * Execution method delegating the anotated method for "BeforeUnDeploy"
	 * 
	 * @param event
	 *            The observed event
	 * @param testClass
	 *            The testclass with the annotation
	 */
	public final void executeBeforeUnDeploy(@Observes BeforeUnDeploy event,
			TestClass testClass) {
		execute(testClass.getMethods(BeforeUnDeployment.class));
	}

	/**
	 * Execution method delegating the anotated method for "AfterUnDeploy"
	 * 
	 * @param event
	 *            The observed event
	 * @param testClass
	 *            The testclass with the annotation
	 */
	public final void executeAfterUnDeploy(@Observes AfterUnDeploy event,
			TestClass testClass) {
		execute(testClass.getMethods(AfterUnDeployment.class));
	}

	/**
	 * Executes a delegation method form annotations
	 * 
	 * @param methods
	 *            Methods to run
	 */
	private void execute(Method[] methods) {
		if (methods == null) {
			return;
		}
		for (Method method : methods) {
			try {
				method.invoke(null);
			} catch (Exception e) {
				throw new UnsupportedOperationException(
						"Process lifecycle for method: " + method, e);
			}
		}
	}
}
