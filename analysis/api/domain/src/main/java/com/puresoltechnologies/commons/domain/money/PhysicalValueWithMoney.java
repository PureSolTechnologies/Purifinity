package com.puresoltechnologies.commons.domain.money;

import com.puresoltechnologies.commons.domain.AbstractValue;

public class PhysicalValueWithMoney<T> extends AbstractValue<T> {

    private static final long serialVersionUID = -7173959637653740015L;

    public PhysicalValueWithMoney(T value,
	    PhysicalParameterWithMoney<T> parameter) {
	super(value, parameter);
    }

    @Override
    public PhysicalParameterWithMoney<T> getParameter() {
	return (PhysicalParameterWithMoney<T>) super.getParameter();
    }

}
