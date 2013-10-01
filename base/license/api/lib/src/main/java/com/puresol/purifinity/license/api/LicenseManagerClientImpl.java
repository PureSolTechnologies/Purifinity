package com.puresol.purifinity.license.api;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import com.puresol.commons.license.domain.ExpiredLicenseException;
import com.puresol.commons.license.domain.License;
import com.puresol.commons.license.domain.LicenseException;

public class LicenseManagerClientImpl implements LicenseManagerClient {

	@Override
	public void checkIfLicensed(Class<?> clazz) throws LicenseException {
		Date currentTime = new Date();
		checkExpiration(currentTime);
	}

	void checkExpiration(Date currentTime) {
		Calendar expireDate = new GregorianCalendar(2014, 0, 1, 0, 0, 0);
		if (expireDate.getTime().getTime() <= currentTime.getTime()) {
			throw new ExpiredLicenseException(
					"The license expired 2013-09-01. Please request for a new license.");
		}
	}

	@Override
	public void addLicenseFile(File licenseFile) {
		// TODO Auto-generated method stub
	}

	@Override
	public Set<License> getInstalledLicenses() {
		// TODO Auto-generated method stub
		return new HashSet<License>();
	}

}
