package com.puresol.license.api;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This object contains all information for a single license.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class License {

	private final Date issueDate;
	private final Date expirationDate;
	private final String licenser;
	private final String licensee;
	private final List<LicensedClass> licensedClasses;

	public License(Date issueDate, Date expirationDate, String licenser,
			String licensee, List<LicensedClass> licensedClasses) {
		super();
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
		this.licenser = licenser;
		this.licensee = licensee;
		this.licensedClasses = Collections.unmodifiableList(licensedClasses);
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public String getLicenser() {
		return licenser;
	}

	public String getLicensee() {
		return licensee;
	}

	public List<LicensedClass> getLicensedClasses() {
		return licensedClasses;
	}
}
