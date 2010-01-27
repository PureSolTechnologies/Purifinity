package com.puresol.coding.analysis;

import javax.i18n4j.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.lang.Language;
import com.puresol.coding.lang.LanguageNotSupportedException;
import com.puresol.coding.lang.cpp.metrics.CodeRangeMetrics4CPP;
import com.puresol.coding.lang.fortran.metrics.CodeRangeMetrics4Fortran;
import com.puresol.coding.lang.java.metrics.CodeRangeMetrics4Java;

public class MetricsFactory {

    private static final Translator translator =
	    Translator.getTranslator(MetricsFactory.class);

    public static CodeRangeMetrics create(CodeRange codeRange)
	    throws LanguageNotSupportedException {
	if (codeRange.getLanguage() == Language.JAVA) {
	    return new CodeRangeMetrics4Java(codeRange);
	}
	if (codeRange.getLanguage() == Language.FORTRAN) {
	    return new CodeRangeMetrics4Fortran(codeRange);
	}
	if (codeRange.getLanguage() == Language.CPP) {
	    return new CodeRangeMetrics4CPP(codeRange);
	}
	throw new LanguageNotSupportedException(translator.i18n(
		"Language {0} is not (yet) supported in MetricsFactory!",
		codeRange.getLanguage()));
    }

}
