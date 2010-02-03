package com.puresol.hpmath;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.puresol.utils.MethodInvokationException;

/**
 * 
 * @author rludwig
 * 
 */
public class HPNewtonsMethod {

    public final Object object;
    public final String methodName;
    public final Method method;

    public HPNewtonsMethod(Object object, String methodName)
	    throws MethodInvokationException {
	this.object = object;
	this.methodName = methodName;
	try {
	    method =
		    object.getClass().getMethod(methodName,
			    BigDecimal.class);
	} catch (Throwable e) {
	    throw new MethodInvokationException(object.getClass(),
		    methodName, BigDecimal.class, BigDecimal.class);
	}
    }

    public Object getObject() {
	return object;
    }

    public String getMethodName() {
	return methodName;
    }

    public BigDecimal function(BigDecimal x)
	    throws MethodInvokationException {
	try {
	    return (BigDecimal) method.invoke(object, x);
	} catch (Throwable e) {
	    throw new MethodInvokationException(object.getClass(),
		    methodName, BigDecimal.class, BigDecimal.class);
	}
    }

    public BigDecimal find(BigDecimal y, BigDecimal leftEdge,
	    BigDecimal rightEdge, BigDecimal accuracy)
	    throws MethodInvokationException, IntervalException {
	BigDecimal left = function(leftEdge);
	BigDecimal right = function(rightEdge);
	if (left.signum() == right.signum()) {
	    throw new IntervalException(leftEdge.doubleValue(), left
		    .doubleValue(), rightEdge.doubleValue(), right
		    .doubleValue(), y.doubleValue());
	}
	int counter = 0;
	boolean first = true;
	BigDecimal centerPoint =
		rightEdge.add(leftEdge).divide(new BigDecimal(2.0));
	BigDecimal current = function(centerPoint).subtract(y);
	BigDecimal last = current;
	while (((current.subtract(last).abs().doubleValue() > accuracy
		.doubleValue()) || (first))
		&& (counter < 100)) {
	    if (left.signum() == current.signum()) {
		leftEdge = centerPoint;
		left = current;
	    } else if (right.signum() == current.signum()) {
		rightEdge = centerPoint;
		right = current;
	    } else {
		rightEdge = centerPoint;
		right = current;
	    }
	    centerPoint =
		    rightEdge.add(leftEdge).divide(new BigDecimal(2.0));
	    last = current;
	    current = function(centerPoint).subtract(y);
	    first = false;
	    counter++;
	}
	return centerPoint;
    }
}
