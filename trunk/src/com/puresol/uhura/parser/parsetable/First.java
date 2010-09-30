package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.EmptyTerminal;
import com.puresol.uhura.grammar.production.Production;

/**
 * This class calculates the FIRST sets for a given grammar. Pre-calcualted are
 * only first elements for non-terminals. Terminals are returned by this class
 * directly.
 * 
 * 
 * 
 * From german edition of dragon book:
 * 
 * 1) Wenn X ein Terminal ist, ist FIRST(X) = (X).
 * 
 * 2) Wenn X ein Nichtterminal und X --> Y1 Y2 ... Yk eine Produktion fuer ein
 * beliebiges k>=1 ist, gehen Sie dagegen wie folgt vor: Platzieren Sie a in
 * FIRST(X), wenn fuer irgendein i gilt, dass a in FIRST(Yi) und epsilon in
 * allen FIRST(Y1),...,FIRST(Yi-1) ist, d.h. wenn Y1...Yi-1 --> epsilon. Wenn
 * epsilon fuer alle j=1,2,...,k in FIRST(Y1) ist, fuegen Sie epsilon zu
 * FIRST(X) hinzu. Zum Beispiel ist alles, was sich in FIRST(Y1) befindet, mit
 * Sicherheit auch in FIRST(X). Wird Y1 nicht nach epsilon abgeleitet, fuegen
 * wir FIRST(X) nichts mehr hinzu. Gilt dagegen Y1 --> epsilon, fuegen wir
 * FIRST(Y2) hinzu usw.
 * 
 * 3) Ist X --> epsilon eine Produktion, fuegen wir epsilon zu FIRST(X) hinzu.
 * 
 * 
 * @author Rick-Rainer Ludwig
 */
public class First implements Serializable {

	private static final long serialVersionUID = -2025671282094501163L;

	private final Grammar grammar;

	/**
	 * This field contains the first sets for all productions contained within
	 * the grammar.
	 */
	private final Map<String, Set<Construction>> firstGrammar = new HashMap<String, Set<Construction>>();
	/**
	 * The field contains first sets for helper productions to avoid double
	 * calculation.
	 */
	private final Map<Production, Set<Construction>> firstNonGrammar = new HashMap<Production, Set<Construction>>();

	public First(Grammar grammar) {
		super();
		this.grammar = grammar;
		calculateForGrammarProductions();
	}

	private void calculateForGrammarProductions() {
		initFirstMapForGrammarProductions();
		calculateFirstForGrammarProductions();
	}

	/**
	 * Initializes the first map for data input.
	 */
	private void initFirstMapForGrammarProductions() {
		for (Production production : grammar.getProductions().getList()) {
			if (!firstGrammar.containsKey(production.getName())) {
				firstGrammar.put(production.getName(),
						new LinkedHashSet<Construction>());
			}
		}
	}

	private void calculateFirstForGrammarProductions() {
		while (iteratationForGrammarProductions())
			;
	}

	private boolean iteratationForGrammarProductions() {
		boolean changed = false;
		for (Production production : grammar.getProductions().getList()) {
			if (iterateForGrammarProductions(production)) {
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * This is rule 2 from Dragon Book:
	 * 
	 * @param production
	 * @return
	 */
	private boolean iterateForGrammarProductions(Production production) {
		Set<Construction> firstSet = firstGrammar.get(production.getName());
		int startLength = firstSet.size();
		if (production.isEmpty()) {
			/* rule 3 */
			firstSet.add(EmptyTerminal.getInstance());
		} else {
			/* rule 2 */
			for (Construction construction : production.getConstructions()) {
				if (construction.isTerminal()) {
					firstSet.add(construction);
					// terminal is found and there is nothing to proceed...
					break;
				}
				for (Construction firstConstruction : firstGrammar
						.get(construction.getName())) {
					firstSet.add(firstConstruction);
				}
				if (!firstGrammar.get(construction.getName()).contains(
						EmptyTerminal.getInstance())) {
					break;
				}
			}
		}
		return (startLength != firstSet.size());
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
	 * This method contains rule 1 from Dragon Book:
	 * 
	 * 1) Wenn X ein Terminal ist, ist FIRST(X) = (X).
	 * 
	 * @param x
	 * @return
	 */
	public Set<Construction> get(Construction x) {
		if (x.isTerminal()) {
			Set<Construction> result = new LinkedHashSet<Construction>();
			result.add(x);
			return result;
		}
		return firstGrammar.get(x.getName());
	}

	public Set<Construction> get(Production production) {
		if (!firstNonGrammar.containsKey(production)) {
			calculate(production);
		}
		return firstNonGrammar.get(production);
	}

	private void calculate(Production production) {
		Set<Construction> result = new LinkedHashSet<Construction>();
		boolean hasEmptyDerivation = true;
		for (Construction construction : production.getConstructions()) {
			Set<Construction> first = get(construction);
			result.addAll(first);
			if (first.contains(EmptyTerminal.getInstance())) {
				result.remove(EmptyTerminal.getInstance());
			} else {
				hasEmptyDerivation = false;
				break;
			}
		}
		if (hasEmptyDerivation) {
			result.add(EmptyTerminal.getInstance());
		}
		firstNonGrammar.put(production, result);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (String productionName : firstGrammar.keySet()) {
			buffer.append(productionName);
			buffer.append("\t");
			buffer.append("{");
			boolean firstRun = true;
			for (Construction construction : firstGrammar.get(productionName)) {
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
