package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class ChangePasswordEntity implements Serializable {

    private static final long serialVersionUID = -505937251130423200L;

    private final String oldPassword;
    private final String newPassword;

    /**
     * Default constructor needed by JSON.
     */
    public ChangePasswordEntity() {
	oldPassword = null;
	newPassword = null;
    }

    @JsonCreator
    public ChangePasswordEntity(
	    @JsonProperty("oldPassword") String oldPassword,
	    @JsonProperty("newPassword") String newPassword) {
	super();
	this.oldPassword = oldPassword;
	this.newPassword = newPassword;
    }

    public String getOldPassword() {
	return oldPassword;
    }

    public String getNewPassword() {
	return newPassword;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((newPassword == null) ? 0 : newPassword.hashCode());
	result = prime * result
		+ ((oldPassword == null) ? 0 : oldPassword.hashCode());
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
	ChangePasswordEntity other = (ChangePasswordEntity) obj;
	if (newPassword == null) {
	    if (other.newPassword != null)
		return false;
	} else if (!newPassword.equals(other.newPassword))
	    return false;
	if (oldPassword == null) {
	    if (other.oldPassword != null)
		return false;
	} else if (!oldPassword.equals(other.oldPassword))
	    return false;
	return true;
    }

}
