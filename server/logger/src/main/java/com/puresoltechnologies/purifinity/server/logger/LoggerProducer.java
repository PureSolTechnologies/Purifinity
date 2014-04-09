package com.puresoltechnologies.purifinity.server.logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * This producer is used to produce an instance of {@link Logger}. It is mainly
 * used to have a central point of creation and an easy and clean code.
 * </p>
 * <p>
 * The logger is inject later on with
 * </p>
 * 
 * <pre>
 * {@literal @}Inject<br/>
 * private Logger logger;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LoggerProducer {

	/**
	 * This is the actual producer method to create the {@link Logger} instance.
	 * 
	 * @param injectionPoint
	 *            is the {@link InjectionPoint} where to inject the
	 *            {@link Logger}.
	 * @return A {@link Logger} object is returned which can be used for
	 *         logging.
	 */
	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint) {
		Class<?> beanClass = injectionPoint.getMember().getDeclaringClass();
		return LoggerFactory.getLogger(beanClass);
	}

}
