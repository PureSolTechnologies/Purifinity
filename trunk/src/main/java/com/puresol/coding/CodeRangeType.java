package com.puresol.coding;

import javax.i18n4java.Translator;

public enum CodeRangeType {

	FILE {
		@Override
		public String getName() {
			return translator.i18n("file");
		}
	},
	CLASS {
		@Override
		public String getName() {
			return translator.i18n("class");
		}
	},
	INTERFACE {
		@Override
		public String getName() {
			return translator.i18n("interface");
		}
	},
	ENUMERATION {
		@Override
		public String getName() {
			return translator.i18n("enumeration");
		}
	},
	ANNOTATION {
		@Override
		public String getName() {
			return translator.i18n("annotation");
		}
	},
	MODULE {
		@Override
		public String getName() {
			return translator.i18n("module");
		}
	},
	CONSTRUCTOR {
		@Override
		public String getName() {
			return translator.i18n("constructor");
		}
	},
	METHOD {
		@Override
		public String getName() {
			return translator.i18n("method");
		}
	},
	PROGRAM {
		@Override
		public String getName() {
			return translator.i18n("program");
		}
	},
	FUNCTION {
		@Override
		public String getName() {
			return translator.i18n("function");
		}
	},
	SUBROUTINE {
		@Override
		public String getName() {
			return translator.i18n("subroutine");
		}
	};

	private static final Translator translator = Translator
			.getTranslator(CodeRangeType.class);

	public abstract String getName();

}
