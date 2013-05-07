package com.puresol.license.api;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.puresol.license.api.exception.ExpiredLicenseException;

public class LicenseManagerImplTest {

    private final LicenseManagerImpl licenseManager = new LicenseManagerImpl();

    @Test
    public void testCheckExpirationNotExpired() {
	licenseManager.checkExpiration(new Date());

	Calendar calendar = GregorianCalendar.getInstance();
	calendar.set(2013, 4, 31, 23, 59);
	licenseManager.checkExpiration(calendar.getTime());
    }

    @Test(expected = ExpiredLicenseException.class)
    public void testCheckExpirationExpired() {
	Calendar calendar = GregorianCalendar.getInstance();
	calendar.set(2013, 5, 1, 0, 0);
	licenseManager.checkExpiration(calendar.getTime());
    }

    @Test(expected = ExpiredLicenseException.class)
    public void testCheckExpirationExpired2() {
	Calendar calendar = GregorianCalendar.getInstance();
	calendar.set(2013, 5, 1, 0, 1);
	licenseManager.checkExpiration(calendar.getTime());
    }

    @Test(expected = ExpiredLicenseException.class)
    public void testCheckExpirationExpired3() {
	Calendar calendar = GregorianCalendar.getInstance();
	calendar.set(2013, 5, 1, 1, 0);
	licenseManager.checkExpiration(calendar.getTime());
    }
}
