package com.puresoltechnologies.purifinity.server.common.plugins;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

@Interceptor
@EJBFacade
public class EJBFacadeInterceptor {

	@Inject
	private Logger logger;

	@AroundInvoke
	public Object invoke(InvocationContext ctx) throws Throwable {
		logger.info("Going to invoke EJB: {}#{}", ctx.getTarget().getClass()
				.getName(), ctx.getMethod().getName());
		Object result = ctx.proceed();
		logger.info("Invoked EJB: {}#{}", ctx.getTarget().getClass().getName(),
				ctx.getMethod().getName());
		return result;
	}

}
