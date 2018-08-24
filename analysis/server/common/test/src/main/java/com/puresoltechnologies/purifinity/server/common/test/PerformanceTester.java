package com.puresoltechnologies.purifinity.server.common.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PerformanceTester {

	public static <T> PerformanceTestResult<T> runPerformanceTest(
			int numberOfThreads, final int actionsPerThread,
			final PerformanceTest<T> test) throws InterruptedException {
		ExecutorService executor = Executors
				.newFixedThreadPool(numberOfThreads);

		long start = System.currentTimeMillis();
		List<Future<Map<Integer, T>>> futures = new ArrayList<>();
		for (int threadId = 0; threadId < numberOfThreads; threadId++) {
			final int finalThreadId = threadId;
			Future<Map<Integer, T>> future = executor
					.submit(new Callable<Map<Integer, T>>() {
						@Override
						public Map<Integer, T> call() throws Exception {
							Map<Integer, T> threadResults = new HashMap<>();
							for (int eventId = 0; eventId < actionsPerThread; eventId++) {
								threadResults.put(eventId,
										test.start(finalThreadId, eventId));
							}
							return threadResults;
						}
					});
			futures.add(future);
		}
		executor.shutdown();
		executor.awaitTermination(60, TimeUnit.SECONDS);
		long stop = System.currentTimeMillis();

		Map<Integer, Map<Integer, T>> results = new HashMap<>();
		List<Throwable> throwables = new ArrayList<>();
		for (int threadId = 0; threadId < futures.size(); threadId++) {
			try {
				Future<Map<Integer, T>> future = futures.get(threadId);
				Map<Integer, T> threadResults = future.get();
				results.put(threadId, threadResults);
			} catch (ExecutionException e) {
				throwables.add(e.getCause());
			}
		}
		return new PerformanceTestResult<T>(new Date(start), new Date(stop),
				numberOfThreads, actionsPerThread, results, throwables);
	}
}
