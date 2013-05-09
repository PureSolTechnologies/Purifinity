package com.puresol.license.creator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LicenseManager {

	private File getStorageDirectory() {
		return new File(System.getProperty("user.home"));
	}

	public void addLicensee(String licensee) {

	}

	public List<String> getLicensees() {
		return new ArrayList<String>();
	}

}
