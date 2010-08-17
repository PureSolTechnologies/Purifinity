package com.puresol.uhura.parser.statetable;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;

/**
 * This class represents a single item defined in the Dragon Book for keeping
 * the current parsing state for a production.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Item {

	/**
	 * This is the reference id for the production observed.
	 */
	private final Production production;
	/**
	 * This is the current position. Zero is the left most position which is at
	 * the beginning of the parsing process.
	 */
	private int position;

	public Item(Production production, int position) {
		this.production = production;
		this.position = position;
	}

	/**
	 * @return the productionId
	 */
	public Production getProduction() {
		return production;
	}

	public void incPosition() {
		position++;
	}

	public int getPosition() {
		return position;
	}

	public Construction getNext() {
		if (position >= production.getConstructions().size()) {
			return null;
		}
		return production.getConstructions().get(position);
	}

	public boolean hasNext() {
		return (position < production.getConstructions().size());
	}

	@Override
	public String toString() {
		return production.toShortString(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + position;
		result = prime * result
				+ ((production == null) ? 0 : production.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (position != other.position)
			return false;
		if (production == null) {
			if (other.production != null)
				return false;
		} else if (!production.equals(other.production))
			return false;
		return true;
	}

}
