package com.puresol.commons.license.domain.dto;

import com.puresol.commons.license.domain.Licensee;

/**
 * This value object represents a single licensee.
 * 
 * @author Rick-Rainer Ludwig
 */
public class LicenseeDTO {

	/*
	 * TODO add a list of people of the customer which should be notified when
	 * the license expires!
	 */

	private String customerId = "";
	private String name = "";

	public LicenseeDTO() {
	}

	public LicenseeDTO(Licensee licensee) {
		super();
		customerId = licensee.getCustomerId();
		name = licensee.getName();
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public final Licensee toLicensee() {
		return new Licensee(customerId, name);
	}
}
