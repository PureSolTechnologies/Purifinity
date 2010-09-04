package com.puresol.uhura.parser.parsetable;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;

public class LR1Item extends LR0Item {

	private static final long serialVersionUID = -1434126363541910894L;

	private final Set<Construction> lookahead = new CopyOnWriteArraySet<Construction>();

	public LR1Item(Production production, int position) {
		super(production, position);
	}

	public void addLookahead(Construction construction) {
		lookahead.add(construction);
	}

	public void addAllLookahead(Set<Construction> constructions) {
		lookahead.addAll(constructions);
	}

	/**
	 * @return the lookahead
	 */
	public Set<Construction> getLookahead() {
		return lookahead;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(super.toString());
		boolean first = true;
		buffer.append(", {");
		for (Construction construction : lookahead) {
			if (first) {
				first = false;
			} else {
				buffer.append(", ");
			}
			buffer.append(construction.toShortString());
		}
		buffer.append("}");
		return buffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((lookahead == null) ? 0 : lookahead.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LR1Item other = (LR1Item) obj;
		if (lookahead == null) {
			if (other.lookahead != null)
				return false;
		} else if (!lookahead.equals(other.lookahead))
			return false;
		return true;
	}

}
