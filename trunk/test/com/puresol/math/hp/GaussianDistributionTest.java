package com.puresol.math.hp;

import org.junit.Test;

import com.puresol.math.hp.GaussianDistribution;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GaussianDistributionTest extends TestCase {

	@Test
	public void testDefaultConstructor() {
		GaussianDistribution gd = new GaussianDistribution();
		Assert.assertEquals(0.0, gd.getAverage(), 1e-10);
		Assert.assertEquals(1.0, gd.getStandardDeviation(), 1e-10);
	}

	@Test
	public void testConstructor() {
		GaussianDistribution gd = new GaussianDistribution(1.23, 4.56);
		Assert.assertEquals(1.23, gd.getAverage(), 1e-10);
		Assert.assertEquals(4.56, gd.getStandardDeviation(), 1e-10);
	}

	@Test
	public void testFunction() {
		GaussianDistribution gd = new GaussianDistribution();
		Assert.assertEquals(1.0 / (gd.getStandardDeviation() * Math
				.sqrt(2.0 * Math.PI)), gd.function(0.0), 1e-10);
	}

	@Test
	public void testIntegration() {
		GaussianDistribution gd = new GaussianDistribution();
		double val = gd.integration(0.0 - 4.5 * gd.getStandardDeviation(),
				0.0 + 4.5 * gd.getStandardDeviation());
		System.out.println(val);
		double fail = 1000000.0 - val * 1000000.0;
		System.out.println(fail);
	}

	@Test
	public void testSimpleIntegration() {
		GaussianDistribution gd = new GaussianDistribution();
		double val = gd.simpleIntegration(
				0.0 - 4.5 * gd.getStandardDeviation(), 0.0 + 4.5 * gd
						.getStandardDeviation(), 1000);
		System.out.println(val);
		double fail = 1000000.0 - val * 1000000.0;
		System.out.println(fail);
	}

	@Test
	public void testIntegrations() {
		GaussianDistribution gd = new GaussianDistribution(1.0, 1.0);
		System.out.println(gd.simpleIntegration(-2.0, 4.0, 10000));
		System.out.println(gd.integration(-2.0, 4.0));
	}

	@Test
	public void testIntegrationAgainstErrorFunction() {
		GaussianDistribution gd = new GaussianDistribution(1.0, 1.0);
		System.out.println(gd.simpleIntegration(-10.0, -0.0, 1000));
		System.out.println(gd.phi(-0.0));
		Assert.assertEquals(gd.simpleIntegration(-10.0, 0.0, 1000),
				gd.phi(0.0), 1e-4);
	}
}

