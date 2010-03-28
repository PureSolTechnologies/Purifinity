/***************************************************************************
 *
 *   McCabeMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.metrics;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.AbstractMetric;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends AbstractMetric {

	private static final Logger logger = Logger.getLogger(McCabeMetric.class);
	private static final Translator translator = Translator
			.getTranslator(McCabeMetric.class);

	private int cyclomaticNumber = 1;

	public McCabeMetric(CodeRange codeRange) {
		super(codeRange);
		calculate();
	}

	private void calculate() {
		try {
			CodeRange codeRange = getCodeRange();
			TokenStream tokenStream = codeRange.getTokenStream();
			for (int index = codeRange.getStart(); index <= codeRange.getStop(); index++) {
				Token token = tokenStream.get(index);
				if (token.getPublicity() != TokenPublicity.HIDDEN) {
					SourceTokenDefinition def = (SourceTokenDefinition) token
							.getDefinitionInstance();
					addCyclomaticNumber(def.getCyclomaticNumber(token,
							tokenStream));
				}
			}
		} catch (TokenException e) {
			logger.error(e);
		}
	}

	private void addCyclomaticNumber(int cyclomaticNumber) {
		this.cyclomaticNumber += cyclomaticNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.McCabeMetric#getCyclomaticNumber()
	 */
	public int getCyclomaticNumber() {
		return cyclomaticNumber;
	}

	public void print() {
		System.out.println("v(G) = " + cyclomaticNumber);
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	@Override
	public QualityLevel getQualityLevel() {
		CodeRange range = getCodeRange();
		if ((range.getType() == CodeRangeType.FILE)
				|| (range.getType() == CodeRangeType.CLASS)
				|| (range.getType() == CodeRangeType.ENUMERATION)) {
			if (getCyclomaticNumber() < 100) {
				return QualityLevel.HIGH;
			}
			if (getCyclomaticNumber() < 125) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getType() == CodeRangeType.METHOD)
				|| (range.getType() == CodeRangeType.FUNCTION)
				|| (range.getType() == CodeRangeType.INTERFACE)) {
			if (getCyclomaticNumber() < 15) {
				return QualityLevel.HIGH;
			}
			if (getCyclomaticNumber() < 20) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		}
		return QualityLevel.HIGH; // not evaluated...
	}

	@Override
	public String getName() {
		return translator.i18n("McCabe metric");
	}
}
