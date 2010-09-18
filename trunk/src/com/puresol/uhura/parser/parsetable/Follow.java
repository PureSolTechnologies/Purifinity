package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.EmptyTerminal;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.Production;

/**
 * The rules from Dragon Book are:
 * 
 * 1) Platzieren Sie $ in FOLLOW(S), wobei S das Startsymbol und $ die rechte
 * Endmarkierung fuer die Eingabe sind.
 * 
 * 2) Gibt es eine Produktion A --> alpha B beta, ist der gesamte Inhalt von
 * FIRST(beta) ausser epsilon in FOLLOW(B) enthalten.
 * 
 * 3) Gibt es eine Produktion A --> alpha B oder A --> alpha B beta, wobei
 * FIRST(beta) epsilon enthaelt, ist der gesamt Inhalt von FOLLOW(A) auch in
 * FOLLOW(B) enthalten.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Follow implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Grammar grammar;
	private final First first;

	private final ConcurrentMap<String, Set<Construction>> follow = new ConcurrentHashMap<String, Set<Construction>>();

	public Follow(Grammar grammar) {
		super();
		this.grammar = grammar;
		this.first = new First(grammar);
		calculate();
	}

	public Follow(Grammar grammar, First first) {
		super();
		this.grammar = grammar;
		this.first = first;
		calculate();
	}

	private void calculate() {
		initFollowMap();
		addFinishToStart();
		iterate();
	}

	private void initFollowMap() {
		for (Production production : grammar.getProductions().getList()) {
			if (follow.get(production.getName()) == null) {
				follow.put(production.getName(),
						new LinkedHashSet<Construction>());
			}
		}
	}

	/**
	 * This method ass the finish construction to the start symbol. If a start
	 * symbol is defined by StartProduction this construction is used. If the
	 * there is no such production, the fist production is used.
	 * 
	 * This is rule 1 from Dragon book:
	 * 
	 * 1) Platzieren Sie $ in FOLLOW(S), wobei S das Startsymbol und $ die
	 * rechte Endmarkierung fuer die Eingabe sind.
	 */
	private void addFinishToStart() {
		follow.get(grammar.getProductions().get(0).getName()).add(
				FinishTerminal.getInstance());
	}

	private void iterate() {
		boolean changed;
		do {
			changed = false;
			for (Production production : grammar.getProductions().getList()) {
				if (iterate(production)) {
					changed = true;
				}
			}
		} while (changed);
	}

	/**
	 * This is rule 2 from Dragon Book:
	 * 
	 * 2) Gibt es eine Produktion A --> alpha B beta, ist der gesamte Inhalt von
	 * FIRST(beta) ausser epsilon in FOLLOW(B) enthalten.
	 */
	private boolean iterate(Production production) {
		List<Construction> constructions = production.getConstructions();
		boolean changed = false;
		/*
		 * Search production for non-terminals...
		 */
		for (int i = 0; i < constructions.size(); i++) {
			Construction construction = constructions.get(i);
			if (construction.isTerminal()) {
				continue;
			}
			Set<Construction> followSet = follow.get(construction.getName());
			int startSize = followSet.size();
			/*
			 * For the found non-terminal find the following constructions by
			 * first sets.
			 */
			if (i < constructions.size() - 1) {
				for (int j = i + 1; j < constructions.size(); j++) {
					Construction followingConstruction = constructions.get(j);
					Set<Construction> firstSet = first
							.get(followingConstruction);
					for (Construction follower : firstSet) {
						if (!follower.equals(EmptyTerminal.getInstance())) {
							followSet.add(follower);
						}
					}
					if (!firstSet.contains(EmptyTerminal.getInstance())) {
						break;
					}
					if (j == constructions.size() - 1) {
						followSet.addAll(follow.get(production.getName()));
					}
				}
			} else {
				followSet.addAll(follow.get(production.getName()));
			}
			if (startSize < followSet.size()) {
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * @return the grammar
	 */
	public Grammar getGrammar() {
		return grammar;
	}

	public Set<Construction> get(Construction x) {
		return follow.get(x.getName());
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (String productionName : follow.keySet()) {
			buffer.append(productionName);
			buffer.append("\t");
			buffer.append("{");
			boolean firstRun = true;
			for (Construction construction : follow.get(productionName)) {
				if (firstRun) {
					firstRun = false;
				} else {
					buffer.append(", ");
				}
				buffer.append(construction.toString());
			}
			buffer.append(" }\n");
		}
		return buffer.toString();
	}
}
