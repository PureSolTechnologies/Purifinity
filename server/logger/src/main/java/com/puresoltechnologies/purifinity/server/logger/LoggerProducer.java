package com.puresoltechnologies.purifinity.server.logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProducer {

	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint) {
		Class<?> beanClass = injectionPoint.getMember().getDeclaringClass();
		return LoggerFactory.getLogger(beanClass);
	}

}
