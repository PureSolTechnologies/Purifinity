package com.puresol.passwordstore.core.impl.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"activation_keys\"")
@NamedQueries({ @NamedQuery(name = "GetActivationKey", query = "SELECT key FROM ActivationKeyEntity key WHERE key.userId=:userId") })
public class ActivationKeyEntity implements Serializable {

	private static final long serialVersionUID = -2553248470197200308L;

	@Id
	@Column(name = "\"UserId\"", nullable = false)
	public long userId;

	@Column(name = "\"ActivationKey\"", nullable = false)
	public String activationKey;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

}
