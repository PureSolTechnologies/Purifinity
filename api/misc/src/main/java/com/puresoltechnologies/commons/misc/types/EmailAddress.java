package com.puresoltechnologies.commons.misc.types;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class represents an email address.
 * 
 * Details were taken from:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Email_address">
 * http://en.wikipedia.org/wiki/Email_address</a></li>
 * <li><a
 * href="http://en.wikipedia.org/wiki/Hostname">http://en.wikipedia.org/wiki
 * /Hostname</a></li>
 * </ul>
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public final class EmailAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String HOSTNAME_CHARACTERS = "[-a-zA-Z0-9]";
    private static final String IP_ADDRESS_REGEXP = "\\[(\\d{1,3}\\.){3}\\d{1,3}\\";
    private static final String DOMAIN_PART_REGEXP = "^(("
	    + HOSTNAME_CHARACTERS + "{1,63}\\.)+" + HOSTNAME_CHARACTERS
	    + "{2,63}\\.?|" + IP_ADDRESS_REGEXP + "])$";

    private static final Pattern DOMAIN_PART_PATTERN = Pattern
	    .compile(DOMAIN_PART_REGEXP);

    /**
     * 
     * @param address
     * @throws IllegalEmailAddressException
     */
    public static void validate(String address)
	    throws IllegalEmailAddressException {
	if (address == null) {
	    throw new IllegalEmailAddressException(address,
		    "Email address is null.");
	}
	if (address.length() > 254) {
	    throw new IllegalEmailAddressException(address,
		    "Email address is longer than 254 characters.");
	}
	String[] parts = address.split("@");
	try {
	    validateDomainPart(parts[1]);
	    validateLocalPart(parts[0]);
	} catch (IllegalEmailAddressException e) {
	    throw new IllegalEmailAddressException(address, e.getReason());
	}
    }

    public static void validateDomainPart(String domainPart) {
	int length = domainPart.length();
	if (length > 253) {
	    throw new IllegalEmailAddressException("?@" + domainPart,
		    "Domain part is longer than 253 characters (" + length
			    + " characters).");
	}
	Matcher matcher = DOMAIN_PART_PATTERN.matcher(domainPart);
	if (!matcher.matches()) {
	    throw new IllegalEmailAddressException(domainPart,
		    "Domain part is invalid.");
	}
    }

    public static void validateLocalPart(String localPart) {
	if (localPart.length() > 64) {
	    throw new IllegalEmailAddressException(localPart + "@?",
		    "Local part is longer than 64 characters.");
	}
    }

    private final String localPart;
    private final String domainPart;
    private final String address;

    /**
     * This default constructor is needed for Jackson JSON serialization.
     */
    public EmailAddress() {
	localPart = null;
	domainPart = null;
	address = null;
    }

    @JsonCreator
    public EmailAddress(@JsonProperty("address") String address) {
	validate(address);
	String[] parts = address.split("@");
	this.address = address;
	this.localPart = parts[0];
	this.domainPart = parts[1];
    }

    public EmailAddress(String localPart, String domainPart) {
	this.address = localPart + "@" + domainPart;
	validate(address);
	this.localPart = localPart;
	this.domainPart = domainPart;
    }

    @Override
    public final String toString() {
	return address;
    }

    public final String getLocalPart() {
	return localPart;
    }

    public final String getDomainPart() {
	return domainPart;
    }

    public final String getAddress() {
	return address;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((address == null) ? 0 : address.hashCode());
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
	EmailAddress other = (EmailAddress) obj;
	if (address == null) {
	    if (other.address != null)
		return false;
	} else if (!address.equals(other.address))
	    return false;
	return true;
    }

}
