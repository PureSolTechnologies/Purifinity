package com.puresol.coding.evaluator.metric;

import java.util.ArrayList;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.AbstractMetric;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenStream;
import com.puresol.utils.Property;

public class CodeDepth extends AbstractMetric {

	private static final Logger logger = Logger.getLogger(CodeDepth.class);
	private static final Translator translator = Translator
			.getTranslator(CodeDepth.class);

	public static final String NAME = translator.i18n("Code Depth Metric");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(CodeDepth.class, "enabled",
				"Switches calculation of CodeDepth on and off.", Boolean.class,
				"true"));
	}

	private int maxLayer = 0;

	public CodeDepth(CodeRange range) {
		super(range);
		calculate();
	}

	private void calculate() {
		try {
			CodeRange range = getCodeRange();
			TokenStream stream = range.getTokenStream();
			int layer = 0;
			for (int index = range.getStart(); index <= range.getStop(); index++) {
				Token token = stream.get(index);
				SourceTokenDefinition def;
				def = (SourceTokenDefinition) token.getDefinitionInstance();
				if (def.changeBlockLayer() != 0) {
					layer += def.changeBlockLayer();
				}
				if (layer > maxLayer) {
					maxLayer = layer;
				}
			}
		} catch (TokenException e) {
			logger.error(e);
		}
	}

	public int getMaxLayer() {
		return maxLayer;
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	@Override
	public QualityLevel getQualityLevel() {
		int maxLayer = getMaxLayer();
		if (maxLayer > 6) {
			return QualityLevel.LOW;
		} else if (maxLayer > 4) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
