package com.puresol.coding.analysis.metrics;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.AbstractMetric;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenStream;

public class CodeDepth extends AbstractMetric {

    private static final Logger logger = Logger.getLogger(CodeDepth.class);

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
		def =
			(SourceTokenDefinition) token
				.getDefinitionInstance();
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
}
