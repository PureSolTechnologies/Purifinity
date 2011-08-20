package com.puresol.math.hp;

import java.lang.reflect.Method;

import com.puresol.utils.MethodInvokationException;

/**
 * 
 * @author rludwig
 * 
 */
public class NewtonsMethod {

	public final Object object;
	public final String methodName;
	public final Method method;

	public NewtonsMethod(Object object, String methodName)
			throws MethodInvokationException {
		this.object = object;
		this.methodName = methodName;
		try {
			method = object.getClass().getMethod(methodName, double.class);
		} catch (Throwable e) {
			throw new MethodInvokationException(object.getClass(), methodName,
					double.class, double.class);
		}
	}

	public Object getObject() {
		return object;
	}

	public String getMethodName() {
		return methodName;
	}

	public double function(double x) throws MethodInvokationException {
		try {
			return (Double) method.invoke(object, x);
		} catch (Throwable e) {
			throw new MethodInvokationException(object.getClass(), methodName,
					double.class, double.class);
		}
	}

	public double find(double y, double leftEdge, double rightEdge,
			double accuracy) throws MethodInvokationException,
			IntervalException {
		double left = function(leftEdge);
		double right = function(rightEdge);
		if (Math.signum(left) == Math.signum(right)) {
			throw new IntervalException(leftEdge, left, rightEdge, right, y);
		}
		int counter = 0;
		boolean first = true;
		double centerPoint = (rightEdge + leftEdge) / 2.0;
		double current = function(centerPoint) - y;
		double last = current;
		while (((Math.abs((current - last) / current) > accuracy) || (first))
				&& (counter < 1000)) {
			if (Math.signum(left) == Math.signum(current)) {
				leftEdge = centerPoint;
				left = current;
			} else if (Math.signum(right) == Math.signum(current)) {
				rightEdge = centerPoint;
				right = current;
			} else {
				rightEdge = centerPoint;
				right = current;
			}
			centerPoint = (rightEdge + leftEdge) / 2.0;
			last = current;
			current = function(centerPoint) - y;
			first = false;
		}
		return centerPoint;
	}
}
