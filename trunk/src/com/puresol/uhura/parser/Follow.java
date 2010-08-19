package com.puresol.uhura.parser;

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
	 * <pre>
	 * 1. nimm $ zu FOLLOW(S) hinzu (S sei das Startsymbol)
	 * </pre>
	 */
	private void addFinishToStart() {
		follow.get(
				grammar.getProductions().getProductions().iterator().next()
						.getName()).add(FinishConstruction.getInstance());
	}

	/**
	 * <pre>
	 * 2. gibt es eine Produktion (A ! B), so nimm FIRST() \ {"} zu FOLLOW(B)
	 * hinzu
	 * </pre>
	 */
	private void addFirsts() {
		for (Production production : grammar.getProductions().getProductions()) {
			List<Construction> constructions = production.getConstructions();
			for (int i = 0; i < constructions.size(); i++) {
				Construction construction = constructions.get(i);
				if (construction.isTerminal()) {
					continue;
				}
				if (i + 1 < constructions.size()) {
					for (Construction follower : first.get(constructions
							.get(i + 1))) {
						if (!follower.equals(EmptyConstruction.getInstance())) {
							follow.get(construction.getName()).add(follower);
						}
					}
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
		for (Production production : grammar.getProductions().getProductions()) {
			List<Construction> constructions = production.getConstructions();
			for (int i = 0; i < constructions.size(); i++) {
				Construction construction = constructions.get(i);
				if (construction.isTerminal()) {
					continue;
				}
				if (i + 1 == constructions.size()) {
					if (i - 1 <= 0) {
						follow.get(construction.getName()).add(
								FinishConstruction.getInstance());
						continue;
					}
					for (Construction follows : follow.get(constructions
							.get(i - 1))) {
						follow.get(construction.getName()).add(follows);
					}
				} else if (i + 2 == constructions.size()) {
					Construction beta = constructions.get(i + 1);
					if (beta.isTerminal()) {
						continue;
					}
					boolean followerIsEmpty = false;
					for (Production follower : grammar.getProductions().get(
							beta.getName())) {
						if (follower.isEmpty()) {
							followerIsEmpty = true;
							break;
						}
					}
					if (!followerIsEmpty) {
						continue;
					}
					if (i - 1 > 0) {
						for (Construction follows : follow.get(constructions
								.get(i - 1))) {
							follow.get(construction.getName()).add(follows);
						}
					}
				}
			}
		}
	}

	private void initFollowMap() {
		for (Production production : grammar.getProductions().getProductions()) {
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
