package com.puresoltechnologies.purifinity.server.accountmanager.core.impl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"companies\"")
public class CompanyEntity {

	@Column(name = "\"Representative\"", nullable = false)
	private Long representative;

	@Id
	@Column(name = "\"CompanyId\"", nullable = false)
	private Long companyId;

	@Column(name = "\"AddressHint\"", nullable = false)
	private String addressHint;

	@Column(name = "\"Street\"", nullable = false)
	private String street;

	@Column(name = "\"Number\"", nullable = false)
	private String number;

	@Column(name = "\"ZipCode\"", nullable = false)
	private String zipCode;

	@Column(name = "\"City\"", nullable = false)
	private String city;

	@Column(name = "\"State\"", nullable = false)
	private String state;

	@Column(name = "\"Country\"", nullable = false)
	private String country;

	@Column(name = "\"TaxId\"", nullable = false)
	private String taxId;

	public Long getRepresentative() {
		return representative;
	}

	public void setRepresentative(Long representative) {
		this.representative = representative;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getAddressHint() {
		return addressHint;
	}

	public void setAddressHint(String addressHint) {
		this.addressHint = addressHint;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

}
