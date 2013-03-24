package com.puresol.coding.evaluation.api;

import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;
import com.puresol.utils.math.Value;

public class SourceCodeQualityValue implements Value<SourceCodeQuality> {

	private static final ParameterWithArbitraryUnit<SourceCodeQuality> PARAMETER = SourceCodeQualityParameter
			.getInstance();

	private final SourceCodeQuality quality;

	public SourceCodeQualityValue(SourceCodeQuality quality) {
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
