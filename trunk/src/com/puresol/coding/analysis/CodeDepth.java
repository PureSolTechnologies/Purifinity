package com.puresol.coding.analysis;

import com.puresol.coding.CodeRange;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public class CodeDepth extends AbstractMetric {

    private int maxLayer = 0;

    public CodeDepth(CodeRange range) {
	super(range);
	calculate();
    }

    private void calculate() {
	CodeRange range = getCodeRange();
	TokenStream stream = range.getTokenStream();
	int layer = 0;
	for (int index = range.getStart(); index <= range.getStop(); index++) {
	    Token token = stream.get(index);
	    SourceTokenDefinition def =
		    (SourceTokenDefinition) token.getDefinitionInstance();
	    if (def.changeBlockLayer() != 0) {
		layer += def.changeBlockLayer();
	    }
	    if (layer > maxLayer) {
		maxLayer = layer;
	    }
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
