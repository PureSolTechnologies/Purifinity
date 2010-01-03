package com.puresol.data;

public class EMailAddress {

	private String addressName = "";
	private String addressServer = "";

	public EMailAddress(String address) {
		set(address);
		if (!isValid()) {
			throw new MailformedEMailException(this);
		}
	}

	public EMailAddress(String addressName, String addressServer,
			Country country) {
		this.addressName = addressName;
		this.addressServer = addressServer;
		if (!isValid()) {
			throw new MailformedEMailException(this);
		}
	}

	private void set(String address) {
		String[] splits = address.split("@");
		if (splits.length != 2) {
			throw new MailformedEMailException("EMail address '" + address
					+ "' is invalid!");
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
}
