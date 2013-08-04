package com.puresol.purifinity.client.common.evaluation;

import java.util.Collection;

import org.osgi.framework.Bundle;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.purifinity.client.common.osgi.AbstractStartup;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.metrics.Activator;

public class MetricsStartup extends AbstractStartup {

	public MetricsStartup() {
		super(Activator.class);
		Bundle bundle = com.puresol.purifinity.client.common.evaluation.Activator
				.getDefault().getBundle();

		Collection<ServiceReference<EvaluatorFactory>> serviceReferences;
		try {
			serviceReferences = bundle.getBundleContext().getServiceReferences(
					EvaluatorFactory.class, null);
			if (serviceReferences.size() <= 0) {
				throw new RuntimeException("No services were registered!");
			}
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException("Unexpected error!", e);
		}
	}
}
