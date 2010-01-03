package com.puresol.data;

import javax.i18n4j.Translator;

public class PhoneNumber {

	private static final Translator translator = Translator
			.getTranslator(PhoneNumber.class);

	private PhoneType phoneType = PhoneType.getDefault();
	private PublicityType phoneNumberType = PublicityType.getDefault();
	private String countryDial;
	private String countyDial;
	private String number;

	public PhoneNumber(PhoneType phoneType, PublicityType phoneNumberType,
			String countryDial, String countyDial, String number) {
		this.phoneType = phoneType;
		this.phoneNumberType = phoneNumberType;
		this.countryDial = countryDial;
		this.countyDial = countyDial;
		this.number = number;
		if (!isValid()) {
			throw new IllegalePhoneNumberException(this);
		}
	}

	/**
	 * @return the phoneType
	 */
	public PhoneType getPhoneType() {
		return phoneType;
	}

	/**
	 * @return the phoneNumberType
	 */
	public PublicityType getPhoneNumberType() {
		return phoneNumberType;
	}

	/**
	 * @return the countryDial
	 */
	public String getCountryDial() {
		return countryDial;
	}

	/**
	 * @return the countyDial
	 */
	public String getCountyDial() {
		return countyDial;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	private boolean isValid() {
		if (getCountryDial().isEmpty()) {
			return false;
		}
		if (getCountyDial().isEmpty()) {
			return false;
		}
		if (getNumber().isEmpty()) {
			return false;
		}
		return true;
	}

	public String toString() {
		return translator.i18n("{0}-{1}-{2}", getCountryDial(),
				getCountyDial(), getNumber());
	}
}
