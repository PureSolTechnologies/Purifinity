package com.puresol.uhura.grammar.production;

import java.util.ArrayList;
import java.util.List;

public class Production {

	private final int typeId;
	private final List<ProductionElement> elements = new ArrayList<ProductionElement>();

	public Production(int typeId) {
		super();
		this.typeId = typeId;
	}

	/**
	 * @return the name
	 */
	public int getTypeId() {
		return typeId;
	}

	public void addElement(ProductionElement element) {
		elements.add(element);
	}

	/**
	 * @return the elements
	 */
	public List<ProductionElement> getElements() {
		return elements;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(typeId);
		result.append(":");
		for (ProductionElement element : elements) {
			result.append(" ");
			result.append(element);
		}
		return result.toString();
	}
}
