package com.puresol.uhura.parser.packrat;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Head implements Serializable {

	private static final long serialVersionUID = 1217226903924359385L;

	private final String production;
	private final Set<String> involvedSet = new LinkedHashSet<String>();
	private final Set<String> evalSet = new LinkedHashSet<String>();

	public Head(String production) {
		super();
		this.production = production;
	}

	String getProduction() {
		return production;
	}

	public void setInvolved(String production2) {
		involvedSet.clear();
		involvedSet.add(production);
	}

	void addInvolved(String production) {
		involvedSet.add(production);
	}

	Set<String> getInvolvedSet() {
		return involvedSet;
	}

	void addEvalSet(Set<String> involvedSet) {
		evalSet.addAll(involvedSet);
	}

	void setEvalSet(Set<String> involvedSet) {
		evalSet.clear();
		evalSet.addAll(involvedSet);
	}

	Set<String> getEvalSet() {
		return evalSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evalSet == null) ? 0 : evalSet.hashCode());
		result = prime * result
				+ ((involvedSet == null) ? 0 : involvedSet.hashCode());
		result = prime * result
				+ ((production == null) ? 0 : production.hashCode());
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
		Head other = (Head) obj;
		if (evalSet == null) {
			if (other.evalSet != null)
				return false;
		} else if (!evalSet.equals(other.evalSet))
			return false;
		if (involvedSet == null) {
			if (other.involvedSet != null)
				return false;
		} else if (!involvedSet.equals(other.involvedSet))
			return false;
		if (production == null) {
			if (other.production != null)
				return false;
		} else if (!production.equals(other.production))
			return false;
		return true;
	}
}
