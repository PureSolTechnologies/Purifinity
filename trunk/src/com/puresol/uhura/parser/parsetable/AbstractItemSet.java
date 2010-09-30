package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.puresol.uhura.grammar.production.Construction;

/**
 * This class is the base implementation of an item set. This abstract class can
 * take different items of interface Item in dependence to the parser used.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the item type of interface Item to be used.
 */
public class AbstractItemSet<T extends Item> implements Serializable {

	private static final long serialVersionUID = 4299654494281633726L;

	/*
	 * The following fields have concurrent versions of lists and sets for
	 * iterating and manipulating values in different item calculations.
	 */
	private final Set<T> allItems = new LinkedHashSet<T>();
	private final Set<T> kernelItems = new LinkedHashSet<T>();
	private final Set<T> nonKernelItems = new LinkedHashSet<T>();

	/**
	 * This constructor takes a single kernel item.
	 * 
	 * @param kernelItem
	 */
	public AbstractItemSet(T kernelItem) {
		kernelItems.add(kernelItem);
		allItems.add(kernelItem);
	}

	/**
	 * This constructor takes a list of kernel items.
	 * 
	 * @param kernelItem
	 */
	public AbstractItemSet(Set<T> kernelItems) {
		this.kernelItems.addAll(kernelItems);
		this.allItems.addAll(kernelItems);
	}

	/**
	 * This constructor takes an abstract item set and creates a new item set
	 * with base of the given item set.
	 * 
	 * @param itemSet
	 */
	public AbstractItemSet(AbstractItemSet<T> itemSet) {
		this.kernelItems.addAll(itemSet.getKernelItems());
		this.nonKernelItems.addAll(itemSet.getNonKernelItems());
		this.allItems.addAll(itemSet.getKernelItems());
		this.allItems.addAll(itemSet.getNonKernelItems());
	}

	public int getSize() {
		return allItems.size();
	}

	public boolean containsItem(T item) {
		if (kernelItems.contains(item)) {
			return true;
		}
		if (nonKernelItems.contains(item)) {
			return true;
		}
		return false;
	}

	public boolean addKernelItems(Set<T> items) {
		boolean result = false;
		for (T item : items) {
			if (addKernelItem(item)) {
				result = true;
			}
		}
		return result;
	}

	public boolean addKernelItem(T item) {
		boolean result = false;
		if (allItems.add(item)) {
			kernelItems.add(item);
			result = true;
		}
		return result;
	}

	public boolean removeItem(T item) {
		if (!allItems.remove(item)) {
			return false;
		}
		kernelItems.remove(item);
		nonKernelItems.remove(item);
		return true;
	}

	public boolean addNonKernelItems(Set<T> items) {
		boolean result = false;
		for (T item : items) {
			if (addNonKernelItem(item)) {
				result = true;
			}
		}
		return result;
	}

	public boolean addNonKernelItem(T item) {
		boolean result = false;
		if (allItems.add(item)) {
			nonKernelItems.add(item);
			result = true;
		}
		return result;
	}

	public Set<T> getAllItems() {
		return allItems;
	}

	public Set<T> getKernelItems() {
		return kernelItems;
	}

	public Set<T> getNonKernelItems() {
		return nonKernelItems;
	}

	/**
	 * This method returns all constructions which are next to be expected to be
	 * found.
	 * 
	 * @return
	 */
	public List<Construction> getNextConstructions() {
		List<Construction> constructions = new ArrayList<Construction>();
		for (T item : kernelItems) {
			Construction element = item.getNext();
			if (element != null) {
				constructions.add(element);
			}
		}
		for (T item : nonKernelItems) {
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
		for (T item : kernelItems) {
			Construction element = item.getNext();
			if (element == null) {
				continue;
			}
			if (element.equals(construction)) {
				items.add(item);
			}
		}
		for (T item : nonKernelItems) {
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
		for (Item item : getKernelItems()) {
			for (Construction construction : item.getProduction()
					.getConstructions()) {
				grammarSymbols.add(construction);
			}
		}
		for (Item item : getNonKernelItems()) {
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
		for (T item : kernelItems) {
			buffer.append("  ");
			buffer.append(item);
			buffer.append("\n");
		}
		for (T item : nonKernelItems) {
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
				+ ((nonKernelItems == null) ? 0 : nonKernelItems.hashCode());
		result = prime * result
				+ ((allItems == null) ? 0 : allItems.hashCode());
		result = prime * result
				+ ((kernelItems == null) ? 0 : kernelItems.hashCode());
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
		if (nonKernelItems == null) {
			if (other.nonKernelItems != null)
				return false;
		} else if (!nonKernelItems.equals(other.nonKernelItems))
			return false;
		if (allItems == null) {
			if (other.allItems != null)
				return false;
		} else if (!allItems.equals(other.allItems))
			return false;
		if (kernelItems == null) {
			if (other.kernelItems != null)
				return false;
		} else if (!kernelItems.equals(other.kernelItems))
			return false;
		return true;
	}

}
