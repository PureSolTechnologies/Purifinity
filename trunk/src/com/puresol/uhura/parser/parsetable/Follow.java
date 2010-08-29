package com.puresol.uhura.parser.parsetable;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.EmptyConstruction;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.grammar.production.Production;

/**
 * @author Rick-Rainer Ludwig
 * 
 */
public class Follow {

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
		addFirsts();
		addFollows();
	}

	/**
	 * This method ass the finish construction to the start symbol. If a start
	 * symbol is defined by StartProduction this construction is used. If the
	 * there is no such production, the fist production is used.
	 * 
	 * <pre>
	 * 1. nimm $ zu FOLLOW(S) hinzu (S sei das Startsymbol)
	 * </pre>
	 */
	private void addFinishToStart() {
		follow.get(grammar.getProductions().get(0).getName()).add(
				FinishConstruction.getInstance());
	}

	/**
	 * <pre>
	 * 2. gibt es eine Produktion (A ! B), so nimm FIRST() \ {"} zu FOLLOW(B)
	 * hinzu
	 * </pre>
	 */
	private void addFirsts() {
		for (Production production : grammar.getProductions().getList()) {
			addFirsts(production);
		}
	}

	private void addFirsts(Production production) {
		List<Construction> constructions = production.getConstructions();
		for (int i = 0; i < constructions.size() - 1; i++) {
			Construction construction = constructions.get(i);
			if (construction.isTerminal()) {
				continue;
			}
			String constructionName = construction.getName();
			Construction nextConstruction = constructions.get(i + 1);
			for (Construction follower : first.get(nextConstruction)) {
				if (!follower.equals(EmptyConstruction.getInstance())) {
					follow.get(constructionName).add(follower);
				}
			}
		}
	}

	/**
	 * <pre>
	 * 3. gibt es eine Produktion (A ! B) oder (A ! B) mit  ) ", so f¨uge
	 * FOLLOW(A) zu FOLLOW(B) hinzu
	 * </pre>
	 */
	private void addFollows() {
		for (Production production : grammar.getProductions().getList()) {
			addFollows(production);
		}
	}

	private void addFollows(Production production) {
		List<Construction> constructions = production.getConstructions();
		for (int i = 0; i < constructions.size(); i++) {
			Construction construction = constructions.get(i);
			if (construction.isTerminal()) {
				continue;
			}
			Set<Construction> productionFollow = follow.get(production
					.getName());
			if (productionFollow == null) {
				continue;
			}
			if (i + 1 == constructions.size()) {
				follow.get(construction.getName()).addAll(productionFollow);
			} else if (i + 2 == constructions.size()) {
				Construction nextConstruction = constructions.get(i + 1);
				if (first.get(nextConstruction).contains(
						EmptyConstruction.getInstance())) {
					follow.get(construction.getName()).addAll(productionFollow);
				}
			}
		}
	}

	private void initFollowMap() {
		for (Production production : grammar.getProductions().getList()) {
			String productionName = production.getName();
			Set<Construction> set = follow.get(productionName);
			if (set == null) {
				follow.put(productionName,
						new CopyOnWriteArraySet<Construction>());
			}
		}
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
			for (Construction construction : follow.get(productionName)) {
				buffer.append(" ");
				buffer.append(construction.toShortString());
			}
			buffer.append(" }\n");
		}
		return buffer.toString();
	}
}
