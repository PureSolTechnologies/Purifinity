package com.puresol.coding.tokentypes;

import javax.i18n4java.Translator;

import com.puresol.data.Identifiable;

public enum SymbolType implements Identifiable {

	OPERANT {
		@Override
		public String getIdentifier() {
			return translator.i18n("Operant");
		}
	},
	OPERATOR {
		@Override
		public String getIdentifier() {
			return translator.i18n("Operator");
		}
	},
	COMMENT {
		@Override
		public String getIdentifier() {
			return translator.i18n("Comment");
		}
	},
	HIDDEN {
		@Override
		public String getIdentifier() {
			return translator.i18n("Hidden");
		}
	};

	private static final Translator translator = Translator
			.getTranslator(SymbolType.class);

	@Override
	public abstract String getIdentifier();

}
