package com.puresoltechnologies.purifinity.commons.test;

/**
 * This interface is used by {@link PerformanceTester} to start a single action
 * which is run in repetitive and in parallel.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface PerformanceTest<T> {

	/**
	 * This is the start method. The actual test is triggered with it.
	 * 
	 * @param threadId
	 *            is the id of the thread started by {@link PerformanceTester}.
	 * @param eventId
	 *            is the id of the event within the thread started by
	 *            {@link PerformanceTester}.
	 * @throws Exception
	 *             is thrown in any case of issue.
	 */
	public T start(int threadId, int eventId) throws Exception;

}
