package com.puresol.html;

import javax.i18n4j.Translator;

import com.puresol.data.Identifiable;

public enum LinkTarget implements Identifiable {
	DEFAULT(""), BLANK("_blank"), SELF("_self"), TOP("_top");

	private static final Translator translator = Translator
			.getTranslator(LinkTarget.class);

	private String keyword;

	private LinkTarget(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	@Override
	public String getIdentifier() {
		if (this == DEFAULT) {
			return translator.i18n("default");
		}
		if (this == BLANK) {
			return translator.i18n("new");
		}
		if (this == SELF) {
			return translator.i18n("self");
		}
		if (this == TOP) {
			return translator.i18n("top");
		}
		return null;
	}
}
