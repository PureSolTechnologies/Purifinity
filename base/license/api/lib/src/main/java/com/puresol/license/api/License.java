package com.puresol.license.api;

import java.security.PublicKey;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * This object contains all information for a single license.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class License {

	private final PublicKey publicKey;
	private final Product product;
	private final Date issueDate;
	private final Date expirationDate;
	private final Licenser licenser;
	private final Licensee licensee;
	private final Set<LicensedClass> licensedClasses;

	public License(PublicKey publicKey, Product product, Date issueDate,
			Date expirationDate, Licenser licenser, Licensee licensee,
			Set<LicensedClass> licensedClasses) {
		super();
		this.publicKey = publicKey;
		this.product = product;
		this.issueDate = issueDate;
		this.expirationDate = expirationDate;
		this.licenser = licenser;
		this.licensee = licensee;
		this.licensedClasses = Collections.unmodifiableSet(licensedClasses);
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public Product getProduct() {
		return product;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public Licenser getLicenser() {
		return licenser;
	}

	public Licensee getLicensee() {
		return licensee;
	}

	public Set<LicensedClass> getLicensedClasses() {
		return licensedClasses;
	}
}
