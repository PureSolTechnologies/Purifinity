package com.puresol.purifinity.client.common.evaluation;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.puresol.purifinity.client.common.chart.renderer.ColorProvider;
import com.puresol.purifinity.client.common.evaluation.metrics.ChartConfigProvider;
import com.puresol.purifinity.client.common.evaluation.metrics.QualityLevelColorProvider;
import com.puresol.purifinity.client.common.evaluation.metrics.SourceCodeQualityColorProvider;
import com.puresol.purifinity.client.common.evaluation.metrics.maintainability.MIColorProvider;
import com.puresol.purifinity.client.common.evaluation.metrics.maintainability.MIParetoChartConfigProvider;
import com.puresol.purifinity.coding.evaluation.api.QualityLevelParameter;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;

public class Activator extends AbstractUIPlugin {
	// The shared instance
	private static Activator plugin = null;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		if (plugin != null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was already started!");
		}
		plugin = this;
		registerColorProvider(new SourceCodeQualityColorProvider(),
				SourceCodeQualityParameter.NAME);
		registerColorProvider(new QualityLevelColorProvider(),
				QualityLevelParameter.NAME);
		registerColorProvider(new MIColorProvider(),
				MaintainabilityIndexEvaluatorParameter.MI.getName());
		registerParetoChartConfigProvider(new MIParetoChartConfigProvider(),
				MaintainabilityIndexEvaluatorParameter.MI.getName());
	}

	public void registerColorProvider(ColorProvider provider,
			String parameterName) {
		Dictionary<String, Object> dictionary = new Hashtable<String, Object>();
		dictionary.put("parameterName", parameterName);
		getBundle().getBundleContext().registerService(ColorProvider.class,
				provider, dictionary);
	}

	public void registerParetoChartConfigProvider(ChartConfigProvider provider,
			String parameterName) {
		Dictionary<String, Object> dictionary = new Hashtable<String, Object>();
		dictionary.put("parameterName", parameterName);
		getBundle().getBundleContext().registerService(
				ChartConfigProvider.class, provider, dictionary);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		if (plugin == null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was never started!");
		}
		plugin = null;
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		if (plugin == null) {
			throw new RuntimeException("A " + Activator.class.getName()
					+ " plugin was never started!");
		}
		return plugin;
	}

}
