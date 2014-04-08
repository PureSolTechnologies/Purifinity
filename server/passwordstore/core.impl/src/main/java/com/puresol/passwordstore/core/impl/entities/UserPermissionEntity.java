package com.puresol.passwordstore.core.impl.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "\"user_permissions\"")
@IdClass(UserPermissionPK.class)
public class UserPermissionEntity implements Serializable {

	private static final long serialVersionUID = -889232980585341603L;

	@Id
	@Column(name = "\"UserId\"", nullable = false)
	private long userId;

	@Id
	@Column(name = "\"Permission\"", nullable = false)
	private String permission;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		UserPermissionEntity other = (UserPermissionEntity) obj;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
