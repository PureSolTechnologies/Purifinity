/***************************************************************************
 *
 *   McCabeMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import com.puresol.coding.tokentypes.ProgrammingLanguageTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class AbstractMcCabeMetric extends AbstractMetric {

    private int cyclomaticNumber = 1;

    public AbstractMcCabeMetric(CodeRange codeRange) {
	super(codeRange);
	calculate();
    }

    private void calculate() {
	CodeRange codeRange = getCodeRange();
	TokenStream tokenStream = codeRange.getTokenStream();
	for (int index = codeRange.getStart(); index <= codeRange
		.getStop(); index++) {
	    Token token = tokenStream.get(index);
	    if (token.getPublicity() != TokenPublicity.HIDDEN) {
		ProgrammingLanguageTokenDefinition def =
			(ProgrammingLanguageTokenDefinition) token
				.getDefinitionInstance();
		addCyclomaticNumber(def.getCyclomaticNumber(token,
			tokenStream));
	    }
	}

    }

    private void addCyclomaticNumber(int cyclomaticNumber) {
	this.cyclomaticNumber += cyclomaticNumber;
    }

    public int getCyclomaticNumber() {
	return cyclomaticNumber;
    }

    public void print() {
	System.out.println("v(G) = " + cyclomaticNumber);
    }

    public static boolean isSuitable(CodeRange codeRange) {
	return true;
    }
}
