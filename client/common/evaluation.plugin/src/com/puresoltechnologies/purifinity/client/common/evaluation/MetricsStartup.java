package com.puresoltechnologies.purifinity.client.common.evaluation;

import com.puresoltechnologies.purifinity.client.common.osgi.AbstractStartup;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.Activator;

public class MetricsStartup extends AbstractStartup {

	public MetricsStartup() {
		super(Activator.class);
	}
}