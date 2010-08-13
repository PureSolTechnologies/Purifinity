package com.puresol.uhura.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserRule {

	private final String name;
	private final List<ParserRuleElement> elements = new ArrayList<ParserRuleElement>();

	public ParserRule(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void addElement(ParserRuleElement element) {
		elements.add(element);
	}

	/**
	 * @return the elements
	 */
	public List<ParserRuleElement> getElements() {
		return elements;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(name);
		result.append(":");
		for (ParserRuleElement element : elements) {
			result.append(" ");
			result.append(element);
		}
		return result.toString();
	}
}
