package com.puresol.coding.analysis.evaluator;

import javax.i18n4j.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.data.Identifiable;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public enum AvailableEvaluators implements Identifiable {

	NONE {
		@Override
		public Class<? extends CodeEvaluator> getEvaluatorClass() {
			return TranslatorImplementation.class;
		}

		@Override
		public String getIdentifier() {
			return translator.i18n("I18N4Java Translator Implementation");
		}
	};

	private static final Translator translator = Translator
			.getTranslator(AvailableEvaluators.class);

	@Override
	public abstract String getIdentifier();

	public abstract Class<? extends CodeEvaluator> getEvaluatorClass();

	public CodeEvaluator newInstance(CodeRange codeRange) {
		try {
			return Instances.createInstance(getEvaluatorClass(), codeRange);
		} catch (ClassInstantiationException e) {
			throw new StrangeSituationException(e);
		}
	}
}
