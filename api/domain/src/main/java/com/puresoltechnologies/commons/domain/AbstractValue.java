package com.puresoltechnologies.commons.domain;

/**
 * This class is an abstract implementation of a {@link Value}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the type of the value which is to be stored.
 */
public abstract class AbstractValue<T> implements Value<T> {

    private static final long serialVersionUID = 1L;

    private final T value;
    private final Parameter<T> parameter;

    /**
     * This is the initial value constructor.
     * 
     * @param value
     *            is the value to be set.
     * @param parameter
     *            is the corresponding parameter for the value.
     */
    public AbstractValue(T value, Parameter<T> parameter) {
	super();
	this.value = value;
	this.parameter = parameter;
    }

    @Override
    public T getValue() {
	return value;
    }

    @Override
    public Parameter<T> getParameter() {
	return parameter;
    }

    @Override
    public String toString() {
	if (parameter == null) {
	    return value.toString();
	} else {
	    return parameter.getName() + "=" + value.toString()
		    + parameter.getUnit();
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((parameter == null) ? 0 : parameter.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractValue<?> other = (AbstractValue<?>) obj;
	if (parameter == null) {
	    if (other.parameter != null)
		return false;
	} else if (!parameter.equals(other.parameter))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

}
