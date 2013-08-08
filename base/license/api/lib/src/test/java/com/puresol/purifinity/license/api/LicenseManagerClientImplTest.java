package com.puresol.purifinity.license.api;

import static org.junit.Assume.assumeTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.puresol.commons.license.domain.ExpiredLicenseException;

public class LicenseManagerClientImplTest {

	private final LicenseManagerClientImpl licenseManagerClient = new LicenseManagerClientImpl();

	@Test
	public void testCheckExpirationNotExpired() {
		assumeTrue(new GregorianCalendar(2013, 9, 1).before(GregorianCalendar
				.getInstance()));
		licenseManagerClient.checkExpiration(new Date());
	}

	@Test
	public void testCheckExpirationNotExpired2() {
		Calendar calendar = new GregorianCalendar(2013, 8, 30, 23, 59, 59);
		licenseManagerClient.checkExpiration(calendar.getTime());
	}

	@Test(expected = ExpiredLicenseException.class)
	public void testCheckExpirationExpired() {
		Calendar calendar = new GregorianCalendar(2013, 9, 1, 0, 0, 0);
		licenseManagerClient.checkExpiration(calendar.getTime());
	}

	@Test(expected = ExpiredLicenseException.class)
	public void testCheckExpirationExpired2() {
		Calendar calendar = new GregorianCalendar(2013, 9, 1, 0, 1);
		licenseManagerClient.checkExpiration(calendar.getTime());
	}

	@Test(expected = ExpiredLicenseException.class)
	public void testCheckExpirationExpired3() {
		Calendar calendar = new GregorianCalendar(2013, 9, 1, 1, 0);
		licenseManagerClient.checkExpiration(calendar.getTime());
	}
}
