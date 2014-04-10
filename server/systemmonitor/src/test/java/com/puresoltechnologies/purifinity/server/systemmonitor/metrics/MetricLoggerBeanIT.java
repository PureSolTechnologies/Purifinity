package com.puresoltechnologies.purifinity.server.systemmonitor.metrics;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;

public class MetricLoggerBeanIT {

	private static Weld weld;
	private static WeldContainer weldContainer;

	@BeforeClass
	public static void initialize() {
		weld = new Weld();
		weldContainer = weld.initialize();
	}

	@AfterClass
	public static void destroy() {
		weldContainer = null;
		weld.shutdown();
		weld = null;
	}

	private MetricLogger metricLogger;

	@Before
	public void setup() {
		metricLogger = weldContainer.instance().select(MetricLogger.class)
				.get();
		assertNotNull(metricLogger);
	}

	@After
	public void tearDown() {
		((MetricLoggerBean) metricLogger).disconnect();
	}

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
		assertTrue(speed > 500);
	}
}
