package com.puresol.hpmath;

import org.junit.Test;

import com.puresol.hpmath.GaussianDistribution;

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
	double val =
		gd.integration(0.0 - 6.0 * gd.getStandardDeviation(),
			0.0 + 6.0 * gd.getStandardDeviation());
	System.out.println(val);
	double fail = val * 1000000.0;
	System.out.println(fail);
    }

    @Test
    public void testConfidence() {
	Assert.assertEquals(0.682689492137, GaussianDistribution
		.confidence(1.0), 1e-7);
	Assert.assertEquals(0.954499736104, GaussianDistribution
		.confidence(2.0), 1e-7);
	Assert.assertEquals(0.997300203937, GaussianDistribution
		.confidence(3.0), 1e-7);
	Assert.assertEquals(0.999936657516, GaussianDistribution
		.confidence(4.0), 1e-7);
	Assert.assertEquals(0.999999426697, GaussianDistribution
		.confidence(5.0), 1e-7);
	Assert.assertEquals(0.999999998027, GaussianDistribution
		.confidence(6.0), 1e-7);
    }

    @Test
    public void testSigmaLevel() {
	Assert.assertEquals(1.0, GaussianDistribution
		.sigmaLevel(0.682689492137), 1e-7);
	Assert.assertEquals(2.0, GaussianDistribution
		.sigmaLevel(0.954499736104), 1e-7);
	Assert.assertEquals(3.0, GaussianDistribution
		.sigmaLevel(0.997300203937), 1e-7);
	Assert.assertEquals(4.0, GaussianDistribution
		.sigmaLevel(0.999936657516), 1e-7);
	Assert.assertEquals(5.0, GaussianDistribution
		.sigmaLevel(0.999999426697), 1e-7);
	Assert.assertEquals(6.0, GaussianDistribution
		.sigmaLevel(0.999999997027), 1e-7);
    }
}
