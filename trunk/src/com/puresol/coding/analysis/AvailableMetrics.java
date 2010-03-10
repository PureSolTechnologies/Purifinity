package com.puresol.coding.analysis;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.metrics.CodeDepth;
import com.puresol.coding.analysis.metrics.EntropyMetric;
import com.puresol.coding.analysis.metrics.HalsteadMetric;
import com.puresol.coding.analysis.metrics.MaintainabilityIndex;
import com.puresol.coding.analysis.metrics.McCabeMetric;
import com.puresol.coding.analysis.metrics.Metric;
import com.puresol.coding.analysis.metrics.SLOCMetric;
import com.puresol.data.Identifiable;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public enum AvailableMetrics implements Identifiable {

	SLOC {
		@Override
		public String getIdentifier() {
			return translator.i18n("SLOC metric");
		}

		@Override
		public Class<? extends Metric> getMetricClass() {
			return SLOCMetric.class;
		}
	},
	CODE_DEPTH {
		@Override
		public String getIdentifier() {
			return translator.i18n("Code depth metric");
		}

		@Override
		public Class<? extends Metric> getMetricClass() {
			return CodeDepth.class;
		}
	},
	MC_CABE {
		@Override
		public String getIdentifier() {
			return translator.i18n("McCabe metric");
		}

		@Override
		public Class<? extends Metric> getMetricClass() {
			return McCabeMetric.class;
		}
	},
	HALSTEAD {
		@Override
		public String getIdentifier() {
			return translator.i18n("Halstead metric");
		}

		@Override
		public Class<? extends Metric> getMetricClass() {
			return HalsteadMetric.class;
		}
	},
	MAINTAINABILITY {
		@Override
		public String getIdentifier() {
			return translator.i18n("Maintainability index");
		}

		@Override
		public Class<? extends Metric> getMetricClass() {
			return MaintainabilityIndex.class;
		}
	},
	ENTROPY {
		@Override
		public String getIdentifier() {
			return translator.i18n("Entropy metric");
		}

		@Override
		public Class<? extends Metric> getMetricClass() {
			return EntropyMetric.class;
		}
	};

	private static final Translator translator = Translator
			.getTranslator(AvailableMetrics.class);

	@Override
	public abstract String getIdentifier();

	public abstract Class<? extends Metric> getMetricClass();

	public Metric newInstance(CodeRange codeRange) {
		try {
			return Instances.createInstance(getMetricClass(), codeRange);
		} catch (ClassInstantiationException e) {
			throw new StrangeSituationException(e);
		}
	}
}
