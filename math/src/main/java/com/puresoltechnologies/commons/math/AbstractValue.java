package com.puresoltechnologies.commons.math;

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

}
