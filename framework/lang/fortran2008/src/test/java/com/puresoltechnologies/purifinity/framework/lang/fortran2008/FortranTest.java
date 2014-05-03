package com.puresoltechnologies.purifinity.framework.lang.fortran2008;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.LanguageDependedSLOCMetric;
import com.puresoltechnologies.purifinity.framework.lang.fortran2008.Fortran;
import com.puresoltechnologies.purifinity.framework.lang.fortran2008.metrics.SLOCMetricImpl;

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
