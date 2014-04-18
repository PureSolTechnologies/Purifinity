package com.puresoltechnologies.purifinity.server.passwordstore.client;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;
import java.util.Vector;

/**
 * This principal is used to create a principal group with a certain name.
 * 
 * @author Rick-Rainer Ludwig
 */
public class NamedGroup implements Group {

	private final String name;

	Vector<Principal> set = new Vector<Principal>();

	public NamedGroup(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((set == null) ? 0 : set.hashCode());
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
		NamedGroup other = (NamedGroup) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[principal: ");
		buffer.append(name);
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public boolean addMember(Principal user) {
		if (set.contains(user)) {
			return false;
		}
		set.add(user);
		return true;
	}

	@Override
	public boolean removeMember(Principal user) {
		if (!set.contains(user)) {
			return false;
		}
		set.remove(user);
		return true;
	}

	@Override
	public boolean isMember(Principal member) {
		return set.contains(member);
	}

	@Override
	public Enumeration<? extends Principal> members() {
		return set.elements();
	}
}
