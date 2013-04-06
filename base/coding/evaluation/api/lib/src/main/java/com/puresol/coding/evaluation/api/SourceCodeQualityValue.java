package com.puresol.coding.evaluation.api;

import com.puresol.utils.math.AbstractValue;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;

public class SourceCodeQualityValue extends AbstractValue<SourceCodeQuality> {

    private static final long serialVersionUID = 5054283728836837150L;

    private static final ParameterWithArbitraryUnit<SourceCodeQuality> PARAMETER = SourceCodeQualityParameter
	    .getInstance();

    public SourceCodeQualityValue(SourceCodeQuality quality) {
	super(quality, PARAMETER);
    }

    @Override
    public Parameter<SourceCodeQuality> getParameter() {
	return super.getParameter();
    }

}
