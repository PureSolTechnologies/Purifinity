package com.puresol.coding.lang.fortran;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresol.coding.lang.fortran.metrics.SLOCMetricImpl;
import com.puresol.coding.metrics.sloc.LanguageDependedSLOCMetric;

public class FortranTest {

    @Test
    public void testSingleton() {
	Fortran fortran = Fortran.getInstance();
	assertNotNull(fortran);
	assertSame(fortran, Fortran.getInstance());
    }

    @Test
    public void checkServices() {
	Fortran fortran = Fortran.getInstance();
	LanguageDependedSLOCMetric implementation = fortran
		.getImplementation(LanguageDependedSLOCMetric.class);
	assertNotNull(implementation);
	assertEquals(SLOCMetricImpl.class, implementation.getClass());
    }

}
