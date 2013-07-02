package com.puresol.commons.utils.math;

import java.io.Serializable;

public interface Value<T> extends Serializable {

    public T getValue();

    public Parameter<T> getParameter();
}
