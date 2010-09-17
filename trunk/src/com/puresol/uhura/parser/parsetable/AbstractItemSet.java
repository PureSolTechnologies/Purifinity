package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.production.Construction;

public class AbstractItemSet<T extends Item> implements Serializable {

	private static final long serialVersionUID = 4299654494281633726L;

	private final Set<T> allItems = new CopyOnWriteArraySet<T>();
	private final Set<T> primaryItems = new CopyOnWriteArraySet<T>();
	private final Set<T> addedItems = new CopyOnWriteArraySet<T>();

	public AbstractItemSet(T primaryItem) {
		this.primaryItems.add(primaryItem);
		this.allItems.add(primaryItem);
	}

	public AbstractItemSet(Set<T> primaryItems) {
		this.primaryItems.addAll(primaryItems);
		this.allItems.addAll(primaryItems);
	}

	public AbstractItemSet(AbstractItemSet<T> itemSet) {
		this.primaryItems.addAll(itemSet.getAllItems());
		this.allItems.addAll(itemSet.getAllItems());
	}

	public int getSize() {
		return allItems.size();
	}

	public boolean containsItem(T item) {
		if (primaryItems.contains(item)) {
			return true;
		}
		if (addedItems.contains(item)) {
			return true;
		}
		return false;
	}

	public void addAddedItems(Set<T> items) {
		addedItems.addAll(items);
		allItems.addAll(items);
	}

	public void addItem(T item) {
		addedItems.add(item);
		allItems.add(item);
	}

	public Set<T> getAllItems() {
		return allItems;
	}

	public Set<T> getPrimaryItems() {
		return primaryItems;
	}

	public Set<T> getAddedItems() {
		return addedItems;
	}

	/**
	 * This method returns all constructions which are next to be expected to be
	 * found.
	 * 
	 * @return
	 */
	public List<Construction> getNextConstructions() {
		List<Construction> constructions = new ArrayList<Construction>();
		for (T item : primaryItems) {
			Construction element = item.getNext();
			if (element != null) {
				constructions.add(element);
			}
		}
		for (T item : addedItems) {
			Construction element = item.getNext();
			if (element != null) {
				constructions.add(element);
			}
		}
		return constructions;
	}

	/**
	 * This method returns all items which have the given construction as next
	 * construction.
	 * 
	 * @param construction
	 * @return
	 */
	public List<T> getNextItems(Construction construction) {
		List<T> items = new ArrayList<T>();
		for (T item : primaryItems) {
			Construction element = item.getNext();
			if (element == null) {
				continue;
			}
			if (element.equals(construction)) {
				items.add(item);
			}
		}
		for (T item : addedItems) {
			Construction element = item.getNext();
			if (element == null) {
				continue;
			}
			if (element.equals(construction)) {
				items.add(item);
			}
		}
		return items;
	}

	public Set<Construction> getAllGrammarSymbols() {
		Set<Construction> grammarSymbols = new LinkedHashSet<Construction>();
		for (Item item : getPrimaryItems()) {
			for (Construction construction : item.getProduction()
					.getConstructions()) {
				grammarSymbols.add(construction);
			}
		}
		for (Item item : getAddedItems()) {
			for (Construction construction : item.getProduction()
					.getConstructions()) {
				grammarSymbols.add(construction);
			}
		}
		return grammarSymbols;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (T item : primaryItems) {
			buffer.append("  ");
			buffer.append(item);
			buffer.append("\n");
		}
		for (T item : addedItems) {
			buffer.append("+ ");
			buffer.append(item);
			buffer.append("\n");
		}
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
		int result = 1;
		result = prime * result
				+ ((addedItems == null) ? 0 : addedItems.hashCode());
		result = prime * result
				+ ((allItems == null) ? 0 : allItems.hashCode());
		result = prime * result
				+ ((primaryItems == null) ? 0 : primaryItems.hashCode());
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
		@SuppressWarnings("rawtypes")
		AbstractItemSet other = (AbstractItemSet) obj;
		if (addedItems == null) {
			if (other.addedItems != null)
				return false;
		} else if (!addedItems.equals(other.addedItems))
			return false;
		if (allItems == null) {
			if (other.allItems != null)
				return false;
		} else if (!allItems.equals(other.allItems))
			return false;
		if (primaryItems == null) {
			if (other.primaryItems != null)
				return false;
		} else if (!primaryItems.equals(other.primaryItems))
			return false;
		return true;
	}

}
