package com.puresol.license.api;

import java.io.File;

import com.puresol.license.api.exception.LicenseException;

/**
 * This is the interface for license manager.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface LicenseManager {

	/**
	 * This method is used to check for a {@link Class} to be licensed.
	 * 
	 * @param clazz
	 *            is the {@link Class} to be checked.
	 * @throws LicenseException
	 *             is thrown in cases of issues with the license.
	 */
	public void checkIfLicensed(Class<?> clazz) throws LicenseException;

	/**
	 * This method adds a new license file to the license manager.
	 * 
	 * @param licenseFile
	 *            is a {@link File} object pointing to the license file to be
	 *            added.
	 */
	public void addLicenseFile(File licenseFile);

}
