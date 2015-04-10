package com.puresoltechnologies.purifinity.evaluation.api;

import com.puresoltechnologies.commons.domain.AbstractValue;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

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
