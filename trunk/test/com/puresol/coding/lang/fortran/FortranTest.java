package com.puresol.coding.lang.fortran;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class FortranTest extends TestCase {

    @Test
    public void testSingleton() {
	Fortran fortran1 = Fortran.getInstance();
	Assert.assertNotNull(fortran1);
	Fortran fortran2 = Fortran.getInstance();
	Assert.assertNotNull(fortran2);
	Assert.assertSame(fortran1, fortran2);
    }

    @Test
    public void testGetName() {
	Assert.assertEquals("Fortran", Fortran.getInstance().getName());
    }

    @Test
    public void testIsObjectOriented() {
	Assert.assertEquals(false, Fortran.getInstance().isObjectOriented());
    }

}
