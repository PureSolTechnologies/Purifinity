package com.puresol.html;

import javax.i18n4j.Translator;

import com.puresol.data.Identifiable;

public enum LinkTarget implements Identifiable {
    DEFAULT {
	@Override
	public String getIdentifier() {
	    return translator.i18n("default");
	}

	@Override
	public String getKeyword() {
	    return "";
	}
    },
    BLANK {
	@Override
	public String getIdentifier() {
	    return translator.i18n("blank");
	}

	@Override
	public String getKeyword() {
	    return "_blank";
	}
    },
    SELF {
	@Override
	public String getIdentifier() {
	    return translator.i18n("self");
	}

	@Override
	public String getKeyword() {
	    return "_self";
	}
    },
    TOP {
	@Override
	public String getIdentifier() {
	    return translator.i18n("top");
	}

	@Override
	public String getKeyword() {
	    return "_top";
	}
    },
    PARENT {
	@Override
	public String getIdentifier() {
	    return translator.i18n("parent");
	}

	@Override
	public String getKeyword() {
	    return "_parent";
	}
    };

    private static final Translator translator =
	    Translator.getTranslator(LinkTarget.class);

    public abstract String getKeyword();

    @Override
    public abstract String getIdentifier();
}
