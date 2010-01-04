package com.puresol.data;

import com.puresol.exceptions.StrangeSituationException;
import com.puresol.testing.NoGetterAndSetterValueTest;

public class Address implements Cloneable {

    private String street = "";
    private String number = "";
    private String stateOrProvince = "";
    private String zipCode = "";
    private String town = "";
    @NoGetterAndSetterValueTest
    private Country country = null;

    public Address(String street, String number, String zipCode,
	    String town, Country country, String stateOrProvince)
	    throws IllegalAddressException {
	this.street = street;
	this.number = number;
	this.stateOrProvince = stateOrProvince;
	this.zipCode = zipCode;
	this.town = town;
	if (country == null) {
	    throw new IllegalAddressException(this);
	}
	this.country = country.clone();
	check();
    }

    public Address(String street, String number, String zipCode,
	    String town, Country country) throws IllegalAddressException {
	this.street = street;
	this.number = number;
	this.stateOrProvince = "";
	this.zipCode = zipCode;
	this.town = town;
	if (country == null) {
	    throw new IllegalAddressException(this);
	}
	this.country = country.clone();
	check();
    }

    private void check() throws IllegalAddressException {
	check(street);
	check(number);
	check(zipCode);
	check(town);
	if (stateOrProvince == null) {
	    throw new IllegalAddressException(this);
	}
    }

    private void check(String string) throws IllegalAddressException {
	if (string == null) {
	    throw new IllegalAddressException(this);
	}
	if (string.isEmpty()) {
	    throw new IllegalAddressException(this);
	}
    }

    /**
     * @return the street
     */
    public String getStreet() {
	return street;
    }

    /**
     * @param street
     *            the street to set
     */
    public void setStreet(String street) {
	this.street = street;
    }

    /**
     * @return the number
     */
    public String getNumber() {
	return number;
    }

    /**
     * @param number
     *            the number to set
     */
    public void setNumber(String number) {
	this.number = number;
    }

    /**
     * @return the stateOrProvince
     */
    public String getStateOrProvince() {
	return stateOrProvince;
    }

    /**
     * @param stateOrProvince
     *            the stateOrProvince to set
     */
    public void setStateOrProvince(String stateOrProvince) {
	this.stateOrProvince = stateOrProvince;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
	return zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

    /**
     * @return the town
     */
    public String getTown() {
	return town;
    }

    /**
     * @param town
     *            the town to set
     */
    public void setTown(String town) {
	this.town = town;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
	return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(Country country) {
	this.country = country;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result =
		prime * result
			+ ((country == null) ? 0 : country.hashCode());
	result =
		prime * result
			+ ((number == null) ? 0 : number.hashCode());
	result =
		prime
			* result
			+ ((stateOrProvince == null) ? 0 : stateOrProvince
				.hashCode());
	result =
		prime * result
			+ ((street == null) ? 0 : street.hashCode());
	result = prime * result + ((town == null) ? 0 : town.hashCode());
	result =
		prime * result
			+ ((zipCode == null) ? 0 : zipCode.hashCode());
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
	Address other = (Address) obj;
	if (country == null) {
	    if (other.country != null)
		return false;
	} else if (!country.equals(other.country))
	    return false;
	if (number == null) {
	    if (other.number != null)
		return false;
	} else if (!number.equals(other.number))
	    return false;
	if (stateOrProvince == null) {
	    if (other.stateOrProvince != null)
		return false;
	} else if (!stateOrProvince.equals(other.stateOrProvince))
	    return false;
	if (street == null) {
	    if (other.street != null)
		return false;
	} else if (!street.equals(other.street))
	    return false;
	if (town == null) {
	    if (other.town != null)
		return false;
	} else if (!town.equals(other.town))
	    return false;
	if (zipCode == null) {
	    if (other.zipCode != null)
		return false;
	} else if (!zipCode.equals(other.zipCode))
	    return false;
	return true;
    }

    @Override
    public Address clone() {
	try {
	    Address cloned = (Address) super.clone();
	    cloned.street = this.street;
	    cloned.number = this.number;
	    cloned.stateOrProvince = this.stateOrProvince;
	    cloned.zipCode = this.zipCode;
	    cloned.town = this.town;
	    cloned.country = this.country;
	    return cloned;
	} catch (CloneNotSupportedException e) {
	    throw new StrangeSituationException(e);
	}
    }
}
