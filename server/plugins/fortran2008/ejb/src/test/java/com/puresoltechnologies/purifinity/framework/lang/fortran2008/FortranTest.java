package com.puresoltechnologies.purifinity.framework.lang.fortran2008;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.plugin.fortran2008.Fortran;

public class FortranTest {

	@Test
	public void testSingleton() {
		Fortran fortran = Fortran.getInstance();
		assertNotNull(fortran);
		assertSame(fortran, Fortran.getInstance());
	}
}
