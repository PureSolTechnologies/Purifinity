package com.puresoltechnologies.purifinity.server.systemmonitor.metrics;

import static org.junit.Assert.assertNotNull;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.commons.math.CompoundSIUnit;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.PhysicalParameter;
import com.puresoltechnologies.commons.math.PhysicalValue;
import com.puresoltechnologies.commons.math.Value;

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
		CompoundSIUnit siUnit = new CompoundSIUnit();
		PhysicalParameter<Double> parameter = new PhysicalParameter<Double>(
				"v", siUnit, LevelOfMeasurement.RATIO, "velocity", Double.class);
		Value<Double> value = new PhysicalValue<Double>(42.123, parameter);
		metricLogger.logEvent(value);
	}
}
