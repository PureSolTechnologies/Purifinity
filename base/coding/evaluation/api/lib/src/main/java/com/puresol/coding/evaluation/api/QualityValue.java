package com.puresol.coding.evaluation.api;

import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;
import com.puresol.utils.math.Value;

public class QualityValue implements Value<SourceCodeQuality> {

    public static final ParameterWithArbitraryUnit<SourceCodeQuality> PARAMETER = new ParameterWithArbitraryUnit<SourceCodeQuality>(
	    "source code quality", "", LevelOfMeasurement.ORDINAL,
	    "Quality measure of related source code range.",
	    SourceCodeQuality.class);

    private final SourceCodeQuality quality;

    public QualityValue(SourceCodeQuality quality) {
	super();
	this.quality = quality;
    }

    @Override
    public SourceCodeQuality getValue() {
	return quality;
    }

    @Override
    public Parameter<SourceCodeQuality> getParameter() {
	return PARAMETER;
    }

}
