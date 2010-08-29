package com.puresol.uhura.parser.parsetable;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.EmptyConstruction;
import com.puresol.uhura.grammar.production.Production;

/**
 * This class calculates the FIRST sets for a given grammar. Pre-calcualted are
 * only first elements for non-terminals. Terminals are returned by this class
 * directly.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class First {

	private final Grammar grammar;

	private final ConcurrentMap<String, Set<Construction>> first = new ConcurrentHashMap<String, Set<Construction>>();

	public First(Grammar grammar) {
		super();
		this.grammar = grammar;
		calculate();
	}

	private void calculate() {
		initFirstMap();
		addEmpty();
		calculateFirstTerminals();
		checkForEmptyConstructions();
	}

	private void calculateFirstTerminals() {
		while (iterate())
			;
	}

	/**
	 * Initializes the first map for data input.
	 */
	private void initFirstMap() {
		for (Production production : grammar.getProductions().getList()) {
			first.put(production.getName(),
					new CopyOnWriteArraySet<Construction>());
		}
	}

	/**
	 * This method looks for all empty constructions and adds the empty
	 * construction to the first set.
	 */
	private void addEmpty() {
		for (Production production : grammar.getProductions().getList()) {
			if (production.isEmpty()) {
				add(production.getName(), EmptyConstruction.getInstance());
			}
		}
	}

	private boolean iterate() {
		boolean changed = false;
		for (Production production : grammar.getProductions().getList()) {
			if (iterate(production)) {
				changed = true;
			}
		}
		return changed;
	}

	private boolean iterate(Production production) {
		boolean changed = false;
		for (Construction construction : production.getConstructions()) {
			if (construction.isTerminal()) {
				if (!first.get(production.getName()).contains(construction)) {
					add(production.getName(), construction);
					changed = true;
				}
				// terminal is found and there is nothing to proceed...
				break;
			}
			if (construction.getName().equals(production.getName())) {
				// don't do endless looping...
				break;
			}
			boolean containsEmpty = false;
			for (Construction current : first.get(construction.getName())) {
				if (!current.equals(EmptyConstruction.getInstance())) {
					if (!first.get(production.getName()).contains(current)) {
						add(production.getName(), current);
						changed = true;
					}
				} else {
					containsEmpty = true;
				}
			}
			if (!containsEmpty) {
				break;
			}
		}
		return changed;
	}

	private void checkForEmptyConstructions() {
		for (Production production : grammar.getProductions().getList()) {
			boolean allEmpty = true;
			for (Construction construction : production.getConstructions()) {
				if (construction.isTerminal()) {
					allEmpty = false;
					break;
				}
				if (!first.get(production.getName()).contains(
						EmptyConstruction.getInstance())) {
					allEmpty = false;
					break;
				}
			}
			if (allEmpty) {
				add(production.getName(), EmptyConstruction.getInstance());
			}
		}
	}

	private void add(String productionName, Construction construction) {
		first.get(productionName).add(construction);
	}

	/**
	 * @return the grammar
	 */
	public Grammar getGrammar() {
		return grammar;
	}

	/**
	 * This method returns the first set for a specified construction.
	 * 
	 * @param x
	 * @return
	 */
	public Set<Construction> get(Construction x) {
		if (x.isTerminal()) {
			Set<Construction> result = new CopyOnWriteArraySet<Construction>();
			result.add(x);
			return result;
		}
		return first.get(x.getName());
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (String productionName : first.keySet()) {
			buffer.append(productionName);
			buffer.append("\t");
			buffer.append("{");
			for (Construction construction : first.get(productionName)) {
				buffer.append(" ");
				buffer.append(construction.toShortString());
			}
			buffer.append(" }\n");
		}
		return buffer.toString();
	}
}
