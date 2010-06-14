/***************************************************************************
 *
 *   McCabeMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.mccabe;

import java.util.ArrayList;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.utils.Property;

/**
 * This class calculates the cyclomatic number v(G) from a code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class McCabeMetric extends AbstractMetric {

	private static final long serialVersionUID = 4402746003873908301L;

	private static final Logger logger = Logger.getLogger(McCabeMetric.class);
	private static final Translator translator = Translator
			.getTranslator(McCabeMetric.class);

	public static final String NAME = translator.i18n("McCabe Metric");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(McCabeMetric.class, "enabled",
				"Switches calculation of McCabe Metric on and off.",
				Boolean.class, "true"));
	}

	private int cyclomaticNumber = 1;

	public McCabeMetric(CodeRange codeRange) {
		super(codeRange);
		calculate();
	}

	private void calculate() {
		try {
			CodeRange codeRange = getCodeRange();
			TokenStream tokenStream = codeRange.getTokenStream();
			for (int index = codeRange.getStartId(); index <= codeRange
					.getStopId(); index++) {
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
		if ((range.getCodeRangeType() == CodeRangeType.FILE)
				|| (range.getCodeRangeType() == CodeRangeType.CLASS)
				|| (range.getCodeRangeType() == CodeRangeType.ENUMERATION)) {
			if (getCyclomaticNumber() < 100) {
				return QualityLevel.HIGH;
			}
			if (getCyclomaticNumber() < 125) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.LOW;
		} else if ((range.getCodeRangeType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getCodeRangeType() == CodeRangeType.METHOD)
				|| (range.getCodeRangeType() == CodeRangeType.FUNCTION)
				|| (range.getCodeRangeType() == CodeRangeType.INTERFACE)) {
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
		return NAME;
	}
}
