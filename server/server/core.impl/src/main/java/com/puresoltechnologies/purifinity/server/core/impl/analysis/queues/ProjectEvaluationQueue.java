package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProjectEvaluationQueue {

	public static final String TYPE = "javax.jms.Queue";
	public static final String NAME = "java:jboss/jms/queue/projectEvaluationQueue";

}
