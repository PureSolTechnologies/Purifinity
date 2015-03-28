package com.puresoltechnologies.purifinity.server.core.impl;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

import com.puresoltechnologies.purifinity.server.core.api.PurifinityServer;
import com.puresoltechnologies.purifinity.server.domain.PurifinityServerStatus;

/**
 * This is the central implementation of the Purifinity server.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PurifinityServerBean implements PurifinityServer {

	private final OperatingSystemMXBean operatingSystemMXBean = ManagementFactory
			.getOperatingSystemMXBean();
	private final RuntimeMXBean runtimeMXBean = ManagementFactory
			.getRuntimeMXBean();

	@Override
	public PurifinityServerStatus getStatus() {
		long startTime = runtimeMXBean.getStartTime();
		long uptime = runtimeMXBean.getUptime();
		int availableProcessors = operatingSystemMXBean
				.getAvailableProcessors();
		double systemLoadAverage = operatingSystemMXBean.getSystemLoadAverage();

		long freeMemory = Runtime.getRuntime().freeMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long maxMemory = Runtime.getRuntime().maxMemory();

		return new PurifinityServerStatus(startTime, uptime, totalMemory
				- freeMemory, totalMemory, maxMemory, systemLoadAverage,
				availableProcessors);
	}
}
