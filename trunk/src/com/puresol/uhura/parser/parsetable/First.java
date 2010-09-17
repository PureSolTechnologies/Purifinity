package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

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

	private final ConcurrentMap<String, Set<Construction>> first = new ConcurrentHashMap<String, Set<Construction>>();

	public First(Grammar grammar) {
		super();
		this.grammar = grammar;
		calculate();
	}

	private void calculate() {
		initFirstMap();
		calculateFirstTerminals();
	}

	/**
	 * Initializes the first map for data input.
	 */
	private void initFirstMap() {
		for (Production production : grammar.getProductions().getList()) {
			if (!first.containsKey(production.getName())) {
				first.put(production.getName(),
						new CopyOnWriteArraySet<Construction>());
			}
		}
	}

	private void calculateFirstTerminals() {
		while (iterate())
			;
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

	/**
	 * This is rule 2 from Dragon Book:
	 * 
	 * @param production
	 * @return
	 */
	private boolean iterate(Production production) {
		Set<Construction> firstSet = first.get(production.getName());
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
				for (Construction firstConstruction : first.get(construction
						.getName())) {
					firstSet.add(firstConstruction);
				}
				if (!first.get(construction.getName()).contains(
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
			Set<Construction> result = new CopyOnWriteArraySet<Construction>();
			result.add(x);
			return result;
		}
		return first.get(x.getName());
	}

	public Set<Construction> get(Production production) {
		Set<Construction> result = new CopyOnWriteArraySet<Construction>();
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
		return result;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (String productionName : first.keySet()) {
			buffer.append(productionName);
			buffer.append("\t");
			buffer.append("{");
			boolean firstRun = true;
			for (Construction construction : first.get(productionName)) {
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
