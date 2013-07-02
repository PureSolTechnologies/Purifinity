package com.puresol.commons.license.domain;

import java.security.PublicKey;
import java.util.Arrays;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result
				+ ((issueDate == null) ? 0 : issueDate.hashCode());
		result = prime * result
				+ ((licensedClasses == null) ? 0 : licensedClasses.hashCode());
		result = prime * result
				+ ((licensee == null) ? 0 : licensee.hashCode());
		result = prime * result
				+ ((licenser == null) ? 0 : licenser.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		License other = (License) obj;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (issueDate == null) {
			if (other.issueDate != null)
				return false;
		} else if (!issueDate.equals(other.issueDate))
			return false;
		if (licensedClasses == null) {
			if (other.licensedClasses != null)
				return false;
		} else if (!licensedClasses.equals(other.licensedClasses))
			return false;
		if (licensee == null) {
			if (other.licensee != null)
				return false;
		} else if (!licensee.equals(other.licensee))
			return false;
		if (licenser == null) {
			if (other.licenser != null)
				return false;
		} else if (!licenser.equals(other.licenser))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (publicKey == null) {
			if (other.publicKey != null)
				return false;
		} else if (!Arrays.equals(publicKey.getEncoded(),
				other.publicKey.getEncoded()))
			return false;
		return true;
	}
}
