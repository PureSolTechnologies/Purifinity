package com.puresol.math.hp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.math.hp.NormalizedGaussianDistribution;

public class NormalizedGaussianDistributionTest {

	@Test
	public void testFunction() {
		NormalizedGaussianDistribution gd = new NormalizedGaussianDistribution();
		assertEquals(1.0 / Math.sqrt(2.0 * Math.PI), gd.function(0.0), 1e-10);
	}

	@Test
	public void testIntegration() {
		NormalizedGaussianDistribution gd = new NormalizedGaussianDistribution();
		double val = gd.integration(-4.5, +4.5);
		System.out.println(val);
		double fail = 1000000.0 - val * 1000000.0;
		System.out.println(fail);
	}

	@Test
	public void testSimpleIntegration() {
		NormalizedGaussianDistribution gd = new NormalizedGaussianDistribution();
		double val = gd.simpleIntegration(-4.5, +4.5, 1000);
		System.out.println(val);
		double fail = 1000000.0 - val * 1000000.0;
		System.out.println(fail);
	}

	@Test
	public void testConfidence() {
		assertEquals(0.682689492137,
				NormalizedGaussianDistribution.confidence(1.0), 1e-7);
		assertEquals(0.954499736104,
				NormalizedGaussianDistribution.confidence(2.0), 1e-7);
		assertEquals(0.997300203937,
				NormalizedGaussianDistribution.confidence(3.0), 1e-7);
		assertEquals(0.999936657516,
				NormalizedGaussianDistribution.confidence(4.0), 1e-7);
		assertEquals(0.999999426697,
				NormalizedGaussianDistribution.confidence(5.0), 1e-7);
		assertEquals(0.999999998027,
				NormalizedGaussianDistribution.confidence(6.0), 1e-7);
	}

	@Test
	public void testSigmaLevel() {
		assertEquals(1.0,
				NormalizedGaussianDistribution.sigmaLevel(0.682689492137), 1e-7);
		assertEquals(2.0,
				NormalizedGaussianDistribution.sigmaLevel(0.954499736104), 1e-7);
		assertEquals(3.0,
				NormalizedGaussianDistribution.sigmaLevel(0.997300203937), 1e-7);
		/*
		 * A higher level than 4.0 causes rounding effects... :-(
		 */
		assertEquals(4.0,
				NormalizedGaussianDistribution.sigmaLevel(0.999936657516), 1e-6);
		assertEquals(5.0,
				NormalizedGaussianDistribution.sigmaLevel(0.999999426697), 1e-4);
		assertEquals(6.0,
				NormalizedGaussianDistribution.sigmaLevel(0.999999997027), 1e-1); /*
																				 * double
																				 * precision
																				 * limitation
																				 * !
																				 * :
																				 * -
																				 * (
																				 */
	}
}
