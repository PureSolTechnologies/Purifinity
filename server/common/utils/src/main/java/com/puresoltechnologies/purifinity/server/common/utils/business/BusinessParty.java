package com.puresoltechnologies.purifinity.server.common.utils.business;

import java.util.Locale;

/**
 * This enum is used to identify the party of a business duty and
 * responsibility. It is either the seller of buyer.
 * 
 * @author Rick-Rainer Ludwig
 */
public enum BusinessParty {

	SELLER, BUYER;

	/**
	 * This method returns the name of the party.
	 * 
	 * @param locale
	 * @return
	 */
	public String getName(Locale locale) {
		switch (this) {
		case SELLER:
			return "seller";
		case BUYER:
			return "buyer";
		default:
			throw new RuntimeException("Unknown business party '" + name()
					+ "'!");
		}
	}

}
