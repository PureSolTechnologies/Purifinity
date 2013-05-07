package com.puresol.license.api;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.puresol.license.api.exception.ExpiredLicenseException;
import com.puresol.license.api.exception.LicenseException;

public class LicenseManagerImpl implements LicenseManager {

    @Override
    public void checkIfLicensed(Class<?> clazz) throws LicenseException {
	Date currentTime = new Date();
	checkExpiration(currentTime);
    }

    void checkExpiration(Date currentTime) {
	Calendar expireDate = GregorianCalendar.getInstance();
	expireDate.set(2013, 5, 1, 0, 0);
	if (expireDate.getTime().getTime() <= currentTime.getTime()) {
	    throw new ExpiredLicenseException(
		    "The license expired 2013-06-01. Please request for a new license.");
	}
    }

    @Override
    public void addLicenseFile(File licenseFile) {
	// TODO Auto-generated method stub

    }

}
