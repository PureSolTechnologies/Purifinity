package com.puresol.data;

import java.util.Locale;

import com.puresol.exceptions.StrangeSituationException;

public class Country implements Cloneable {

	private String country = "";

	public Country(String isoCountry) {
		setCountry(isoCountry);
	}

	public Country(Country country) {
		setCountry(country);
	}

	public void setCountry(String isoCountry) {
		for (String iso : Locale.getISOCountries()) {
			if (iso.equals(isoCountry)) {
				this.country = isoCountry;
			}
		}
		throw new IllegaleCountryCodeException();
	}

	public void setCountry(Country country) {
		this.country = country.getISOCountry();
	}

	public String getISOCountry() {
		return country;
	}

	public String getDisplayName() {
		return new Locale("", country).getDisplayCountry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

	public Country clone() {
		try {
			Country cloned = (Country) super.clone();
			cloned.country = this.country;
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new StrangeSituationException(e);
		}
	}
}
