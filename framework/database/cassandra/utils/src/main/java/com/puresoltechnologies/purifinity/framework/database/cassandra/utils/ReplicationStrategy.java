package com.puresoltechnologies.purifinity.framework.database.cassandra.utils;

public enum ReplicationStrategy {

	SIMPLE_STRATEGY("SimpleStrategy"), NETWORK_TOPOLOGY_STRATEGY(
			"NetworkTopologyStrategy");

	private final String name;

	private ReplicationStrategy(String name) {
		this.name = name;
	}

	public Object getStrategyName() {
		return name;
	}

}
