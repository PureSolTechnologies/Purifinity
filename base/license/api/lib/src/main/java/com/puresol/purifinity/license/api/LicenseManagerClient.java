package com.puresol.purifinity.license.api;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.puresol.license.domain.License;
import com.puresol.license.domain.LicenseException;

/**
 * This is the interface for license manager.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface LicenseManagerClient {

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

	/**
	 * This method returns all installed Licenses.
	 * 
	 * @return A {@link List} of {@link License} is returned containing all
	 *         installed licenses.
	 */
	public Set<License> getInstalledLicenses();

}
