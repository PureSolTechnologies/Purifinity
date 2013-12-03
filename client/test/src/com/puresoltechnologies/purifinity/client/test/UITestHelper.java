package com.puresoltechnologies.purifinity.client.test;

import java.util.concurrent.TimeUnit;

/**
 * This class contains a collection of helper functionality.
 * 
 * @author Rick-Rainer Ludwig
 */
public class UITestHelper {

	/**
	 * This interface represents a test condition.
	 * 
	 * @author Rick-Rainer Ludwig
	 * 
	 */
	public interface Condition {

		/**
		 * This method returns whether or not the condition is valid.
		 * 
		 * @return <code>true</code> is returned if the condition is valid.
		 *         <code>false</code> is returned otherwise.
		 */
		public boolean valid();

	}

	/**
	 * 
	 * @param condition
	 * @param maxRetries
	 * @param delay
	 * @param timeUnit
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean waitFor(Condition condition, int maxRetries,
			int delay, TimeUnit timeUnit) throws InterruptedException {
		for (int retries = 0; retries < maxRetries; retries++) {
			if (condition.valid()) {
				return true;
			}
			Thread.sleep(timeUnit.toMillis(delay));
		}
		return condition.valid();
	}

	/**
	 * A private constructor to avoid instantiation.
	 */
	private UITestHelper() {
	}
}
