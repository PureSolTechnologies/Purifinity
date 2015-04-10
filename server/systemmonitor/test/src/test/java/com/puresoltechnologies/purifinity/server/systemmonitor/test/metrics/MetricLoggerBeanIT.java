package com.puresoltechnologies.purifinity.server.systemmonitor.test.metrics;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.junit.Test;

import com.puresoltechnologies.commons.domain.GeneralValue;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.server.systemmonitor.test.AbstractSystemMonitorServerTest;
import com.puresoltechnologies.server.systemmonitor.core.api.metrics.MetricLogger;

public class MetricLoggerBeanIT extends AbstractSystemMonitorServerTest {

	@Inject
	private MetricLogger metricLogger;

	@Test
	public void testSingleEntry() {
		GeneralValue<Double> value = new GeneralValue<Double>(1.2345678,
				new ParameterWithArbitraryUnit<>("heap usage", "MB",
						LevelOfMeasurement.RATIO, "Heap memory of JVM in MB",
						Double.class));
		metricLogger.logEvent(value);
	}

	@Test
	public void testParallelFullEntries() throws InterruptedException {
		int SECOND_TO_MILLIS = 1000;
		int NUMBER_OF_THREADS = 8;
		final int NUMBER_OF_EVENTS_PER_THREAD = 125;

		ExecutorService executor = Executors
				.newFixedThreadPool(NUMBER_OF_THREADS);
		long start = System.currentTimeMillis();
		for (int threadId = 0; threadId < NUMBER_OF_THREADS; threadId++) {
			final int threadIdFinal = threadId;
			executor.submit(new Runnable() {

				@Override
				public void run() {
					String metricPrefix = "parameter" + threadIdFinal + "/";
					for (int parameterId = 0; parameterId < NUMBER_OF_EVENTS_PER_THREAD; parameterId++) {
						GeneralValue<Double> value = new GeneralValue<Double>(
								1.2345678, new ParameterWithArbitraryUnit<>(
										metricPrefix + parameterId, "MB",
										LevelOfMeasurement.RATIO,
										"Heap memory of JVM in MB",
										Double.class));
						metricLogger.logEvent(value);
					}
				}
			});
		}
		executor.shutdown();
		executor.awaitTermination(60, TimeUnit.SECONDS);
		long stop = System.currentTimeMillis();
		double speed = NUMBER_OF_THREADS * NUMBER_OF_EVENTS_PER_THREAD
				* SECOND_TO_MILLIS / (double) (stop - start);
		System.out.println("MetricLogger speed: " + speed + " metrics/s");
		assertTrue("Speed minimum not reached.", speed > 250);
	}
}
