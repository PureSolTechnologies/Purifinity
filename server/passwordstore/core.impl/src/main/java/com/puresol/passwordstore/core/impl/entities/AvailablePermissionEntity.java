package com.puresol.passwordstore.core.impl.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"available_permissions\"")
public class AvailablePermissionEntity implements Serializable {

	private static final long serialVersionUID = 6749260302921990252L;

	@Id
	@Column(name = "\"Permission\"", nullable = false)
	public String permission;

	@Column(name = "\"Description\"", nullable = false)
	public String description;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
