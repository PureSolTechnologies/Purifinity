package com.puresol.data;

import com.puresol.exceptions.StrangeSituationException;

public class EMailAddress implements Cloneable {

    private String addressName = "";
    private String addressServer = "";

    public EMailAddress(String address) throws MailformedEMailException {
	set(address);
	if (!isValid()) {
	    throw new MailformedEMailException(this);
	}
    }

    public EMailAddress(String addressName, String addressServer)
	    throws MailformedEMailException {
	this.addressName = addressName;
	this.addressServer = addressServer;
	if (!isValid()) {
	    throw new MailformedEMailException(this);
	}
    }

    private void set(String address) throws MailformedEMailException {
	String[] splits = address.split("@");
	if (splits.length != 2) {
	    throw new MailformedEMailException(address);
	}
	addressName = splits[0];
	addressServer = splits[1];
    }

    public String toString() {
	return addressName + "@" + addressServer;
    }

    private boolean isValid() {
	if (addressName.isEmpty()) {
	    return false;
	}
	if (addressServer.isEmpty()) {
	    return false;
	}
	if (!addressServer.contains(".")) {
	    return false;
	}
	return true;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result =
		prime
			* result
			+ ((addressName == null) ? 0 : addressName
				.hashCode());
	result =
		prime
			* result
			+ ((addressServer == null) ? 0 : addressServer
				.hashCode());
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
	EMailAddress other = (EMailAddress) obj;
	if (addressName == null) {
	    if (other.addressName != null)
		return false;
	} else if (!addressName.equals(other.addressName))
	    return false;
	if (addressServer == null) {
	    if (other.addressServer != null)
		return false;
	} else if (!addressServer.equals(other.addressServer))
	    return false;
	return true;
    }

    @Override
    public EMailAddress clone() {
	try {
	    EMailAddress cloned = (EMailAddress) super.clone();
	    cloned.addressName = this.addressName;
	    cloned.addressServer = this.addressServer;
	    return cloned;
	} catch (CloneNotSupportedException e) {
	    throw new StrangeSituationException(e);
	}
    }
}
