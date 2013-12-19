package com.puresoltechnologies.purifinity.server.eventlogger.impl;

import java.util.Set;

import javax.enterprise.inject.Produces;

import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Service;
import com.puresoltechnologies.commons.utils.Workaround;

@Workaround(message = "This is a workaround due to a bug. See: https://code.google.com/p/guava-libraries/issues/detail?id=1433#c20 and http://code.google.com/p/guava-libraries/issues/detail?id=1527.")
public class ServiceProducer {

	@Produces
	Set<Service> dummyServices() {
		return ImmutableSet.of();
	}

}
