package com.puresol.data;

import javax.i18n4j.Translator;

public class IllegalPersonNameException extends Exception {

    private static final long serialVersionUID = 5448626226620182780L;

    private static final Translator translator =
	    Translator.getTranslator(IllegalPersonNameException.class);

    public IllegalPersonNameException(PersonName name) {
	super(translator.i18n("Name '{0}' is invalid!", name));
    }

}
