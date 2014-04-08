package com.puresol.accountmanager.core.impl.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class AddressPK implements Serializable {

	private static final long serialVersionUID = 6433583462149060973L;

	@Id
	@Column(name = "\"UserId\"", nullable = false)
	private Long userId;

	@Id
	@Column(name = "\"AddressId\"", nullable = false)
	private Long addressId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

}
